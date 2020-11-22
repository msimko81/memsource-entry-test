package cz.memsource.entrytest.setup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
/**
 * {@inheritDoc}
 *
 * Uses JPA for persisting account credentials.
 */
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    @Override
    public Account getAccount() {
        return accountDao.findAll().get(0);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.deleteAll();
        accountDao.save(account);
    }
}
