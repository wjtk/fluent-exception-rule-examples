package pl.wkr.test.usage.helper;

import org.assertj.core.api.AbstractThrowableAssert;
import pl.wkr.fluentrule.usage.NotEnoughMoney;

import static org.assertj.core.api.Assertions.assertThat;

public class NotEnoughMoneyAssert extends AbstractThrowableAssert<NotEnoughMoneyAssert, NotEnoughMoney> {

    protected NotEnoughMoneyAssert(NotEnoughMoney actual) {
        super(actual, NotEnoughMoneyAssert.class);
    }

    public NotEnoughMoneyAssert hasLackingMoney(int expected) {
        int actualLacks = actual.getLackingMoney();
        assertThat(actualLacks).overridingErrorMessage(
                "\nExpected NotEnoughMoney.getLackingMoney : <%d> but was: <%d>", expected, actualLacks).
                isEqualTo(expected);
        return myself;
    }
}
