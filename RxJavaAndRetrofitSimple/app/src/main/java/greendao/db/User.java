package greendao.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * 作者：quzongyang
 * 创建时间：2017/3/24
 * 类描述：
 */

@Entity
public class User {
    @Id
    private String phone;
    private String name;
    private String age;
    private String address;
    private String sex;
    @Property(nameInDb = "password")
    private String psw;
    @Generated(hash = 217470630)
    public User(String phone, String name, String age, String address, String sex,
            String psw) {
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
        this.psw = psw;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getPsw() {
        return this.psw;
    }
    public void setPsw(String psw) {
        this.psw = psw;
    }

}
