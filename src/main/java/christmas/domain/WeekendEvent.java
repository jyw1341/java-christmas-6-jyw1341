package christmas.domain;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.enums.MenuType;

import static christmas.enums.DayOfWeek.FRIDAY;
import static christmas.enums.DayOfWeek.SATURDAY;

public class WeekendEvent extends Event {

    @Override
    public boolean checkCondition(Orders orders) {
        DayOfWeek today = getToday(orders.getDate());
        return isWeekend(today);
    }

    @Override
    public Integer getBenefit(Orders orders) {
        int count = countMenuType(orders.getMenu(), MenuType.MAIN_COURSE);
        return count * EventType.WEEKEND.getBenefit();
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek.getCount() == FRIDAY.getCount() || dayOfWeek.getCount() == SATURDAY.getCount();
    }

    @Override
    public EventType getType() {
        return EventType.WEEKEND;
    }
}
