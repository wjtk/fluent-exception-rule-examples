package pl.wkr.test.usage.helper;

import org.assertj.core.api.Assertions;
import pl.wkr.fluentrule.usage.NotEnoughMoney;

import java.sql.SQLException;

public class MyAssertions extends Assertions {

    public static NotEnoughMoneyAssert assertThat(NotEnoughMoney actual) {
        return new NotEnoughMoneyAssert(actual);
    }

    public static SQLExceptionAssert assertThat(SQLException actual) {
        return new SQLExceptionAssert(actual);
    }
}
