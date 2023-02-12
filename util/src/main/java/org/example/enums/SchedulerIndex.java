package org.example.enums;

public enum SchedulerIndex {
    AREA(1),
    NAME(2),
    ADDRESS(3),
    BRAND(4),
    SELF(5),
    PREMIUM_GASOLINE(6),
    LPG(6),
    GASOLINE(7),
    DIESEL(8);
    private int index;

    SchedulerIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
