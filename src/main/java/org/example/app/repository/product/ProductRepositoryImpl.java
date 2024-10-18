package org.example.app.repository.product;

import org.example.app.config.HibernateUtil;
import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductRepositoryImpl implements ProductRepository {

    private static final Logger LOGGER =
            Logger.getLogger(ProductRepository.class.getName());

    @Override
    public void save(ProductDtoRequest request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Транзакція стартує
            transaction = session.beginTransaction();
            String hql = "INSERT INTO Product (productName, measure, quota, price) " +
                    "VALUES (:productName, :measure, :quota, :price)";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("productName", request.productName());
            query.setParameter("measure", request.measure());
            query.setParameter("quota", request.quota());
            query.setParameter("price", request.price());
            query.executeUpdate();
            // Транзакція виконується
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public Optional<List<Product>> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            // Транзакція стартує
            transaction = session.beginTransaction();
            List<Product> list =
                    session.createQuery("FROM Product", Product.class).list();
            // Транзакція виконується
            transaction.commit();
            // Повертаємо Optional-контейнер з колецією даних
            return Optional.of(list);
        } catch (Exception e) {
            // Якщо помилка повертаємо порожній Optional-контейнер
            return Optional.empty();
        }
    }

    // ---- Path Params ----------------------

    @Override
    public Optional<Product> getById(Long id) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Транзакція стартує
            transaction = session.beginTransaction();
            Query<Product> query =
                    session.createQuery("FROM Product WHERE id = :id", Product.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            Product product = query.uniqueResult();
            // Транзакція виконується
            transaction.commit();
            // Повертаємо Optional-контейнер з об'єктом
            return Optional.ofNullable(product);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            // Якщо помилка або такого об'єкту немає в БД,
            // повертаємо порожній Optional-контейнер
            return Optional.empty();
        }
    }

    @Override
    public void update(Long id, ProductDtoRequest request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Транзакция стартует
            transaction = session.beginTransaction();
            String hql = "UPDATE Product SET productName = :productName," +
                    " measure = :measure, quota = :quota, price = :price" +
                    " WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("productName", request.productName());
            query.setParameter("measure", request.measure());
            query.setParameter("quota", request.quota());
            query.setParameter("price", request.price());
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Транзакція стартує
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Product WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            // Транзакція виконується
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return false;
        }
    }

    // ---- Utils ----------------------

    public boolean isIdExists(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Перевірка наявності об'єкту за певним id
            Product product = new Product();
            product.setId(id);
            product = session.get(Product.class, product.getId());

            if (product != null) {
                Query<Product> query =
                        session.createQuery("FROM Product", Product.class);
                query.setMaxResults(1);
                query.getResultList();
            }
            return product != null;
        }
    }

    public Optional<Product> getLastEntity() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Транзакція стартує
            transaction = session.beginTransaction();
            Query<Product> query =
                    session.createQuery("FROM Product ORDER BY id DESC", Product.class);
            query.setMaxResults(1);
            Product product = query.uniqueResult();
            // Транзакція виконується
            transaction.commit();
            return Optional.of(product);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return Optional.empty();
        }
    }
}
