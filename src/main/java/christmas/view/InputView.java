package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.order.OrderRequest;

public class InputView {

    public static final String MESSAGE_GREETINGS = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String MESSAGE_ASK_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String MESSAGE_GET_ORDER =
            "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public void readDate(OrderRequest orderRequest) {
        System.out.println(MESSAGE_GREETINGS);
        while (true) {
            try {
                System.out.println(MESSAGE_ASK_DATE);
                String input = Console.readLine();
                orderRequest.setDate(input);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void readMenu(OrderRequest orderRequest) {
        while (true) {
            try {
                System.out.println(MESSAGE_GET_ORDER);
                String input = Console.readLine();
                orderRequest.setMenu(input);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
