package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.order.Orders;

import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import static christmas.enums.DayOfWeek.SUNDAY;

public class SpecialEvent extends Event {
    @Override
    public Map<EventType, Integer> getBenefit(Orders orders) {
        Map<EventType, Integer> result = new EnumMap<>(EventType.class);

        result.put(EventType.SPECIAL, 0);
        if (orders.getTotalOrderAmount() < EVENT_LIMIT) {
            return result;
        }
        DayOfWeek today = getToday(orders.getDate());
        if (today.getCount() == SUNDAY.getCount() || isChristmas(orders.getDate())) {
            result.put(EventType.SPECIAL, EventType.SPECIAL.getBenefit());
        }
        return result;
    }

    private boolean isChristmas(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH); // 월 (0부터 시작)
        int day = calendar.get(Calendar.DAY_OF_MONTH); // 일

        int christmasMonth = Calendar.DECEMBER; // 12월
        int christmasDay = 25;

        return month == christmasMonth && day == christmasDay;
    }
}
