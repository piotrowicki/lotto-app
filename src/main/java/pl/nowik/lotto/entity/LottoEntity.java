package pl.nowik.lotto.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "DRAW_DL")
@NamedQuery(name = "Lotto.findByNumbersAndDrawDate", query = "from LottoEntity where numbers = ?1 and drawDate = ?2")
public class LottoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "NUMBERS")
    public String numbers;

    @Column(name = "DRAW_DATE", columnDefinition = "DATE")
    public LocalDate drawDate;

    @Column(name = "CREATE_DATE", columnDefinition = "DATETIME")
    public LocalDateTime createDate;

    @Column(name = "UPDATE_DATE", columnDefinition = "DATETIME")
    public LocalDateTime updateDate;

    public static Optional<LottoEntity> findByNumbersAndDrawDate(String numbers, LocalDate drawDate) {
        return find("#Lotto.findByNumbersAndDrawDate", numbers, drawDate).firstResultOptional();
    }

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now();
    }
}
