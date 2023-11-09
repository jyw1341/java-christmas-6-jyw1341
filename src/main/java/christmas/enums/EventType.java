package christmas.enums;

public enum EventType {
    D_DAY(1_200, "크리스마스 디데이 할인"),
    WEEKDAY(2_023, "평일 할인"),
    WEEKEND(2_023, "주말 할인"),
    SPECIAL(1_000, "특별 할인"),
    PRESENT(25_000, "증정 이벤트");

    private final Integer benefit;
    private final String name;

    EventType(Integer benefit, String name) {
        this.benefit = benefit;
        this.name = name;
    }

    public Integer getBenefit() {
        return benefit;
    }

    public String getName() {
        return name;
    }
}
