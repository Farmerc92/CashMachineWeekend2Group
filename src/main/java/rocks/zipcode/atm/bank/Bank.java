package rocks.zipcode.atm.bank;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import rocks.zipcode.atm.ActionResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {


    private static Map<Integer, Account> accounts = new HashMap<Integer, Account>();

    public Bank() {
//        accounts.put(1000, new BasicAccount(new AccountData(
//                1000, 1234, "Example 1", "example1@gmail.com", 500
//        )));
//
//        accounts.put(2000, new PremiumAccount(new AccountData(
//                2000, 1234,"Example 2", "example2@gmail.com", 200
//        )));
    }

    public static Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with those credentials. \nPlease Try again or create a new account.");
        }
    }

    /*public ActionResult<AccountData> getAccountByPin(int pin) {
        Account account = accounts.get(pin);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + pin + "\nPlease Try again or create a new account.");
        }
    }*/

// In progress - Giles ************************************************************************************
    /*public ActionResult<AccountData> createAccount(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }*/
//****************************************************************************************************************
    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }

    public Integer createAccount(String name, String email, Integer pin, Integer balance){
        if (balance > 200){
            int premiumId = AccountData.getRandomIdPremiumAccount();
            while (this.accounts.containsKey(premiumId)){
                premiumId = AccountData.getRandomIdPremiumAccount();
            }
            this.accounts.put(premiumId, new PremiumAccount(new AccountData(premiumId, pin, name, email, balance)));
            return premiumId;
        }
        else {
            int basicId = AccountData.getRandomIdBasicAccount();
            while(this.accounts.containsKey(basicId)){
                basicId = AccountData.getRandomIdBasicAccount();
            }
            this.accounts.put(basicId, new BasicAccount(new AccountData(basicId, pin, name, email, balance)));
            return basicId;
        }
    }

    public void loadBankAccounts() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        accounts = objectMapper.readValue(new File("accounts.json"), new TypeReference<HashMap<Integer, Account>>() {
        });

    }

    public static void saveBankAccounts() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithType(new TypeReference<HashMap<Integer,Account>>() {} ).writeValue(new File("accounts.json"), accounts);

    }
}
