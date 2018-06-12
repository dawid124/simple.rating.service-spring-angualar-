package pl.webd.dawid124.simpleratingservice.products.service;

import pl.webd.dawid124.simpleratingservice.products.model.FetchData;
import pl.webd.dawid124.simpleratingservice.products.model.Product;
import pl.webd.dawid124.simpleratingservice.products.model.ProductListModel;

import java.util.List;

public interface ProductsService {

    int createProduct(Product product);

    Product getProduct(long id);

    List<ProductListModel> fetchProducts(FetchData fetchData);
}
