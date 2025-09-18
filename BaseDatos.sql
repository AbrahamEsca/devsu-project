-- =====================================
-- CREAR TABLA person
-- =====================================
CREATE TABLE person (
    person_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(1),
    age INT,
    identification VARCHAR(50) UNIQUE NOT NULL,
    address VARCHAR(200),
    phone VARCHAR(10)
);

-- =====================================
-- CREAR TABLA client
-- =====================================
CREATE TABLE client (
    person_id BIGINT PRIMARY KEY,
    client_id VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_person_client FOREIGN KEY (person_id)
        REFERENCES person (person_id)
        ON DELETE CASCADE
);

-- =====================================
-- CREAR TABLA account_type
-- =====================================
CREATE TABLE account_type (
    account_type_id BIGSERIAL PRIMARY KEY,
    dsc_account VARCHAR(100) NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

-- =====================================
-- CREAR TABLA account
-- =====================================
CREATE TABLE account (
    account_id BIGSERIAL PRIMARY KEY,
    client_id VARCHAR(20) NOT NULL,
    account_number VARCHAR(11) UNIQUE NOT NULL,
    account_type_id BIGINT NOT NULL,
    initial_balance NUMERIC(15,2) NOT NULL,
    current_balance NUMERIC(15,2) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_account_type FOREIGN KEY (account_type_id)
        REFERENCES account_type (account_type_id)
);

-- =====================================
-- CREAR TABLA movement
-- =====================================
CREATE TABLE movement (
    movement_id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    movement_type VARCHAR(50) NOT NULL,
    movement_date TIMESTAMP NOT NULL,
    amount NUMERIC(15,2) NOT NULL,
    balance NUMERIC(15,2) NOT NULL,
    CONSTRAINT fk_account_movement FOREIGN KEY (account_id)
        REFERENCES account (account_id)
);

INSERT INTO public.account_type
(account_type_id, dsc_account, active)
VALUES(nextval('account_type_account_type_id_seq'::regclass), 'Ahorros', true);
INSERT INTO public.account_type
(account_type_id, dsc_account, active)
VALUES(nextval('account_type_account_type_id_seq'::regclass), 'Corriente', true);
INSERT INTO public.account_type
(account_type_id, dsc_account, active)
VALUES(nextval('account_type_account_type_id_seq'::regclass), 'Inversiones', true);
commit;