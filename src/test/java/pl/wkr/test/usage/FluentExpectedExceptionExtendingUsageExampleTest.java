package pl.wkr.test.usage;

import org.junit.Rule;
import org.junit.Test;
import pl.wkr.fluentrule.api.FluentExpectedException;
import pl.wkr.fluentrule.usage.CoffeeMachine;
import pl.wkr.test.usage.helper.NotEnoughMoneyAssert;

public class FluentExpectedExceptionExtendingUsageExampleTest {

    @Rule
    public FluentExpectedException thrown = FluentExpectedException.none();

    private CoffeeMachine coffeeMachine = new CoffeeMachine(4);


    @Test
    public void fluentrule_with_custom_assertion() throws Exception {
        coffeeMachine.insertCoin(1);

        thrown.assertWith(NotEnoughMoneyAssert.class).hasLackingMoney(3);
        coffeeMachine.getCoffee();
    }

}
