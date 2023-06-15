public class VIPAccount implements BankAccount{
    public double getMoney() {
        return money;
    }
    private double money;
    public String getName() {
        return name;
    }
    private final String name;
    public double getMonthlyInterest() {
        return 0.01;
    }
    VIPAccount(double money, String name){
        /**
         * Two parameters: money is the balance of account and name is the name associated with the account.
         */
        this.money = money;
        this.name = name;
    }
    public double deposit(double amount){
        if(money < 0) {
            System.out.println("Please enter a non-negative amount.");
            return 0;
        }
        this.money += amount;
        System.out.println(this);
        return this.money;
    }
    public double withdraw(double amount){
        if(money < 0) {
            System.out.println("Please enter a non-negative amount.");
            return 0;
        }
        if(this.money - amount < 0) {
            System.out.println("Not enough balance");
            return 0;
        }
        this.money -= amount;
        System.out.println(this);
        return this.money;
    }
    public void display(){
        System.out.println("**Account Details**");
        System.out.println("Name: " + this.name);
        System.out.println("Account Type: VIP");
        System.out.println("Balance: " + this.money);
    }
    public double calculateInterest(int months) {
        return getMonthlyInterest()*this.money*months;
    }
    @Override
    public String toString() {
        return "Name: " + this.name + "\n" + "Balance: " + this.money;
    }
}
