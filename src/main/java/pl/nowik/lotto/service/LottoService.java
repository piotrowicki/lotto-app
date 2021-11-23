package pl.nowik.lotto.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pl.nowik.lotto.dto.LottoDto;
import pl.nowik.lotto.dto.LottoStatisticDto;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.repository.LottoRepository;
import pl.nowik.lotto.util.NumberConverterUtil;

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

    public List<LottoStatisticDto> calculateStats() {
        Map<Integer, Long> statistic = getNumbersList().stream()
                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));

        return statistic.entrySet().stream()
                .map(entry -> LottoStatisticDto.of(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())))
                .collect(Collectors.toList());
    }

    private List<Integer> getNumbersList() {
        return NumberConverterUtil.collectNumbersList(repository.findAll().list());
    }
}
