package christmas.enums;

public enum MenuType {
    APPETIZERS("애피타이저"),
    MAIN_COURSE("메인"),
    DESSERTS("디저트"),
    BEVERAGES("음료");

    private final String name;

    MenuType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
