package ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums;

public enum Operation {

    PLUS("+"), MINUS("-"), TIMES("×"), DIVIDE("÷");

    private String value;

    private Operation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
