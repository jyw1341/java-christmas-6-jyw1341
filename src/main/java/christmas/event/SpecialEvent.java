package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.order.Orders;

import java.util.Calendar;
import java.util.Date;

import static christmas.enums.DayOfWeek.SUNDAY;

public class SpecialEvent extends Event {
    @Override
    public Integer getBenefit(Orders orders) {
        int result = 0;

        if (shouldSkipEvent(orders)) {
            return result;
        }
        DayOfWeek today = getToday(orders.getDate());
        if (isSpecialDay(today, orders.getDate())) {
            result = EventType.SPECIAL.getBenefit();
        }
        return result;
    }

    private boolean isSpecialDay(DayOfWeek dayOfWeek, Date date) {
        return dayOfWeek.getCount() == SUNDAY.getCount() || isChristmas(date);
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

    @Override
    public EventType getType() {
        return EventType.SPECIAL;
    }
}
