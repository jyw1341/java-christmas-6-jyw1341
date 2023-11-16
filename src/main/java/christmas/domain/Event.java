package christmas.domain;

import christmas.enums.DayOfWeek;
import christmas.enums.EventType;
import christmas.enums.Menu;
import christmas.enums.MenuType;

import java.util.Date;
import java.util.Map;

import static christmas.utils.CalenderUtils.getDayOfWeek;

public abstract class Event {

    public abstract Integer getBenefit(Orders orders);

    public abstract EventType getType();

    public abstract boolean checkCondition(Orders orders);

    protected DayOfWeek getToday(Date date) {
        return DayOfWeek.getDay(getDayOfWeek(date));
    }

    protected int countMenuType(Map<Menu, Integer> menu, MenuType menuType) {
        return menu.keySet().stream()
                .filter(menuItem -> menuItem.getType().equals(menuType))
                .mapToInt(menu::get)
                .sum();
    }
}
