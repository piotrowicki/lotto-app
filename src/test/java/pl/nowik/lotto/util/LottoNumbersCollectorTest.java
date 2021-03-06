package pl.nowik.lotto.util;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import pl.nowik.lotto.entity.LottoEntity;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class LottoNumbersCollectorTest {

    @Inject
    LottoNumbersCollector collector;

    @Test
    public void test() {
        // given
        LottoEntity entity = new LottoEntity();
        entity.numbers = "1 2 3 4 5 6";

        // when
        List<Integer> result = collector.collectNumbersList(List.of(entity));

        // then
        assertIterableEquals(result, List.of(1, 2, 3, 4, 5, 6));
    }
}
