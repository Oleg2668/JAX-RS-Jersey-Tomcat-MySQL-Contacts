package org.example.app.service.user;

import jakarta.inject.Inject;
import org.example.app.dto.user.UserDtoRequest;
import org.example.app.entity.User;
import org.example.app.repository.user.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    // Анотація @Inject для вбудування (ін'єкції)
    // об'єкту репозиторія в цей клас
    @Inject
    private UserRepository repository;

    @Override
    public User create(UserDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        repository.save(request);
        return repository.getLastEntity()
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll()
                .orElse(Collections.emptyList());
    }

    // ---- Path Params ----------------------

    @Override
    public User getById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        return repository.getById(id).orElse(null);
    }

    @Override
    public User update(Long id, UserDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        if (repository.getById(id).isPresent()) {
            repository.update(id, request);
        }
        return repository.getById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        if (repository.getById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // ---- Query Params ----------------------

    public List<User> fetchByFirstName(String firstName) {
        return repository.fetchByFirstName(firstName)
                .orElse(Collections.emptyList());
    }

    public List<User> fetchByLastName(String lastName) {
        return repository.fetchByLastName(lastName)
                .orElse(Collections.emptyList());
    }

    public List<User> fetchAllOrderBy(String orderBy) {
        return repository.fetchAllOrderBy(orderBy)
                .orElse(Collections.emptyList());
    }

    public List<User> fetchByLastNameOrderBy(String lastName,
                                             String orderBy) {
        return repository.fetchByLastNameOrderBy(lastName,
                orderBy).orElse(Collections.emptyList());
    }

    public List<User> fetchBetweenIds(int from, int to) {
        return repository.fetchBetweenIds(from, to)
                .orElse(Collections.emptyList());
    }

    public List<User> fetchLastNameIn(String lastName1,
                                      String lastName2) {
        return repository.fetchLastNameIn(lastName1,
                        lastName2)
                .orElse(Collections.emptyList());
    }
}