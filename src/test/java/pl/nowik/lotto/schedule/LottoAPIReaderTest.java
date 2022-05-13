package pl.nowik.lotto.schedule;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class LottoAPIReaderTest {

    @Inject
    LottoAPIReader reader;

    @Test
    public void shouldReadTheResult() {
        // when
        Optional<String> result = reader.getUrlLottoResult();

        // then
        assertTrue(result.isPresent());
    }
}
