package learningspring.ioc.annotation.demo1.controller;

import learningspring.ioc.annotation.demo1.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * UserController
 *
 * @author Chen Rui
 * @version 1.0
 **/
@Controller("userController")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;


}
