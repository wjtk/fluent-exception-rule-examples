package pl.wkr.test.usage;

import org.junit.Rule;
import org.junit.Test;

import java.sql.SQLException;

public class DryFluentExpectedExceptionTest {

    @Rule
    public DryFluentExpectedException thrown = new DryFluentExpectedException();

    @Test
    public void should_catch_lost_connection_exception() {
        thrown.expectLostConnection().hasMessageContaining("#144");

        //sample connection lost simulation
        throw new IllegalStateException(new IllegalStateException(new SQLException("connection lost, #144")));
    }
}



