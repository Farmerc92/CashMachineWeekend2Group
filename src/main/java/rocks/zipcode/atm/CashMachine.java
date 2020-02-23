package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author ZipCodeWilmington
 */
public class CashMachine {

    private final Bank bank;
    private AccountData accountData = null;

    public CashMachine(Bank bank) {
        this.bank = bank;
    }

    private Consumer<AccountData> update = data -> {
        accountData = data;
    };

    public void login(int pin) {
        tryCall(
                () -> bank.getAccountById(pin),
                update
        );
    }

    /*public void getPin(int pin) {
        tryCall(
                () -> bank.getAccountByPin(pin),
                update
        );
    }*/

    public void deposit(int amount) {
        if (accountData != null && amount > 0) {
            tryCall(
                    () -> bank.deposit(accountData, amount),
                    update
            );
        }
    }

    public void withdraw(int amount) {
        if (accountData != null && amount > 0) {
            tryCall(
                    () -> bank.withdraw(accountData, amount),
                    update
            );
        }
    }

    public void clear() {
        if (accountData != null) {
            accountData = null;
        }
    }

    public void exitProgram() {
        try {
            Bank.saveBankAccounts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public String toString() {
        return accountData != null ? accountData.toString() : "Try account 1000 or 2000 and click submit.";
    }

    private <T> void tryCall(Supplier<ActionResult<T> > action, Consumer<T> postAction) {
        try {
            ActionResult<T> result = action.get();
            if (result.isSuccess()) {
                T data = result.getData();
                postAction.accept(data);
            } else {
                String errorMessage = result.getErrorMessage();
                throw new RuntimeException(errorMessage);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
