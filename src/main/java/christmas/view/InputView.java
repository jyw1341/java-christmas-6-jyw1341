package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.order.OrderRequest;

import static christmas.view.Messages.*;

public class InputView {

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
