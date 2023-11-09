package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.order.Orders;

import java.util.Set;

import static christmas.enums.DayOfWeek.SUNDAY;
import static christmas.enums.DayOfWeek.THURSDAY;

public class WeekdayEvent extends Event {

    public static final int WEEKDAY_DISCOUNT = 2_023;

    @Override
    public Integer getBenefit(Orders orders) {
        if (orders.getTotalOrderAmount() < EVENT_LIMIT) {
            return 0;
        }
        int result = 0;
        DayOfWeek today = getToday(orders.getDate());
        if (today.getCount() >= SUNDAY.getCount() && today.getCount() <= THURSDAY.getCount()) {
            Set<Menu> menus = orders.getMenu().keySet();
            int dessertCount = (int) menus.stream().filter((menu) -> menu.getType().equals(MenuType.DESSERTS)).count();
            result = dessertCount * WEEKDAY_DISCOUNT;
        }
        return result;
    }
}
