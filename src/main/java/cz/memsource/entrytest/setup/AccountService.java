package cz.memsource.entrytest.setup;

/**
 * Account service. Provides methods for obtaining and updating user account credentials.
 */
public interface AccountService {

    /**
     * Get user account credentials.
     *
     * @return user account credentials
     */
    Account getAccount();

    /**
     * Save user account credentials.
     *
     * @param account user credentials
     */
    void saveAccount(Account account);
}
