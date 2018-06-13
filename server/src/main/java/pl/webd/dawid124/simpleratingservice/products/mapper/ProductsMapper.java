package pl.webd.dawid124.simpleratingservice.products.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import pl.webd.dawid124.simpleratingservice.products.model.FetchData;
import pl.webd.dawid124.simpleratingservice.products.model.Product;
import pl.webd.dawid124.simpleratingservice.products.model.ProductListModel;

import java.util.List;

@Repository
public interface ProductsMapper {

    String FETCH_PRODUCTS_QUERY =
            "select p.id, p.name, p.descriptions, p.price, avg(r.rating) as rating, min(pic.src) as picture " +
            "from products p " +
            "left join pictures pic on pic.product_fk = p.id " +
            "left join ratings r on r.product_fk = p.id " +
            "where p.name like #{searchText} " +
            "group by p.id " +
            "order by p.id asc " +
            "LIMIT #{limit} " +
            "OFFSET #{offset}";

    @Insert("insert into products(name,descriptions,price,color,producer,type_fk) " +
            "values(#{name},#{descriptions},#{price},#{color},#{producer},#{type.id})")
    @Options(useGeneratedKeys=true)
    int insertProduct(Product product);

    @Select("select * from products where id = #{id}")
    Product getProduct(long id);

    @Select(FETCH_PRODUCTS_QUERY)
    List<ProductListModel> fetchProducts(FetchData fetchData);

    @Update("update products set name = #{name}, descriptions = #{descriptions}, price = #{price}, " +
            "color = #{color}, producer = #{producer}, type_fk = #{type.id} where id = #{id}")
    int updateProduct(Product product);
}
