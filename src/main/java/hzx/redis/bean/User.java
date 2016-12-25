package hzx.redis.bean;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Introduction:  [Simple Introduction of the java documents]
 * Package Name:  hzx.redis.bean
 * Project Name:  redis.test
 * Author:  ZongxingH
 * Create Time:  2016/12/16 11:38
 */
public class User implements Serializable{

    private static final long serialVersionUID = 1748735942704123010L;
    private String uid;
    private BigInteger id;
    private String name;
    private String addr;
    private String sex;
    private int age;

    public User(){}

    public User(BigInteger id, String name, String addr, String sex, int age) {
        if (null == id){
            System.out.println("用户ID不能为空");
            return;
        }
        this.id = id;
        this.uid = (31 * id.hashCode()) + "";
        this.name = name;
        this.addr = addr;
        this.sex = sex;
        this.age = age;
    }

    public static String genUid(BigInteger id){
        return (31 * id.hashCode()) + "";
    }
    public String getUid(BigInteger id) {
        this.setUid(genUid(id));
        return genUid(id);
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (addr != null ? !addr.equals(user.addr) : user.addr != null) return false;
        return sex != null ? sex.equals(user.sex) : user.sex == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}
