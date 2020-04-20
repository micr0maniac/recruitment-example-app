package touk.cinema.domain;

public class Voucher {

    private VoucherId id;

    private String code;

    private int percentage;

    public Voucher() {
    }

    public Voucher(String code, int percentage) {
        this.id = new VoucherId();
        this.code = code;
        this.percentage = percentage;
    }

    public int percentage() {
        return percentage;
    }

}
