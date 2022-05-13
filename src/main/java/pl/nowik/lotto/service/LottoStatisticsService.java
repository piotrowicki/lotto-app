package pl.nowik.lotto.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pl.nowik.lotto.dto.LottoStatisticDto;
import pl.nowik.lotto.entity.LottoEntity;
import pl.nowik.lotto.util.LottoNumbersCollector;

@RequestScoped
public class LottoStatisticsService {

    @Inject
    LottoNumbersCollector collector;

    public List<LottoStatisticDto> calculateStats() {
        return getGroupedNumbers().entrySet().stream()
                .map(entry -> LottoStatisticDto.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private TreeMap<Integer, Long> getGroupedNumbers() {
        return getNumbersList().stream()
                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
    }

    private List<Integer> getNumbersList() {
        return collector.collectNumbersList(LottoEntity.listAll());
    }

    public List<LottoStatisticDto> getMostCommon() {
        return getSixComparingBy(Entry.<Integer, Long>comparingByValue().reversed());
    }

    public List<LottoStatisticDto> getLeastCommon() {
        return getSixComparingBy(Entry.<Integer, Long>comparingByValue());
    }

    private List<LottoStatisticDto> getSixComparingBy(Comparator<Entry<Integer, Long>> comparator) {
        return getGroupedNumbers()
                .entrySet()
                .stream()
                .sorted(comparator)
                .limit(6)
                .map(entry -> LottoStatisticDto.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
