package com.pdf.convert;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class KnTest {
    public static void main(String[] args) {

        String startTimeStr = "2019-12-24 23:25:25";
        String endTimeStr = "2019-12-26 16:28:25";
        
        Date startTime = DateUtil.parse(startTimeStr, "yy-mm-dd HH:mm:ss");
        
        Date endTime = DateUtil.parse(endTimeStr, "yy-mm-dd HH:mm:ss");

        long spend = DateUtil.between(startTime, endTime, DateUnit.HOUR);

        System.out.println("spend = " + spend);

        Integer aa = 11;
        Integer bb = 11;
        System.out.println(" = " + bb.equals(aa));
        
    }
}
