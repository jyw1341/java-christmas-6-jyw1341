package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.order.Orders;

import java.util.Set;

import static christmas.enums.DayOfWeek.FRIDAY;
import static christmas.enums.DayOfWeek.SATURDAY;

public class WeekendEvent extends Event {

    public static final int WEEKEND_DISCOUNT = 2_023;

    @Override
    public Integer getBenefit(Orders orders) {
        if (orders.getTotalOrderAmount() < EVENT_LIMIT) {
            return 0;
        }
        int result = 0;
        DayOfWeek today = getToday(orders.getDate());
        if (today.getCount() == FRIDAY.getCount() || today.getCount() == SATURDAY.getCount()) {
            Set<Menu> menus = orders.getMenu().keySet();
            int dessertCount = (int) menus.stream().filter((menu) -> menu.getType().equals(MenuType.MAIN_COURSE)).count();
            result = dessertCount * WEEKEND_DISCOUNT;
        }
        return result;
    }
}
