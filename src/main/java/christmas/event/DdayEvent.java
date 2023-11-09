package christmas.event;

import christmas.enums.EventType;
import christmas.order.Orders;

import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

public class DdayEvent extends Event {

    public static final int D_DAY_EVENT_FIRST_DAY = 1;
    public static final int D_DAY_EVENT_LAST_DAY = 25;
    public static final int D_DAY_BENEFIT = 100;

    @Override
    public Map<EventType, Integer> getBenefit(Orders orders) {
        Map<EventType, Integer> result = new EnumMap<>(EventType.class);
        result.put(EventType.D_DAY, 0);
        if (orders.getTotalOrderAmount() < EVENT_LIMIT) {
            return result;
        }
        int day = getDay(orders.getDate());
        if (day >= D_DAY_EVENT_FIRST_DAY && day <= D_DAY_EVENT_LAST_DAY) {
            int benefit = EventType.D_DAY.getBenefit() + ((day - 1) * D_DAY_BENEFIT);
            result.put(EventType.D_DAY, benefit);
        }
        return result;
    }

    private int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
