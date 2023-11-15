package christmas;

import christmas.domain.Benefit;
import christmas.domain.Orders;
import christmas.dto.OrderRequest;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        EventPlanner eventPlanner = new EventPlanner();

        OrderRequest orderRequest = inputView.readReservationInfo();
        Orders orders = new Orders(orderRequest.getDate(), orderRequest.getMenu());
        Benefit benefit = eventPlanner.applyEvent(orders);

        outputView.printEventResult(orders, benefit);
    }
}
