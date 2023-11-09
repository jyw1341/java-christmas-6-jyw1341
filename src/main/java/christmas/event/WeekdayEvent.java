package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.order.Orders;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static christmas.enums.DayOfWeek.SUNDAY;
import static christmas.enums.DayOfWeek.THURSDAY;

public class WeekdayEvent {

    public static final int WEEKDAY_DISCOUNT = 2_023;
    public static final int EVENT_LIMIT = 10_000;

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

    private DayOfWeek getToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return DayOfWeek.getToday(dayOfWeek);
    }
}
