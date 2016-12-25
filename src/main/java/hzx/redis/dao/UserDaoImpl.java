package hzx.redis.dao;

import com.alibaba.fastjson.JSON;
import hzx.redis.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Introduction:  [Simple Introduction of the java documents]
 * Package Name:  hzx.redis.dao
 * Project Name:  redis.test
 * Author:  ZongxingH
 * Create Time:  2016/12/16 12:23
 */
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    public boolean save(final User user) {
        Object result = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.set(
                        redisTemplate.getStringSerializer().serialize(user.getUid(user.getId())),
                        redisTemplate.getStringSerializer().serialize(JSON.toJSONString(user)));
                return new BigInteger("1");
            }
        });
        if ((new BigInteger("1")).equals(result)){
            return true;
        }
        return false;
    }

    public boolean delete(final User user) {
        Object result = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) {
                connection.del(redisTemplate.getStringSerializer().serialize(User.genUid(user.getId())));
                return new BigInteger("1");
            }
        });
        if ((new BigInteger("1")).equals(result)){
            return true;
        }
        return false;
    }

    public User get(final BigInteger id) {
        return redisTemplate.execute(new RedisCallback<User>() {
            public User doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] key = redisTemplate.getStringSerializer().serialize(User.genUid(id));
                if (connection.exists(key)) {
                    byte[] value = connection.get(key);
                    String jsonUser = redisTemplate.getStringSerializer().deserialize(value);
                    User user = JSON.parseObject(jsonUser,User.class);
                    return user;
                }
                System.out.println("根据用户ID没有查到对应的用户，用户ID为[" + id + "]");
                return null;
            }
        });
    }

    public boolean update(User user) {
        User user1 = get(user.getId());
        if (null == user1){
            System.out.println("根据用户ID没有查询到对应的数据");
            return false;
        }
        User newUer = new User();
        newUer.setId(user.getId() != null ? user.getId() : user1.getId());
        newUer.setName(user.getName() != null ? user.getName() : user1.getName());
        newUer.setSex(user.getSex() != null ? user.getSex() : user1.getSex());
        newUer.setAddr(user.getAddr() != null ? user.getAddr() : user1.getAddr());
        newUer.setAge(user.getAge() != 0 ? user.getAge() : user1.getAge());
        return save(newUer);
    }

    public List<User> getByIds(List<BigInteger> ids) {
        List<User> users = new ArrayList<User>();
        if (null == ids || ids.size() == 0){
            System.out.println("ID列表为空，请检查！");
            return users;
        }
        for (BigInteger id : ids){
            User user = get(id);
            if (null != user) {
                users.add(user);
            }
        }
        return users;
    }
}
