package com.meituan.food.extract.impl;

import com.meituan.food.ApplicationLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public class CargoExtracterTest {

    @Autowired
    CargoExtracter cargoExtracter;

    @Test
    public void test()
    {
        cargoExtracter.updateCargoAva(LocalDate.now());
    }
}