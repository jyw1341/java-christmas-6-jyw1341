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

class WeekdayEventTest {


    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7"}) //2023년 12월 3일은 일요일이다. 12월 7일은 목요일이다
    @DisplayName("일~목요일에 디저트를 시키면 2023원 할인을 받는다.")
    void testDiscount1(String day) throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1,시저샐러드-1,제로콜라-1,아이스크림-1");
        orderRequest.setDate(day);
        Orders order = new Orders(orderRequest);
        WeekdayEvent weekdayEvent = new WeekdayEvent();
        int result = weekdayEvent.getBenefit(order);
        assertEquals(EventType.WEEKDAY.getBenefit(), result);
    }

    @Test
    @DisplayName("일~목요일이 아닌날은 디저트를 시켜도 할인을 받지 못한다.")
    void testDiscount2() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1,시저샐러드-1,제로콜라-1,아이스크림-1");
        orderRequest.setDate("1"); //12월 1일은 금요일이다.
        Orders order = new Orders(orderRequest);
        WeekdayEvent weekdayEvent = new WeekdayEvent();
        int result = weekdayEvent.getBenefit(order);
        assertEquals(0, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7"}) //2023년 12월 3일은 일요일이다. 12월 7일은 목요일이다
    @DisplayName("일~목요일에 디저트를 시켜도 총주문금액이 만원 이하면 할인을 못 받는다.")
    void testDiscount3(String day) throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("아이스크림-1");
        orderRequest.setDate(day);
        Orders order = new Orders(orderRequest);
        WeekdayEvent weekdayEvent = new WeekdayEvent();
        int result = weekdayEvent.getBenefit(order);
        assertEquals(0, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7"}) //2023년 12월 3일은 일요일이다. 12월 7일은 목요일이다
    @DisplayName("일~목요일에 디저트를 여러개 시키면 개당 2023원 할인을 받는다.")
    void testDiscount4(String day) throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1,시저샐러드-1,제로콜라-1,아이스크림-3,초코케이크-1");
        orderRequest.setDate(day);
        Orders order = new Orders(orderRequest);
        WeekdayEvent weekdayEvent = new WeekdayEvent();
        int result = weekdayEvent.getBenefit(order);
        assertEquals(EventType.WEEKDAY.getBenefit() * 4, result);
    }
}