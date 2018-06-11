package pl.webd.dawid124.simpleratingservice.products.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pl.webd.dawid124.simpleratingservice.products.model.Product;

@Repository
public interface ProductsMapper {

    @Insert("insert into products(name,descriptions,price,color,producer,type_fk) " +
            "values(#{name},#{descriptions},#{price},#{color},#{producer},#{type.id})")
    @Options(useGeneratedKeys=true)
    int insertProduct(Product product);

    @Select("select * from products where id = #{id}")
    Product getProduct(long id);
}
