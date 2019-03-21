package learningspring.transaction.declarative;

/**
 * AccountDao
 *
 * @author Chen Rui
 * @version 1.0
 **/
public interface AccountDao {

    /**
     * 转出
     * @param from 转出账户
     * @param money 转出金额
     */
    void out(String from, double money);

    /**
     * 转入
     * @param to 转入账户
     * @param money 转入金额
     */
    void in(String to, double money);
}
