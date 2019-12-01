package models;

import java.util.ArrayList;
import java.util.UUID;

/***
 * a bank account, each bank account has a user and a uuid
 */
public class BankAccount {

    //a random uuid
    private UUID accountId;
    //the existing amount
    private int amount;
    //the operations performed by a user
    private ArrayList<Operation> operations;
    //the owner of the bank account : further features need to be developed ...
    private User user;

    public BankAccount() {
        this.accountId = UUID.randomUUID();
        this.amount = 0;
        this.operations = new ArrayList<Operation>();
    }

    //Getters and Setters

    public ArrayList<Operation> getOperation() {
        return operations;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /***
     * add each operation performed to a list of operations, needed to display operations later.
     * @param operation
     */
    public void addOperations(Operation operation) {
        if (operation != null)
            this.operations.add(operation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return accountId.equals(that.accountId);
    }


    @Override
    public String toString() {
        return "BankAccount{" +
                "accoundId =" + accountId +
                ", operation =" + operations +
                ", user =" + user +
                '}';
    }
}
