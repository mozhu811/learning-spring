package learningspring.transaction.programmatic;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * AccountDao实现类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    @Override
    public void out(String from, double money) {
        this.getJdbcTemplate().update("UPDATE account SET money = money - ? WHERE name = ?", money, from);
    }

    @Override
    public void in(String to, double money) {
        this.getJdbcTemplate().update("UPDATE account SET money = money + ? WHERE name = ?", money, to);
    }
}
