package pl.nowik.lotto.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@ApplicationScoped
public class LottoAPIReader {

    private static final Logger LOG = Logger.getLogger(LottoAPIReader.class);

    @ConfigProperty(name = "lotto.url.path")
    String lottoUrl;

    public Optional<String> getUrlLottoResult() {
        try {
            URL url = new URL(lottoUrl);
            URLConnection connection = url.openConnection();
            String location = connection.getHeaderField("Location");

            if (location != null) {
                connection = new URL(location).openConnection();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = reader.lines().collect(Collectors.joining(" "));

            LOG.info(String.format("Line read: [%s].", result));
            
            return Optional.of(result);
        } catch (IOException ex) {
            LOG.info(String.format("Problem with processing data from %s", lottoUrl));
            return Optional.empty();
        }
    }
}
