package com.epam.repository;

import com.epam.db.ConnectionPool;
import com.epam.db.ConnectionPoolImpl;
import com.epam.entity.Feedback;
import com.epam.entity.enums.Star;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Singleton
 **/
@Slf4j
public class FeedbackRepositoryImpl implements FeedbackRepository { // todo
    private static FeedbackRepositoryImpl INSTANCE;

    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    private FeedbackRepositoryImpl() {
    }

    public static FeedbackRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FeedbackRepositoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<Feedback> findAll() {
        return null;
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Feedback> findAllByProductId(Long productId) {
        Connection connection = connectionPool.getConnection();
        List<Feedback> feedbackList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT *" +
                        "FROM \"Feedback\"" +
                        "WHERE product_id = ?;")) {


            preparedStatement.setLong(1, productId);

            ResultSet feedbackResultSet = preparedStatement.executeQuery();

            while (feedbackResultSet.next()) {
                long feedbackId = feedbackResultSet.getLong("id");
                Long feedbackClientId = feedbackResultSet.getLong("client_id");
                String feedbackTitle = feedbackResultSet.getString("title");
                String feedbackDescription = feedbackResultSet.getString("description");
                OffsetDateTime feedbackDateTime = feedbackResultSet.getObject("feedback_date_time", OffsetDateTime.class);
                String feedbackStarName = feedbackResultSet.getString("star");
                Star feedbackStar = Star.resolveStarByName(feedbackStarName);

                Feedback feedback = new Feedback(feedbackId, feedbackClientId,
                        productId, feedbackTitle, feedbackDescription, feedbackDateTime, feedbackStar);

                feedbackList.add(feedback);
            }
        } catch (SQLException throwables) {
            log.error("Exception was occurred: " + throwables);
            throwables.printStackTrace();
        }

        log.debug("All feedbacks were retrieved");
        connectionPool.releaseConnection(connection);
        return feedbackList;
    }

    @Override
    public Feedback add(Feedback feedback) {
        return null;
    }

    @Override
    public Feedback update(Feedback feedback) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void remove(Feedback feedback) {

    }
}
