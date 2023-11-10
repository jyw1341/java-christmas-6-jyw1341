package christmas.event;

import christmas.enums.EventType;
import christmas.order.Orders;

import java.util.Calendar;
import java.util.Date;

public class DdayEvent extends Event {

    public static final int D_DAY_EVENT_FIRST_DAY = 1;
    public static final int D_DAY_EVENT_LAST_DAY = 25;
    public static final int D_DAY_BENEFIT = 100;

    @Override
    public Integer getBenefit(Orders orders) {
        int result = 0;
        if (shouldSkipEvent(orders)) {
            return result;
        }
        int day = getDay(orders.getDate());
        if (day >= D_DAY_EVENT_FIRST_DAY && day <= D_DAY_EVENT_LAST_DAY) {
            result = EventType.D_DAY.getBenefit() + ((day - 1) * D_DAY_BENEFIT);
        }
        return result;
    }

    private int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public EventType getType() {
        return EventType.D_DAY;
    }
}
