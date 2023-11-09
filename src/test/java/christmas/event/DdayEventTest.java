package christmas.event;

import christmas.enums.EventType;
import christmas.order.OrderRequest;
import christmas.order.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;
import java.util.Map;

import static christmas.event.DdayEvent.D_DAY_BENEFIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DdayEventTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})
    @DisplayName("12월 1일에 1000원을 할인 받고 그 이후 할인 금액이 100원씩 증가한다.")
    void getBenefit1(String day) throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3");
        orderRequest.setDate(day);
        Orders order = new Orders(orderRequest);
        Event event = new DdayEvent();
        Map<EventType, Integer> result = event.getBenefit(order);
        int expected = EventType.D_DAY.getBenefit() + (D_DAY_BENEFIT * (Integer.parseInt(day) - 1));
        assertEquals(expected, result.get(EventType.D_DAY));
    }

    @ParameterizedTest
    @ValueSource(strings = {"21", "22", "23", "24", "25"})
    @DisplayName("12월 1일에 1000원을 할인 받고 그 이후 할인 금액이 100원씩 증가한다.")
    void getBenefit2(String day) throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3");
        orderRequest.setDate(day);
        Orders order = new Orders(orderRequest);
        Event event = new DdayEvent();
        Map<EventType, Integer> result = event.getBenefit(order);
        int expected = EventType.D_DAY.getBenefit() + (D_DAY_BENEFIT * (Integer.parseInt(day) - 1));
        assertEquals(expected, result.get(EventType.D_DAY));
    }

    @ParameterizedTest
    @ValueSource(strings = {"26", "27", "28", "29", "30", "31"})
    @DisplayName("25일 이후로는 할인을 받지 못한다.")
    void getBenefit3(String day) throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3");
        orderRequest.setDate(day);
        Orders order = new Orders(orderRequest);
        Event event = new DdayEvent();
        Map<EventType, Integer> result = event.getBenefit(order);
        assertEquals(0, result.get(EventType.D_DAY));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})
    @DisplayName("총 주문 금액이 만원 미만이면 디데이 할인을 못받는다.")
    void getBenefit4(String day) throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("시저샐러드-1");
        orderRequest.setDate(day);
        Orders order = new Orders(orderRequest);
        Event event = new DdayEvent();
        Map<EventType, Integer> result = event.getBenefit(order);
        assertEquals(0, result.get(EventType.D_DAY));
    }
}