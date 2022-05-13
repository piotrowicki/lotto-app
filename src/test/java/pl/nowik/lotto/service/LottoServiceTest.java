package pl.nowik.lotto.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import pl.nowik.lotto.dto.LottoDto;
import pl.nowik.lotto.entity.LottoEntity;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class LottoServiceTest {

    @Inject
    LottoService service;

    @Test
    @Transactional
    public void shouldReturnLastDraw() {
        // given
        LottoEntity first = new LottoEntity();
        first.numbers = "1 2 3 4 5 6";
        first.drawDate = LocalDate.of(2022, 1, 1);

        LottoEntity second = new LottoEntity();
        second.numbers = "1 2 3 4 5 6";
        second.drawDate = LocalDate.of(2022, 2, 2);

        first.persist();
        second.persist();

        // when
        LottoDto result = service.getLastByDrawDate();

        // then
        assertEquals(result.numbers, second.numbers);
        assertEquals(result.drawDate, second.drawDate);
    }

    @Test
    @Transactional
    public void shouldSaveValidData() {
        // given
        LottoEntity entity = new LottoEntity();
        entity.numbers = "1 2 3 4 5 6";
        entity.drawDate = LocalDate.of(2022, 1, 1);

        // when
        service.saveIfNotExist(entity);

        // then
        assertEquals(1, LottoEntity.count());
    }

    @Test
    @Transactional
    public void shouldReturnExceptionWhenNumbersMissing() {
        // given
        LottoEntity entity = new LottoEntity();
        entity.drawDate = LocalDate.of(2022, 1, 1);

        // when
        assertThrows(ConstraintViolationException.class, () -> service.saveIfNotExist(entity));
    }

    @Test
    @Transactional
    public void shouldReturnExceptionWhenDrawDateMissing() {
        // given
        LottoEntity entity = new LottoEntity();
        entity.numbers = "1 2 3 4 5 6";

        // when
        assertThrows(ConstraintViolationException.class, () -> service.saveIfNotExist(entity));
    }

    @Test
    @Transactional
    public void shouldReturnExceptionWhenDrawFormatIsWrong() {
        // given
        LottoEntity entity = new LottoEntity();
        entity.numbers = "2022-01-01";

        // when
        assertThrows(ConstraintViolationException.class, () -> service.saveIfNotExist(entity));
    }
}
