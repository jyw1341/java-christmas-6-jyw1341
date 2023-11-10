package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.order.Orders;

import java.util.Set;

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
        if (today.getCount() == FRIDAY.getCount() || today.getCount() == SATURDAY.getCount()) {
            Set<Menu> menus = orders.getMenu().keySet();
            int count = (int) menus.stream().filter((menu) -> menu.getType().equals(MenuType.MAIN_COURSE)).count();
            result = count * EventType.WEEKEND.getBenefit();

        }
        return result;
    }

    @Override
    public EventType getType() {
        return EventType.WEEKEND;
    }
}
