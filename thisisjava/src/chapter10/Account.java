package chapter10;

public class Account {
    private long balance;

    public Account() {}

    public long getBalance(){
        return this.balance;
    }

    public void deposit(int money) {
        this.balance += money;
    }

    public void withdraw(int money) throws BalanceInsufficientException {
        if(balance < money) {
            throw new BalanceInsufficientException("잔고 부족:"+(money-balance)+"모자람");
        }
        balance -= money;
    }
}
