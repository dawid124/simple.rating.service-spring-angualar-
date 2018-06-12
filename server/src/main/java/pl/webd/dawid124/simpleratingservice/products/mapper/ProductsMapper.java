package pl.webd.dawid124.simpleratingservice.products.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pl.webd.dawid124.simpleratingservice.products.model.FetchData;
import pl.webd.dawid124.simpleratingservice.products.model.Product;
import pl.webd.dawid124.simpleratingservice.products.model.ProductListModel;

import java.util.List;

@Repository
public interface ProductsMapper {

    String FETCH_PRODUCTS_QUERY =
            "select p.id, p.name, p.descriptions, avg(r.rating) as rating, min(pic.src) " +
            "from products p " +
            "join pictures pic on pic.product_fk = p.id " +
            "join ratings r on r.product_fk = p.id " +
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
}
