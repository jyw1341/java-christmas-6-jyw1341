package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.order.Orders;

import java.util.Calendar;
import java.util.Date;

public abstract class Event {

    public static final int EVENT_LIMIT = 10_000;

    public abstract Integer getBenefit(Orders orders);

    public abstract EventType getType();

    protected DayOfWeek getToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return DayOfWeek.getToday(dayOfWeek);
    }

    protected boolean shouldSkipEvent(Orders orders) {
        return orders.getTotalOrderAmount() < EVENT_LIMIT;
    }
}
