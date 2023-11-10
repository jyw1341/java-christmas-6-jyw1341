package christmas.event;

import christmas.enums.Badge;
import christmas.enums.EventType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Benefit {

    private final Map<EventType, Integer> benefits;

    public Benefit(Map<EventType, Integer> benefits) {
        this.benefits = Collections.unmodifiableMap(new EnumMap<>(benefits));
    }

    public Integer getDiscountAmount() {
        Set<Map.Entry<EventType, Integer>> collect = benefits.entrySet().stream()
                .filter((benefit) -> !benefit.getKey().equals(EventType.PRESENT))
                .collect(Collectors.toSet());
        return collect.stream().mapToInt(Map.Entry::getValue).sum();

    }

    public Integer getTotalBenefit() {
        return benefits.values().stream().mapToInt(Integer::intValue).sum();
    }

    public boolean hasPresent() {
        return benefits.containsKey(EventType.PRESENT);
    }

    public Badge getBadge() {
        int totalBenefit = getTotalBenefit();
        if (totalBenefit >= Badge.SANTA.getLimit()) {
            return Badge.SANTA;
        }
        if (totalBenefit >= Badge.TREE.getLimit()) {
            return Badge.TREE;
        }
        if (totalBenefit >= Badge.STAR.getLimit()) {
            return Badge.STAR;
        }
        return Badge.NONE;
    }

    public Map<EventType, Integer> getBenefits() {
        return benefits;
    }
}
