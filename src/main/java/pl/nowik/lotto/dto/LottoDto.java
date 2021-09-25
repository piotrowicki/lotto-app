package pl.nowik.lotto.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class LottoDto {

    private final String numbers;
    private final LocalDate drawDate;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    public LottoDto(String numbers, LocalDate drawDate, LocalDateTime createDate, LocalDateTime updateDate) {
        this.numbers = numbers;
        this.drawDate = drawDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public String getNumbers() {
        return this.numbers;
    }

    public LocalDate getDrawDate() {
        return this.drawDate;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LottoDto)) {
            return false;
        }
        LottoDto lottoDto = (LottoDto) o;
        return Objects.equals(numbers, lottoDto.numbers) && Objects.equals(drawDate, lottoDto.drawDate)
                && Objects.equals(createDate, lottoDto.createDate) && Objects.equals(updateDate, lottoDto.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers, drawDate, createDate, updateDate);
    }
}