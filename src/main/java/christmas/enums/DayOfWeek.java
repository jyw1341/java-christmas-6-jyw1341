package christmas.enums;

public enum DayOfWeek {
    SUNDAY(1, "일요일"),
    MONDAY(2, "월요일"),
    TUESDAY(3, "화요일"),
    WEDNESDAY(4, "수요일"),
    THURSDAY(5, "목요일"),
    FRIDAY(6, "금요일"),
    SATURDAY(7, "토요일");

    private final int count;
    private final String koreanName;

    DayOfWeek(int count, String koreanName) {
        this.koreanName = koreanName;
        this.count = count;
    }

    public static DayOfWeek getDay(int count) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getCount() == (count)) {
                return day;
            }
        }
        return null;
    }

    public int getCount() {
        return count;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
