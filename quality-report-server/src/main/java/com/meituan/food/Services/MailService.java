package com.meituan.food.Services;

import com.sankuai.it.mail.sdk.service.MailThriftService;
import com.sankuai.it.mail.sdk.structs.MailStructDTO;
import com.sankuai.it.mail.sdk.structs.SendMailResultDTO;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Component
public class MailService {

    @Resource
    private MailThriftService mailThriftService;

    public void sendMailTo() throws TException {
        MailStructDTO mailModel = new MailStructDTO();
        mailModel.setUseHtml(true);
        mailModel.setFromName("发件人名称");
        mailModel.setBody("<html><head></head><body>我是测试邮件</body></html>");
        mailModel.setTo(Arrays.asList("guomengyao@meituan.com")); //收件人
        mailModel.setCc(Arrays.asList("shiyongxiang@meituan.com"));  //抄送
        mailModel.setBcc(Arrays.asList("shiyongxiang@meituan.com"));  //密送
        mailModel.setSubject("邮件主题");
     //   mailModel.setAttachments(getAttachments()); //带附件发送

        SendMailResultDTO resultModel = mailThriftService.sendMail(mailModel);
        System.out.println(resultModel);
    }
}
