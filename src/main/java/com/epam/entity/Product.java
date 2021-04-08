package com.epam.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Product extends AbstractBaseEntity {
    private String name;

    @Nullable
    private String description;

    private CurrencyUnit currencyUnit;

    private Money price;

    //todo maybe add picture

    @Setter(AccessLevel.NONE)
    private final List<Category> categoryList = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    private final List<Feedback> feedbackList = new ArrayList<>();

    public Product(String name, @Nullable String description, CurrencyUnit currencyUnit, Money price) {
        this.name = name;
        this.description = description;
        this.currencyUnit = currencyUnit;
        this.price = price;
    }

    public Product(Long id, String name, String description, CurrencyUnit currencyUnit, Money price) {
        super(id);
        this.name = name;
        this.description = description;
        this.currencyUnit = currencyUnit;
        this.price = price;
    }

    public boolean addCategory(Category category) {
        return categoryList.add(category);
    }

    public boolean addCategories(List<Category> categories) {
        return categoryList.addAll(categories);
    }

    public boolean addCategories(Category... categories) {
        return categoryList.addAll(Arrays.asList(categories));
    }

    public boolean removeCategory(Category category) {
        return categoryList.remove(category);
    }

    public boolean removeCategory(List<Category> categories) {
        return categoryList.removeAll(categories);
    }

    public boolean removeCategory(Category... categories) {
        return categoryList.removeAll(Arrays.asList(categories));
    }

    public boolean addFeedback(Feedback feedback) {
        return feedbackList.add(feedback);
    }

    public boolean addFeedbacks(List<Feedback> feedbacks) {
        return feedbackList.addAll(feedbacks);
    }

    public boolean addFeedbacks(Feedback... feedbacks) {
        return feedbackList.addAll(Arrays.asList(feedbacks));
    }

    public boolean removeFeedback(Feedback feedback) {
        return feedbackList.remove(feedback);
    }

    public boolean removeFeedback(List<Feedback> feedbacks) {
        return feedbackList.removeAll(feedbacks);
    }

    public boolean removeFeedback(Feedback... feedbacks) {
        return feedbackList.removeAll(Arrays.asList(feedbacks));
    }
}
