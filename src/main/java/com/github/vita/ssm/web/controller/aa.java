package com.github.vita.ssm.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * desc
 *
 * @author Yang Cheng
 * @date 2016-09-19
 */
public class aa {
    public static void main(String[] args) throws Exception{
        String endtime = "2017-02-28";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 将endtime加一天,使得sql中可以查到endtime当天数据
            Date date = sdf.parse(endtime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_YEAR, 1);
            endtime = sdf.format(cal.getTime());
        } catch (ParseException e) {
        }
        System.out.println(endtime);
    }
}
