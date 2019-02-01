package com.common.util;


import java.sql.Date;
import java.text.SimpleDateFormat;

public class TimeStampUtil {

    public static String getTimeFormate(String type) {
        long timeStamp = System.currentTimeMillis();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
        SimpleDateFormat sdf = null;
        if (type.equals("second")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        }
        if (type.equals("minute")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//这个是你要转成后的时间的格式
        }
        if (type.equals("hour")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH");//这个是你要转成后的时间的格式
        }
        if (type.equals("day")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");//这个是你要转成后的时间的格式
        }
        return sdf == null ? null : sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
    }
}
