package hzx.redis.main;

import hzx.redis.bean.User;
import hzx.redis.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Introduction:  [Simple Introduction of the java documents]
 * Package Name:  hzx.redis.main
 * Project Name:  redis.test
 * Author:  ZongxingH
 * Create Time:  2016/12/16 13:08
 */
public class RedisTestMain {
    public static void main(String[] args){
        // redis单节点测试
//        ApplicationContext app = new ClassPathXmlApplicationContext("redis-cluster-appctx.xml");
        // redis集群测试
        ApplicationContext app = new ClassPathXmlApplicationContext("redis-cluster-appctx.xml");
        UserDao userDao = (UserDao) app.getBean("userDaoImpl");
        User user1 = new User(new BigInteger("23400000001"),"AAAAAA","安徽.合肥","男",21);
        User user2 = new User(new BigInteger("23400000002"),"BBBBBB","中国.上海","女",19);
        User user3 = new User(new BigInteger("23400000003"),"CCCCCC","中国.北京","男",25);
        User user4 = new User(new BigInteger("23400000004"),"DDDDDD","中国.合肥","男",22);

        // 保存
        System.out.println("-------------------------------------保存---------------------------------------");
        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);
        userDao.save(user4);
        System.out.println("保存成功");
        System.out.println("--------------------------------------------------------------------------------");

        // 查询
        System.out.println("-------------------------------------查询---------------------------------------");
        User u1 = userDao.get(user1.getId());
        System.out.println(u1);
        User u2 = userDao.get(user2.getId());
        System.out.println(u2);
        User u3 = userDao.get(user3.getId());
        System.out.println(u3);
        User u4 = userDao.get(user4.getId());
        System.out.println(u4);
        System.out.println("--------------------------------------------------------------------------------");

        // 修改
        System.out.println("-------------------------------------修改---------------------------------------");
        User newUser = new User();
        newUser.setId(user1.getId());
        newUser.setAddr("中国.合肥");
        userDao.update(newUser);

        User u11 = userDao.get(user1.getId());
        System.out.println(u11);
        System.out.println("--------------------------------------------------------------------------------");

        // 删除
        System.out.println("---------------------------------------删除-------------------------------------");
        userDao.delete(user1);

        List<BigInteger> ids = new ArrayList<BigInteger>();
        ids.add(user1.getId());
        ids.add(user2.getId());
        ids.add(user3.getId());
        ids.add(user4.getId());
        List<User> users = userDao.getByIds(ids);
        for (User u : users){
            System.out.println(u);
        }
        System.out.println("--------------------------------------------------------------------------------");

    }
}
