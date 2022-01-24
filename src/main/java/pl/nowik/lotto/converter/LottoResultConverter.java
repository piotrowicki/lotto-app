package pl.nowik.lotto.converter;

import java.time.LocalDate;

import javax.enterprise.context.RequestScoped;

import pl.nowik.lotto.entity.LottoEntity;

@RequestScoped
public class LottoResultConverter {

    public LottoEntity toEntity(String result) {
        String[] splittedDraw = result.split(" ");

        int firstSpace = result.indexOf(" ") + 1;

        LottoEntity entity = new LottoEntity();
        entity.drawDate = LocalDate.parse(splittedDraw[0]);
        entity.numbers = result.substring(firstSpace);
        return entity;
    }
}
