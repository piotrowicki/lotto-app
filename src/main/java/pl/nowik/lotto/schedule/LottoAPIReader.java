package pl.nowik.lotto.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import io.quarkus.scheduler.Scheduled;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.service.LottoService;

@ApplicationScoped
public class LottoAPIReader {

    private static final Logger LOG = Logger.getLogger(LottoAPIReader.class);

    private static final String LOTTO_URL = "http://app.lotto.pl/wyniki/?type=dl";

    @Inject
    LottoService lottoService;

    @Transactional
    @Scheduled(every = "7h")
    void readAndSave() {
        try {
            String result = getUrlLottoResult();
            LOG.info(String.format("Read result: %s", result));

            LottoEntity entity = toEntity(result);
            LottoEntity dbEntity = LottoEntity
                    .find("#Lotto.findByNumbersAndDrawDate", entity.getNumbers(), entity.getDrawDate()).firstResult();

            if (dbEntity == null) {
                entity.persist();
                LOG.info("Lotto draw saved.");
            }

        } catch (IOException e) {
            LOG.info(String.format("Problem with processing data from %s", LOTTO_URL));
        }
    }

    private String getUrlLottoResult() throws IOException {
        URL url = new URL(LOTTO_URL);
        URLConnection connection = url.openConnection();
        String location = connection.getHeaderField("Location");

        if (location != null) {
            connection = new URL(location).openConnection();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return reader.lines().collect(Collectors.joining(" "));
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
