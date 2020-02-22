package rocks.zipcode.atm;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import rocks.zipcode.atm.bank.BasicAccount;

import java.util.logging.Logger;

public class BankTest {

    private static final Logger LOGGER = Logger.getLogger(BankTest.class.getName());
    @Test
    public void createAccountTest(){
        Bank bank = new Bank();
        String expectedName = "Chris";
        String expectedEmail = "dog@gmail.com";
        Integer expectedPin = 1234;
        Integer expectedBalance = 150;
        Integer expectedId = bank.createAccount(expectedName, expectedEmail, expectedPin, expectedBalance);
        Assert.assertEquals(1, Bank.getAccounts().size());
        Assert.assertTrue(Bank.getAccounts().containsKey(expectedId));
        Assert.assertEquals(expectedEmail, Bank.getAccounts().get(expectedId).getAccountData().getEmail());
        Assert.assertEquals(expectedName, Bank.getAccounts().get(expectedId).getAccountData().getName());
        Assert.assertEquals(expectedId, new Integer (Bank.getAccounts().get(expectedId).getAccountData().getId()));
        Assert.assertEquals(expectedBalance, new Integer (Bank.getAccounts().get(expectedId).getAccountData().getBalance()));
        Assert.assertEquals(expectedPin, new Integer (Bank.getAccounts().get(expectedId).getAccountData().getPin()));
        Assert.assertTrue(Bank.getAccounts().get(expectedId) instanceof BasicAccount);
    }
}
