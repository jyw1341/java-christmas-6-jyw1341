package christmas.event;

import christmas.enums.EventType;
import christmas.order.Orders;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {
    public static final int EVENT_LIMIT = 10_000;
    private final List<Event> events;

    public EventPlanner() {
        this.events = new ArrayList<>();
        events.add(new WeekdayEvent());
        events.add(new WeekendEvent());
        events.add(new SpecialEvent());
        events.add(new PresentEvent());
        events.add(new DdayEvent());
    }

    public Benefit applyEvent(Orders orders) {
        Map<EventType, Integer> result = new EnumMap<>(EventType.class);

        if (shouldSkipEvent(orders)) {
            return new Benefit(result);
        }
        for (Event event : events) {
            if (event.checkCondition(orders)) {
                result.put(event.getType(), event.getBenefit(orders));
            }
        }
        return new Benefit(result);
    }

    private boolean shouldSkipEvent(Orders orders) {
        return orders.getTotalOrderAmount() < EVENT_LIMIT;
    }
}
