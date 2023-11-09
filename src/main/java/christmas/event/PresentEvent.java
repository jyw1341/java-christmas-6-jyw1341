package christmas.event;

import christmas.enums.EventType;
import christmas.order.Orders;

import java.util.EnumMap;
import java.util.Map;

public class PresentEvent extends Event {

    public static final int PRESENT_LIMIT = 120_000;

    @Override
    public Map<EventType, Integer> getBenefit(Orders orders) {
        Map<EventType, Integer> result = new EnumMap<>(EventType.class);
        result.put(EventType.PRESENT, 0);
        if (orders.getTotalOrderAmount() < PRESENT_LIMIT) {
            return result;
        }
        result.put(EventType.PRESENT, EventType.PRESENT.getBenefit());
        return result;
    }
}
