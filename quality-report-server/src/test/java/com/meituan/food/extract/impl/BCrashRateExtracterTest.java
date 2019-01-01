package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.extract.IOneMonthDataExtract;
import com.sankuai.it.mail.sdk.service.MailThriftService;
import com.sankuai.it.mail.sdk.structs.MailStructDTO;
import com.sankuai.it.mail.sdk.structs.SendMailResultDTO;
import groovy.util.logging.Slf4j;
import org.apache.thrift.TException;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Arrays;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class BCrashRateExtracterTest {

   /* @Resource(name = "bCrashRateExtracter")
    private IOneMonthDataExtract iOneMonthDataExtract;
*/
    @Resource
    private MailThriftService mailThriftService;

    @Test
    public void extractData4Month() throws TException {
        MailStructDTO mailModel = new MailStructDTO();
        mailModel.setUseHtml(true);
        mailModel.setFromName("发件人名称");
        mailModel.setBody("<html><head></head><body>我是测试邮件</body></html>");
        mailModel.setTo(Arrays.asList("guomengyao@meituan.com")); //收件人
        mailModel.setCc(Arrays.asList("shiyongxiang@meituan.com"));  //抄送
//        mailModel.setBcc(Arrays.asList("zhuoyue02@meituan.com"));  //密送
        mailModel.setSubject("邮件主题");
        mailModel.setAttachments(Lists.newArrayList()); //带附件发送

        SendMailResultDTO resultModel = mailThriftService.sendMail(mailModel);
        System.out.println(resultModel);
//        iOneMonthDataExtract.extractData4Month("2018-11-01","2018-11-30");
    }
}