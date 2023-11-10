package christmas;

import christmas.event.Benefit;
import christmas.event.EventPlanner;
import christmas.order.OrderRequest;
import christmas.order.Orders;
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
