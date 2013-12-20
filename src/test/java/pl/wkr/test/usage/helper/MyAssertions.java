package pl.wkr.test.usage.helper;

import org.assertj.core.api.Assertions;
import pl.wkr.fluentrule.usage.NotEnoughMoney;

public class MyAssertions extends Assertions {

    public static NotEnoughMoneyAssert assertThat(NotEnoughMoney actual) {
        return new NotEnoughMoneyAssert(actual);
    }
}
