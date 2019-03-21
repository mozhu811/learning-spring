package learningspring.transaction.programmatic;

/**
 * AccountService
 *
 * @author Chen Rui
 * @version 1.0
 **/
public interface AccountService {

    /**
     * 转账
     * @param from 转出账户
     * @param to 转入账户
     * @param money 交易金额
     */
    void transfer(String from, String to, Double money);
}
