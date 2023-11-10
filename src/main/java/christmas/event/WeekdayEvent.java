package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.enums.MenuType;
import christmas.order.Orders;

import static christmas.enums.DayOfWeek.SUNDAY;
import static christmas.enums.DayOfWeek.THURSDAY;

public class WeekdayEvent extends Event {

    @Override
    public Integer getBenefit(Orders orders) {
        int result = 0;
        DayOfWeek today = getToday(orders.getDate());
        if (isWeekday(today)) {
            int count = countMenuType(orders.getMenu(), MenuType.DESSERTS);
            result = count * EventType.WEEKDAY.getBenefit();
        }
        return result;
    }

    private boolean isWeekday(DayOfWeek dayOfWeek) {
        return dayOfWeek.getCount() >= SUNDAY.getCount() && dayOfWeek.getCount() <= THURSDAY.getCount();
    }

    @Override
    public EventType getType() {
        return EventType.WEEKDAY;
    }
}
