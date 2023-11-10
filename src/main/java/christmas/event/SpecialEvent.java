package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.order.Orders;

import java.util.Calendar;
import java.util.Date;

import static christmas.enums.DayOfWeek.SUNDAY;
import static christmas.utils.CalenderUtils.getDayOfMonth;
import static christmas.utils.CalenderUtils.getMonth;

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
        return getMonth(date) == Calendar.DECEMBER && getDayOfMonth(date) == 25;
    }

    @Override
    public EventType getType() {
        return EventType.SPECIAL;
    }
}
