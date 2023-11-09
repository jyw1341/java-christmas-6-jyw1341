package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.order.Orders;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import static christmas.enums.DayOfWeek.FRIDAY;
import static christmas.enums.DayOfWeek.SATURDAY;

public class WeekendEvent extends Event {

    @Override
    public Map<EventType, Integer> getBenefit(Orders orders) {
        Map<EventType, Integer> result = new EnumMap<>(EventType.class);
        result.put(EventType.WEEKEND, 0);
        if (orders.getTotalOrderAmount() < EVENT_LIMIT) {
            return result;
        }
        DayOfWeek today = getToday(orders.getDate());
        if (today.getCount() == FRIDAY.getCount() || today.getCount() == SATURDAY.getCount()) {
            Set<Menu> menus = orders.getMenu().keySet();
            int count = (int) menus.stream().filter((menu) -> menu.getType().equals(MenuType.MAIN_COURSE)).count();
            result.put(EventType.WEEKEND, count * EventType.WEEKEND.getBenefit());

        }
        return result;
    }
}
