package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.order.Orders;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public abstract class Event {

    public static final int EVENT_LIMIT = 10_000;

    public abstract Map<EventType, Integer> getBenefit(Orders orders);

    protected DayOfWeek getToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return DayOfWeek.getToday(dayOfWeek);
    }
}
