package ua.juliazozulia.taskcollector.Utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Julia on 21.04.2016.
 */
public class DateUtils {
    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}

