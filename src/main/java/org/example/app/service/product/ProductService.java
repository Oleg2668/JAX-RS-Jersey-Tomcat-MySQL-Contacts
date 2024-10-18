package org.example.app.service.product;

import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.Product;
import org.example.app.service.BaseService;

import java.util.List;

public interface ProductService extends BaseService<Product, ProductDtoRequest> {

    // Створення нового запису
    Product create(ProductDtoRequest request);
    // Отримання всіх записів
    List<Product> getAll();

    // ---- Path Params ----------------------

    // Отримання запису за id
    Product getById(Long id);
    // Оновлення запису за id
    Product update(Long id, ProductDtoRequest request);
    // Видалення запису за id
    boolean deleteById(Long id);

}
