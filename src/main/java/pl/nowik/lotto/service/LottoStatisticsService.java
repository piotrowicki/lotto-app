package pl.nowik.lotto.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pl.nowik.lotto.dto.LottoStatisticDto;
import pl.nowik.lotto.repository.LottoRepository;
import pl.nowik.lotto.util.LottoNumbersCollector;

@RequestScoped
public class LottoStatisticsService {

    @Inject
    LottoRepository repository;

    @Inject
    LottoNumbersCollector collector;

    public List<LottoStatisticDto> calculateStats() {
        Map<Integer, Long> statistic = getNumbersList().stream()
                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));

        return statistic.entrySet().stream()
                .map(entry -> LottoStatisticDto.of(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())))
                .collect(Collectors.toList());
    }

    private List<Integer> getNumbersList() {
        return collector.collectNumbersList(repository.listAll());
    }
}
