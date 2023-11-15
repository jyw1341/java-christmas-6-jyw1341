package christmas.domain;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;

import java.util.Calendar;
import java.util.Date;

import static christmas.enums.DayOfWeek.SUNDAY;
import static christmas.utils.CalenderUtils.getDayOfMonth;
import static christmas.utils.CalenderUtils.getMonth;

public class SpecialEvent extends Event {

    @Override
    public boolean checkCondition(Orders orders) {
        DayOfWeek today = getToday(orders.getDate());
        return isSpecialDay(today, orders.getDate());
    }

    @Override
    public Integer getBenefit(Orders orders) {
        return EventType.SPECIAL.getBenefit();
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
