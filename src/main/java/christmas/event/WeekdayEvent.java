package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.order.Orders;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static christmas.enums.DayOfWeek.SUNDAY;
import static christmas.enums.DayOfWeek.THURSDAY;

public class WeekdayEvent extends Event {

    @Override
    public Integer getBenefit(Orders orders) {
        int result = 0;
        if (shouldSkipEvent(orders)) {
            return result;
        }
        DayOfWeek today = getToday(orders.getDate());
        if (today.getCount() >= SUNDAY.getCount() && today.getCount() <= THURSDAY.getCount()) {
            Set<Menu> menus = orders.getMenu().keySet();
            Set<Menu> desserts = menus.stream()
                    .filter((menu) -> menu.getType().equals(MenuType.DESSERTS))
                    .collect(Collectors.toSet());
            int count = desserts.stream().mapToInt((dessert) -> {
                Map<Menu, Integer> m = orders.getMenu();
                return m.get(dessert);
            }).sum();
            result = count * EventType.WEEKDAY.getBenefit();
        }
        return result;
    }

    @Override
    public EventType getType() {
        return EventType.WEEKDAY;
    }
}
