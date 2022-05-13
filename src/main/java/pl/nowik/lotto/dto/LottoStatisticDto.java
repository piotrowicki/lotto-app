package pl.nowik.lotto.dto;

public class LottoStatisticDto {
    private String number;
    private String quantity;

    private LottoStatisticDto(String number, String quantity) {
        this.number = number;
        this.quantity = quantity;
    }

    public static LottoStatisticDto of(Integer number, Long quantity) {
        return new LottoStatisticDto(String.valueOf(number), String.valueOf(quantity));
    }

    public String getNumber() {
        return number;
    }

    public String getQuantity() {
        return quantity;
    }
}
