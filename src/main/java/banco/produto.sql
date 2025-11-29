CREATE table Produtos (
    id_produto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_produto VARCHAR(200) NOT NULL,
    descricao_produto TEXT,
    quantidade_estoque INT,
    preco DECIMAL(10,2) NOT NULL,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP
);
