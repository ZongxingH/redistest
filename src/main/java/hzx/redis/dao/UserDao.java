package hzx.redis.dao;

import hzx.redis.bean.User;

import java.math.BigInteger;
import java.util.List;

/**
 * Introduction:  [Simple Introduction of the java documents]
 * Package Name:  hzx.redis.dao
 * Project Name:  redis.test
 * Author:  ZongxingH
 * Create Time:  2016/12/16 11:26
 */
public interface UserDao {
    public boolean save(User user);
    public boolean delete(User user);
    public User get(BigInteger id);
    public boolean update(User user);
    public List<User> getByIds(List<BigInteger> ids);
}
