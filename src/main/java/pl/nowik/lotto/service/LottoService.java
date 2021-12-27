package pl.nowik.lotto.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pl.nowik.lotto.dto.LottoDto;
import pl.nowik.lotto.repository.LottoRepository;

@ApplicationScoped
public class LottoService {

    @Inject
    LottoRepository repository;

    public List<LottoDto> findAll() {
        return repository.findAll().project(LottoDto.class).list();
    }
}
