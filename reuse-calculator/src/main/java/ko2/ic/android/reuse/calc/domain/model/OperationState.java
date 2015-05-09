package ko2.ic.android.reuse.calc.domain.model;

import java.util.ArrayList;
import java.util.List;

import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Number;
import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Operation;

import com.google.inject.Singleton;

@Singleton
public class OperationState implements State {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDropNumber(List<String> list, String str) {
        list.add(str);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputNumber(List<String> list, Number num) {
        list.add(num.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputSign(List<String> list) {
        String last = list.get(list.size() - 2);
        if (last.charAt(0) == '-') {
            last = last.replace("-", "");
        } else {
            last = "-" + last;
        }
        list.add(last);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputOperation(CalculateEvent calcEvent, List<String> list, Operation op) {
        list.set(list.size() - 1, op.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> onInputEqual(CalculateEvent calcEvent, List<String> list) {
        String last = list.get(list.size() - 1);
        if (last.equals("+") || last.equals("-") || last.equals("*") || last.equals("/")) {
            String preLast = list.get(list.size() - 2);
            List<String> resultList = new ArrayList<String>();
            resultList.add(preLast);
            resultList.add(last);
            resultList.add(preLast);
            String result = calcEvent.calculate(resultList);
            resultList.add("=");
            resultList.add(result);
            list.clear();
            list.add(result);
            list.add(last);
            list.add(preLast);
            return resultList;
        }

        list.add(last);
        calcEvent.calculate(list);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputComma(List<String> list) {
        list.add("0.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputPercent(List<String> inputList) {
        inputList.add("0.01");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDropPercent(List<String> inputList, String str) {
    }
}
