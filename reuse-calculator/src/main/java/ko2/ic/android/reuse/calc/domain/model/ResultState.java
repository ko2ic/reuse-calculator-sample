package ko2.ic.android.reuse.calc.domain.model;

import java.util.ArrayList;
import java.util.List;

import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Number;
import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Operation;

import com.google.inject.Singleton;

@Singleton
public class ResultState extends NumberState {

    private String first;

    private String mid;

    private String result;

    public void setCalcInfoWhenInputEqual(String first, String op, String result) {
        this.first = first;
        this.mid = op;
        this.result = result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDropNumber(List<String> list, String str) {
        list.clear();
        list.add(str);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInputNumber(List<String> list, Number num) {
        list.clear();
        list.add(num.getValue());
    }

    @Override
    public void onInputOperation(CalculateEvent calcEvent, List<String> list, Operation op) {
        String last = list.get(list.size() - 1);
        list.clear();
        list.add(last);
        list.add(op.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> onInputEqual(CalculateEvent calcEvent, List<String> list) {

        String last = result;
        if (list.size() == 1 && mid != null) {
            list.clear();
            list.add(result);
            list.add(mid);
            list.add(first);
        } else if (list.size() == 1 && mid == null) {
            return new ArrayList<String>();
        } else {
            last = list.get(0);
            mid = list.get(1);
            first = list.get(2);
        }

        String result = calcEvent.calculate(list);
        list.clear();
        list.add(result);
        list.add(mid);
        list.add(first);

        List<String> resultList = new ArrayList<String>();
        resultList.add(last);
        resultList.add(mid);
        resultList.add(first);
        resultList.add("=");
        resultList.add(result);
        this.result = result;
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDropPercent(List<String> inputList, String str) {
        inputList.clear();
        if (str.contains("%%")) {
            str = str.replaceFirst("%", "");
        }
        inputList.add(str);
    }
}
