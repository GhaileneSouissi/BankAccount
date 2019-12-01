package operations;

import models.BankAccount;
import models.Operation;
import models.Types;

import java.util.ArrayList;


/***
 * all the actions that a simple user can do with his bank account
 *  - deposit
 *  - withdrawal
 *  - show transactions history
 *  - create a bank account
 *  - take a credit : new feature to develop
 */
public interface OperationsInterface {

    Boolean depositOrWithdraw(int amount, BankAccount account, Types types);

    ArrayList<Operation> showHistory(BankAccount account);

    void createBankAccount(BankAccount account);

}
