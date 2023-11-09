package christmas.order;

import christmas.enums.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OrderTest {

    @Test
    @DisplayName("주문한 메뉴들의 가격의 합은 같아야한다.")
    void getTotalOrderAmount() throws ParseException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setMenu("바비큐립-1,시저샐러드-1,제로콜라-1");
        orderRequest.setDate("25");
        Orders order = new Orders(orderRequest);
        int result = Menu.BBQ_RIBS.getPrice() + Menu.CAESAR_SALAD.getPrice() + Menu.ZERO_COKE.getPrice();

        assertEquals(result, order.getTotalOrderAmount());
    }
}