package christmas.domain;

import christmas.enums.Menu;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class Orders {

    private final Date date;
    private final Map<Menu, Integer> menu;

    public Orders(Date date, Map<Menu, Integer> menu) {
        this.date = date;
        this.menu = menu;
    }

    public Map<Menu, Integer> getMenu() {
        Map<Menu, Integer> result = new EnumMap<>(Menu.class);
        result.putAll(menu);
        return result;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public Integer getTotalOrderAmount() {
        int result = 0;
        Set<Map.Entry<Menu, Integer>> menuEntrySet = menu.entrySet();
        for (Map.Entry<Menu, Integer> entry : menuEntrySet) {
            Menu key = entry.getKey();
            Integer value = entry.getValue();
            result += key.getPrice() * value;
        }
        return result;
    }
}
