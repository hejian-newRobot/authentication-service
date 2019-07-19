package com.service.auth.dao;

/**
 * 项目名称：auth-service
 * 包名称:com.service.auth.dao
 * 类描述：
 * 创建人：hejian
 * 创建时间：2019/6/20 10:12
 * 修改人：hejian
 * 修改时间：2019/6/20 10:12
 * 修改备注：
 *
 * @author hejian
 */


import com.service.auth.entity.Account;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 账户数据库操作类
 *
 * @author hejian
 */
@Component
@Mapper
public interface UserDao {

    /**
     * 根据用户名查找账户信息
     *
     * @param username 用户名
     * @return 账户信息
     */
    Account findByUserName(@Param("username") String username);

    /**
     * 删除所有记录
     */
    void deleteAll();

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     */
    void save(@Param("user") Account user);
}
