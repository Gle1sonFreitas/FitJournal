-- 1. Tabela de Usuários
CREATE TABLE usuarios (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          userName VARCHAR(100) NOT NULL,
                          email VARCHAR(150) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL
);

-- 2. Catálogo de Exercícios
CREATE TABLE exercicios (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL,
                            grupo_muscular VARCHAR(50), -- Ex: 'Peito', 'Costas', 'Pernas'
                            equipamento VARCHAR(50)      -- Ex: 'Barra', 'Halter', 'Máquina'
);

-- 3. Fichas de Treino (Templates)
CREATE TABLE rotinas (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         id_usuario INT NOT NULL,
                         nome VARCHAR(100) NOT NULL, -- Ex: 'Treino A - Peito e Tríceps'
                         CONSTRAINT fk_rotina_usuario FOREIGN KEY (id_usuario)
                             REFERENCES usuarios(id) ON DELETE CASCADE
);

-- 4. Exercícios pertencentes a cada Rotina (Tabela Intermediária)
CREATE TABLE rotina_exercicios (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   id_rotina INT NOT NULL,
                                   id_exercicio INT NOT NULL,
                                   CONSTRAINT fk_rotex_rotina FOREIGN KEY (id_rotina)
                                       REFERENCES rotinas(id) ON DELETE CASCADE,
                                   CONSTRAINT fk_rotex_exercicio FOREIGN KEY (id_exercicio)
                                       REFERENCES exercicios(id) ON DELETE CASCADE
);

-- 5. Registro da Sessão de Treino no Dia
CREATE TABLE treinos (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         id_rotina INT NOT NULL,
                         data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT fk_treino_rotina FOREIGN KEY (id_rotina)
                             REFERENCES rotinas(id) ON DELETE CASCADE
);

-- 6. Séries Executadas (Cargas e Repetições Reais)
CREATE TABLE treino_series (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               id_treino INT NOT NULL,
                               id_exercicio INT NOT NULL,
                               numero_serie INT NOT NULL,
                               carga NUMERIC(5,2) NOT NULL DEFAULT 0, -- Suporta decimais como 12.5 kg
                               repeticoes INT NOT NULL DEFAULT 0,
                               completado BOOLEAN DEFAULT FALSE,
                               CONSTRAINT fk_serie_treino FOREIGN KEY (id_treino)
                                   REFERENCES treinos(id) ON DELETE CASCADE,
                               CONSTRAINT fk_serie_exercicio FOREIGN KEY (id_exercicio)
                                   REFERENCES exercicios(id) ON DELETE CASCADE
);

-- 1. Inserindo Usuário
INSERT INTO usuarios (userName, email, password) VALUES
    ('dev_fitness', 'dev@fitjournal.com', 'senha123');

-- 2. Inserindo Exercícios no Catálogo
INSERT INTO exercicios (nome, grupo_muscular, equipamento) VALUES
                                                               ('Supino Reto com Barra', 'Peito', 'Barra'),
                                                               ('Supino Inclinado com Halteres', 'Peito', 'Halter'),
                                                               ('Tríceps Corda', 'Tríceps', 'Polia'),
                                                               ('Agachamento Livre', 'Pernas', 'Barra'),
                                                               ('Puxada Alta', 'Costas', 'Polia');

-- 3. Criando uma Rotina (Ficha) para o Usuário 1
INSERT INTO rotinas (id_usuario, nome) VALUES
    (1, 'Treino A - Peito e Tríceps');

-- 4. Vinculando os Exercícios à Rotina 1
INSERT INTO rotina_exercicios (id_rotina, id_exercicio) VALUES
                                                                         (1, 1),
                                                                         (1, 2),
                                                                         (1, 3);

-- 5. Criando a 1ª Sessão de Treino Executada (Semana 1)
INSERT INTO treinos (id_rotina, data_hora) VALUES
    (1, '2026-09-01 08:30:00');

-- 6. Registrando as Séries Executadas no Treino 1
INSERT INTO treino_series (id_treino, id_exercicio, numero_serie, carga, repeticoes, completado) VALUES
-- Supino Reto
(1, 1, 1, 60.00, 12, TRUE),
(1, 1, 2, 70.00, 10, TRUE),
(1, 1, 3, 70.00, 8, TRUE),
-- Supino Inclinado
(1, 2, 1, 22.00, 10, TRUE),
(1, 2, 2, 24.00, 8, TRUE),
-- Tríceps Corda
(1, 3, 1, 30.00, 12, TRUE),
(1, 3, 2, 35.00, 10, TRUE);

-- 7. Criando a 2ª Sessão de Treino Executada (Semana 2 - Mostrando Progressão)
INSERT INTO treinos (id_rotina, data_hora) VALUES
    (1, '2026-09-08 08:30:00');

-- 8. Registrando as Séries Executadas no Treino 2 (Cargas aumentadas)
INSERT INTO treino_series (id_treino, id_exercicio, numero_serie, carga, repeticoes, completado) VALUES
-- Supino Reto (Aumento de 2.5kg nas séries pesadas)
(2, 1, 1, 60.00, 12, TRUE),
(2, 1, 2, 72.50, 10, TRUE),
(2, 1, 3, 72.50, 8, TRUE),
-- Supino Inclinado (Aumento de 2kg nos halteres)
(2, 2, 1, 24.00, 10, TRUE),
(2, 2, 2, 26.00, 8, TRUE),
-- Tríceps Corda (Aumento de repetições)
(2, 3, 1, 35.00, 12, TRUE),
(2, 3, 2, 35.00, 12, TRUE);