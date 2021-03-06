package models;

import operations.OperationsInterface;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/***
 * the bank user/client
 */
public class User implements OperationsInterface {
    //user Id
    private UUID userId;

    private String name;
    private String age;
    //user bank accounts, a user can have multiple bank accounts
    private ArrayList<BankAccount> bankAccounts;

    //user constructor, a random uuid is given to each user
    public User(String name, String age) {
        this.userId = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.bankAccounts = new ArrayList<>();

    }

    /***
     * make an operation
     * if the operation is successful, the operation status will be set to true, otherwise it will be false.
     * @param amount
     * @param account
     * @param type
     * @return
     */
    public Boolean depositOrWithdraw(int amount, BankAccount account, Types type) {

        Boolean operationStatus = false;

        //parameter validation
        if (amount == 0)
            throw new InvalidParameterException("amount should not be equal to 0");
        if (account == null)
            throw new InvalidParameterException("bank account should not be equal to null");
        if (account == null)
            throw new InvalidParameterException("the operation type should be specified, not equals to null");

        int actualAmount = account.getAmount();

        if (type == Types.Deposit) {
            //create a new operation, each operation has a date time (the system date time)
            Operation operation = new Operation(LocalDateTime.now(), Types.Deposit, amount);
            //add operation to the operation list, to display later the history if needed
            account.addOperations(operation);
            //add the amount
            account.setAmount(actualAmount + amount);
            //set the status to true
            operationStatus = true;

        } else if (type == Types.Withdrawal) {
            //simple verification, a user cannot withdrawal an amount of money higher than the existing one
            if (actualAmount > amount) {
                Operation operation = new Operation(LocalDateTime.now(), Types.Withdrawal, amount);
                account.addOperations(operation);
                account.setAmount(actualAmount - amount);
                operationStatus = true;
            } else
                System.out.println("you can't widhrawal this amount, you only have "
                        + actualAmount + ", consider taking a credit");
        }

        return operationStatus;
    }

    /***
     * display the operation history, account must not be null
     * @param account
     * @return
     */
    public ArrayList<Operation> showHistory(BankAccount account) {
        if (account == null) throw new InvalidParameterException("account must not be null");
        for (Operation operation : account.getOperation()) {
            System.out.println(operation.toString());
        }
        return account.getOperation();
    }


    /**
     * create the bank account, account must not be null
     *
     * @param account
     */
    public void createBankAccount(BankAccount account) {
        if (account == null) throw new InvalidParameterException("account must not be null");
        bankAccounts.add(account);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", bankAccounts=" + bankAccounts +
                '}';
    }
}
