package com.meituan.food.mapper;

import com.meituan.food.ApplicationLoader;
import com.meituan.food.po.WeekIssuePO;
import groovy.util.logging.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class WeekIssuePOMapperTest {

    @Resource
    private WeekIssuePOMapper weekIssuePOMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        WeekIssuePO weekIssuePO = new WeekIssuePO();
        weekIssuePO.setBrief("xsd");
        weekIssuePO.setCreatedAt(new Date());
        weekIssuePO.setUpdatedAt(new Date());
        weekIssuePO.setDepartment("!");
        weekIssuePO.setLevel("lev");
        weekIssuePO.setType("type");
        weekIssuePO.setWiki("http://123.wiki.com");
        weekIssuePOMapper.insert(weekIssuePO);
    }

    @Test
    public void batchInsert() {
        WeekIssuePO weekIssuePO = new WeekIssuePO();
        weekIssuePO.setBrief("xsd");
        weekIssuePO.setCreatedAt(new Date());
        weekIssuePO.setUpdatedAt(new Date());
        weekIssuePO.setDepartment("!");
        weekIssuePO.setLevel("lev");
        weekIssuePO.setType("type");
        weekIssuePO.setWiki("http://123.wiki.com");
        WeekIssuePO weekIssuePO1 = new WeekIssuePO();
        weekIssuePO1.setBrief("xsd");
        weekIssuePO1.setCreatedAt(new Date());
        weekIssuePO1.setUpdatedAt(new Date());
        weekIssuePO1.setDepartment("!");
        weekIssuePO1.setLevel("lev");
        weekIssuePO1.setType("type");
        weekIssuePO1.setWiki("http://123.wiki.com");
        weekIssuePOMapper.batchInsert(Lists.newArrayList(weekIssuePO, weekIssuePO1));
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}