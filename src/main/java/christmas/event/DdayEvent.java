package christmas.event;

import christmas.enums.EventType;
import christmas.order.Orders;

import static christmas.utils.CalenderUtils.getDayOfMonth;

public class DdayEvent extends Event {

    public static final int D_DAY_EVENT_FIRST_DAY = 1;
    public static final int D_DAY_EVENT_LAST_DAY = 25;
    public static final int D_DAY_BENEFIT = 100;

    @Override
    public boolean checkCondition(Orders orders) {
        return isDdayPeriod(getDayOfMonth(orders.getDate()));
    }

    @Override
    public Integer getBenefit(Orders orders) {
        int day = getDayOfMonth(orders.getDate());
        return EventType.D_DAY.getBenefit() + ((day - 1) * D_DAY_BENEFIT);
    }

    private boolean isDdayPeriod(int day) {
        return day >= D_DAY_EVENT_FIRST_DAY && day <= D_DAY_EVENT_LAST_DAY;
    }

    @Override
    public EventType getType() {
        return EventType.D_DAY;
    }
}
