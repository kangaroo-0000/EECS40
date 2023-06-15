interface BankAccount {
    /**
     * Interface BankAccount. To be implemented by standard and VIP account.
     */
    String getName();

    double deposit(double amount);

    double withdraw(double amount);

    void display();

    double calculateInterest(int months);
}
