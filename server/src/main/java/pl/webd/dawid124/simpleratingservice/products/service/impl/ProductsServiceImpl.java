package pl.webd.dawid124.simpleratingservice.products.service.impl;

import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.file.mapper.FileMapper;
import pl.webd.dawid124.simpleratingservice.file.model.Picture;
import pl.webd.dawid124.simpleratingservice.products.mapper.ProductsMapper;
import pl.webd.dawid124.simpleratingservice.products.model.Product;
import pl.webd.dawid124.simpleratingservice.products.service.ProductsService;

import java.util.List;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    private ProductsMapper productsMapper;
    private FileMapper fileMapper;

    public ProductsServiceImpl(ProductsMapper productsMapper, FileMapper fileMapper) {
        this.productsMapper = productsMapper;
        this.fileMapper = fileMapper;
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

        List<Picture> pictures = fileMapper.getPicturesByProductId(id);
        product.setPictures(pictures);
        return product;
    }


}
