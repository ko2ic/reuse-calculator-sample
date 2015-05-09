package ko2.ic.android.reuse.calc.domain.model;

import java.util.List;

/**
 * CalculateEvent.<br>
 * @author $Author$
 * @version $Revision$
 */
public interface CalculateEvent {

    String calculate(List<String> list);

    void setCalcInfoWhenResultState(String first, String op, String result);
}
