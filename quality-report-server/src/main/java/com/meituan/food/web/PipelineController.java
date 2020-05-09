package com.meituan.food.web;

import com.meituan.food.extract.IOneDayItPipelineExtract;
import com.meituan.food.extract.IOneDayPrPipelineExtract;
import com.meituan.food.extract.IOneDayTpPipelineExtract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/pipeline")
public class PipelineController {

    @Resource
    private IOneDayItPipelineExtract oneDayItPipelineExtract;

    @Resource
    private IOneDayPrPipelineExtract oneDayPrPipelineExtract;

    @Resource
    private IOneDayTpPipelineExtract oneDayTpPipelineExtract;

    @GetMapping("/insert/itdate")
    public String insertDate(@RequestParam("date") String date) throws ParseException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateL = LocalDate.parse(date, fmt);
        LocalDate dateAdd = dateL.plusDays(+1);
        oneDayItPipelineExtract.updateItPipelineData(dateL);
        return "OK!";
    }
    @GetMapping("/insert/it")
    public String insertFromToDate(@RequestParam("from") String start,@RequestParam("to") String end) throws ParseException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startL = LocalDate.parse(start, fmt);
        LocalDate startAdd = startL.plusDays(+1);//start默认加昨天
        LocalDate endL = LocalDate.parse(end, fmt);
        LocalDate endAdd = endL.plusDays(+1);
        do {
            oneDayItPipelineExtract.updateItPipelineData(startAdd);
            startAdd = startAdd.plusDays(1);
        } while (startAdd.toEpochDay() <= endAdd.toEpochDay());

        return "OK!";
    }
    @GetMapping("/insert/pr")
    public String insertPRFromToDate(@RequestParam("from") String start,@RequestParam("to") String end) throws ParseException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startL = LocalDate.parse(start, fmt);
        LocalDate endL = LocalDate.parse(end, fmt);
        do {
            oneDayPrPipelineExtract.UpdatePrPipelineData(startL);
            startL = startL.plusDays(1);
        } while (startL.toEpochDay() <= endL.toEpochDay());

        return "OK!";
    }

    @GetMapping("/insert/tp")
    public String insertTPFromToDate(@RequestParam("from") String start,@RequestParam("to") String end) throws ParseException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startL = LocalDate.parse(start, fmt);
        LocalDate endL = LocalDate.parse(end, fmt);
        do {
            oneDayTpPipelineExtract.UpdateTpPipelineData(startL);
            startL = startL.plusDays(1);
        } while (startL.toEpochDay() <= endL.toEpochDay());

        return "OK!";
    }

    @GetMapping("/insert/direction")
    public String insertDirectionDate(@RequestParam("direction_id") Integer direction_id,@RequestParam("direction_name") String direction_name,@RequestParam("group_name") String group_name) throws ParseException {

        oneDayTpPipelineExtract.insertDirection(direction_id,direction_name,group_name);
        return "OK!";
    }

    @GetMapping("/insert/pr/repo")
    public String insertRepoPRFromToDate(@RequestParam("from") String start,@RequestParam("to") String end) throws ParseException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startL = LocalDate.parse(start, fmt);
        LocalDate endL = LocalDate.parse(end, fmt);
        do {
            startL = startL.plusDays(1);
            oneDayPrPipelineExtract.UpdatePrAutoData(startL);

        } while (startL.toEpochDay() <= endL.toEpochDay());

        return "OK!";
    }


    @GetMapping("/insert/pr/repoMis")
    public String insertRepoMis(@RequestParam("repo") String repo,@RequestParam("mis") String mis) throws ParseException {
        int result = oneDayPrPipelineExtract.UpdateDirectionMis(repo,mis);
        if (result ==1){
            return "更新pr仓库负责人成功！";
        }else return "更新pr仓库负责人失败！";

    }


}
