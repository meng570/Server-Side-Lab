package com.stu.helloserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stu.helloserver.common.Result;
import com.stu.helloserver.common.ResultCode;
import com.stu.helloserver.dto.UserDTO;
import com.stu.helloserver.entity.User;
import com.stu.helloserver.mapper.UserMapper;
import com.stu.helloserver.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private HttpServletRequest request;

    @Override
    public Result<String> register(UserDTO userDTO) {
        // 1. 校验参数
        if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty()
                || userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            return Result.error(ResultCode.PARAM_ERROR);
        }

        // 2. 检查用户名是否已存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userDTO.getUsername());
        User existUser = userMapper.selectOne(wrapper);
        if (existUser != null) {
            return Result.error(ResultCode.USER_EXIST);
        }

        // 3. 插入新用户
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userMapper.insert(user);

        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        // 1. 校验参数
        if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty()
                || userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            return Result.error(ResultCode.PARAM_ERROR);
        }

        // 2. 查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userDTO.getUsername());
        User user = userMapper.selectOne(wrapper);

        // 3. 校验用户和密码
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(userDTO.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }

        // 4. 登录成功，将用户信息存入session
        request.getSession().setAttribute("loginUser", user);
        return Result.success("登录成功");
    }

    @Override
    public Result<String> getUserInfo(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        // 隐藏密码，只返回用户名
        return Result.success("用户名：" + user.getUsername());
    }
}