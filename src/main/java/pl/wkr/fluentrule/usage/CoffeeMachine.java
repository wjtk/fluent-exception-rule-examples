package pl.wkr.fluentrule.usage;

public class CoffeeMachine {

    private int coffeePrice;
    private int insertedMoney;

    public CoffeeMachine(int coffeePrice) {
        if( coffeePrice < 0) {
            throw new IllegalArgumentException("Coffee price should be grater than zero.");
        }
        this.coffeePrice = coffeePrice;
        this.insertedMoney = 0;
    }

    public void insertCoin(int value) {
        insertedMoney += value;
    }

    public Coffee getCoffee() throws NotEnoughMoney {
        if(insertedMoney < coffeePrice) {
            throw new NotEnoughMoney(coffeePrice - insertedMoney);
        }
        return new Coffee();
    }

    public int getInsertedMoney() {
        return insertedMoney;
    }

}
