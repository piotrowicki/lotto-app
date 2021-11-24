package pl.nowik.lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;

import pl.nowik.lotto.entity.LottoEntity;

@RequestScoped
public class LottoNumbersCollector {
    public List<Integer> collectNumbersList(List<LottoEntity> entities) {
        return entities.stream()
                .map(s -> s.getNumbers().split(" "))
                .flatMap(Arrays::stream)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
