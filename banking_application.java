package project1;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

// ---- Bank Class ----
class Bank {
    private String name;
    private String address;
    private double capital;

    public String getName() { return name; }
    public void setName(String _name) { this.name = _name; }

    public String getAddress() { return address; }
    public void setAddress(String _address) { this.address = _address; }

    public double getCapital() { return capital; }
    public void setCapital(double _capital) { this.capital = _capital; }

    public Bank(String name, String address, double capital) {
        setName(name);
        setAddress(address);
        setCapital(capital);
    }
}

// ---- Customer Class ----
class Customer {
    private String firstname;
    private String secondname;
    private int id;

    public String getFirstName() { return firstname; }
    public void setFirstName(String _firstname) { this.firstname = _firstname; }

    public String getSecondName() { return secondname; }
    public void setSecondName(String _secondname) { this.secondname = _secondname; }

    public int getId() { return id; }
    public void setId(int _id) { this.id = _id; }

    public Customer(String firstname, String secondname, int id) {
        setFirstName(firstname);
        setSecondName(secondname);
        setId(id);
    }
}

// ---- BankAccount Class ----
class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void Add(double amount) {
        balance += amount;
        System.out.println("Added amount: " + amount);
    }

    public void Retract(double amount) {
        if (amount > balance) {
            System.out.println("Not enough balance to retract " + amount);
        } else {
            balance -= amount;
            System.out.println("Retracted amount: " + amount);
        }
    }

    public void Display() {
        System.out.println("Current Balance: " + balance);
    }
}

// ---- CustomerAccount Class ----
class CustomerAccount {
    private Customer customer;
    private BankAccount bankacc;

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer _customer) { this.customer = _customer; }

    public BankAccount getBankAccount() { return bankacc; }
    public void setBankAccount(BankAccount _bankaccount) { this.bankacc = _bankaccount; }

    public CustomerAccount(Customer customer, BankAccount bankacc) {
        setCustomer(customer);
        setBankAccount(bankacc);
    }
}

// ---- BankAccounts Class ----
class BankAccounts {
    private Bank bank;
    private Vector<CustomerAccount> CurrentAccounts;

    public Bank getBank() { return bank; }
    public void setBank(Bank _bank) { this.bank = _bank; }

    public Vector<CustomerAccount> getCurrentAccounts() { return CurrentAccounts; }
    public void setCurrentAccounts(Vector<CustomerAccount> _CurrentAccounts) {
        this.CurrentAccounts = _CurrentAccounts;
    }

    public BankAccounts(Bank bank, Vector<CustomerAccount> CurrentAccounts) {
        setBank(bank);
        setCurrentAccounts(CurrentAccounts);
    }
}

// ---- BankingAccountMenu (Main Program) ----
public class BankingAccountApp {

    Scanner inputScan = new Scanner(System.in);
    BankAccount bankacc = new BankAccount(550);

    protected void DisplayMenu() {
        System.out.println("----- Banking Account Management -----\n");
        System.out.println("0. Exit bank account");
        System.out.println("1. Pre-transaction view");
        System.out.println("2. View after transaction");
        System.out.println("3. View customer details");
        System.out.print("\nAdd your choice: ");
    }

    public void Run() {
        int choice = -1;
        while (choice != 0) {
            try {
                DisplayMenu();
                choice = inputScan.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.println("\n--- Pre-Transaction ---");
                        bankacc.Display();
                        bankacc.Add(125.5);
                        bankacc.Retract(255);
                        System.out.println();
                    }
                    case 2 -> {
                        System.out.println("\n--- Post-Transaction ---");
                        bankacc.Display();
                        System.out.println();
                    }
                    case 3 -> {
                        System.out.println("\n--- Customer Details ---");
                        CustomerAccount customer1 = new CustomerAccount(new Customer("Andreea", "Drg", 666), new BankAccount(20));
                        System.out.println("Customer's first name: " + customer1.getCustomer().getFirstName());
                        System.out.println("Customer's second name: " + customer1.getCustomer().getSecondName());
                        System.out.println("Customer's ID: " + customer1.getCustomer().getId());
                        BankAccounts bankaccs = new BankAccounts(new Bank("ING", "unknown", 200), new Vector<>());
                        bankaccs.getCurrentAccounts().add(customer1);
                        System.out.println();
                    }
                    case 0 -> System.out.println("\nExiting program...");
                    default -> System.out.println("\nInvalid choice. Try again.\n");
                }
            } catch (Exception e) {
                System.out.println("Message: " + e.getMessage());
                inputScan.nextLine(); // Clear input buffer
            }
        }

        System.out.println("\nThe transaction was completed successfully!");
        LocalDate randomDate = createRandomDate(2021, 2021);
        System.out.println("Access Date: " + randomDate);
    }

    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }

    public static void main(String[] args) {
        new BankingAccountApp().Run();
    }
}
