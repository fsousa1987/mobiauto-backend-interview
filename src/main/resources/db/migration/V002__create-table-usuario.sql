CREATE TABLE usuario
(
    id    UUID         NOT NULL PRIMARY KEY,
    nome  VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE INDEX idx_usuario_nome ON usuario (nome);
CREATE INDEX idx_usuario_senha ON usuario (senha);