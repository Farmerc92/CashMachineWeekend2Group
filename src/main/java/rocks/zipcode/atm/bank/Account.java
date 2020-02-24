package rocks.zipcode.atm.bank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BasicAccount.class, name = "BasicAccount"),

        @JsonSubTypes.Type(value = PremiumAccount.class, name = "PremiumAccount") }
)

/**
 * @author ZipCodeWilmington
 */
public abstract class Account {

    private AccountData accountData;

    public Account(AccountData accountData) {
        this.accountData = accountData;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void deposit(int amount) {
        updateBalance(getBalance() + amount);
    }

    public boolean withdraw(int amount) {
        if (canWithdraw(amount)) {
            updateBalance(getBalance() - amount);
            return true;
        } else {
            return false;
        }
    }

    protected boolean canWithdraw(int amount) {
        return getBalance() >= amount;
    }

    public int getBalance() {
        return accountData.getBalance();
    }

    private void updateBalance(int newBalance) {
        accountData = new AccountData(accountData.getId(), accountData.getPin(), accountData.getName(), accountData.getEmail(),
                newBalance);
    }
}
