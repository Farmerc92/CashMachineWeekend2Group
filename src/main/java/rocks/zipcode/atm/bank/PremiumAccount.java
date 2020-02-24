package rocks.zipcode.atm.bank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZipCodeWilmington
 */
public class PremiumAccount extends Account {

    private static final int OVERDRAFT_LIMIT = 100;


    @JsonCreator
    public PremiumAccount(@JsonProperty("accountData") AccountData accountData) {
        super(accountData);
    }

    @Override
    protected boolean canWithdraw(int amount) {
        return getBalance() + OVERDRAFT_LIMIT >= amount;
    }
}
