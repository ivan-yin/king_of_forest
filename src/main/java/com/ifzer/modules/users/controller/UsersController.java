package com.ifzer.modules.users.controller;


import com.ifzer.common.BaseController;
import com.ifzer.modules.users.entity.Users;
import com.ifzer.modules.users.service.IUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * <p>
 *  前端控制器: UsersController
 * </p>
 * @author nelson
 * @since 2018-04-19
 * @version 1.0
 * @copyright ifzer.com
 */
@Controller
@RequestMapping("/users/")
public class UsersController extends BaseController<Users>{

    private final static Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private IUsersService usersService;

    @GetMapping("list")
    public ModelAndView list(HttpServletResponse response, ModelAndView mv){
//        RespData respData = new RespData();
//        final List<Users> users = usersService.selectList(new EntityWrapper<>());
//        respData.setListData(users);
//        mv.addObject(respData);

        mv.setViewName("/modules/users/list");// /modules/users/list or list 都可以，但要把模板放在resources的相对目录下
        mv.getModel().put("headerList", getHeaderList(eClz));
        return mv;
    }

}
