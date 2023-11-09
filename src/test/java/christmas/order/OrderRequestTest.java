package christmas.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.order.OrderRequest.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class OrderRequestTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "31"})
    @DisplayName("날짜 입력이 1~31일에 해당하면 예외가 발생하지 않는다")
    public void testSetDate1(String date) {
        OrderRequest orderRequest = new OrderRequest();
        assertDoesNotThrow(() -> {
            orderRequest.setDate(date); // Valid date input
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "0", "1.2", "-5", "a", "12ab", "ㅇㄴㅁㅇㄴ"})
    @DisplayName("날짜 입력이 1이상 31 이하의 사이의 숫자가 아니면 예외")
    public void testSetDate2(String date) {
        OrderRequest orderRequest = new OrderRequest();
        assertThatThrownBy(() -> {
            orderRequest.setDate(date); // Valid date input
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_INVALID_DATE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1", "티본스테이크-5", "초코케이크-10", "초코케이크-10,제로콜라-3"})
    @DisplayName("메뉴 입력이 올바른 형식이면 예외가 발생하지 않는다")
    public void testSetMenu1(String menu) {
        OrderRequest orderRequest = new OrderRequest();
        assertDoesNotThrow(() -> {
            orderRequest.setMenu(menu); // Valid date input
        });
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴를 주문하면 예외처리한다")
    public void testSetMenu2() {
        OrderRequest orderRequest = new OrderRequest();
        assertThatThrownBy(() -> {
            orderRequest.setMenu("없는메뉴-1"); // Valid date input
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_INVALID_MENU);
    }

    @Test
    @DisplayName("주문한 메뉴가 1개 미만이면 예외 처리한다")
    public void testSetMenu3() {
        OrderRequest orderRequest = new OrderRequest();
        assertThatThrownBy(() -> {
            orderRequest.setMenu("양송이수프-0"); // Valid date input
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_INVALID_MENU);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1-1", "양송이수프-1a", "양송이수프--1", "양송이수프", "양송이수프#1", "양송이수프 1-"})
    @DisplayName("주문 입력이 형식에 맞지 않으면 예외처리한다.")
    public void testSetMenu4(String input) {
        OrderRequest orderRequest = new OrderRequest();
        assertThatThrownBy(() -> {
            orderRequest.setMenu(input); // Valid date input
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_INVALID_MENU);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1,양송이수프-1", "제로콜라-1,양송이수프-1,제로콜라-1"})
    @DisplayName("메뉴를 중복해서 주문하면 예외처리한다.")
    public void testSetMenu5(String input) {
        OrderRequest orderRequest = new OrderRequest();
        assertThatThrownBy(() -> {
            orderRequest.setMenu(input); // Valid date input
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_INVALID_MENU);
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-3", "레드와인-1,제로콜라-3", "샴페인-2", "제로콜라-1,레드와인-1,샴페인-1"})
    @DisplayName("음료만 주문 시 예외가 발생한다.")
    void testSetMenu6(String input) {
        OrderRequest orderRequest = new OrderRequest();
        assertThatThrownBy(() -> {
            orderRequest.setMenu(input); // Valid date input
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_ONLY_BEVERAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-10,티본스테이크-10,레드와인-1"})
    @DisplayName("20개가 넘는 메뉴를 주문하면 예외가 발생한다.")
    void testSetMenu7(String input) {
        OrderRequest orderRequest = new OrderRequest();
        assertThatThrownBy(() -> {
            orderRequest.setMenu(input); // Valid date input
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_OVER_MAX_ORDER);
    }
}