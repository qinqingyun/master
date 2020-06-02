package com.meituan.food.config;

import com.sankuai.meituan.org.opensdk.client.RemoteServiceClient;
import com.sankuai.meituan.org.opensdk.service.*;
import com.sankuai.meituan.org.opensdk.service.impl.*;
import com.sankuai.meituan.org.queryservice.domain.param.DataScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenSdkConfig {

    private final static String REMOTE_APP_KEY = "com.sankuai.it.bsi.mdmgatewayservice";
    //TODO 替换成你的appkey
    //线上环境
/*    private final static String CLIENT_APP_KEY = "0d6cf99067";
//    //TODO 替换成你的appSecret
    private final static String APP_SECRET = "85762a22382f45e4a3416d9fa48ea4f6";

   //线下
//    private final static String CLIENT_APP_KEY ="cc6049a1ee";
//    private final static String APP_SECRET = "247fcce757a44d6d9710f88719818988";*/


    /*
    qdata的appkey
    */

    //线下环境
    private final static String CLIENT_APP_KEY = "45b4871070";
    private final static String APP_SECRET = "5cdbf1489d164f11bbaf2166cdd7cdf2";

    //线上环境
/*  private final static String CLIENT_APP_KEY ="215f5b75ef";
    private final static String APP_SECRET = "b8efa677094741de81cdd777c89e99c6";*/
    private final static Integer APP_TENANT_ID = 1;//如需查询其它租户数据，请设置成其它租户ID
    private final static String APP_SOURCE = "MT";//设置租户下对应的source.如果设置为"ALL"，则查tenantId下所有sources。


    @Bean
    public RemoteServiceClient remoteServiceClient() throws Exception {

        // 设置App默认的数据访问范围。如下设置，App默认所有的请求是针对美团租户下“MT”数据域的ORG数据
        DataScope dataScope = new DataScope();
        dataScope.setTenantId(APP_TENANT_ID);
        dataScope.setSources(Arrays.asList(APP_SOURCE));
        //线上环境
        RemoteServiceClient remoteServiceClient = new RemoteServiceClient(CLIENT_APP_KEY, APP_SECRET, REMOTE_APP_KEY, dataScope);
        //BETA 环境，IP直连方式--直连方式已经废弃
//        RemoteServiceClient remoteServiceClient = new RemoteServiceClient(CLIENT_APP_KEY, APP_SECRET, REMOTE_APP_KEY, dataScope, "10.24.58.212:9001,10.24.58.115:9001");      //serverIpPorts（最后一个参数） 配置null或者空字符串 则不ip直连
//        RemoteServiceClient remoteServiceClient = new RemoteServiceClient(CLIENT_APP_KEY, APP_SECRET, REMOTE_APP_KEY, dataScope);
        return remoteServiceClient;
    }

    @Bean
    public CompService compService(RemoteServiceClient remoteServiceClient) {
        CompServiceImpl compService = new CompServiceImpl(remoteServiceClient);
        return compService;
    }

    @Bean
    public DictService dictService(RemoteServiceClient remoteServiceClient) {
        DictServiceImpl dictService = new DictServiceImpl(remoteServiceClient);
        return dictService;
    }

    @Bean
    public EmpService empService(RemoteServiceClient remoteServiceClient) {
        EmpServiceImpl empService = new EmpServiceImpl(remoteServiceClient);
        return empService;
    }

    @Bean
    public JobCodeService jobCodeService(RemoteServiceClient remoteServiceClient) {
        JobCodeServiceImpl jobCodeService = new JobCodeServiceImpl(remoteServiceClient);
        return jobCodeService;
    }

    @Bean
    public OrgService orgService(RemoteServiceClient remoteServiceClient) {
        OrgServiceImpl orgService = new OrgServiceImpl(remoteServiceClient);
        return orgService;
    }

    @Bean
    public SiteCodeService siteCodeService(RemoteServiceClient remoteServiceClient) {
        SiteCodeServiceImpl siteCodeService = new SiteCodeServiceImpl(remoteServiceClient);
        return siteCodeService;
    }
}
