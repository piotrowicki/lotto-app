package pl.nowik.lotto.schedule;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import io.quarkus.scheduler.Scheduled;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.service.LottoService;

@ApplicationScoped
public class LottoAPISchedule {

    private static final Logger LOG = Logger.getLogger(LottoAPISchedule.class);

    @Inject
    LottoAPIReader lottoAPIReader;

    @Inject
    LottoService lottoService;

    @Transactional
    @Scheduled(every = "7h")
    void readAndSave() {
        lottoAPIReader.getUrlLottoResult().map(this::toEntity).ifPresentOrElse(this::saveIfNotExist, () -> {
            LOG.info("Result already exist doing nothing.");
        });
    }

    private void saveIfNotExist(LottoEntity entity) {
        lottoService.findByNumbersAndDrawDate(entity.getNumbers(), entity.getDrawDate()).ifPresentOrElse(result -> {
            LOG.info(String.format("Result already exist doing nothing.", result));
        }, () -> {
            entity.persist();
            LOG.info(String.format("Lotto draw saved!.", entity));
        });
    }

    private LottoEntity toEntity(String result) {
        String[] splittedDraw = result.split(" ");

        int firstSpace = result.indexOf(" ") + 1;

        LottoEntity entity = new LottoEntity();
        entity.setDrawDate(LocalDate.parse(splittedDraw[0]));
        entity.setNumbers(result.substring(firstSpace));
        return entity;
    }
}
