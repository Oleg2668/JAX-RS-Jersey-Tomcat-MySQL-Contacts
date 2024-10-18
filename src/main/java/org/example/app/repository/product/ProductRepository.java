package org.example.app.repository.product;

import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.Product;
import org.example.app.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product, ProductDtoRequest> {
    // Створення нового запису
    void save(ProductDtoRequest request);
    // Отримання всіх записів
    Optional<List<Product>> getAll();

    // ---- Path Params ----------------------

    // Отримання запису за id
    Optional<Product> getById(Long id);
    // Оновлення запису за id
    void update(Long id, ProductDtoRequest request);
    // Видалення запису за id
    boolean deleteById(Long id);
    // Перевірка наявності id
    boolean isIdExists(Long id);

    // ---- Utils ----------------------

    // Отримання останнього запису
    Optional<Product> getLastEntity();
}