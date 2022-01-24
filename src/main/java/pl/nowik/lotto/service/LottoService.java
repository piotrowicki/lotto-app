package pl.nowik.lotto.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import pl.nowik.lotto.dto.LottoDto;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.repository.LottoRepository;

@ApplicationScoped
public class LottoService {

    private static final Logger LOG = Logger.getLogger(LottoService.class);

    @Inject
    LottoRepository repository;

    public List<LottoDto> findAll() {
        return repository.findAll().project(LottoDto.class).list();
    }

    public void saveIfNotExist(LottoEntity entity) {
        LottoEntity.findByNumbersAndDrawDate(entity.numbers, entity.drawDate)
                .ifPresentOrElse(
                        result -> LOG.info(String.format("Result [%s] already exist doing nothing.", result.numbers)),
                        () -> {
                            entity.persist();
                            LOG.info(String.format("Lotto draw [%s] saved!.", entity.numbers));
                        });
    }
}
