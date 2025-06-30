// Full program with Account, SavingsAccount, BankDemo
import java.util.ArrayList;

class Account {
    protected String accountHolder;
    protected int accountNumber;
    protected double balance;
    protected ArrayList<String> transactions;

    public Account(String holder, int accNum, double initialDeposit) {
        this.accountHolder = holder;
        this.accountNumber = accNum;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        transactions.add("Account opened with deposit: Rs." + initialDeposit);
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: Rs." + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: Rs." + amount);
        } else {
            transactions.add("Failed withdrawal of Rs." + amount + " due to insufficient balance.");
            System.out.println("Insufficient balance!");
        }
    }

    public void showBalance() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account No: " + accountNumber);
        System.out.println("Current Balance: Rs." + balance);
    }

    public void showTransactionHistory() {
        System.out.println("\n--- Transaction History for Account No " + accountNumber + " ---");
        for (String tx : transactions) {
            System.out.println(tx);
        }
    }
}

class SavingsAccount extends Account {
    private final double MIN_BALANCE = 1000;

    public SavingsAccount(String holder, int accNum, double initialDeposit) {
        super(holder, accNum, initialDeposit);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            transactions.add("Withdrew: Rs." + amount);
        } else {
            transactions.add("Failed withdrawal of Rs" + amount + " - Minimum balance Rs." + MIN_BALANCE + " required.");
            System.out.println("Cannot withdraw Rs." + amount + ". Minimum Rs." + MIN_BALANCE + " must remain.");
        }
    }
}

public class BankDemo {
    public static void main(String[] args) {
        SavingsAccount acc1 = new SavingsAccount("Tamsil", 1001, 3000);
        acc1.deposit(5000);
        acc1.withdraw(1000);
        acc1.withdraw(1000);
        acc1.showBalance();
        acc1.showTransactionHistory();
    }
}
