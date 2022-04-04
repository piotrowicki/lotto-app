package pl.nowik.lotto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import pl.nowik.lotto.config.DatabaseConfig;
import pl.nowik.lotto.dto.LottoDto;
import pl.nowik.lotto.entity.LottoEntity;

@QuarkusTest
@QuarkusTestResource(DatabaseConfig.class)
public class LottoServiceTest {

    @Inject
    LottoService service;

    @Test
    @Transactional
    public void testFindLastDraw() {
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
}
