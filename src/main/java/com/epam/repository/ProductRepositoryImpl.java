package com.epam.repository;

import com.epam.db.TransactionIsolationType;
import com.epam.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Singleton
 **/
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {
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

        List<Product> productList = new ArrayList<>();

        try {
            ResultSet productResultSet = transactionalHandler.transactional(TransactionIsolationType.TRANSACTION_REPEATABLE_READ,
                    transaction);
            while (productResultSet.next()) {
                Product product = buildProductEntity(productResultSet);
                productList.add(product);
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"Product\" WHERE id=?");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeQuery();
        };

        Optional<Product> optionalProduct = Optional.empty();
        try {
            ResultSet productResultSet = transactionalHandler.transactional(transaction);
            while (productResultSet.next()) {
                Product product = buildProductEntity(productResultSet);
                optionalProduct = Optional.of(product);
            }
        } catch (SQLException throwables) {
            log.error("SQL interaction is failed: ", throwables);
            throwables.printStackTrace();
        }
        log.debug("Product with id {} was retrieved: {}", id, optionalProduct);
        return optionalProduct;
    }

    @Override
    public Product add(Product product) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void remove(Product product) {

    }

    private Product buildProductEntity(ResultSet productResultSet) throws SQLException {
        long productId = productResultSet.getLong("id");
        String productName = productResultSet.getString("name");
        String productDescription = productResultSet.getString("description");
        double productDoublePrice = productResultSet.getDouble("price");
        Money productPrice = Money.of(Product.getCurrencyUnit(), productDoublePrice);

        Product product = new Product(productId, productName, productDescription, productPrice);
        log.trace("Product entity was built: {}", product);
        return product;
    }
}
