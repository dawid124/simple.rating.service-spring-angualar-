package pl.webd.dawid124.simpleratingservice.ratings.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pl.webd.dawid124.simpleratingservice.ratings.model.Rating;

import java.util.List;

@Repository
public interface RatingsMapper {

    @Insert("insert into ratings(user_fk,product_fk,rating,date,descriptions) values(#{userId},#{productId},#{rating},#{date},#{descriptions})")
    @Options(useGeneratedKeys=true)
    int insertRating(Rating rating);

    @Select("select r.*, u.id as userId, u.username from ratings r join users u on u.id = r.user_fk WHERE product_fk=#{productId}")
    List<Rating> getRatingsByProductId(long productId);

    @Select("select distinct u.email from ratings r join users u on u.id = r.user_fk WHERE product_fk=#{productId}")
    List<String> getRatingUsers(long productId);
}
