package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.enums.MenuType;
import christmas.order.Orders;

import static christmas.enums.DayOfWeek.FRIDAY;
import static christmas.enums.DayOfWeek.SATURDAY;

public class WeekendEvent extends Event {

    @Override
    public Integer getBenefit(Orders orders) {
        int result = 0;
        if (shouldSkipEvent(orders)) {
            return result;
        }
        DayOfWeek today = getToday(orders.getDate());
        if (isWeekend(today)) {
            int count = countMenuType(orders.getMenu(), MenuType.MAIN_COURSE);
            result = count * EventType.WEEKEND.getBenefit();

        }
        return result;
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek.getCount() == FRIDAY.getCount() || dayOfWeek.getCount() == SATURDAY.getCount();
    }

    @Override
    public EventType getType() {
        return EventType.WEEKEND;
    }
}
