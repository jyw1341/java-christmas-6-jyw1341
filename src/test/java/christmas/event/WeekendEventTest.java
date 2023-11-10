package christmas.event;

import christmas.enums.EventType;
import christmas.order.OrderRequest;
import christmas.order.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeekendEventTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "2"}) //2023년 12월 1일은 금요일이다.
    @DisplayName("금,토에 메인 메뉴를 시키면 메뉴당 2023원 할인을 받는다.")
    void testDiscount1(String day) throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1,시저샐러드-1,제로콜라-1,아이스크림-1");
        orderRequest.setDate(day);
        Orders order = new Orders(orderRequest);

        OrderRequest orderRequest2 = new OrderRequest();
        orderRequest2.setMenu("바비큐립-1,크리스마스파스타-1");
        orderRequest2.setDate(day);
        Orders order2 = new Orders(orderRequest2);

        Event weekendEvent = new WeekendEvent();
        int result = weekendEvent.getBenefit(order);
        int result2 = weekendEvent.getBenefit(order2);

        assertEquals(EventType.WEEKEND.getBenefit(), result);
        assertEquals(EventType.WEEKEND.getBenefit() * 2, result2);
    }

    @Test
    @DisplayName("금,토 아닌날은 메인 메뉴를 시켜도 할인을 받지 못한다.")
    void testDiscount2() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1,시저샐러드-1,제로콜라-1");
        orderRequest.setDate("3"); //12월 3일은 일요일이다.
        Orders order = new Orders(orderRequest);
        Event weekendEvent = new WeekendEvent();
        int result = weekendEvent.getBenefit(order);
        assertEquals(0, result);
    }

}