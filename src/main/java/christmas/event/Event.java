package christmas.event;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.order.Orders;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public abstract class Event {

    public static final int EVENT_LIMIT = 10_000;

    public abstract Integer getBenefit(Orders orders);

    public abstract EventType getType();

    protected DayOfWeek getToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return DayOfWeek.getToday(dayOfWeek);
    }

    protected boolean shouldSkipEvent(Orders orders) {
        return orders.getTotalOrderAmount() < EVENT_LIMIT;
    }

    protected int countMenuType(Map<Menu, Integer> menu, MenuType menuType) {
        return menu.keySet().stream()
                .filter(menuItem -> menuItem.getType().equals(menuType))
                .mapToInt(menu::get)
                .sum();
    }
}
