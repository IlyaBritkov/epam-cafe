package com.epam.repository;

import com.epam.entity.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends Repository<Feedback, Long> {

    @Override
    List<Feedback> findAll();

    @Override
    Optional<Feedback> findById(Long id);

    List<Feedback> findAllByProductId(Long productId);

    @Override
    Feedback add(Feedback feedback);

    @Override
    Feedback update(Feedback feedback);

    @Override
    void removeById(Long id);

    @Override
    void remove(Feedback feedback);
}
