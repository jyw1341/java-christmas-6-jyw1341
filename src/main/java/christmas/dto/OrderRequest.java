package christmas.dto;

import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.utils.RegexExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static christmas.utils.Validator.*;

public class OrderRequest {

    public static final String INPUT_SEPARATOR = ",";
    public static final String MENU_SEPARATOR = "-";
    public static final String ERROR_INVALID_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String ERROR_INVALID_MENU = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String ERROR_ONLY_BEVERAGE = "[ERROR] 음료만 주문 시, 주문할 수 없습니다.";
    public static final String ERROR_OVER_MAX_ORDER = "[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";
    public static final int MIN_DATE = 1;
    public static final int MAX_DATE = 31;
    public static final int MAX_ORDER_AMOUNT = 20;
    private Date date;
    private Map<Menu, Integer> menu;

    public Date getDate() {
        return date;
    }

    public void setDate(String input) {
        validateDateInput(input);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = String.format("2023-12-%s", input);
        try {
            this.date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE);
        }
    }

    private void validateDateInput(String input) {
        validateIsNotBlank(input, ERROR_INVALID_DATE);
        validateIsNumber(input, ERROR_INVALID_DATE);
        validateNumberInRange(input, MIN_DATE, MAX_DATE, ERROR_INVALID_DATE);
    }

    public Map<Menu, Integer> getMenu() {
        return menu;
    }

    public void setMenu(String input) {
        validateMenuInput(input);
        Map<Menu, Integer> result = new EnumMap<>(Menu.class);
        for (String menuAndAmount : input.split(INPUT_SEPARATOR)) {
            String[] parts = menuAndAmount.split(MENU_SEPARATOR);
            Menu menu = Menu.findMenu(parts[0]);
            Integer amount = Integer.parseInt(parts[1]);
            result.put(menu, amount);
        }
        validateMenuEventConditions(result);
        this.menu = result;
    }

    private void validateMenuInput(String input) {
        validateIsNotBlank(input, ERROR_INVALID_MENU);
        List<String> menus = new ArrayList<>();
        for (String menuAmountPair : input.split(INPUT_SEPARATOR)) {
            validateCorrectFormat(menuAmountPair, RegexExpression.WORD_DASH_NUMBER, ERROR_INVALID_MENU);
            String[] menuAndAmount = menuAmountPair.split(MENU_SEPARATOR);
            String menu = menuAndAmount[0];
            menus.add(menu);
            String amount = menuAndAmount[1];
            validateCorrectFormat(amount, RegexExpression.NUMBER_GREATER_THAN_1, ERROR_INVALID_MENU);
        }
        validateIsElementUnique(menus, ERROR_INVALID_MENU);
    }

    private void validateMenuEventConditions(Map<Menu, Integer> menus) {
        validateOnlyBeverage(menus);
        validateOverMaxOrder(menus);
    }

    private void validateOnlyBeverage(Map<Menu, Integer> menus) {
        Set<Menu> menuSet = menus.keySet();
        long beverageCount = menuSet.stream().filter((menu) -> menu.getType().equals(MenuType.BEVERAGES)).count();
        if (menuSet.size() == beverageCount) {
            throw new IllegalArgumentException(ERROR_ONLY_BEVERAGE);
        }
    }

    private void validateOverMaxOrder(Map<Menu, Integer> menus) {
        int total = menus.values().stream().mapToInt(Integer::intValue).sum();
        if (total > MAX_ORDER_AMOUNT) {
            throw new IllegalArgumentException(ERROR_OVER_MAX_ORDER);
        }
    }
}
