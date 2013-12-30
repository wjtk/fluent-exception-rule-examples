package pl.wkr.test.usage;

import org.junit.Rule;
import org.junit.Test;
import pl.wkr.fluentrule.api.FluentExpectedException;
import pl.wkr.fluentrule.usage.CoffeeMachine;
import pl.wkr.test.usage.helper.NotEnoughMoneyAssert;
import pl.wkr.test.usage.helper.SQLExceptionAssert;

import java.sql.SQLException;

public class FluentExpectedExceptionExtendingUsageExampleTest {

    @Rule
    public FluentExpectedException thrown = FluentExpectedException.none();

    private CoffeeMachine coffeeMachine = new CoffeeMachine(4);


    @Test
    public void fluentrule_with_custom_assertion() throws Exception {
        coffeeMachine.insertCoin(1);

        thrown.expectWith(NotEnoughMoneyAssert.class).hasLackingMoney(3);
        coffeeMachine.getCoffee();
    }

    @Test
    public void fluentrule_with_custom_assertion_for_cause() throws Exception {
        thrown.expectCauseWith(SQLExceptionAssert.class).hasMessageContaining("foreign key").hasErrorCode(11);
        throw  new Exception(new SQLException("foreign key", "open", 11));
    }

    @Test
    public void fluentrule_with_custom_assertion_for_root_cause() throws Exception {
        thrown.expectRootCauseWith(SQLExceptionAssert.class).hasErrorCode(12).hasMessageContaining("primary key");
        throw  new Exception( new Exception(new SQLException("primary key", "cursor", 12)));
    }







}
