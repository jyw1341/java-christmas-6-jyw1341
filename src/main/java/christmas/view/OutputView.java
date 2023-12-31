package christmas.view;

import christmas.domain.Benefit;
import christmas.domain.Orders;
import christmas.enums.Badge;
import christmas.enums.EventType;
import christmas.enums.Menu;

import java.util.Date;
import java.util.Map;

import static christmas.utils.CalenderUtils.getDayOfMonth;
import static christmas.view.Messages.*;

public class OutputView {

    public void printEventResult(Orders orders, Benefit benefit) {
        printPreview(orders.getDate());
        printOrderMenu(orders.getMenu());
        printTotalAmount(orders.getTotalOrderAmount());
        printPresent(benefit.hasPresent());
        printBenefits(benefit.getBenefits());
        printTotalBenefits(benefit.getTotalBenefit());
        printFinalPayment(orders.getTotalOrderAmount() - benefit.getDiscountAmount());
        printBadge(benefit.getBadge());
    }

    private void printPreview(Date date) {
        System.out.printf(MESSAGE_PREVIEW_FORMAT, getDayOfMonth(date));
    }

    private void printOrderMenu(Map<Menu, Integer> menu) {
        System.out.println(MESSAGE_ORDER_MENU);
        for (Map.Entry<Menu, Integer> entry : menu.entrySet()) {
            String menuName = entry.getKey().getName();
            int amount = entry.getValue();
            System.out.printf(MESSAGE_MENU_FORMAT, menuName, amount);
        }
        System.out.println();
    }

    private void printTotalAmount(int amount) {
        System.out.println(MESSAGE_TOTAL_AMOUNT);
        System.out.printf(CURRENCY_FORMAT + TWO_LINE_SPACE, amount);
    }

    private void printPresent(boolean hasPresent) {
        System.out.println(MESSAGE_PRESENT_MENU);
        if (hasPresent) {
            System.out.println(MESSAGE_CHAMPAGNE);
            return;
        }
        System.out.println(MESSAGE_NONE);
    }

    private void printBenefits(Map<EventType, Integer> benefits) {
        System.out.println(MESSAGE_BENEFITS_LIST);
        if (benefits.isEmpty()) {
            System.out.println(MESSAGE_NONE);
            return;
        }
        for (Map.Entry<EventType, Integer> entry : benefits.entrySet()) {
            int amount = entry.getValue();
            if (amount > 0) {
                String name = entry.getKey().getName();
                System.out.printf(MESSAGE_BENEFITS_FORMAT, name, amount);
            }
        }
        System.out.println();
    }

    private void printTotalBenefits(int benefits) {
        System.out.println(MESSAGE_TOTAL_BENEFITS);
        if (benefits == 0) {
            System.out.printf(CURRENCY_FORMAT + TWO_LINE_SPACE, benefits);
            return;
        }
        System.out.printf(CURRENCY_FORMAT_MINUS + TWO_LINE_SPACE, benefits);
    }

    private void printFinalPayment(int payment) {
        System.out.println(MESSAGE_FINAL_PAYMENT);
        System.out.printf(CURRENCY_FORMAT + TWO_LINE_SPACE, payment);
    }

    private void printBadge(Badge badge) {
        System.out.println(MESSAGE_EVENT_BADGE);
        System.out.println(badge.getName());
    }
}
