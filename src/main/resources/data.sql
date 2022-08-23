CREATE TABLE Usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45),
    email VARCHAR(45),
    senha VARCHAR(21),
    cpf VARCHAR(11),
    telefone VARCHAR(14),
    cep VARCHAR(45)
);

INSERT INTO Usuario (nome, email, senha, cpf, telefone, cep) VALUES
    ('Jamal', 'jamal@teste.com', 'teste123', '22222040568', '123456789', '66666-666');

INSERT INTO Usuario (nome, email, senha, cpf, telefone, cep) VALUES
    ('Gerônimo', 'geronimo@teste.com', 'teste123', '66642080592', '987654321', '55555-555');