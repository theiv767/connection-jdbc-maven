CREATE DATABASE IF NOT EXISTS gerenciamento_biblioteca;
USE gerenciamento_biblioteca;

-- UTILITÁRIOS ---------------------------------------
select * from livros;
set sql_safe_updates = 0;
delete from autores;
-- ---------------------------------------------------


-- Criação da tabela autores.
CREATE TABLE autores (
    id int primary key not null auto_increment,
    nome VARCHAR(255),
    nacionalidade VARCHAR(255),
    data_nascimento DATE,
    genero VARCHAR(255)
);

-- Criação da tabela de endereços
CREATE TABLE enderecos(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    cep VARCHAR(8),
    bairro VARCHAR(50),
    rua VARCHAR(50),
    numero VARCHAR(10)    
);

-- Criação da tabela editoras.
CREATE TABLE editoras (
    id int primary key not null auto_increment,
    nome VARCHAR(255),
    id_endereco INT,
    pais VARCHAR(255),
    email_contato VARCHAR(255),
    telefone_contato VARCHAR(255),
    FOREIGN KEY (id_endereco) REFERENCES enderecos(id)

);

-- Criação da tabela livros.
CREATE TABLE livros (
    id int primary key not null auto_increment,
    titulo VARCHAR(255),
    id_autor INT,
    id_editora INT,
    ano_publicacao DATE,
    genero VARCHAR(255),
    numero_paginas INT,
    FOREIGN KEY (id_autor) REFERENCES autores(id),
    FOREIGN KEY (id_editora) REFERENCES editoras(id)
);

-- Criação da tabela usuários
CREATE TABLE usuarios(
	id int primary key not null auto_increment,
	nome VARCHAR(50),
    idade int,
    telefone VARCHAR(11),
    id_endereco int,
    FOREIGN KEY (id_endereco) references enderecos(id)
);

-- Criação da tabela emprestimos.
CREATE TABLE emprestimos (
    id int primary key not null auto_increment,
    id_livro INT,
    id_usuario INT,
    data_emprestimo DATE,
    data_devolucao DATE,
    situacao VARCHAR(255),
    FOREIGN KEY (id_livro) REFERENCES livros(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

-- Criação da tabela reservas.
CREATE TABLE reservas (
    id int primary key not null auto_increment,
    id_livro INT,
    id_usuario INT,
    data_reserva DATE,
    situacao VARCHAR(255),
    FOREIGN KEY (id_livro) REFERENCES livros(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
