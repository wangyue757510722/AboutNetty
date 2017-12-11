package user;

/**
 * Created by wangyue66 on 2017/12/5.
 */
public class User {
    private int age;
    public User(){
        this(20);
    }
    public User(int age){
        this.age=age;
    }
    public int getAge (){
        return age;
    }

    @Override
    public String toString(){
        return "User->age:"+age;
    }
}
