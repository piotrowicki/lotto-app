package pl.nowik.lotto.repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pl.nowik.lotto.entity.LottoEntity;

@ApplicationScoped
public class LottoRepository implements PanacheRepository<LottoEntity> {
}