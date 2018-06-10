package pl.webd.dawid124.simpleratingservice.products.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.products.mapper.ProductsMapper;
import pl.webd.dawid124.simpleratingservice.products.model.Product;
import pl.webd.dawid124.simpleratingservice.products.service.ProductsService;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    private ProductsMapper productsMapper;

    public ProductsServiceImpl(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    @Override
    public int createProduct(Product product) {
        return productsMapper.insertProduct(product);
    }


}
