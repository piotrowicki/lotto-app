package pl.nowik.lotto.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;

import org.jboss.logging.Logger;

import pl.nowik.lotto.dto.LottoDto;
import pl.nowik.lotto.entity.LottoEntity;

@ApplicationScoped
public class LottoService {

    private static final Logger LOG = Logger.getLogger(LottoService.class);

    public List<LottoDto> findAll() {
        return LottoEntity.findAll().project(LottoDto.class).list();
    }

    public LottoDto getLastByDrawDate() {
        return LottoDto.of(LottoEntity.getLastByDrawDate());
    }

    public void saveIfNotExist(@Valid LottoEntity entity) {
        LottoEntity.findByNumbersAndDrawDate(entity.numbers, entity.drawDate)
                .ifPresentOrElse(
                        result -> LOG.info(String.format("Result [%s] already exist doing nothing.", result.numbers)),
                        () -> {
                            entity.persist();
                            LOG.info(String.format("Lotto draw [%s] saved!.", entity.numbers));
                        });
    }
}
