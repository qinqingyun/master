package com.meituan.food.job.impl;

import com.meituan.food.extract.IMailDataExtract;
import com.meituan.food.extract.IOneDayEffDataEx;
import com.meituan.food.job.IMailJob;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class MailJobImpl implements IMailJob {

    @Resource
    private List<IMailDataExtract> dataExtracts;

    @Override
    public void sync() {
        LocalDate day = LocalDate.now().minusDays(2);

        List<CompletableFuture<Void>> extractFutures = dataExtracts.stream()
                .map(dataExtract -> CompletableFuture.runAsync(() -> {
                    try {
                        dataExtract.extractMailData4EffDay(day);
                    } catch (MDMThriftException e) {
                        e.printStackTrace();
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                }))
                .collect(Collectors.toList());
        extractFutures.forEach(CompletableFuture::join);

    }

}
