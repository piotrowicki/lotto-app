package pl.nowik.lotto.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.quarkus.runtime.annotations.RegisterForReflection;
import pl.nowik.lotto.entity.LottoEntity;

@JsonInclude(Include.NON_NULL)
@RegisterForReflection
public class LottoDto {

    public final String numbers;
    public final LocalDate drawDate;
    public final LocalDateTime createDate;
    public final LocalDateTime updateDate;

    public LottoDto(String numbers, LocalDate drawDate, LocalDateTime createDate, LocalDateTime updateDate) {
        this.numbers = numbers;
        this.drawDate = drawDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public LottoDto(String numbers, LocalDate drawDate) {
        this(numbers, drawDate, null, null);
    }

    public static LottoDto of(LottoEntity entity) {
        return new LottoDto(entity.numbers, entity.drawDate);
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