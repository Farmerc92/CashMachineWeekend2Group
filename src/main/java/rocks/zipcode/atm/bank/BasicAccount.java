package rocks.zipcode.atm.bank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZipCodeWilmington
 */
public class BasicAccount extends Account {

    @JsonCreator
    public BasicAccount(@JsonProperty("accountData") AccountData accountData) {
        super(accountData);
    }
}
