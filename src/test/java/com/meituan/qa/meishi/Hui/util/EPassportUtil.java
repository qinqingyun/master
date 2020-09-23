package com.meituan.qa.meishi.Hui.util;
import com.meituan.service.mobile.mtthrift.generic.GenericService;
import com.meituan.service.mobile.mtthrift.proxy.ThriftClientProxy;
import com.meituan.service.mobile.mtthrift.util.json.JacksonUtils;
import com.sankuai.sjst.ecom.epassport.service.client.thrift.model.*;
import com.sankuai.sjst.ecom.epassport.service.client.thrift.model.bizaccount.BizAccountFields;
import com.sankuai.sjst.ecom.epassport.service.client.thrift.model.bizaccount.BizAccountTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class EPassportUtil {
    private static final Logger logger = LogManager.getLogger(EPassportUtil.class);
    private static final String LOCAL_APPKEY = "com.sankuai.sjst.qa.accountservice";
    private static GenericService bizAccountInfoThriftService = genericBizAccountInfoService();
    private static GenericService bizAccountAdminThriftService = genericBizAccountAdminService();
    /**
     * 根据accountId获取token
     *
     * @param accountId
     * @return token
     * @throws TException
     */
    public static String getEPassportToken(Integer accountId) throws TException {
        List<String> paramTypes = new ArrayList<String>();
        paramTypes.add("com.sankuai.sjst.ecom.epassport.service.client.thrift.model.CreateTokenReq");
         List<String> paramValues = new ArrayList<String>();
         CreateTokenReq createTokenReq = new CreateTokenReq();
         createTokenReq.setBizAcctID(accountId);

         String expected = JacksonUtils.serialize(createTokenReq);
         paramValues.add(expected);

         String createTokenRespString = bizAccountAdminThriftService.$invoke("createToken", paramTypes, paramValues);

         CreateTokenResp createTokenResp = JacksonUtils.deserialize(createTokenRespString, CreateTokenResp.class);
         if (createTokenResp.getToken() == null) {
             return createTokenResp.getStatus().getMessage();
         }
         return createTokenResp.getToken().getAccessToken();
    }

    /**
     * 根据login获取token
     *
     * @param login 登录名
     * @return token
     * @throws TException
     */
    public static String getEPassportTokenByLogin(String login) throws TException {
        return getEPassportToken(getAccountIdByLogin(login));
    }

    /**
     * 根据login获取accountId
     *
     * @param login
     * @return accountId
     * @throws TException
     */
    public static int getAccountIdByLogin(String login) throws TException {

        List<String> paramTypes = new ArrayList<String>();
        paramTypes.add("com.sankuai.sjst.ecom.epassport.service.client.thrift.model.GetBizAccountByLoginReq");
         List<String> paramValues = new ArrayList<String>();
         GetBizAccountByLoginReq getBizAccountByLoginReq = new GetBizAccountByLoginReq();
         getBizAccountByLoginReq.setLogin(login);

         BizAccountFields bizAccountFields = new BizAccountFields();
         bizAccountFields.setId(true);

         getBizAccountByLoginReq.setFields(bizAccountFields);

         String expected = JacksonUtils.serialize(getBizAccountByLoginReq);
         paramValues.add(expected);

         String respString = bizAccountInfoThriftService.$invoke("getBizAccountByLogin", paramTypes, paramValues);

         GetBizAccountResp resp = JacksonUtils.deserialize(respString, GetBizAccountResp.class);
         return resp.getBizAccount().getId();
    }

        /**
         * 根据token获取用户信息
         *
         * @param token
         * @return BizAccountTO 用户信息
         * @throws TException
         */
        public static BizAccountTO getBizAccountByToken(String token) throws TException {
            List<String> paramTypes = new ArrayList<String>();
            paramTypes.add("com.sankuai.sjst.ecom.epassport.service.client.thrift.model.GetBizAccountByTokenReq");

            List<String> paramValues = new ArrayList<String>();
            GetBizAccountByTokenReq req = new GetBizAccountByTokenReq();
            req.setToken(token);

            BizAccountFields bizAccountFields = new BizAccountFields();
            bizAccountFields.setLogin(true);
            bizAccountFields.setId(true);
            bizAccountFields.setDpID(true);
            bizAccountFields.setPhone(true);

            req.setFields(bizAccountFields);

            String expected = JacksonUtils.serialize(req);
            paramValues.add(expected);

            String respString = bizAccountInfoThriftService.$invoke("getBizAccountByToken", paramTypes, paramValues);

            GetBizAccountByTokenResp resp = JacksonUtils.deserialize(respString, GetBizAccountByTokenResp.class);
            return resp.getBizAccount();
        }

        private static GenericService genericBizAccountAdminService() {
            return genericService(
                    LOCAL_APPKEY,
                    "com.sankuai.sjst.ecom.epassportadminservice",
                    "com.sankuai.sjst.ecom.epassport.service.client.thrift.service.BizAccountAdminThriftService",
                    9101);
        }

        private static GenericService genericBizAccountInfoService() {
            return genericService(
                    LOCAL_APPKEY,
                    "com.sankuai.sjst.ecom.epassportservice",
                    "com.sankuai.sjst.ecom.epassport.service.client.thrift.service.BizAccountInfoThriftService",
                    9001);
        }

        public static GenericService genericService(String appKey, String remoteAppkey, String serviceName, int port) {
            ThriftClientProxy clientProxy = new ThriftClientProxy();
            clientProxy.setAppKey(appKey);
            clientProxy.setRemoteAppkey(remoteAppkey);
            clientProxy.setGenericServiceName(serviceName);
            clientProxy.setRemoteServerPort(port);
            clientProxy.setFilterByServiceName(true);
            clientProxy.setGeneric("json");

            try {
                clientProxy.afterPropertiesSet();
                GenericService genericService = (GenericService) clientProxy.getObject();
                return genericService;
            } catch (Exception e) {
                return null;
            }
        }

}
