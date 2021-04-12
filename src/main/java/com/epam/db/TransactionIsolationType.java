package com.epam.db;

public enum TransactionIsolationType {
    TRANSACTION_NONE(0),
    TRANSACTION_READ_UNCOMMITTED(1),
    TRANSACTION_READ_COMMITTED(2),
    TRANSACTION_REPEATABLE_READ(4),
    TRANSACTION_SERIALIZABLE(8);

    TransactionIsolationType(int constant) {
    }

    public static TransactionIsolationType resolveTransactionTypeByConstant(int constant) {
        switch (constant){
            case 0: return TRANSACTION_NONE;
            case 1: return TRANSACTION_READ_UNCOMMITTED;
            case 2: return TRANSACTION_READ_COMMITTED;
            case 4: return TRANSACTION_REPEATABLE_READ;
            case 8: return TRANSACTION_SERIALIZABLE;
            default: throw new IllegalArgumentException("There is no transaction type collocated with constant: "+ constant);
        }
    }
}
