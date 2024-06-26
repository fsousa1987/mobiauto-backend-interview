CREATE TABLE revenda
(
    id          UUID PRIMARY KEY,
    cnpj        VARCHAR(14)  NOT NULL UNIQUE,
    nome_social VARCHAR(120) NOT NULL
);

CREATE INDEX idx_revenda_cnpj ON revenda (cnpj);