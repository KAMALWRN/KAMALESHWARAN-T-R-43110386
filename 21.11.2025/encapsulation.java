class encapsulation;
public class Bank {
    private double balance;

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public double getBalance() {
        return balance;
    }
}

public class BankEncapsulationDemo {
    public static void main(String[] args) {
        Bank myAcc = new Bank();
        myAcc.deposit(1000);
        System.out.println("Current Balance: " + myAcc.getBalance());
    }
}
