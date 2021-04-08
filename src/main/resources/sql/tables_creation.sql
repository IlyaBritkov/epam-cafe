CREATE TABLE IF NOT EXISTS "BankCard" (
    id SERIAL PRIMARY KEY,
    card_number BIGINT UNIQUE NOT NULL,
    month_expired INT NOT NULL,
    year_expired INT NOT NULL,
    csv INT NOT NULL,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(30) NOT NULL
)
;

CREATE TABLE IF NOT EXISTS "User" (
    id SERIAL PRIMARY KEY,
    login VARCHAR(25) NOT NULL,
    "password" VARCHAR(25) NOT NULL,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    role "UserRole" NOT NULL
)
;

CREATE TABLE IF NOT EXISTS "Client" (
    user_id INT PRIMARY KEY REFERENCES "User"(id),
    loyalty_points INT NOT NULL,
    status "ClientStatus" NOT NULL
)
;

CREATE TABLE IF NOT EXISTS "BankCardClient" (
    id SERIAL PRIMARY KEY,
    client_id INT NOT NULL REFERENCES "BankCard"(id),
    bank_card_id iNT NOT NULL REFERENCES "Client"(user_id)
)
;

CREATE TABLE IF NOT EXISTS "Product" (
    id SERIAL PRIMARY KEY,
    "name" VARCHAR(35) NOT NULL,
    description VARCHAR,
    price DOUBLE PRECISION NOT NULL
)
;

CREATE TABLE IF NOT EXISTS "Category" (
    id SERIAL PRIMARY KEY,
    "name" VARCHAR(35) NOT NULL,
    description VARCHAR
)
;

CREATE TABLE IF NOT EXISTS "ProductCategory" (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL REFERENCES "Product"(id),
    category_id INT NOT NULL REFERENCES "Category"(id)
)
;

CREATE TABLE IF NOT EXISTS "ClientFavoriteProducts" (
    id SERIAL PRIMARY KEY,
    client_id INT NOT NULL REFERENCES "Client"(user_id),
    product_id INT NOT NULL REFERENCES "Product"(id)
)
;

CREATE TABLE IF NOT EXISTS "Feedback" (
    id SERIAL PRIMARY KEY,
    client_id INT NOT NULL REFERENCES "Client"(user_id),
    product_id INT NOT NULL REFERENCES "Product"(id),
    title VARCHAR(20),
    description VARCHAR,
    feedback_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    star "Star" NOT NULL
)
;

CREATE TABLE IF NOT EXISTS "Order" (
    id SERIAL PRIMARY KEY,
    client_id INT NOT NULL REFERENCES "Client"(user_id),
    order_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    expected_pick_up_time  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    actual_pick_up_time TIMESTAMP WITHOUT TIME ZONE,
    status "OrderStatus" NOT NULL,
    payment_type "PaymentType" NOT NULL
)
;

CREATE TABLE IF NOT EXISTS "OrderSet" (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL REFERENCES "Order"(id),
    product_id INT NOT NULL REFERENCES "Product"(id),
    amount INT NOT NULL
)
;

CREATE TABLE IF NOT EXISTS "HistoricalOrderSet" (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR NOT NULL,
    product_description VARCHAR,
    price DOUBLE PRECISION NOT NULL,
    product_amount INT NOT NULL
)
;
