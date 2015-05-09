package ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums;

public enum Number {

    ZERO("0"), DOUBLE_ZERO("00"),

    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9");

    private String value;

    private Number(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
