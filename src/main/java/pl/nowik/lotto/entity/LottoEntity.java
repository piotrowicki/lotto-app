package pl.nowik.lotto.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "DRAW_DL")
public class LottoEntity extends PanacheEntity {

    @Column(name = "NUMBERS")
    private String numbers;

    @Column(name = "DRAW_DATE", columnDefinition = "DATE")
    private LocalDate drawDate;

    @Column(name = "CREATE_DATE", columnDefinition = "DATETIME")
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE", columnDefinition = "DATETIME")
    private LocalDateTime updateDate;

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

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
