package christmas.event;

import christmas.enums.EventType;
import christmas.order.OrderRequest;
import christmas.order.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialEventTest {

    @ParameterizedTest
    @ValueSource(strings = {"3", "10", "17", "24", "25"}) //달력에 별이 있는 날은 12월의 모든 일요일과 크리스마스 날이다.
    @DisplayName("달력에 별이 있는 날에는 총 주문 금액에서 1000원을 할인 받는다.")
    void testGetBenefit1(String day) throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1,시저샐러드-1,제로콜라-1,아이스크림-1");
        orderRequest.setDate(day);
        Orders order = new Orders(orderRequest);
        Event event = new SpecialEvent();
        int result = event.getBenefit(order);
        assertEquals(EventType.SPECIAL.getBenefit(), result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"4", "11", "26"}) //달력에 별이 있는 날은 12월의 모든 일요일과 크리스마스 날이다.
    @DisplayName("달력에 별이 없는 날에는 할인을 못받는다.")
    void testGetBenefit2(String day) throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1,시저샐러드-1,제로콜라-1,아이스크림-1");
        orderRequest.setDate(day);
        Orders order = new Orders(orderRequest);
        Event event = new SpecialEvent();
        int result = event.getBenefit(order);
        assertEquals(0, result);
    }
}