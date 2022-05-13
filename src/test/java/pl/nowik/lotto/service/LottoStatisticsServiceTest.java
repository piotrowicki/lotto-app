package pl.nowik.lotto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import pl.nowik.lotto.config.DatabaseConfig;
import pl.nowik.lotto.dto.LottoStatisticDto;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.util.LottoNumbersCollector;

@QuarkusTest
@QuarkusTestResource(DatabaseConfig.class)
public class LottoStatisticsServiceTest {

    @Inject
    LottoStatisticsService service;

    @InjectMock
    LottoNumbersCollector collector;

    @Test
    public void shouldGroupByQuantity() {
        // given
        PanacheMock.mock(LottoEntity.class);

        // when
        List<Integer> draw = List.of(1, 2, 3, 3, 4, 5);

        // when
        when(LottoEntity.listAll()).thenReturn(Collections.emptyList());
        when(collector.collectNumbersList(anyList())).thenReturn(draw);

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
        PanacheMock.mock(LottoEntity.class);

        // when
        List<Integer> draw = List.of(1, 1, 1, 1, 1, 1,
                2, 2, 2, 2, 2,
                3, 3, 3, 3,
                4, 4, 4,
                5, 5,
                6, 6,
                7, 7,
                8,
                9,
                10,
                11,
                12);

        // when
        when(LottoEntity.listAll()).thenReturn(Collections.emptyList());
        when(collector.collectNumbersList(anyList())).thenReturn(draw);

        List<LottoStatisticDto> result = service.getMostCommon();

        // then
        assertNotNull(result);
        assertEquals(6, result.size());
        assertResult(result, "1", "6");
        assertResult(result, "2", "5");
        assertResult(result, "3", "4");
        assertResult(result, "4", "3");
        assertResult(result, "5", "2");
        assertResult(result, "6", "2");
    }

    @Test
    public void shouldGetSixLowestResults() {
        // given
        PanacheMock.mock(LottoEntity.class);

        // when
        List<Integer> draw = List.of(1, 1, 1, 1, 1, 1,
                2, 2, 2, 2, 2,
                3, 3, 3, 3,
                4, 4, 4,
                5, 5,
                6, 6,
                7, 7,
                8,
                9,
                10,
                11,
                12);

        // when
        when(LottoEntity.listAll()).thenReturn(Collections.emptyList());
        when(collector.collectNumbersList(anyList())).thenReturn(draw);

        List<LottoStatisticDto> result = service.getLeastCommon();

        // then
        assertNotNull(result);
        assertEquals(6, result.size());
        assertResult(result, "5", "2");
        assertResult(result, "8", "1");
        assertResult(result, "9", "1");
        assertResult(result, "10", "1");
        assertResult(result, "11", "1");
        assertResult(result, "12", "1");
    }

    private void assertResult(List<LottoStatisticDto> list, String number, String quantity) {
        list.forEach(dto -> {
            if (dto.getNumber().equals(number)) {
                assertEquals(dto.getQuantity(), quantity);
            }
        });
    }
}
