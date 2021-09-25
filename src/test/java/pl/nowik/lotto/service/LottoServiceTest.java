package pl.nowik.lotto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.repository.LottoRepository;
import pl.nowik.lotto.service.LottoService.LottoStatisticDto;

@QuarkusTest
public class LottoServiceTest {

    @Inject
    LottoService service;

    @InjectMock
    LottoRepository repository;

    @Test
    public void shouldCalculateStatisticTest() {
        // given
        LottoEntity entity = new LottoEntity();
        entity.setNumbers("1 2 3 4 5 5");

        PanacheQuery panache = mock(PanacheQuery.class);
        when(repository.findAll()).thenReturn(panache);
        when(panache.list()).thenReturn(List.of(entity));

        // when
        List<LottoStatisticDto> result = service.calculateStats();

        // then
        assertFalse(result.isEmpty());
        assertEquals(result.size(), 5);

        List<String> quantities = result.stream().map(LottoStatisticDto::getQuantity).collect(Collectors.toList());

        assertTrue(quantities.containsAll(List.of("1", "1", "1", "1", "2")));
    }
}
