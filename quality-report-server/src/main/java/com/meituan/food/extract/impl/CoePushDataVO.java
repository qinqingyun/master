package com.meituan.food.extract.impl;

import lombok.Data;

@Data
public class CoePushDataVO {
   private int s1Conut;
   private int s2Conut;
   private int s3Conut;
   private int s4Count;
   private int s9Count;
   private int otherCount;
   private int allCount;
   private String orgName;
   private int incompleteCount;
   private String incompleteMessage;
   private int overdueTodoCount;
   private String overdueTodo;
   private String coeMessage;

   public CoePushDataVO newVO(){
       this.setS1Conut(0);
       this.setS2Conut(0);
       this.setS3Conut(0);
       this.setS4Count(0);
       this.setS9Count(0);
       this.setOtherCount(0);
       this.setAllCount(0);
       this.setIncompleteCount(0);
       this.setOverdueTodoCount(0);
       this.setOrgName("");
       this.setIncompleteMessage("");
       this.setOverdueTodo("");
       this.setCoeMessage("");

       return this;
   }
}
