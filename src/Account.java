public abstract class Account implements BankingOperations{

    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double balance) { //constructor
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
}