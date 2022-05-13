package pl.nowik.lotto.schedule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import pl.nowik.lotto.converter.LottoResultConverter;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.service.LottoService;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class LottoAPIScheduleTest {

    @Inject
    LottoAPISchedule schedule;

    @Inject
    LottoResultConverter converter;

    @InjectMock
    LottoAPIReader reader;

    @InjectMock
    LottoService service;

    @Test
    public void shouldSaveValidResult() {
        // given
        Optional<String> result = Optional.of("2022-01-01 1 2 3 4 5 6");

        // when
        when(reader.getUrlLottoResult()).thenReturn(result);
        schedule.readAndSave();

        // then
        verify(service).saveIfNotExist(any(LottoEntity.class));
    }
}
