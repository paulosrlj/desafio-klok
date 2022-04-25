CREATE TABLE cobrancas (
    id SERIAL PRIMARY KEY,
    valor NUMERIC(12, 2) NOT NULL,
    dono_cobranca VARCHAR(100) NOT NULL,
    data_vencimento DATE NOT NULL,
    pago BOOLEAN DEFAULT FALSE
);


CREATE TABLE adesoes (
    id SERIAL PRIMARY KEY,
    valor NUMERIC(12, 2) NOT NULL,
    data_vencimento DATE NOT NULL,
    pago BOOLEAN DEFAULT FALSE
);
