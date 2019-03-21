package learningspring.transaction.declarative;


import org.springframework.transaction.annotation.Transactional;

/**
 * AccountService实现类
 *
 * @author Chen Rui
 * @version 1.0
 **/
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService{

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
    public void transfer(String from, String to, Double money) {
        accountDao.out(from, money);
        int i = 1/0;
        accountDao.in(to,money);

    }
}
