package org.example.app.service.product;

import jakarta.inject.Inject;
import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.Product;
import org.example.app.repository.product.ProductRepository;


import java.util.Collections;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    // Анотація @Inject для вбудування (ін'єкції)
    // об'єкту репозиторія в цей клас
    @Inject
    private ProductRepository repository;

    public Product create(ProductDtoRequest request) {
        repository.save(request);
        return repository.getLastEntity()
                .orElse(null);
    }

    public List<Product> getAll() {
        return repository.getAll()
                .orElse(Collections.emptyList());
    }

    // ---- Path Param ----------------------

    public Product getById(Long id) {
        return repository.getById(id).orElse(null);
    }

    public Product update(Long id, ProductDtoRequest request) {
        if (repository.getById(id).isPresent()) {
            repository.update(id, request);
        }
        return repository.getById(id).orElse(null);
    }

    public boolean deleteById(Long id) {
        if (repository.isIdExists(id)) {
            repository.deleteById(id);
            return true;
        } else return false;
    }
}
