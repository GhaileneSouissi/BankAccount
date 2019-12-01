import models.BankAccount;
import models.Types;
import models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

public class OperationTest {

    private static User user;
    BankAccount account1 = new BankAccount();
    BankAccount account2 = new BankAccount();

    @Before
    public void init() {
        user = new User("ghailene", "25");
        user.createBankAccount(account1);
        user.createBankAccount(account2);

    }

    @Test(expected = InvalidParameterException.class)
    public void depositMoneyTest1() {
        user.depositOrWithdraw(0, null, null);
    }

    @Test
    public void depositMoneyTest2() {
        Boolean operationStatus = user.depositOrWithdraw(500, account1, Types.Deposit);

        Assert.assertEquals(account1.getAmount(), 500);
        Assert.assertEquals(account2.getAmount(), 0);
        Assert.assertEquals(operationStatus, true);

    }

    @Test
    public void withdrawalMoneyTest1() {
        user.depositOrWithdraw(500, account1, Types.Deposit);
        Boolean operationStatus = user.depositOrWithdraw(300, account1, Types.Withdrawal);

        Assert.assertEquals(account1.getAmount(), 200);
        Assert.assertEquals(account2.getAmount(), 0);
        Assert.assertEquals(operationStatus, true);
    }

    @Test
    public void withdrawalMoneyTest2() {

        user.depositOrWithdraw(500, account1, Types.Deposit);
        Boolean operationStatus = user.depositOrWithdraw(600, account1, Types.Withdrawal);

        Assert.assertEquals(account1.getAmount(), 500);
        Assert.assertEquals(account2.getAmount(), 0);
        Assert.assertEquals(operationStatus, false);

    }

    @Test
    public void showHistoryTest() {
        user.depositOrWithdraw(500, account1, Types.Deposit);
        user.depositOrWithdraw(300, account1, Types.Withdrawal);
        user.depositOrWithdraw(500, account1, Types.Deposit);

        Assert.assertEquals(user.showHistory(account1).size(), 3);

    }

    @After
    public void clean() {
        user = null;
        account1 = null;
        account2 = null;
    }


}
