package com.meituan.qa.meishi.Hui.util;

import com.meituan.mtrace.Tracer;
import com.meituan.toolchain.mario.util.MtraceUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by buyuqi on 2019/12/25.
 */
@Slf4j
public class TracerUtil{
    // 所有的traceId
    protected static List<String> traceIdList = new ArrayList<>();

    private static String TRACE_URL = "http://mtrace.inf.dev.sankuai.com";

    // 初始化Trace
    // Copy from Mario Framework
    public static void initTracer() {
        // 使用框架提供的能力
        MtraceUtil.generatTrace(Thread.currentThread().getStackTrace()[3].getMethodName());
    }

    public static void initAndLogTrace() {
        initTracer();
        traceIdList.add(Tracer.id());
        // log.info(getTraceId());
    }

}
