package com.epam.repository;

import com.epam.db.TransactionIsolationType;
import com.epam.entity.Category;
import com.epam.entity.Product;
import com.epam.util.Products;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Singleton
 **/
@Slf4j
public class ProductRepositoryImpl implements ProductRepository { // todo
    private static ProductRepositoryImpl INSTANCE;

    private ProductRepositoryImpl() {
    }

    public static ProductRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductRepositoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<Product> findAll() {
        TransactionalHandler<ResultSet> transactionalHandler = new TransactionalHandler<>();

        Transaction<ResultSet> transaction = connection -> {
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM \"Product\"");
        };

        List<Product> productList = new LinkedList<>();

        try {
            Optional<ResultSet> optionalProductResultSet = transactionalHandler.transactional(
                    TransactionIsolationType.TRANSACTION_REPEATABLE_READ, transaction);
            if (optionalProductResultSet.isPresent()) {
                ResultSet productResultSet = optionalProductResultSet.get();
                while (productResultSet.next()) {
                    Product product = buildProductEntity(productResultSet);
                    productList.add(product);
                }
            }
        } catch (SQLException throwables) {
            log.error("SQL interaction is failed: ", throwables);
            throwables.printStackTrace();
        }
        log.debug("All products were retrieved");
        log.trace("Retrieved products: {}", productList);
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) {
        TransactionalHandler<ResultSet> transactionalHandler = new TransactionalHandler<>();

        Transaction<ResultSet> transaction = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"Product\" WHERE id=?");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeQuery();
        };

        Optional<Product> optionalProduct = Optional.empty();
        try {
            Optional<ResultSet> optionalProductResultSet = transactionalHandler.transactional(transaction);
            if (optionalProductResultSet.isPresent()) {
                ResultSet productResultSet = optionalProductResultSet.get();
                while (productResultSet.next()) {
                    Product product = buildProductEntity(productResultSet);
                    optionalProduct = Optional.of(product);
                }
            }
        } catch (SQLException throwables) {
            log.error("SQL interaction is failed: ", throwables);
            throwables.printStackTrace();
        }
        log.debug("Product with id {} was retrieved: {}", id, optionalProduct);
        return optionalProduct;
    }

    @Override
    public List<Product> findByName(String name) {
        TransactionalHandler<ResultSet> transactionalHandler = new TransactionalHandler<>();

        List<Product> productList = new ArrayList<>();
        try {
            Optional<ResultSet> optionalProductResultSet = transactionalHandler.transactional(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM \"Product\" WHERE name=?;");
                preparedStatement.setString(1, name);
                return preparedStatement.executeQuery();
            });
            if (optionalProductResultSet.isPresent()) {
                ResultSet productResultSet = optionalProductResultSet.get();
                while (productResultSet.next()) {
                    Product product = buildProductEntity(productResultSet);
                    productList.add(product);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            log.error("Exception occurred while trying to find entities with name = {} : {}",
                    name, throwables);
        }
        return productList;
    }


    // todo
    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return null;
    }

    // todo
    @Override
    public List<Product> findByCategory(Category category) {
        return null;
    }

    @Override
    public Product add(Product product) {
        TransactionalHandler<Integer> transactionalHandler = new TransactionalHandler<>();
        try {
            transactionalHandler.transactional(connection -> {
                        PreparedStatement preparedStatement = connection.prepareStatement("" +
                                "INSERT INTO \"Product\" (name, description, price) VALUES (?,?,?);");
                        preparedStatement.setString(1, product.getName());
                        preparedStatement.setString(2, product.getDescription());
                        preparedStatement.setBigDecimal(3, product.getPrice().getAmount());
                        return preparedStatement.executeUpdate();
                    }
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            log.error("Exception occurred while trying to insert the data: {}, [{}]",
                    throwables, String.format("name=%s, description=%s,price=%f",
                            product.getName(), product.getDescription(), product.getPrice().getAmount()));
        }
        return product;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Product update(Product product) {
        TransactionalHandler<Integer> transactionalHandler = new TransactionalHandler<>();

        try {
            transactionalHandler.transactional(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE \"Product\"" +
                                "SET name = ?," +
                                "    description = ?," +
                                "    price=?" +
                                "WHERE id=?");
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setBigDecimal(3, product.getPrice().getAmount());
                preparedStatement.setLong(4, product.getId());
                return preparedStatement.executeUpdate();
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            log.error("Exception occurred while trying to update entity {}: {}", product, throwables);
        }

        return product;
    }

    @Override
    public void removeById(Long id) {
        TransactionalHandler<Integer> transactionalHandler = new TransactionalHandler<>();
        try {
            transactionalHandler.transactional(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement("" +
                        "DELETE FROM \"Product\" WHERE id=?;");
                preparedStatement.setLong(1, id);
                return preparedStatement.executeUpdate();
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            log.error("Exception occurred while trying to delete entity with id = {}: {}", id, throwables);
        }
    }

    @Override
    public void remove(Product product) {
        removeById(product.getId());
    }

    private Product buildProductEntity(ResultSet productResultSet) throws SQLException {
        long productId = productResultSet.getLong("id");
        String productName = productResultSet.getString("name");
        String productDescription = productResultSet.getString("description");
        double productDoublePrice = productResultSet.getDouble("price");
        Money productPrice = Money.of(Products.getBYN_CURRENCY_UNIT(), productDoublePrice);

        Product product = new Product(productId, productName, productDescription, productPrice);
        log.trace("Product entity was built: {}", product);
        return product;
    }
}