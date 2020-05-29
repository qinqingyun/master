package com.meituan.qa.meishi.Hui.util;

import com.meituan.toolchain.mario.login.LoginUtil;
import com.meituan.toolchain.mario.login.model.DPCUser;
import com.meituan.toolchain.mario.login.model.LoginType;
import com.meituan.toolchain.mario.login.model.MTCUser;
import com.meituan.toolchain.mario.login.model.SSOBUser;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
public class CommonLoginUtil {

//private static String _HOST ="https://epassport.meituan.com";
    private static String _APIPATH = "/bizapi/loginv3";
    private static String _HOST1 = "http://172.19.102.179";
    private static String _APIPATH1 = "/getSSOID";

    private static String _APIPATHDP = "mapi/mlogin/login.api";
    private static String _APIPATHMT = "api/v7/account/login";
    //  private String _CASEFOLDER = "Hui/login";

    public static String ms_c_MTLogin_01()  {

        return ms_c_MTLogin_01("user1");
    }

    public static String ms_c_DPLogin_02(){

        return ms_c_DPLogin_02("user1");

    }

    public static String ms_c_MTLogin_01(String username) {
        MTCUser mtcUser = (MTCUser) LoginUtil.login(LoginType.MT_C_LOGIN, username);

        String mtToken =  mtcUser.getToken();
        log.info("token:"+mtToken);
        return mtToken;
    }
//使用的未解密前的newToken字段
    public static String ms_c_DPLogin_02(String username){
        String dpToken = null;
        DPCUser user = (DPCUser) LoginUtil.login(LoginType.DP_C_LOGIN, username);

//        dpToken = response.getValueByJsonPath("0xcd0a");
        dpToken = user.getToken();
        log.info("token:"+dpToken);
        return dpToken;

    }

    public static String merchantAPPLogin() {
        SSOBUser user = (SSOBUser) LoginUtil.login(LoginType.SSO_B_LOGIN, "merchant");
        String bsid = user.getBsid();
        return bsid;
//        JSONObject request = new JSONObject();
//        try {
//            request = DBDataProvider.getRequest(_APIPATH, "ms_c_merchantAPPLogin_001");
//        }catch(Exception e){
//            log.error(e.getMessage());
//        }
//        String value = String.valueOf(System.currentTimeMillis() / 1000);
//        JsonPathUtil.setJsonPathVaule(request, "$.params.reqtime", value);
//
//        Map<String, Object> urlParams = (Map<String, Object>) request;
//        String siginValue = getSign(urlParams);
//        JsonPathUtil.setJsonPathVaule(request, "$.params.sign", siginValue);
//        ResponseMap response = DBCaseRequestUtil.post("env.api.meishi.merchant.login.host",request);
//        return response.getValueByJsonPath("$.data.access_token");
    }

//    public static String misLogin(){
////        RequestsFromDB request = new RequestsFromDB(Constants.Protocol.http, _HOST1, "8080", _APIPATH1, "ms_c_misLogin_001");
//        JSONObject request = new JSONObject();
//        try {
//            request = DBDataProvider.getRequest(_APIPATH1, "ms_c_misLogin_001");
//        }catch(Exception e){
//            log.error(e.getMessage());
//        }
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("username","liukang12");
//        params.put("password","");
//        request.setRequestBody(params);
//        JsonPathUtil.setJsonPathVaule(request, "$.body", body_value);
//        ResponseMap response = DBCaseRequestUtil.post("",request);
//
//        return  response.getContentFromJSONPath_String("$");
//    }

    private static String getSign(Map<String, Object> urlParams) {
        ArrayList<Map.Entry<String, Object>> list =
                new ArrayList<Map.Entry<String, Object>>(urlParams.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {

            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String sign_str = "cb959f5cc970fe31a2c78eb441864ccf9a2dbf6a";
        for (Map.Entry<String, Object> kv : list) {
            sign_str += kv.getKey() + kv.getValue();
        }

        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(sign_str.getBytes());
            byte[] output = md.digest();
            sign_str = byte2hex(output);
        } catch (NoSuchAlgorithmException ignored) {
        }
        return sign_str;
    }

    public static String byte2hex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString().toLowerCase(Locale.getDefault());
    }


}
