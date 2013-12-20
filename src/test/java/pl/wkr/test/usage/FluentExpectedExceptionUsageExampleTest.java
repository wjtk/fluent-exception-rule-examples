package pl.wkr.test.usage;

import org.junit.Rule;
import org.junit.Test;
import pl.wkr.fluentrule.api.FluentExpectedException;
import pl.wkr.fluentrule.usage.CoffeeMachine;
import pl.wkr.fluentrule.usage.NotEnoughMoney;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;


public class FluentExpectedExceptionUsageExampleTest {

    @Rule
    public FluentExpectedException thrown = FluentExpectedException.none();

    private CoffeeMachine coffeeMachine = new CoffeeMachine(4);

    //bloat code, explanation  -------------

    @Test
    public void assertj_traditional_way_notEnoughMoney(){
        coffeeMachine.insertCoin(3);
        try {
            coffeeMachine.getCoffee();

            failBecauseExceptionWasNotThrown(NotEnoughMoney.class);
        } catch(Exception e) {
            assertThat(e).isInstanceOf(NotEnoughMoney.class).hasMessage("Not enough money, insert 1$ more");
        }
    }


    @Test
    public void fluent_rule_notEnoughMoney() throws Exception {
        coffeeMachine.insertCoin(3);

        thrown.expect(NotEnoughMoney.class).hasMessage("Not enough money, insert 1$ more");
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
    public void fluent_rule_expected_class() {
        thrown.expect(IllegalArgumentException.class);
        //shortcut for:
        thrown.expect().isInstanceOf(IllegalArgumentException.class);

        throw new IllegalArgumentException();
    }


    @Test
    public void fluent_rule_any_class_of() {
        thrown.expectAny(IllegalStateException.class, IllegalArgumentException.class);
        //shortcut for
        thrown.expect().isInstanceOfAny(IllegalStateException.class, IllegalArgumentException.class);

        throw new IllegalArgumentException();
    }


    @Test
    public void fluent_rule_message_of_any_exception() {
        thrown.expect(RuntimeException.class).hasMessageContaining("sql");
        throw new RuntimeException("sql error");
    }


    @Test
    public void fluent_rule_expect_cause() {
        thrown.expectCause().isInstanceOf(IllegalAccessException.class).hasMessageContaining("to low");

        throw new RuntimeException(new IllegalAccessException("to low memory"));
    }

    @Test
    public void fluent_rule_expect_root_cause() {
        thrown.expectCause().isInstanceOf(IllegalStateException.class);
        thrown.expectRootCause().isInstanceOf(IllegalArgumentException.class);

        throw new RuntimeException(new IllegalStateException(new IllegalArgumentException()));

    }

    //rich usages -------------------

    @Test
    public void fluent_rule_many_asserts_1() throws Exception {
        Exception exc = new RuntimeException("this throwable",
                            new IllegalStateException("cause",
                                new IllegalArgumentException("root")));

        thrown.expectAny(RuntimeException.class, SQLException.class)
                .as("exception")
                .hasMessageContaining("this")
                .hasRootCauseInstanceOf(IllegalArgumentException.class)
                .hasCauseInstanceOf(IllegalStateException.class)
                .isEqualTo(exc)
                .isIn(new Exception(), exc )
                .isSameAs(exc);

        thrown.expectCause().as("exception cause").hasMessage("cause");
        thrown.expectRootCause().as("exception rootCause").hasMessage("root").hasNoCause();

        throw exc;
    }



}
