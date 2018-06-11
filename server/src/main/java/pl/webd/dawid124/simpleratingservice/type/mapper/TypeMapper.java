package pl.webd.dawid124.simpleratingservice.type.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pl.webd.dawid124.simpleratingservice.type.model.Type;

import java.util.List;

@Repository
public interface TypeMapper {

    @Insert("insert into product_types(name,descriptions) values(#{name},#{descriptions})")
    @Options(useGeneratedKeys=true)
    int insertType(Type type);

    @Select("select * from product_types where id = #{id}")
    Type getTypeById(long id);

    @Select("select t.* from product_types t join products p on p.TYPE_FK = t.id where p.id = #{id}")
    Type getTypeByProductId(long id);

    @Select("select * from product_types")
    List<Type> fetchAllTypes();
}
