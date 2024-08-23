CREATE TABLE user
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    username     VARCHAR(120)          NOT NULL,
    first_name   VARCHAR(255)          NOT NULL,
    last_name    VARCHAR(255)          NOT NULL,
    email        VARCHAR(255)          NOT NULL,
    password     VARCHAR(255)          NOT NULL,
    phone_number BIGINT                NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE user
    ADD CONSTRAINT uc_user_phone_number UNIQUE (phone_number);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);
CREATE TABLE budget_categories
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    title VARCHAR(240)          NOT NULL,
    CONSTRAINT pk_budget_categories PRIMARY KEY (id)
);
CREATE TABLE goals
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    user_id        BIGINT                NULL,
    title          VARCHAR(100)          NOT NULL,
    date_created   VARCHAR(100)          NOT NULL,
    end_date       VARCHAR(100)          NOT NULL,
    goal_amount    INT                   NOT NULL,
    current_amount INT                   NOT NULL,
    CONSTRAINT pk_goals PRIMARY KEY (id)
);

ALTER TABLE goals
    ADD CONSTRAINT FK_GOALS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);
CREATE TABLE transactions
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    user_id              BIGINT                NULL,
    title                VARCHAR(100)          NOT NULL,
    memo                 TEXT                  NULL,
    date                 datetime              NULL,
    amount               DECIMAL(13, 2)        NOT NULL,
    transaction_types_id BIGINT                NULL,
    budget_categories_id BIGINT                NULL,
    goal_id              BIGINT                NULL,
    CONSTRAINT pk_transactions PRIMARY KEY (id)
);

ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ON_BUDGET_CATEGORIES FOREIGN KEY (budget_categories_id) REFERENCES budget_categories (id);

ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ON_GOAL FOREIGN KEY (goal_id) REFERENCES goals (id);

ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ON_TRANSACTION_TYPES FOREIGN KEY (transaction_types_id) REFERENCES transaction_types (id);

ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);
CREATE TABLE transaction_types
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(120)          NOT NULL,
    CONSTRAINT pk_transaction_types PRIMARY KEY (id)
);
CREATE TABLE user
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    username     VARCHAR(120)          NOT NULL,
    first_name   VARCHAR(255)          NOT NULL,
    last_name    VARCHAR(255)          NOT NULL,
    email        VARCHAR(255)          NOT NULL,
    password     VARCHAR(255)          NOT NULL,
    phone_number BIGINT                NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE user
    ADD CONSTRAINT uc_user_phone_number UNIQUE (phone_number);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);