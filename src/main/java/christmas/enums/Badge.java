package christmas.enums;

public enum Badge {
    SANTA(20_000, "산타"),
    TREE(10_000, "트리"),
    STAR(5_000, "별"),

    NONE(0, "없음");

    private final int limit;
    private final String name;

    Badge(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }
}
