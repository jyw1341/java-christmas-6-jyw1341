package christmas.order;

import christmas.enums.Menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

import static christmas.Validator.*;

public class OrderRequest {

    public static final String INPUT_SEPARATOR = ",";
    public static final String MENU_SEPARATOR = "-";
    public static final String ERROR_INVALID_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String ERROR_INVALID_MENU = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final int MIN_DATE = 1;
    public static final int MAX_DATE = 31;
    private Date date;
    private EnumMap<Menu, Integer> menu;

    public Date getDate() {
        return date;
    }

    public void setDate(String input) throws ParseException {
        validateDate(input);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = String.format("2023-12-%s", input);
        this.date = dateFormat.parse(dateString);
    }

    private void validateDate(String input) {
        validateIsNotBlank(input, ERROR_INVALID_DATE);
        validateIsNumber(input, ERROR_INVALID_DATE);
        validateNumberInRange(input, MIN_DATE, MAX_DATE, ERROR_INVALID_DATE);
    }

    public EnumMap<Menu, Integer> getMenu() {
        return menu;
    }

    public void setMenu(String input) {
        validateMenu(input);
        EnumMap<Menu, Integer> result = new EnumMap<>(Menu.class);
        for (String menuAndAmount : input.split(INPUT_SEPARATOR)) {
            String[] parts = menuAndAmount.split(MENU_SEPARATOR);
            Menu menu = Menu.findMenu(parts[0]);
            Integer amount = Integer.parseInt(parts[1]);
            result.put(menu, amount);
        }
        this.menu = result;
    }

    private void validateMenu(String input) {
        validateIsNotBlank(input, ERROR_INVALID_MENU);
        List<String> menus = new ArrayList<>();
        for (String menuAndAmount : input.split(INPUT_SEPARATOR)) {
            validateCorrectFormat(menuAndAmount, "^[가-힣a-zA-Z]+-\\d+$", ERROR_INVALID_MENU);
            String[] parts = menuAndAmount.split(MENU_SEPARATOR);
            String menu = parts[0];
            menus.add(menu);
            String amount = parts[1];
            validateCorrectFormat(amount, "^[1-9]\\d*$", ERROR_INVALID_MENU);
            validateExistMenu(menu);
        }
        validateIsElementUnique(menus, ERROR_INVALID_MENU);
    }

    private void validateExistMenu(String name) {
        if (Menu.findMenu(name) == null) {
            throw new IllegalArgumentException(ERROR_INVALID_MENU);
        }
    }
}
