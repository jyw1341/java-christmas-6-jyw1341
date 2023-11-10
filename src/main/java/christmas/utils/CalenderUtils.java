package christmas.utils;

import java.util.Calendar;
import java.util.Date;

public class CalenderUtils {

    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


}
