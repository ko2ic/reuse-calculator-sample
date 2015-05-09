package ko2.ic.android.reuse.calc.domain.model;

import java.util.List;

import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Number;
import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Operation;

public interface State {

    public abstract void onInputNumber(List<String> list, Number num);

    public abstract void onInputSign(List<String> list);

    public abstract void onInputOperation(CalculateEvent calcEvent, List<String> list, Operation op);

    public abstract List<String> onInputEqual(CalculateEvent calcEvent, List<String> list);

    public abstract void onInputComma(List<String> list);

    public abstract void onInputPercent(List<String> inputList);

    public abstract void onDropNumber(List<String> inputList, String str);

    public abstract void onDropPercent(List<String> inputList, String str);

}
