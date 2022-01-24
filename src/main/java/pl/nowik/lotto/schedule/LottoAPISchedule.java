package pl.nowik.lotto.schedule;

import java.time.LocalDate;
import java.util.function.Predicate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import io.quarkus.scheduler.Scheduled;
import pl.nowik.lotto.converter.LottoResultConverter;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.service.LottoService;

@ApplicationScoped
public class LottoAPISchedule {

    private static final Logger LOG = Logger.getLogger(LottoAPISchedule.class);

    private static final Predicate<String> IS_VALID = s -> s.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}[0-9 ]+$");

    @Inject
    LottoAPIReader lottoAPIReader;

    @Inject
    LottoService lottoService;

    @Inject
    LottoResultConverter converter;

    @Transactional
    @Scheduled(cron = "0 0 23 * * ?")
    void readAndSave() {
        lottoAPIReader.getUrlLottoResult()
                .filter(IS_VALID)
                .map(converter::toEntity)
                .ifPresentOrElse(entity -> lottoService.saveIfNotExist(entity),
                        () -> LOG.info("Result already exist doing nothing."));
    }
}
