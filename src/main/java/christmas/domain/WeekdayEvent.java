package christmas.domain;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.enums.MenuType;

import static christmas.enums.DayOfWeek.SUNDAY;
import static christmas.enums.DayOfWeek.THURSDAY;

public class WeekdayEvent extends Event {

    @Override
    public boolean checkCondition(Orders orders) {
        DayOfWeek today = getToday(orders.getDate());
        return isWeekday(today);
    }

    @Override
    public Integer getBenefit(Orders orders) {
        int count = countMenuType(orders.getMenu(), MenuType.DESSERTS);
        return count * EventType.WEEKDAY.getBenefit();
    }

    private boolean isWeekday(DayOfWeek dayOfWeek) {
        return dayOfWeek.getCount() >= SUNDAY.getCount() && dayOfWeek.getCount() <= THURSDAY.getCount();
    }

    @Override
    public EventType getType() {
        return EventType.WEEKDAY;
    }
}
