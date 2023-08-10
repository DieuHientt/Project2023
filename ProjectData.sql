CREATE TABLE card_brand (
    brand_id BIGINT PRIMARY KEY,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive')),
    name_brand VARCHAR(255) NOT NULL
);

CREATE TABLE card (
    card_id BIGINT PRIMARY KEY,
    amount INT NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    symbol VARCHAR(10) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive')),
    brand_id BIGINT UNIQUE,
    CONSTRAINT fk_card
        FOREIGN KEY (brand_id)
        REFERENCES card_brand (brand_id)
);

CREATE TABLE transactions (
    transaction_id BIGINT PRIMARY KEY,
    amount INT NOT NULL,
	note VARCHAR(255) NOT NULL,
	card_id BIGINT,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive')),
	wallet_id BIGINT,
	CONSTRAINT transactions_card
        FOREIGN KEY (card_id)
        REFERENCES card (card_id),
	CONSTRAINT transactions_wallet
        FOREIGN KEY (wallet_id)
        REFERENCES wallet (wallet_id)
);
CREATE TABLE transaction_category (
  transaction_id BIGINT ,
  category_id BIGINT ,
  PRIMARY KEY (transaction_id, category_id),
  CONSTRAINT fk_transaction FOREIGN KEY(transaction_id) REFERENCES transactions(transaction_id),
  CONSTRAINT fk_category FOREIGN KEY(category_id) REFERENCES category(category_id)
);
CREATE TABLE category (
    category_id BIGINT PRIMARY KEY,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive')),
    name_category VARCHAR(255) NOT NULL
);

CREATE TABLE outcome (
    outcome_id BIGINT PRIMARY KEY,
    date_time DATE NOT NULL,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive')),
	transaction_id BIGINT,
		CONSTRAINT transactions_outcome
        FOREIGN KEY (transaction_id)
        REFERENCES transactions (transaction_id)
);
CREATE TABLE income (
    income_id BIGINT PRIMARY KEY,
    date_time DATE NOT NULL,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive')),
	transaction_id BIGINT,
	CONSTRAINT transactions_income
        FOREIGN KEY (transaction_id)
        REFERENCES transactions (transaction_id)
);
CREATE TABLE report (
    report_id BIGINT PRIMARY KEY,
    name_report VARCHAR(255) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive')),
	type_report VARCHAR(10) NOT NULL
    
);
CREATE TABLE user_model_report (
  user_id BIGINT ,
  report_id BIGINT ,
  PRIMARY KEY (user_id, report_id),
  CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES user_model(user_id),
  CONSTRAINT fk_report FOREIGN KEY(report_id) REFERENCES report(report_id)
);
CREATE TABLE saving_target(
	sv_id BIGINT PRIMARY KEY,
	name_sv VARCHAR(255) NOT NULL,
	describe_sv VARCHAR(255) NOT NULL,
	amount_sv INT NOT NULL,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive')),
	start_date DATE NOT NULL,
	end_date DATE NOT NULL
);
CREATE TABLE user_model (
    user_id BIGINT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    email_verified VARCHAR(255) NOT NULL,
    enabled VARCHAR(255) NOT NULL,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	sv_id BIGINT UNIQUE ,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive')),
    wallet_id BIGINT UNIQUE ,
    CONSTRAINT fk_user_target
        FOREIGN KEY (sv_id)
        REFERENCES saving_target (sv_id),
	CONSTRAINT fk_wallet_user
        FOREIGN KEY (wallet_id)
        REFERENCES wallet (wallet_id)
    
);
CREATE TABLE currency(
	currency_id BIGINT PRIMARY KEY,
	name_currency VARCHAR(255) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive'))
);
CREATE TABLE wallet_currency (
  wallet_id BIGINT ,
  currency_id BIGINT ,
  PRIMARY KEY (wallet_id, currency_id),
  CONSTRAINT fk_wallet FOREIGN KEY(wallet_id) REFERENCES wallet(wallet_id),
  CONSTRAINT fk_currency FOREIGN KEY(currency_id) REFERENCES currency(currency_id)
);
CREATE TABLE wallet (
    wallet_id BIGINT PRIMARY KEY,
    cash INT NOT NULL,
    creadit INT NOT NULL,
    total INT NOT NULL,
    keycloak_id BIGINT UNIQUE,
    status VARCHAR(20) CHECK (status IN ('active', 'inactive'))
);