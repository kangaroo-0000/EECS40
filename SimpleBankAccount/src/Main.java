import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    /**
     * prints out the menu and loops. BankAccountMap is hashmap that maps name to the BankAccount instance.
     */
    private static final Scanner sc = new Scanner(System.in);
    private static final HashMap<String, BankAccount> BankAccountMap = new HashMap<>();
    public static void main(String[] args) {
        do {
            int option = selectAccountOption();

            switch (option) {
                case 1 -> { // Create Account
                    BankAccount account = create();
                    if (account != null) BankAccountMap.put(account.getName(), account);
                }
                case 2 -> // Display
                {
                    System.out.println("Enter your name: ");
                    String queriedName = sc.next();
                    BankAccount ba = BankAccountMap.get(queriedName);
                    if (null != ba) ba.display();
                    else System.out.println("Name: " + queriedName + " does not exist");
                }
                case 3 -> // Withdraw
                {
                    System.out.println("**Transaction - Withdraw**");
                    System.out.println("Enter your name: ");
                    String queriedName = sc.next();
                    System.out.println("Enter amount to withdraw: ");
                    double withdrawalAmount = sc.nextDouble();
                    BankAccount ba = BankAccountMap.get(queriedName);
                    if (null != ba) ba.withdraw(withdrawalAmount);
                    else System.out.println("Name: " + queriedName + " does not exist");
                }
                case 4 -> // Deposit
                {
                    System.out.println("**Transaction - Deposit**");
                    System.out.println("Enter your name: ");
                    String queriedName = sc.next();
                    System.out.println("Enter Amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    BankAccount ba = BankAccountMap.get(queriedName);
                    if (null != ba) ba.deposit(depositAmount);
                    else System.out.println("Name: " + queriedName + " does not exist");
                }
                case 5 -> // Display ALL
                {
                    if(BankAccountMap.isEmpty()) System.out.println("No accounts currently in this Bank.");
                    else for (BankAccount ba : BankAccountMap.values()) {
                        if (ba.getClass().getName().contains("VIP")) System.out.println("VIP Account Details");
                        else System.out.println("Standard Account Details");
                        ba.display();
                        System.out.println();
                    }
                }
                case 6 -> // Remove Account
                {
                    System.out.println("**Transaction - remove Account**");
                    System.out.println("Enter your name: ");
                    String queriedName = sc.next();
                    if (null != BankAccountMap.remove(queriedName)) System.out.println("Account has been removed!!");
                    else System.out.println("Name: " + queriedName + " does not exist");
                }
                case 7 -> // Calculate Interest
                {
                    System.out.println("**Transaction - calculate interests**");
                    System.out.println("Enter your name: ");
                    String queriedName = sc.next();
                    System.out.println("Enter number of months: ");
                    int months = sc.nextInt();
                    BankAccount ba = BankAccountMap.get(queriedName);
                    if (null != ba) System.out.println("The expected interest is: " + ba.calculateInterest(months));
                    else System.out.println("Name: " + queriedName + " does not exist");
                }
                case 8 -> { //Exit
                    System.out.println("Thanks for banking with us!!");
                    return;
                }
                default -> System.out.println();
            }
        } while(true);
    }
    private static int selectAccountOption() {
        do {
            System.out.println("*** Menu ***");
            System.out.println("1. Create Account ");
            System.out.println("2. Display ");
            System.out.println("3. Withdraw ");
            System.out.println("4. Deposit ");
            System.out.println("5. Display All ");
            System.out.println("6. Remove Account ");
            System.out.println("7. Calculate Interest ");
            System.out.println("8. Exit ");
            System.out.println();
            System.out.print("Enter your choice: ");
            int option;
            try {
                option = sc.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Try again and enter choice between 1-8!!");
                sc.next();
                return 0;
            }
            if (1 <= option && option <= 8) return option;
            System.out.println("Try again and enter choice between 1-8!!");
        } while (true);
    }
    private static BankAccount create() {
        BankAccount ba = null;
        int accountTypeChoice;
        String name;
        double balance;
        System.out.println("""
                **Create New Account**
                1. Create Standard Account\s
                2. Create VIP Account""");
        System.out.println("Enter your choice: ");
        try {
            accountTypeChoice = sc.nextInt();
        } catch(InputMismatchException e) {
            System.out.println("Please input an integer. Try again.");
            sc.next();
            return null;
        }
        System.out.println("Enter your name: ");
        name = sc.next();
        System.out.println("Enter your starting balance: ");
        try {
            balance = sc.nextDouble();
        }catch(InputMismatchException e) {
            System.out.println("Please enter a number! Try again.");
            sc.next();
            return null;
        }
        if(accountTypeChoice == 1) ba = new StandardAccount(balance, name);
        else if(accountTypeChoice == 2) ba = new VIPAccount(balance, name);
        else System.out.println("Wrong Choice!");
        System.out.println("Account Created");
        return ba;
    }

}
