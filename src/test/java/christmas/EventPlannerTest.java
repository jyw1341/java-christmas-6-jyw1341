package christmas;

import christmas.domain.Benefit;
import christmas.domain.Orders;
import christmas.dto.OrderRequest;
import christmas.enums.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static christmas.domain.DdayEvent.D_DAY_BENEFIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventPlannerTest {

    private EventPlanner eventPlanner;

    @BeforeEach
    void set() {
        eventPlanner = new EventPlanner();
    }

    @Test
    @DisplayName("평일 할인만")
    void getTotalBenefit() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1,아이스크림-2");
        orderRequest.setDate("27"); //12월 27일은 월요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.WEEKDAY.getBenefit() * 2;

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("평일 할인 + 스페셜")
    void getTotalBenefit2() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1,아이스크림-2");
        orderRequest.setDate("31"); //12월 31일은 일요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.WEEKDAY.getBenefit() * 2 + EventType.SPECIAL.getBenefit();

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("평일 할인 + 스페셜 + 증정")
    void getTotalBenefit3() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3,아이스크림-2");
        orderRequest.setDate("31"); //12월 31일은 일요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.WEEKDAY.getBenefit() * 2
                + EventType.SPECIAL.getBenefit()
                + EventType.PRESENT.getBenefit();

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("평일 할인 + 스페셜 + 증정 + 디데이")
    void getTotalBenefit4() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3,아이스크림-2");
        orderRequest.setDate("10"); //12월 10일은 일요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.WEEKDAY.getBenefit() * 2
                + EventType.SPECIAL.getBenefit()
                + EventType.PRESENT.getBenefit()
                + EventType.D_DAY.getBenefit()
                + D_DAY_BENEFIT * 9;

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("주말 할인만")
    void getTotalBenefit5() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-2,아이스크림-2");
        orderRequest.setDate("30"); //12월 30일은 토요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.WEEKEND.getBenefit() * 2;

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("주말 할인 + 디데이")
    void getTotalBenefit6() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-2,아이스크림-2");
        orderRequest.setDate("2"); //12월 2일은 토요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.WEEKEND.getBenefit() * 2
                + EventType.D_DAY.getBenefit()
                + D_DAY_BENEFIT;

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("주말 할인 + 디데이 + 증정")
    void getTotalBenefit7() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3,아이스크림-2");
        orderRequest.setDate("2"); //12월 2일은 토요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.WEEKEND.getBenefit() * 3
                + (EventType.D_DAY.getBenefit() + D_DAY_BENEFIT)
                + EventType.PRESENT.getBenefit();

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("스페셜 할인만")
    void getTotalBenefit8() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1");
        orderRequest.setDate("31"); //12월 31일은 일요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.SPECIAL.getBenefit();

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("스페셜 할인 + 디데이")
    void getTotalBenefit9() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1");
        orderRequest.setDate("3"); //12월 3일은 일요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.SPECIAL.getBenefit()
                + (EventType.D_DAY.getBenefit() + D_DAY_BENEFIT * 2);

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("스페셜할인 + 디데이 할인 + 증정")
    void getTotalBenefit10() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3");
        orderRequest.setDate("3"); //12월 3일은 일요일이다. 일요일은 평일 취급이다
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.SPECIAL.getBenefit()
                + EventType.PRESENT.getBenefit()
                + (EventType.D_DAY.getBenefit() + D_DAY_BENEFIT * 2);

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("스페셜할인 + 디데이 할인 + 증정 + 평일 할인")
    void getTotalBenefit11() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3,아이스크림-1");
        orderRequest.setDate("3"); //12월 3일은 일요일이다. 일요일은 평일 취급이다
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.SPECIAL.getBenefit() +
                EventType.PRESENT.getBenefit() +
                (EventType.D_DAY.getBenefit() + D_DAY_BENEFIT * 2) +
                EventType.WEEKDAY.getBenefit();

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("증정만")
    void getTotalBenefit12() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3");
        orderRequest.setDate("28"); //12월 28일은 목요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.PRESENT.getBenefit();

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("증정 + 디데이")
    void getTotalBenefit13() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-3");
        orderRequest.setDate("4"); //12월 28일은 목요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.PRESENT.getBenefit()
                + (EventType.D_DAY.getBenefit() + D_DAY_BENEFIT * 3);

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("디데이만")
    void getTotalBenefit14() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("아이스크림-3");
        orderRequest.setDate("1"); //12월 1일은 금요일이다.
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());

        Benefit benefit = eventPlanner.applyEvent(orders);
        int expected = EventType.D_DAY.getBenefit();

        assertEquals(expected, benefit.getTotalBenefit());
    }

}