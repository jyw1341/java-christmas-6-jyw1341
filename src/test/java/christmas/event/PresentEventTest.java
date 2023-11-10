package christmas.event;

import christmas.enums.EventType;
import christmas.order.OrderRequest;
import christmas.order.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PresentEventTest {

    @Test
    @DisplayName("할인전 총 주문 금액이 12만원 이상이면 증정품 혜택을 받는다")
    void getBenefit1() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3");
        orderRequest.setDate("3"); //12월 3일은 일요일이다.
        Orders order = new Orders(orderRequest);
        Event event = new PresentEvent();
        int result = event.getBenefit(order);
        assertEquals(EventType.PRESENT.getBenefit(), result);
    }

    @Test
    @DisplayName("할인전 총 주문 금액이 12만원 미만이면 증정품 혜택을 못 받는다")
    void getBenefit2() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-2");
        orderRequest.setDate("3"); //12월 3일은 일요일이다.
        Orders order = new Orders(orderRequest);
        Event event = new PresentEvent();
        int result = event.getBenefit(order);
        assertEquals(0, result);
    }
}