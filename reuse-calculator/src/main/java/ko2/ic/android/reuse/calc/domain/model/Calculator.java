package ko2.ic.android.reuse.calc.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ko2.ic.android.reuse.calc.domain.DisplayListener;
import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Number;
import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Operation;

import com.google.inject.Inject;

public class Calculator implements CalculateEvent {

    @Inject
    private NumberState numState;

    @Inject
    private OperationState opState;

    @Inject
    private ResultState resultState;

    private final List<String> inputList = new ArrayList<String>();

    /** 電卓の状態 */
    private State state;

    private DisplayListener listener;

    /**
     * initを実行する。<br>
     */
    public void init(DisplayListener listener) {
        this.listener = listener;
        this.state = resultState;
        inputList.add("0");
    }

    /**
     * onButtonNumberを実行する。<br>
     * @param str
     */
    public void onDropNumber(String str) {
        state.onDropNumber(inputList, str);
        state = numState;
        listener.display(inputList.get(inputList.size() - 1));
    }

    public void onButtonNumber(Number num) {
        state.onInputNumber(inputList, num);
        state = numState;
        listener.display(inputList.get(inputList.size() - 1));
    }

    public void onButtonOp(Operation op) {
        state.onInputOperation(this, inputList, op);
        state = opState;
    }

    public void onButtonClear() {
    }

    public void onButtonAllClear() {
        inputList.clear();
        inputList.add("0");
        state = resultState;
        listener.display(inputList.get(inputList.size() - 1));
    }

    public List<String> onButtonEqual() {
        List<String> list = state.onInputEqual(this, inputList);
        state = resultState;
        return list;
    }

    /**
     * changeSignを実行する。<br>
     */
    public void changeSign() {
        state.onInputSign(inputList);
        state = numState;
        listener.display(inputList.get(inputList.size() - 1));
    }

    /**
     * onButtonCommaを実行する。<br>
     */
    public void onButtonComma() {
        state.onInputComma(inputList);
        state = numState;
        listener.display(inputList.get(inputList.size() - 1));
    }

    /**
     * onDropPercentを実行する。<br>
     * @param str
     */
    public void onDropPercent(String str) {
        state.onDropPercent(inputList, str);
        state = numState;
        listener.display(inputList.get(inputList.size() - 1));
    }

    /**
     * onButtonPercentを実行する。<br>
     */
    public void onButtonPercent() {
        state.onInputPercent(inputList);
        state = numState;
        String str = inputList.get(inputList.size() - 1).replace("%", "");
        BigDecimal value = new BigDecimal(str).multiply(new BigDecimal(0.01).setScale(2, RoundingMode.DOWN));
        inputList.set(inputList.size() - 1, str + "%");
        listener.display(value.toPlainString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String calculate(List<String> list) {
        Queue<String> numQueue = new LinkedList<String>();
        List<String> opList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                if (list.get(i).contains("%")) {
                    String str = list.get(i).replace("%", "");
                    BigDecimal value = new BigDecimal(str).multiply(new BigDecimal(0.01).setScale(2, RoundingMode.DOWN));
                    numQueue.add(value.toPlainString());
                } else {
                    numQueue.add(list.get(i));
                }
            } else {
                opList.add(list.get(i));
            }
        }

        BigDecimal result = new BigDecimal(numQueue.poll());

        for (String op : opList) {
            if (op.equals(Operation.PLUS.getValue())) {
                result = result.add(new BigDecimal(numQueue.poll()));
            } else if (op.equals(Operation.MINUS.getValue())) {
                result = result.subtract(new BigDecimal(numQueue.poll()));
            } else if (op.equals(Operation.TIMES.getValue())) {
                result = result.multiply(new BigDecimal(numQueue.poll()));
            } else if (op.equals(Operation.DIVIDE.getValue())) {
                try {
                    result = result.divide(new BigDecimal(numQueue.poll()), 11, RoundingMode.DOWN);
                } catch (ArithmeticException e) {
                    result = BigDecimal.ZERO;
                    list.clear();
                }

            }
        }

        String strResult = String.valueOf(result);
        StringBuilder sb = new StringBuilder(strResult);
        if (strResult.contains(".")) {
            StringBuilder sbOut = new StringBuilder();
            boolean commaFlag = false;
            for (int i = sb.length() - 1; i >= 0; i--) {
                if (commaFlag) {
                    sbOut.insert(0, sb.charAt(i));
                } else {
                    if (sb.charAt(i) == '.') {
                        commaFlag = true;
                    } else if (sb.charAt(i) != '0') {
                        commaFlag = true;
                        sbOut.insert(0, sb.charAt(i));
                    }
                }
            }
            sb = sbOut;
        }

        listener.display(sb.toString());
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCalcInfoWhenResultState(String first, String op, String result) {
        resultState.setCalcInfoWhenInputEqual(first, op, result);

    }

}
