package ko2.ic.android.reuse.calc.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Number;
import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Operation;

import com.google.inject.Singleton;

@Singleton
public class NumberState implements State {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDropNumber(List<String> list, String str) {
        String last = list.get(list.size() - 1);
        last += str;
        list.set(list.size() - 1, new BigDecimal(last).toPlainString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputNumber(List<String> list, Number num) {
        String last = list.get(list.size() - 1);
        if (last.contains("%")) {
            String str = list.get(list.size() - 1).replace("%", "");
            BigDecimal value = new BigDecimal(str).multiply(new BigDecimal(0.01).setScale(2, RoundingMode.DOWN));
            last = value.toPlainString();
        }
        last += num.getValue();
        list.set(list.size() - 1, new BigDecimal(last).toPlainString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputOperation(CalculateEvent calcEvent, List<String> list, Operation op) {
        calcEvent.calculate(list);
        list.add(op.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> onInputEqual(CalculateEvent calcEvent, List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String string : list) {
            resultList.add(string);
        }
        String result = calcEvent.calculate(list);
        if (list.size() >= 3) {
            calcEvent.setCalcInfoWhenResultState(list.get(list.size() - 1), list.get(list.size() - 2), result);
        }
        resultList.add("=");
        resultList.add(result);
        list.clear();
        list.add(result);

        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputSign(List<String> list) {
        String last = list.get(list.size() - 1);
        if (last.charAt(0) == '-') {
            last = last.replace("-", "");
        } else {
            last = "-" + last;
        }
        list.set(list.size() - 1, last);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputComma(List<String> list) {
        String last = list.get(list.size() - 1);
        if (last.contains(".")) {
            return;
        }

        last += ".";
        list.set(list.size() - 1, last);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputPercent(List<String> list) {
        String last = list.get(list.size() - 1);
        last += "%";
        list.set(list.size() - 1, last);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDropPercent(List<String> inputList, String str) {

    }

}
