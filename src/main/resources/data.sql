-- Dados iniciais carregados automaticamente na primeira execucao.
-- Os INSERTs sao idempotentes (INSERT IGNORE) para nao duplicar a cada start.

INSERT IGNORE INTO marca (id, nome, pais_origem) VALUES
  (1, 'Toyota',     'Japao'),
  (2, 'Volkswagen', 'Alemanha'),
  (3, 'Honda',      'Japao'),
  (4, 'Chevrolet',  'Estados Unidos'),
  (5, 'Fiat',       'Italia');

INSERT IGNORE INTO modelo (id, nome, categoria, marca_id) VALUES
  (1, 'Corolla',  'SEDAN', 1),
  (2, 'Hilux',    'PICKUP', 1),
  (3, 'Gol',      'HATCH', 2),
  (4, 'T-Cross',  'SUV',   2),
  (5, 'Civic',    'SEDAN', 3),
  (6, 'HR-V',     'SUV',   3),
  (7, 'Onix',     'HATCH', 4),
  (8, 'S10',      'PICKUP', 4),
  (9, 'Argo',     'HATCH', 5),
  (10,'Toro',     'PICKUP', 5);

INSERT IGNORE INTO veiculo (id, modelo_id, ano, cor, preco, quilometragem, status) VALUES
  (1, 1, 2022, 'Branco',   135000.00, 25000,  'DISPONIVEL'),
  (2, 1, 2023, 'Prata',    148000.00, 12000,  'DISPONIVEL'),
  (3, 2, 2021, 'Preto',    245000.00, 48000,  'RESERVADO'),
  (4, 3, 2018, 'Vermelho',  48000.00, 67000,  'DISPONIVEL'),
  (5, 4, 2023, 'Cinza',    155000.00, 18000,  'DISPONIVEL'),
  (6, 5, 2020, 'Branco',   125000.00, 42000,  'VENDIDO'),
  (7, 6, 2022, 'Azul',     145000.00, 31000,  'DISPONIVEL'),
  (8, 7, 2024, 'Branco',    92000.00,  5000,  'DISPONIVEL'),
  (9, 8, 2021, 'Cinza',    195000.00, 55000,  'RESERVADO'),
  (10,9, 2023, 'Vermelho',  85000.00, 14000,  'DISPONIVEL'),
  (11,10,2022, 'Preto',    175000.00, 28000,  'DISPONIVEL'),
  (12,2, 2019, 'Branco',   175000.00, 89000,  'VENDIDO');
