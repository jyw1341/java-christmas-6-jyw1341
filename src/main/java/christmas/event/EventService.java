package christmas.event;

import christmas.enums.EventType;
import christmas.order.Orders;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EventService {

    private final List<Event> events;

    public EventService() {
        this.events = new ArrayList<>();
        events.add(new WeekdayEvent());
        events.add(new WeekendEvent());
        events.add(new SpecialEvent());
        events.add(new PresentEvent());
        events.add(new DdayEvent());
    }

    public Benefit applyEvent(Orders orders) {
        Map<EventType, Integer> result = new EnumMap<>(EventType.class);

        for (Event event : events) {
            result.put(event.getType(), event.getBenefit(orders));
        }
        return new Benefit(result);
    }
}
