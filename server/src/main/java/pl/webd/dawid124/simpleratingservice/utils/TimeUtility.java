package pl.webd.dawid124.simpleratingservice.utils;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtility {

    public static Date now() {
        return new Date();
    }

    public static Timestamp nowInTimeStamp() {
        return new Timestamp(new Date().getTime());
    }
}