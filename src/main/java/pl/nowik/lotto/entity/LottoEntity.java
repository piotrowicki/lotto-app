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
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "DRAW_DL")
@NamedQuery(name = "Lotto.findByNumbersAndDrawDate", query = "from LottoEntity where numbers = ?1 and drawDate = ?2")
public class LottoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(name = "NUMBERS")
    public String numbers;

    @NotNull
    @Column(name = "DRAW_DATE", columnDefinition = "DATE")
    public LocalDate drawDate;

    @Column(name = "CREATE_DATE", columnDefinition = "DATETIME")
    public LocalDateTime createDate;

    @Column(name = "UPDATE_DATE", columnDefinition = "DATETIME")
    public LocalDateTime updateDate;

    public static Optional<LottoEntity> findByNumbersAndDrawDate(String numbers, LocalDate drawDate) {
        return find("#Lotto.findByNumbersAndDrawDate", numbers, drawDate).firstResultOptional();
    }

    public static LottoEntity getLastByDrawDate() {
        return (LottoEntity) getEntityManager()
                .createNativeQuery("SELECT * FROM DRAW_DL ORDER BY DRAW_DATE DESC LIMIT 1", LottoEntity.class)
                .getSingleResult();
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
