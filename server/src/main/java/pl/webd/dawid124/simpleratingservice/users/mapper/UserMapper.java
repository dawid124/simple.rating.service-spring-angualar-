package pl.webd.dawid124.simpleratingservice.users.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pl.webd.dawid124.simpleratingservice.users.model.UserModel;

@Repository
@Mapper
public interface UserMapper {

    @Insert("insert into users(username,email,password,role,enabled) values(#{username},#{email},#{password},#{role},#{enabled})")
    int insertUser(UserModel userModel);

    @Select("select * from users WHERE username=#{username}")
    UserModel findByUsername(String username);
}
