package pl.nowik.lotto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import pl.nowik.lotto.dto.LottoStatisticDto;
import pl.nowik.lotto.repository.LottoRepository;
import pl.nowik.lotto.util.LottoNumbersCollector;

@QuarkusTest
public class LottoStatisticCalculationServiceTest {

    @Inject
    LottoStatisticCalculationService service;

    @InjectMock
    LottoRepository repository;

    @InjectMock
    LottoNumbersCollector collector;

    @Test
    public void shouldGroupByQuantity() {
        // when
        List<Integer> draw = List.of(1, 2, 3, 3, 4, 5);

        // when
        when(repository.listAll()).thenReturn(Collections.emptyList());
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

    private void assertResult(List<LottoStatisticDto> list, String number, String quantity) {
        list.forEach(dto -> {
            if (dto.getNumber().equals(number)) {
                assertEquals(dto.getQuantity(), quantity);
            }
        });
    }
}
