package com.meituan.food.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;
import java.util.stream.Collectors;


public final class HttpUtils {

    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    private static final int DEFAULT_SOCKET_TIMEOUT = 5 * 1000;

    private static final int DEFAULT_CONNECTION_TIMEOUT = 2 * 1000;

    private static final String DEFAULT_CHARSET = "UTF-8";

    public static final String JSON_CONTENT_TYPE = "application/json;charset=" + DEFAULT_CHARSET;

    public static Map<String, String> getSignedHeaders(String method, String uri, String key, String token) {
        if (method == null || uri == null) {
            return Collections.emptyMap();
        }
        Map<String, String> signedHeaders = Maps.newHashMap();
        String date = BaUtils.getAuthDate(new Date());
        method = method.toUpperCase();
        String authorization = BaUtils.getAuthorization(uri, method, date, key, token);
        signedHeaders.put("Authorization", authorization);
        signedHeaders.put("Date", date);
        return signedHeaders;
    }

    public static <T> T execute(HttpUriRequest request, Class<T> returnType) {
        try {
            HttpResponse httpResponse = HTTP_CLIENT.execute(request);
            HttpEntity httpEntity = httpResponse.getEntity();
            String content = httpEntity != null ? EntityUtils.toString(httpEntity, DEFAULT_CHARSET) : null;
            if (httpResponse.getStatusLine().getStatusCode() >= 200 && httpResponse.getStatusLine().getStatusCode() < 300) {
                if (returnType == String.class) {
                    return (T) content;
                }
                return JSON.parseObject(content, returnType);
            } else {
                throw new HttpException(httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().getReasonPhrase(), content);
            }
        } catch (IOException e) {
            throw new HttpException(505, "IOException", e);
        }
    }

    public static <T> T doPostWithAuth(URI uri, String content, Class<T> returnType, String key, String token) {
        Map<String, String> headers = getSignedHeaders("POST", uri.getPath(), key, token);
        headers.put("Content-Type", JSON_CONTENT_TYPE);
        return doPost(uri.toString(), content, returnType, headers);
    }

    public static <T> T doPost(String url, String content, Class<T> returnType, Map<String, String> headers) {
        return doPost(url, content, returnType, -1, -1, headers);
    }

    public static <T> T doPost(String url, String content, Class<T> returnType, int sockedTimeoutMs, int connectTimeOutMs, Map<String, String> headers) {
        try {
            HttpPost post = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(sockedTimeoutMs > 0 ? sockedTimeoutMs : DEFAULT_SOCKET_TIMEOUT)
                    .setConnectTimeout(connectTimeOutMs > 0 ? connectTimeOutMs : DEFAULT_CONNECTION_TIMEOUT)
                    .build();

            post.setConfig(requestConfig);
            StringEntity body = new StringEntity(content, DEFAULT_CHARSET);
            post.setEntity(body);
            headers.forEach(post::addHeader);
            T result = execute(post, returnType);
            return result;
        } catch (UnsupportedCharsetException e) {
            throw new HttpException(506, "UnsupportedEncodingException: " + DEFAULT_CHARSET, e);
        }
    }

    public static <T> T doGetWithAuth(URI uri, Class<T> returnType, String key, String token) {
        Map<String, String> headers = getSignedHeaders("GET", uri.getPath(), key, token);
        headers.put("Content-Type", JSON_CONTENT_TYPE);
        return doGet(uri.toString(), returnType, headers);
    }

    public static <T> T doGet(String url, Class<T> returnType, Map<String, String> headers) {
        return doGet(url, returnType, -1, -1, headers);
    }

    public static <T> T doGet(String url, Class<T> returnType, int sockedTimeoutMs, int connectTimeOutMs, Map<String, String> headers) {
        HttpGet get = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(sockedTimeoutMs > 0 ? sockedTimeoutMs : DEFAULT_SOCKET_TIMEOUT)
                .setConnectTimeout(connectTimeOutMs > 0 ? connectTimeOutMs : DEFAULT_CONNECTION_TIMEOUT)
                .build();

        get.setConfig(requestConfig);
        headers.forEach(get::addHeader);
        T result = execute(get, returnType);
        return result;
    }

    /**
     * convert content-type=application/x-www-form-urlencoded to application/json
     * form extract to json
     * 例如 id=123&order=1&params[][paramName]=req1&params[][paramName]=req2&params[][type]=string&params[][type]=int
     * 转换结果
     * {
     *  "id": "123",
     *  "params": [{
     *   "paramName": "req1",
     *   "type": "string"
     *        }, {
     *   "paramName": "req2",
     *   "type": "int"
     *    }],
     *  "order": "1"
     * }
     * @param formData form 数据
     * @return json
     */
    public static String formData2Json(MultiValueMap<String, String> formData) {
        Multimap<String, String> formDataMultiMap = ArrayListMultimap.create();
        formData.forEach(formDataMultiMap::putAll);
        List<Pair<String, String>> formDataPairs = Lists.newArrayListWithCapacity(formDataMultiMap.size());
        //打散key
        Collection<Map.Entry<String, String>> entries = formDataMultiMap.entries();
        String preSet = StringUtils.EMPTY;
        do {
            Iterator<Map.Entry<String, String>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (StringUtils.isEmpty(preSet) || !preSet.equals(entry.getKey())) {
                    preSet = entry.getKey();
                    formDataPairs.add(MutablePair.of(entry.getKey(), entry.getValue()));
                    iterator.remove();
                }
            }
        } while (entries.size() != 0);

        // 构造json
        JSONObject root = new JSONObject();
        formDataPairs.forEach(t -> {
            String k = t.getLeft();
            String v = t.getRight();
            List<String> keys = Arrays.stream(StringUtils.split(k, "["))
                    .map(e -> {
                        if (StringUtils.contains(e, "]")) {
                            return StringUtils.substringBefore(e, "]");
                        }
                        return e;
                    })
                    .collect(Collectors.toList());
            deepSet(root, keys, v);
        });
        return JSONObject.toJSONString(root);
    }

    private static void deepSet(Object obj, List<String> keys, String value) {
        String key = keys.get(0);
        if (keys.size() == 1) {
            if (StringUtils.isEmpty(key)) {
                ((JSONArray) obj).add(value);
            } else {
                ((JSONObject) obj).put(key, value);
            }
        } else {
            String nextKey = keys.get(1);
            if (StringUtils.isEmpty(key)) {
                JSONArray arrObj = (JSONArray) obj;
                int lastIdx = ((JSONArray) obj).size() - 1;
                Object lastVal = null;
                if (lastIdx >= 0) {
                    lastVal = arrObj.get(lastIdx);
                }
                if (lastVal instanceof JSONObject && (((JSONObject) lastVal).get(nextKey) == null || keys.size() > 2)) {
                    key = String.valueOf(lastIdx);
                } else {
                    key = String.valueOf(lastIdx + 1);
                }
            }

            if (StringUtils.isEmpty(nextKey)) {
                Object jsonObj = ((JSONObject) obj).get(key);
                if (!(jsonObj instanceof JSONArray)) {
                    ((JSONObject) obj).put(key, new JSONArray());
                }
            } else {
                Object jsonObj = null;
                if (obj instanceof JSONObject) {
                    JSONObject currentObj = (JSONObject) obj;
                    jsonObj = currentObj.get(key);
                    if (!(jsonObj instanceof JSONObject)) {
                        currentObj.put(key, new JSONObject());
                    }
                } else {
                    JSONArray arr = (JSONArray) obj;
                    Integer idx = Integer.valueOf(key);
                    if (arr.size() > idx) {
                        jsonObj = arr.get(idx);
                    }
                    if (!(jsonObj instanceof JSONObject)) {
                        arr.set(idx, new JSONObject());
                    }
                }

            }
            if (obj instanceof JSONObject) {
                deepSet(((JSONObject) obj).get(key), keys.subList(1, keys.size()), value);
            } else {
                deepSet(((JSONArray) obj).get(Integer.valueOf(key)), keys.subList(1, keys.size()), value);
            }
        }
    }

    private HttpUtils() {
    }

    public static class HttpException extends RuntimeException {
        private int status;
        private String reason;
        private String content;

        public int getStatus() {
            return this.status;
        }

        public String getReason() {
            return this.reason;
        }

        public String getContent() {
            return this.content;
        }

        public HttpException(int status, String reason) {
            super("status=" + status + ",reason=" + reason);
            this.status = status;
            this.reason = reason;
        }

        public HttpException(int status, String reason, Throwable th) {
            super("status=" + status + ",reason=" + reason, th);
            this.status = status;
            this.reason = reason;
        }

        public HttpException(int status, String reason, String content) {
            super("status=" + status + ",reason=" + reason + ",content=" + content);
            this.status = status;
            this.reason = reason;
            this.content = content;
        }
    }
}