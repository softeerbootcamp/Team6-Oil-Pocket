package org.example.enums;

public enum AttributeIndex {
    AREA(1),
    NAME(2),
    ADDRESS(3),
    DATE (4),
    BRAND(5),
    SELF(6),
    PREMIUM_GASOLINE(7),
    LPG(7),
    GASOLINE(8),
    DIESEL(9);
    private int index;

    AttributeIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
