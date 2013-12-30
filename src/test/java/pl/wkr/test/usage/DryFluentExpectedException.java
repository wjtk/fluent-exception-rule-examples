package pl.wkr.test.usage;

import org.assertj.core.api.ThrowableAssert;
import org.junit.Rule;
import org.junit.Test;
import pl.wkr.fluentrule.api.FluentExpectedException;

import java.sql.SQLException;

public class DryFluentExpectedException {

    @Rule
    public MyFluentExpectedException thrown = new MyFluentExpectedException();

    @Test
    public void should_catch_lost_connection_exception() {
        thrown.expectLostConnection().hasMessageContaining("#144");

        //sample connection lost simulation
        throw new IllegalStateException(new IllegalStateException(new SQLException("connection lost, #144")));
    }
}



class MyFluentExpectedException extends FluentExpectedException {

    public ThrowableAssert expectLostConnection() {
        expect().as("expectLostConnection")
                .isInstanceOf(IllegalStateException.class)
                .hasRootCauseInstanceOf(SQLException.class);

        return expectRootCause().as("expectLostConnection-cause").hasMessageContaining("connection lost");
    }
}
