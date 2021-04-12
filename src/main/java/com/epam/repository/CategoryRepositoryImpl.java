package com.epam.repository;

import com.epam.db.ConnectionPool;
import com.epam.db.ConnectionPoolImpl;
import com.epam.entity.Category;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Singleton
 **/
@Slf4j
public class CategoryRepositoryImpl implements CategoryRepository { //todo
    private static CategoryRepositoryImpl INSTANCE;

    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    private CategoryRepositoryImpl() {
    }

    public static CategoryRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRepositoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Category> findAllByProductId(Long productId) {
        Connection connection = connectionPool.getConnection();
        List<Category> categoryList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT *" +
                        "FROM \"Category\"" +
                        "WHERE id IN (" +
                        "   SELECT category_id" +
                        "   FROM \"ProductCategory\"" +
                        "   WHERE product_id = ?);")) {
            preparedStatement.setLong(1, productId);

            ResultSet categoryResultSet = preparedStatement.executeQuery();
            while (categoryResultSet.next()) {
                long categoryId = categoryResultSet.getLong("id");
                String categoryName = categoryResultSet.getString("name");
                String categoryDescription = categoryResultSet.getString("description");

                Category category = new Category(categoryId, categoryName, categoryDescription);
                categoryList.add(category);
            }
        } catch (SQLException throwables) {
            log.error("Exception was occurred: " + throwables);
            throwables.printStackTrace();
        }

        log.debug("All categories were retrieved");
        connectionPool.releaseConnection(connection);
        return categoryList;
    }

    @Override
    public Category add(Category category) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void remove(Category entity) {

    }
}
