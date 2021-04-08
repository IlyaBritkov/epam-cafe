package com.epam.entity;

import com.epam.enums.OrderStatus;
import com.epam.enums.PaymentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.HashMap;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Oder extends AbstractBaseEntity {

    private final Client client;

    private final LocalDateTime orderDateTime;

    private final LocalDateTime expectedPickUpDateTime;

    @Setter
    @Nullable
    private LocalDateTime actualPickUpDateTime;

    @Setter
    private OrderStatus status;

    @Setter
    private PaymentType paymentType;

    private final HashMap<Product, Integer> orderSet = new HashMap<>(); // value - amount

    public Oder(Client client, LocalDateTime orderDateTime, LocalDateTime expectedPickUpDateTime, PaymentType paymentType) {
        this.client = client;
        this.orderDateTime = orderDateTime;
        this.expectedPickUpDateTime = expectedPickUpDateTime;
        this.status = OrderStatus.ORDERED;
        this.paymentType = paymentType;
    }

    public Oder(Long id, Client client, LocalDateTime orderDateTime, LocalDateTime expectedPickUpDateTime,
                @Nullable LocalDateTime actualPickUpDateTime, OrderStatus status, PaymentType paymentType) {
        super(id);
        this.client = client;
        this.orderDateTime = orderDateTime;
        this.expectedPickUpDateTime = expectedPickUpDateTime;
        this.actualPickUpDateTime = actualPickUpDateTime;
        this.status = status;
        this.paymentType = paymentType;
    }
}
