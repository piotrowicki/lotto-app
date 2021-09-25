package pl.nowik.lotto.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Long id;

    @Column(name = "NUMBERS")
    private String numbers;

    @Column(name = "DRAW_DATE", columnDefinition = "DATE")
    private LocalDate drawDate;

    @Column(name = "CREATE_DATE", columnDefinition = "DATETIME")
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE", columnDefinition = "DATETIME")
    private LocalDateTime updateDate;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumbers() {
        return this.numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public LocalDate getDrawDate() {
        return this.drawDate;
    }

    public void setDrawDate(LocalDate drawDate) {
        this.drawDate = drawDate;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public LocalDateTime getUpdateDate() {
        return this.updateDate;
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
