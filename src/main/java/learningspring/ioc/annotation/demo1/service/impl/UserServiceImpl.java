package learningspring.ioc.annotation.demo1.service.impl;

import learningspring.ioc.annotation.demo1.dao.UserDao;
import learningspring.ioc.annotation.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/

@Service("userService")
public class UserServiceImpl  implements UserService {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public void save() {
        System.out.println("UserServiceImpl.save");
        userDao.save();
    }
}
