package com.meituan.food.Services;

import com.sankuai.it.mail.sdk.service.MailThriftService;
import com.sankuai.it.mail.sdk.structs.MailStructDTO;
import com.sankuai.it.mail.sdk.structs.SendMailResultDTO;
import com.sankuai.meituan.org.opensdk.model.domain.items.EmpItems;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Component
public class MailService {

    @Resource
    private MailThriftService mailThriftService;

    public void sendMailTo() throws TException {

        String testBody="<body><h4>人效数据表：</h4><table border=\"1\"><tr><th>mis</th><th>姓名</th><th>创建学城数量</th><th>更新学城数量</th><th>Git代码增加量</th><th>Git代码删除量</th><th>Git代码提交量</th><th>Git代码提交次数</th><th>创建bug数量</th><th>接收bug数量</th><th>日期</th></tr><tr><td>100</td><td>200</td><td>300</td><td>300</td><td>300</td><td>300</td><td>300</td><td>300</td><td>300</td><td>300</td><td>300</td></tr><tr><td>400</td><td>500</td><td>600</td><td>300</td><td>300</td><td>300</td><td>300</td><td>300</td><td>300</td><td>300</td><td>300</td></tr></table></body></html>";

        MailStructDTO mailModel = new MailStructDTO();
        mailModel.setUseHtml(true);
        mailModel.setFromName("发件人名称");
//        mailModel.setBody("<html><head></head><body>我是测试邮件</body></html>");
        mailModel.setBody(testBody);
        mailModel.setTo(Arrays.asList("guomengyao@meituan.com")); //收件人
        mailModel.setCc(Arrays.asList("shiyongxiang@meituan.com"));  //抄送
        mailModel.setBcc(Arrays.asList("shiyongxiang@meituan.com"));  //密送
        mailModel.setSubject("邮件主题");
     //   mailModel.setAttachments(getAttachments()); //带附件发送

        SendMailResultDTO resultModel = mailThriftService.sendMail(mailModel);
        System.out.println(resultModel);
    }
}
