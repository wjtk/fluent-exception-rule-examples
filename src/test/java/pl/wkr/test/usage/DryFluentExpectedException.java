package pl.wkr.test.usage;

import org.assertj.core.api.ThrowableAssert;
import pl.wkr.fluentrule.api.FluentExpectedException;

import java.sql.SQLException;

public class DryFluentExpectedException extends FluentExpectedException {

    public ThrowableAssert expectLostConnection() {
        expect().as("expectLostConnection")
                .isInstanceOf(IllegalStateException.class)
                .hasRootCauseInstanceOf(SQLException.class);

        return expectRootCause().as("expectLostConnection-cause").hasMessageContaining("connection lost");
    }
}
