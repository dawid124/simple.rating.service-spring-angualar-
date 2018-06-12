package pl.webd.dawid124.simpleratingservice.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.webd.dawid124.simpleratingservice.file.model.Picture;
import pl.webd.dawid124.simpleratingservice.file.service.FileService;
import pl.webd.dawid124.simpleratingservice.products.model.FetchData;
import pl.webd.dawid124.simpleratingservice.products.model.Product;
import pl.webd.dawid124.simpleratingservice.products.model.ProductListModel;
import pl.webd.dawid124.simpleratingservice.products.service.ProductsService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class ProductsController {

    private ProductsService productsService;
    private FileService fileService;

    public ProductsController(ProductsService productsService, FileService fileService) {
        this.productsService = productsService;
        this.fileService = fileService;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> creteNewProduct(@RequestParam("product") @Valid String productStr,
                                             @RequestParam("picture") MultipartFile[] pictures)
            throws AuthenticationException, IOException {

        Product product = new ObjectMapper().readValue(productStr, Product.class);

        if (!product.valid()) {
            return new ResponseEntity<>("NOT_VALID", HttpStatus.BAD_REQUEST);
        }

        if (product.getId() > 0) {
            return updateProductAndGetResponse(pictures, product);
        } else {
            return createProductAndGetResponse(pictures, product);
        }
    }

    private ResponseEntity<?> createProductAndGetResponse(MultipartFile[] pictures, Product product) throws IOException {
        int updatedCount = productsService.createProduct(product);

        for (MultipartFile picture: pictures) {
            fileService.createFile(picture.getBytes(), product.getId());
        }

        if (updatedCount > 0) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    private ResponseEntity<?> updateProductAndGetResponse(MultipartFile[] pictures, Product product) throws IOException {
        int updatedCount = productsService.updateProduct(product);

        for (MultipartFile picture: pictures) {
            fileService.createFile(picture.getBytes(), product.getId());
        }

        product.getPictures().stream()
                .filter(Picture::isDeleted)
                .forEach(picture -> fileService.removeFile(picture));

        if (updatedCount > 0) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "public/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("id") long id) {
        Product product = productsService.getProduct(id);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "public/products", method = RequestMethod.POST)
    public ResponseEntity<?> fetchProducts(@RequestBody FetchData fetchData) {
        List<ProductListModel> products = productsService.fetchProducts(fetchData);

        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
