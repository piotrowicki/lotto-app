package pl.nowik.lotto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import pl.nowik.lotto.dto.LottoStatisticDto;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.util.LottoNumbersCollector;

@QuarkusTest
@TestTransaction
@QuarkusTestResource(H2DatabaseTestResource.class)
public class LottoStatisticsServiceTest {

    @Inject
    LottoStatisticsService service;

    @Inject
    LottoNumbersCollector collector;

    @Test
    public void shouldGroupByQuantity() {
        // given
        LottoEntity entity = new LottoEntity();
        entity.numbers = "1 2 3 3 4 5";
        entity.drawDate = LocalDate.of(2022, 1, 1);

        entity.persist();

        // when
        List<LottoStatisticDto> result = service.calculateStats();

        // then
        assertNotNull(result);
        assertResult(result, "1", "1");
        assertResult(result, "2", "1");
        assertResult(result, "3", "2");
        assertResult(result, "4", "1");
        assertResult(result, "5", "1");
    }

    @Test
    public void shouldGetSixHighestResults() {
        // given
        LottoEntity entity1 = new LottoEntity();
        entity1.numbers = "1 1 1 1 1 1";
        entity1.drawDate = LocalDate.of(2022, 1, 1);

        LottoEntity entity2 = new LottoEntity();
        entity2.numbers = "2 2 2 2 2 3";
        entity2.drawDate = LocalDate.of(2022, 1, 1);

        LottoEntity entity3 = new LottoEntity();
        entity3.numbers = "3 3 3 4 4 4";
        entity3.drawDate = LocalDate.of(2022, 1, 1);

        LottoEntity entity4 = new LottoEntity();
        entity4.numbers = "5 6 7 8 9 10";
        entity4.drawDate = LocalDate.of(2022, 1, 1);

        LottoEntity.persist(entity1, entity2, entity3, entity4);

        // when
        List<LottoStatisticDto> result = service.getMostCommon();

        // then
        assertNotNull(result);
        assertEquals(6, result.size());
        assertResult(result, "1", "6");
        assertResult(result, "2", "5");
        assertResult(result, "3", "4");
        assertResult(result, "4", "3");
        assertResult(result, "5", "1");
        assertResult(result, "6", "1");
    }

    @Test
    public void shouldGetSixLowestResults() {
        // given
        LottoEntity entity1 = new LottoEntity();
        entity1.numbers = "1 1 1 1 1 1";
        entity1.drawDate = LocalDate.of(2022, 1, 1);

        LottoEntity entity2 = new LottoEntity();
        entity2.numbers = "2 2 2 2 2 3";
        entity2.drawDate = LocalDate.of(2022, 1, 1);

        LottoEntity entity3 = new LottoEntity();
        entity3.numbers = "3 3 3 4 4 4";
        entity3.drawDate = LocalDate.of(2022, 1, 1);

        LottoEntity entity4 = new LottoEntity();
        entity4.numbers = "5 6 7 8 9 10";
        entity4.drawDate = LocalDate.of(2022, 1, 1);

        LottoEntity.persist(entity1, entity2, entity3, entity4);

        // when
        List<LottoStatisticDto> result = service.getLeastCommon();

        // then
        assertNotNull(result);
        assertEquals(6, result.size());
        assertResult(result, "5", "1");
        assertResult(result, "6", "1");
        assertResult(result, "7", "1");
        assertResult(result, "8", "1");
        assertResult(result, "9", "1");
        assertResult(result, "10", "1");
    }

    private void assertResult(List<LottoStatisticDto> list, String number, String quantity) {
        list.forEach(dto -> {
            if (dto.getNumber().equals(number)) {
                assertEquals(dto.getQuantity(), quantity);
            }
        });
    }
}
