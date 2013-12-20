package pl.wkr.test.usage;

import org.junit.Rule;
import org.junit.Test;
import pl.wkr.fluentrule.api.CheckExpectedException;
import pl.wkr.fluentrule.api.SafeCheck;
import pl.wkr.fluentrule.usage.CoffeeMachine;
import pl.wkr.fluentrule.usage.NotEnoughMoney;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckExpectedExceptionUsageExampleTest {

    @Rule
    public CheckExpectedException thrown = CheckExpectedException.none();

    private CoffeeMachine coffeeMachine = new CoffeeMachine(4);


    @Test
    public void test_exception_check_state() throws NotEnoughMoney {
        coffeeMachine.insertCoin(1);

        thrown.check(new SafeCheck<NotEnoughMoney>() {
            protected void safeCheck(NotEnoughMoney notEnoughMoney) {
                assertThat(coffeeMachine.getInsertedMoney()).isEqualTo(1);
            }
        });

        coffeeMachine.getCoffee();
    }


}
