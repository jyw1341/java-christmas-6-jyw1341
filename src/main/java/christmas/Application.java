package christmas;

import christmas.event.Benefit;
import christmas.event.EventService;
import christmas.order.OrderRequest;
import christmas.order.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        OrderRequest orderRequest = new OrderRequest();
        inputView.readDate(orderRequest);
        inputView.readMenu(orderRequest);
        Orders orders = new Orders(orderRequest);
        EventService eventService = new EventService();
        Benefit benefit = eventService.applyEvent(orders);
        outputView.printPreview(orders.getDate());
        outputView.printOrderMenu(orders.getMenu());
        outputView.printTotalAmount(orders.getTotalOrderAmount());
        outputView.printPresent(benefit.hasPresent());
        outputView.printBenefits(benefit.getBenefits());
        outputView.printTotalBenefits(benefit.getTotalBenefit());
        outputView.printFinalPayment(orders.getTotalOrderAmount() - benefit.getDiscountAmount());
        outputView.printBadge(benefit.getBadge());
    }
}
