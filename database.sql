-- ============================================================
-- Script de criacao do banco de dados (executar UMA vez)
-- Arquivo: database.sql
-- ============================================================
-- Caso o Spring Boot nao crie automaticamente, execute este
-- script no MySQL Workbench ou no terminal mysql.
-- ============================================================

CREATE DATABASE IF NOT EXISTS sistema_automotivo
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE sistema_automotivo;

-- As tabelas abaixo sao criadas automaticamente pelo Hibernate
-- (spring.jpa.hibernate.ddl-auto=update), porem ficam aqui
-- documentadas para fins academicos.

-- Tabela: marca
-- CREATE TABLE marca (
--     id           BIGINT AUTO_INCREMENT PRIMARY KEY,
--     nome         VARCHAR(80) NOT NULL UNIQUE,
--     pais_origem  VARCHAR(50)
-- );

-- Tabela: modelo
-- CREATE TABLE modelo (
--     id         BIGINT AUTO_INCREMENT PRIMARY KEY,
--     nome       VARCHAR(80) NOT NULL,
--     categoria  VARCHAR(20),
--     marca_id   BIGINT NOT NULL,
--     CONSTRAINT fk_modelo_marca FOREIGN KEY (marca_id) REFERENCES marca(id)
-- );

-- Tabela: veiculo
-- CREATE TABLE veiculo (
--     id             BIGINT AUTO_INCREMENT PRIMARY KEY,
--     modelo_id      BIGINT NOT NULL,
--     ano            INT NOT NULL,
--     cor            VARCHAR(30) NOT NULL,
--     preco          DECIMAL(12,2) NOT NULL,
--     quilometragem  INT NOT NULL,
--     status         VARCHAR(20) NOT NULL,
--     CONSTRAINT fk_veiculo_modelo FOREIGN KEY (modelo_id) REFERENCES modelo(id)
-- );
