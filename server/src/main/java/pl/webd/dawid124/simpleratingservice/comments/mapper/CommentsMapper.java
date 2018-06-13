package pl.webd.dawid124.simpleratingservice.comments.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pl.webd.dawid124.simpleratingservice.comments.model.Comment;

import java.util.List;

@Repository
public interface CommentsMapper {

    @Insert("insert into comments(user_fk,product_fk,date,descriptions) values(#{userId},#{productId},#{date},#{descriptions})")
    @Options(useGeneratedKeys=true)
    int insertComment(Comment comment);

    @Select("select c.*, u.id as userId, u.username from comments c join users u on u.id = c.user_fk WHERE product_fk=#{productId}")
    List<Comment> getCommentsByProductId(long id);

    @Select("select distinct u.email from comments c join users u on u.id = c.user_fk WHERE product_fk=#{productId}")
    List<String> getCommentingUsers(long productId);
}
