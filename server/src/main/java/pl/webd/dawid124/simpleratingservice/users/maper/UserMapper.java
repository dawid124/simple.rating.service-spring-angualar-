package pl.webd.dawid124.simpleratingservice.users.maper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import pl.webd.dawid124.simpleratingservice.users.model.UserModel;

@Repository
public interface UserMapper {

    @Insert("insert into users(username,email,password,role,enabled) values(#{username},#{email},#{password},#{role},#{enabled})")
    int insertUser(UserModel userModel);

    @Select("select * from users WHERE username=#{username}")
    UserModel findByUsername(String username);
}
