package pl.nowik.lotto.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

@ApplicationScoped
public class LottoAPIReader {

    private static final Logger LOG = Logger.getLogger(LottoAPIReader.class);

    private static final String LOTTO_URL = "http://app.lotto.pl/wyniki/?type=dl";

    public Optional<String> getUrlLottoResult() {
        try {
            URL url = new URL(LOTTO_URL);
            URLConnection connection = url.openConnection();
            String location = connection.getHeaderField("Location");

            if (location != null) {
                connection = new URL(location).openConnection();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return Optional.of(reader.lines().collect(Collectors.joining(" ")));
        } catch (IOException ex) {
            LOG.info(String.format("Problem with processing data from %s", LOTTO_URL));
            return Optional.empty();
        }
    }
}
