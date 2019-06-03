package com.meituan.food.job.vo;

import lombok.Data;

@Data
public class AppkeyData {
    private String owtName;
    private String pdlName;
    private String srvName;
    private String appkeyName;
    private int departmentId;
    private int departmentId2;

    public AppkeyData(String owtName, String pdlName, String srvName, String appkeyName, int departmentId,int departmentId2) {
        this.owtName = owtName;
        this.pdlName = pdlName;
        this.srvName = srvName;
        this.appkeyName = appkeyName;
        this.departmentId = departmentId;
        this.departmentId2 = departmentId2;

    }

    public AppkeyData() {
    }
}
