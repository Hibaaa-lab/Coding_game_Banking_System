public class BusinessAccount extends Account {
    public BusinessAccount(String AccountNumber, double Balance) {
        super(AccountNumber, Balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if(amount> balance) {
            throw new InsufficientFundsException("Insufficient Funds in Business Account");
        }
        balance -= amount;
        System.out.println("Withdrawn : " + amount + " successfully, New balance : " + balance);
    }
}

