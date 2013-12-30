package pl.wkr.test.usage;

import org.junit.Rule;
import org.junit.Test;
import pl.wkr.fluentrule.api.Check;
import pl.wkr.fluentrule.api.CheckExpectedException;
import pl.wkr.fluentrule.api.SafeCheck;
import pl.wkr.fluentrule.usage.CoffeeMachine;
import pl.wkr.fluentrule.usage.NotEnoughMoney;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckExpectedExceptionUsageExampleTest {

    @Rule
    public CheckExpectedException thrown = CheckExpectedException.none();

    final CoffeeMachine coffeeMachine = new CoffeeMachine(4);

    @Test
    public void test_state_of_objects_raw_check() throws NotEnoughMoney {
        coffeeMachine.insertCoin(2);

        thrown.check(new Check() {
            @Override
            public void check(Throwable throwable) {
                assertThat(coffeeMachine.getInsertedMoney()).isEqualTo(2);  //assert state
            }
        });

        coffeeMachine.getCoffee();
    }

    @Test
    public void test_state_with_type_safe_check() throws NotEnoughMoney {
        coffeeMachine.insertCoin(1);

        thrown.check(new SafeCheck<NotEnoughMoney>() {
            protected void safeCheck(NotEnoughMoney notEnoughMoney) {
                assertThat(notEnoughMoney.getLackingMoney()).isEqualTo(3);  //assert custom exception
            }
        });

        coffeeMachine.getCoffee();
    }

}
