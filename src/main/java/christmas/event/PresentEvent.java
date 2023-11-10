package christmas.event;

import christmas.enums.EventType;
import christmas.order.Orders;

public class PresentEvent extends Event {

    public static final int PRESENT_LIMIT = 120_000;

    @Override
    public boolean checkCondition(Orders orders) {
        return orders.getTotalOrderAmount() > PRESENT_LIMIT;
    }

    @Override
    public Integer getBenefit(Orders orders) {
        return EventType.PRESENT.getBenefit();
    }

    @Override
    public EventType getType() {
        return EventType.PRESENT;
    }
}
