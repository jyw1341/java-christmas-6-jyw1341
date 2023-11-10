package christmas.event;

import christmas.enums.EventType;
import christmas.order.Orders;

public class PresentEvent extends Event {

    public static final int PRESENT_LIMIT = 120_000;

    @Override
    public Integer getBenefit(Orders orders) {
        int result = 0;
        if (orders.getTotalOrderAmount() < PRESENT_LIMIT) {
            return result;
        }
        result = EventType.PRESENT.getBenefit();
        return result;
    }

    @Override
    public EventType getType() {
        return EventType.PRESENT;
    }
}
