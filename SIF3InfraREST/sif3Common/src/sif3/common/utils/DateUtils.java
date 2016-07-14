package sif3.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils extends au.com.systemic.framework.utils.DateUtils
{
    public static Date now()
    {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
    }
}
