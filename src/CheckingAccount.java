public class CheckingAccount extends Account {
    public CheckingAccount (String accountNumber, double balance){
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if(amount > balance){
            throw new InsufficientFundsException("Insufficient funds in Checking Account");
        }
        balance -= amount;
        System.out.println("Withdrawn :"+ amount + ", New balance: " + balance);

    }
}
