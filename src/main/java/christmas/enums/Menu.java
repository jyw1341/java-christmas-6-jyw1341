package christmas.enums;

public enum Menu {

    MUSHROOM_SOUP(MenuType.APPETIZERS, "양송이수프", 6_000),
    TAPAS(MenuType.APPETIZERS, "타파스", 5_500),
    CAESAR_SALAD(MenuType.APPETIZERS, "시저샐러드", 8_000),
    T_BONE_STEAK(MenuType.MAIN_COURSE, "티본스테이크", 55_000),
    BBQ_RIBS(MenuType.MAIN_COURSE, "바비큐립", 54_000),
    SEAFOOD_PASTA(MenuType.MAIN_COURSE, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MenuType.MAIN_COURSE, "크리스마스파스타", 25_000),
    CHOCOLATE_CAKE(MenuType.DESSERTS, "초코케이크", 15_000),
    ICE_CREAM(MenuType.DESSERTS, "아이스크림", 5_000),
    ZERO_COKE(MenuType.BEVERAGES, "제로콜라", 3_000),
    RED_WINE(MenuType.BEVERAGES, "레드와인", 60_000),
    CHAMPAGNE(MenuType.BEVERAGES, "샴페인", 25_000);

    private final MenuType type;
    private final String name;
    private final Integer price;

    Menu(MenuType type, String name, Integer price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public MenuType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
