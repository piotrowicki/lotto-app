package pl.nowik.lotto.schedule;

import com.google.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class LottoAPIScheduleTest {

    @Inject
    LottoAPISchedule reader;

    @Test
    void shouldReadDataTest() {
        // TODO: implement test
    }
}
