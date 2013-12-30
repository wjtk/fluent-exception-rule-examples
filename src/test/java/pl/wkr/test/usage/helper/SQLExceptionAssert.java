package pl.wkr.test.usage.helper;

import org.assertj.core.api.AbstractThrowableAssert;

import java.sql.SQLException;

import static pl.wkr.test.usage.helper.MyAssertions.assertThat;

public class SQLExceptionAssert extends AbstractThrowableAssert<SQLExceptionAssert, SQLException> {

    protected SQLExceptionAssert(SQLException actual) {
        super(actual, SQLExceptionAssert.class);
    }

    public SQLExceptionAssert hasErrorCode(int expected) {
        int actualCode = actual.getErrorCode();
        assertThat(actualCode).overridingErrorMessage(
                "\nExpected SQLException.errorCode : <%d> but was: <%d>", expected, actualCode).isEqualTo(expected);
        return myself;
    }
}