package pl.webd.dawid124.simpleratingservice.ratings.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;
import pl.webd.dawid124.simpleratingservice.ratings.model.Rating;

@Repository
public interface RatingsMapper {

    @Insert("insert into ratings(user_fk,product_fk,rating,date,descriptions) values(#{userId},#{productId},#{rating},#{date},#{descriptions})")
    @Options(useGeneratedKeys=true)
    int insertRating(Rating rating);
}
