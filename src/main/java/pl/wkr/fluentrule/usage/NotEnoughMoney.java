package pl.wkr.fluentrule.usage;

public class NotEnoughMoney extends Exception {

    private final int lackingMoney;

    public NotEnoughMoney(int lackingMoney) {
        super(String.format("Not enough money, insert %d$ more",lackingMoney));
        this.lackingMoney = lackingMoney;
    }

    public int getLackingMoney() {
        return this.lackingMoney;
    }
}
