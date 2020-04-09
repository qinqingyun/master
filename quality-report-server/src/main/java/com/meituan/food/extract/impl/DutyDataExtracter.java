package com.meituan.food.extract.impl;

import com.google.common.collect.ImmutableMap;
import com.meituan.food.extract.IOneDayDutyDataExtract;
import com.meituan.food.mapper.DutyTablePOMapper;
import com.meituan.food.po.DutyTablePO;
import com.meituan.food.utils.DaXiangUtils;
import com.meituan.food.utils.HttpUtils;
import com.sankuai.meituan.attendance.api.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class DutyDataExtracter implements IOneDayDutyDataExtract {

    public static final String content = "周末值班QA须知\n" +
            "1、客服商服反馈群，有问题反馈，会根据配置自动拉群处理，值班同学需关注群内的进度。\n" +
            "2、若提报问题自动匹配错误，需值班同学手动拉相关人进群。若提报问题未自动匹配，则需要值班同学手动拉群处理，并将问题编号记录，同步给buyuqi同学\n" +
            "3、值班同学需关注自己值班当天，群内问题的解决情况，若问题已解决，督促提报人关闭问题。\n" +
            "4、值班同学可参考各平台业务对接人引导wiki：https://km.sankuai.com/page/181829982，拉取相关同学进群，配合解决问题。";

    public static final String contentForB="@所有人 PM&RD同学们，请在需求池wiki中更新下周需评审的需求；截止到晚7点，我们会按照需求池统一安排资源参加评审；除紧急需求外临时需求会议qa不能准时参加，需求评审请提前发行事历；感谢合作。\n"+
            "https://km.sankuai.com/page/305878317";

    @Resource
    private DutyTablePOMapper dutyTablePOMapper;

    @Resource
    private HolidayService holidayService;

    @Override
    public void extractData4Day(LocalDate day) {

        Date firstDate = localDate2Date(day);
        Date secondDate = localDate2Date(day.minusDays(1));
        Integer firstDateType = holidayService.getDayHoliday(firstDate).getType();
        Integer secongDateType = holidayService.getDayHoliday(secondDate).getType();

        int count = 0;

        //1-工作日，2-休息日（包括法定休息日和周末休息日），3-法定节假日
        if (firstDateType != 1 && secongDateType == 1) {
            count++;
            for (int i = 1; i <= 100; i++) {
                Integer nextDateType = holidayService.getDayHoliday(localDate2Date(day.plusDays(i))).getType();
                if (nextDateType != 1) {
                    count++;
                } else {
                    break;
                }
            }
            log.info("值班人员个数为：" + count + "/n");
            List<DutyTablePO> dutyTablePOS = dutyTablePOMapper.selectDutyPerson(count);
            String nameList = "";
            int i = 0;
            for (DutyTablePO dutyTablePO : dutyTablePOS) {
                dutyTablePOMapper.updateByMis(day.plusDays(i).format(DateTimeFormatter.ofPattern("yyyyMMdd")), dutyTablePO.getDutyMis());
                nameList = nameList + "@" + dutyTablePO.getDutyName() + " ";
                i++;
            }

            DaXiangUtils.pushToPerson(nameList + "轮到你们值班了\n" + content, "guomengyao");
            DaXiangUtils.pushToRoom(nameList + "轮到你们值班了\n" + content, 879074L);
            DaXiangUtils.pushToRoom("本周值班人员：" + nameList, 64013968876L);
        }
    }

    @Override
    public void pushToAdmin(LocalDate today) {
        DaXiangUtils.pushToPerson(contentForB,"guomengyao");
        DaXiangUtils.pushToRoom(contentForB,64141332738l);
        DaXiangUtils.pushToRoom(contentForB,64141367262l);
        DaXiangUtils.pushToRoom(contentForB,64141307357l);
        String firstDayStr = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<String> nameList = dutyTablePOMapper.selectByDate(firstDayStr);
        DaXiangUtils.pushToPerson("本周值班人员：" + nameList.toString(), "buyuqi");
    }

    public static Date localDate2Date(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }
}
