package christmas.event;

import christmas.enums.Badge;
import christmas.enums.EventType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BenefitTest {

    @Test
    @DisplayName("할인된 금액을 구한다.")
    void getDiscountAmount1() {
        Map<EventType, Integer> benefits = new EnumMap<>(EventType.class);
        benefits.put(EventType.WEEKEND, EventType.WEEKEND.getBenefit());
        Benefit benefit = new Benefit(benefits);
        int expected = EventType.WEEKEND.getBenefit();

        assertEquals(expected, benefit.getDiscountAmount());
    }

    @Test
    @DisplayName("할인된 금액을 구한다. 증정이벤트는 할인된 금액으로 포함하지 않는다")
    void getDiscountAmount2() {
        Map<EventType, Integer> benefits = new EnumMap<>(EventType.class);
        benefits.put(EventType.WEEKEND, EventType.WEEKEND.getBenefit());
        benefits.put(EventType.PRESENT, EventType.PRESENT.getBenefit());
        Benefit benefit = new Benefit(benefits);
        int expected = EventType.WEEKEND.getBenefit();

        assertEquals(expected, benefit.getDiscountAmount());
    }

    @Test
    @DisplayName("총 혜택 금액을 구한다. 총 혜택금액은 모든 할인과 샴페인 가격을 합친 금액이다")
    void getTotalBenefit() {
        Map<EventType, Integer> benefits = new EnumMap<>(EventType.class);
        benefits.put(EventType.WEEKEND, EventType.WEEKEND.getBenefit());
        benefits.put(EventType.PRESENT, EventType.PRESENT.getBenefit());
        Benefit benefit = new Benefit(benefits);
        int expected = EventType.WEEKEND.getBenefit() + EventType.PRESENT.getBenefit();

        assertEquals(expected, benefit.getTotalBenefit());
    }

    @Test
    @DisplayName("혜택에 증정 혜택이 포함되어 있으면 true")
    void hasPresent1() {
        Map<EventType, Integer> benefits = new EnumMap<>(EventType.class);
        benefits.put(EventType.WEEKEND, EventType.WEEKEND.getBenefit());
        benefits.put(EventType.PRESENT, EventType.PRESENT.getBenefit());
        Benefit benefit = new Benefit(benefits);

        assertTrue(benefit.hasPresent());
    }

    @Test
    @DisplayName("혜택에 증정 혜택이 포함되어 있지 않으면 false")
    void hasPresent2() {
        Map<EventType, Integer> benefits = new EnumMap<>(EventType.class);
        benefits.put(EventType.WEEKEND, EventType.WEEKEND.getBenefit());
        Benefit benefit = new Benefit(benefits);

        assertFalse(benefit.hasPresent());
    }

    @Test
    @DisplayName("총 혜택 금액이 2만원 이상이면 산타 배지 증정")
    void getBadge1() {
        Map<EventType, Integer> benefits = new EnumMap<>(EventType.class);
        benefits.put(EventType.WEEKEND, EventType.WEEKEND.getBenefit());
        benefits.put(EventType.PRESENT, EventType.PRESENT.getBenefit());
        Benefit benefit = new Benefit(benefits);

        assertEquals(Badge.SANTA, benefit.getBadge());
    }

    @Test
    @DisplayName("총 혜택 금액이 1만원 이상이면 트리 배지 증정")
    void getBadge2() {
        Map<EventType, Integer> benefits = new EnumMap<>(EventType.class);
        benefits.put(EventType.WEEKEND, EventType.WEEKEND.getBenefit() * 5);
        Benefit benefit = new Benefit(benefits);

        assertEquals(Badge.TREE, benefit.getBadge());
    }

    @Test
    @DisplayName("총 혜택 금액이 5천원 이상이면 별 배지 증정")
    void getBadge3() {
        Map<EventType, Integer> benefits = new EnumMap<>(EventType.class);
        benefits.put(EventType.WEEKEND, EventType.WEEKEND.getBenefit() * 3);
        Benefit benefit = new Benefit(benefits);

        assertEquals(Badge.STAR, benefit.getBadge());
    }

    @Test
    @DisplayName("총 혜택 금액이 5천원 미만이면 배지 없음")
    void getBadge4() {
        Map<EventType, Integer> benefits = new EnumMap<>(EventType.class);
        benefits.put(EventType.WEEKEND, EventType.WEEKEND.getBenefit() * 2);
        Benefit benefit = new Benefit(benefits);

        assertEquals(Badge.NONE, benefit.getBadge());
    }
}