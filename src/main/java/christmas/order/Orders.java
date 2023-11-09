package christmas.order;

import christmas.enums.Menu;

import java.util.*;

public class Orders {

    private final Date date;
    private final Map<Menu, Integer> menu;

    public Orders(OrderRequest orderRequest) {
        this.date = new Date(orderRequest.getDate().getTime());
        this.menu = Collections.unmodifiableMap(new EnumMap<>(orderRequest.getMenu()));
    }

    public Map<Menu, Integer> getMenu() {
        return menu;
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
