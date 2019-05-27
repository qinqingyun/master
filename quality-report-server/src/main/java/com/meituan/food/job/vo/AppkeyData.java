package com.meituan.food.job.vo;

import lombok.Data;

@Data
public class AppkeyData {
    private String owtName;
    private String pdlName;
    private String srvName;
    private String appkeyName;
    private int departmentId;

    public AppkeyData(String owtName, String pdlName, String srvName, String appkeyName, int departmentId) {
        this.owtName = owtName;
        this.pdlName = pdlName;
        this.srvName = srvName;
        this.appkeyName = appkeyName;
        this.departmentId = departmentId;
    }

    public AppkeyData() {
    }
}
