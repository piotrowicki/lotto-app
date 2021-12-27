package pl.nowik.lotto.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pl.nowik.lotto.dto.LottoDto;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.repository.LottoRepository;

@ApplicationScoped
public class LottoService {

    @Inject
    LottoRepository repository;

    public List<LottoDto> findAll() {
        return repository.findAll().project(LottoDto.class).list();
    }

    public Optional<LottoEntity> findByNumbersAndDrawDate(String numbers, LocalDate drawDate) {
        return LottoEntity.find("#Lotto.findByNumbersAndDrawDate", numbers, drawDate).firstResultOptional();
    }
}
