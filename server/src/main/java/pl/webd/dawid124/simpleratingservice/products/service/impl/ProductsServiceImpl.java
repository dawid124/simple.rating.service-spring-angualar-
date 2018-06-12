package pl.webd.dawid124.simpleratingservice.products.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.comments.mapper.CommentsMapper;
import pl.webd.dawid124.simpleratingservice.file.mapper.FileMapper;
import pl.webd.dawid124.simpleratingservice.products.mapper.ProductsMapper;
import pl.webd.dawid124.simpleratingservice.products.model.FetchData;
import pl.webd.dawid124.simpleratingservice.products.model.Product;
import pl.webd.dawid124.simpleratingservice.products.model.ProductListModel;
import pl.webd.dawid124.simpleratingservice.products.service.ProductsService;
import pl.webd.dawid124.simpleratingservice.ratings.mapper.RatingsMapper;
import pl.webd.dawid124.simpleratingservice.type.mapper.TypeMapper;

import java.util.List;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    private ProductsMapper productsMapper;
    private FileMapper fileMapper;
    private TypeMapper typeMapper;
    private RatingsMapper ratingsMapper;
    private CommentsMapper commentsMapper;

    public ProductsServiceImpl(ProductsMapper productsMapper, FileMapper fileMapper, TypeMapper typeMapper,
                               RatingsMapper ratingsMapper, CommentsMapper commentsMapper) {
        this.productsMapper = productsMapper;
        this.fileMapper = fileMapper;
        this.typeMapper = typeMapper;
        this.ratingsMapper = ratingsMapper;
        this.commentsMapper = commentsMapper;
    }

    @Override
    public int createProduct(Product product) {
        return productsMapper.insertProduct(product);
    }

    @Override
    public Product getProduct(long id) {
        Product product = productsMapper.getProduct(id);
        if (product == null) {
            return null;
        }
        
        product.setType(typeMapper.getTypeByProductId(id));
        product.setPictures(fileMapper.getPicturesByProductId(id));
        product.setRatings(ratingsMapper.getRatingsByProductId(id));
        product.setComments(commentsMapper.getCommentsByProductId(id));
        return product;
    }

    @Override
    public List<ProductListModel> fetchProducts(FetchData fetchData) {
        return productsMapper.fetchProducts(fetchData);
    }


}
