package christmas.view;

import christmas.enums.Badge;
import christmas.enums.EventType;
import christmas.enums.Menu;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class OutputView {

    public static final String MESSAGE_PREVIEW_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public static final String MESSAGE_ORDER_MENU = "<주문 메뉴>";
    public static final String MESSAGE_MENU_FORMAT = "%s %d개%n";
    public static final String MESSAGE_TOTAL_AMOUNT = "<할인 전 총주문 금액>";
    public static final String MESSAGE_PRESENT_MENU = "<증정 메뉴>";
    public static final String MESSAGE_CHAMPAGNE = "샴페인 1개\n";
    public static final String MESSAGE_NONE = "없음\n";
    public static final String MESSAGE_BENEFITS_LIST = "<혜택 내역>";
    public static final String MESSAGE_BENEFITS_FORMAT = "%s: -%,d원";
    public static final String MESSAGE_TOTAL_BENEFITS = "<총혜택 금액>";
    public static final String MESSAGE_FINAL_PAYMENT = "<할인 후 예상 결제 금액>";
    public static final String MESSAGE_EVENT_BADGE = "<12월 이벤트 배지>";

    public void printPreview(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH); // 일
        System.out.printf(MESSAGE_PREVIEW_FORMAT, day);
        System.out.println();
        System.out.println();
    }

    public void printOrderMenu(Map<Menu, Integer> menu) {
        System.out.println(MESSAGE_ORDER_MENU);
        for (Map.Entry<Menu, Integer> entry : menu.entrySet()) {
            String name = entry.getKey().getName();
            int amount = entry.getValue();
            System.out.printf(MESSAGE_MENU_FORMAT, name, amount);
        }
        System.out.println();
    }

    public void printTotalAmount(int amount) {
        System.out.println(MESSAGE_TOTAL_AMOUNT);
        System.out.printf("%,d원%n", amount);
        System.out.println();
    }

    public void printPresent(boolean hasPresent) {
        System.out.println(MESSAGE_PRESENT_MENU);
        if (hasPresent) {
            System.out.println(MESSAGE_CHAMPAGNE);
            return;
        }
        System.out.println(MESSAGE_NONE);
    }

    public void printBenefits(Map<EventType, Integer> benefits) {
        System.out.println(MESSAGE_BENEFITS_LIST);
        if (benefits.values().stream().mapToInt(Integer::intValue).sum() == 0) {
            System.out.println(MESSAGE_NONE);
            System.out.println();
        }
        for (Map.Entry<EventType, Integer> entry : benefits.entrySet()) {
            int amount = entry.getValue();
            if (amount > 0) {
                String name = entry.getKey().getName();
                System.out.printf(MESSAGE_BENEFITS_FORMAT, name, amount);
                System.out.println();
            }
        }
        System.out.println();
    }

    public void printTotalBenefits(int benefits) {
        System.out.println(MESSAGE_TOTAL_BENEFITS);
        if (benefits == 0) {
            System.out.printf("%,d원", benefits);
            System.out.println();
            System.out.println();
            return;
        }
        System.out.printf("-%,d원", benefits);
        System.out.println();
        System.out.println();
    }

    public void printFinalPayment(int payment) {
        System.out.println(MESSAGE_FINAL_PAYMENT);
        System.out.printf("%,d원", payment);
        System.out.println();
        System.out.println();
    }

    public void printBadge(Badge badge) {
        System.out.println(MESSAGE_EVENT_BADGE);
        System.out.println(badge.getName());
    }
}
