package pl.wkr.test.usage;

import org.junit.Rule;
import org.junit.Test;
import pl.wkr.fluentrule.api.FluentExpectedException;
import pl.wkr.fluentrule.usage.CoffeeMachine;
import pl.wkr.fluentrule.usage.NotEnoughMoney;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;


public class FluentExpectedExceptionUsageTest {

    @Rule
    public FluentExpectedException thrown = FluentExpectedException.none();

    private CoffeeMachine coffeeMachine = new CoffeeMachine(4);

    //bloat code, easy to forget failBecauseExceptionWasNotThrown(...)
    @Test
    public void assertj_traditional_way(){
        coffeeMachine.insertCoin(3);
        try {
            coffeeMachine.getCoffee();

            failBecauseExceptionWasNotThrown(NotEnoughMoney.class);
        } catch(Exception e) {
            assertThat(e).isInstanceOf(NotEnoughMoney.class).hasMessage("Not enough money, insert 1$ more");
        }
    }


    //more readable, no bloat code
    @Test
    public void fluent_rule_way() throws Exception {
        coffeeMachine.insertCoin(3);

        thrown.expect(NotEnoughMoney.class).hasMessage("Not enough money, insert 1$ more").hasNoCause();
        coffeeMachine.getCoffee();
    }


    @Test
    public void assertj_constructor() {
        try {
            new CoffeeMachine(-2);
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);

        } catch(Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class).hasMessage("Coffee price should be grater than zero.");
        }
    }

    @Test
    public void fluent_rule_constructor() {
        thrown.expect(IllegalArgumentException.class).hasMessage("Coffee price should be grater than zero.");
        new CoffeeMachine(-2);
    }


    //standard usages --------------------

    @Test
    public void fluent_rule_any_exception() {
        thrown.expect().hasMessage("exc").hasNoCause();

        throw new IllegalStateException("exc");
    }

    @Test
    public void fluent_rule_expected_class() {
        thrown.expect(IllegalArgumentException.class);
        //shortcut for:
        thrown.expect().isInstanceOf(IllegalArgumentException.class);

        throw new IllegalArgumentException();
    }


    @Test
    public void fluent_rule_expect_cause() {
        thrown.expectCause().hasMessageContaining("to low");
        thrown.expectCause(IllegalAccessException.class).hasMessageContaining("memory");

        throw new RuntimeException(new IllegalAccessException("to low memory"));
    }

    @Test
    public void fluent_rule_expect_root_cause() {
        thrown.expectRootCause().hasMessageContaining("is null");
        thrown.expectRootCause(IllegalArgumentException.class).hasMessageContaining("argument");

        throw new RuntimeException(new IllegalStateException(new IllegalArgumentException("argument is null")));

    }

    //rich usages -------------------

    @Test
    public void fluent_rule_many_asserts() throws Exception {
        thrown.expect(RuntimeException.class)
                .as("exception")
                .hasMessageContaining("this")
                .hasRootCauseInstanceOf(IllegalArgumentException.class)
                .hasCauseInstanceOf(IllegalStateException.class);

        thrown.expectCause().as("exception cause").hasMessage("cause");
        thrown.expectRootCause().as("exception rootCause").hasMessage("root").hasNoCause();

        throw new RuntimeException("this throwable",
                new IllegalStateException("cause",
                        new IllegalArgumentException("root")));
    }

}
