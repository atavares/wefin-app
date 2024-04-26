CREATE TABLE pessoa(
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  nome VARCHAR(50) NOT NULL,
  identificador VARCHAR(50) NOT NULL,
  data_nascimento DATE NOT NULL,
  tipo_identificador VARCHAR(50) NOT NULL,
  valor_min_mensal decimal(18,4) NOT NULL,
  valor_max_emprestimo decimal(18,4) NOT NULL
);

CREATE TABLE emprestimo(
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  id_pessoa INT NOT NULL,
  valor_emprestimo decimal(18,4) NOT NULL,
  numero_parcelas INT NOT NULL,
  status_pagamento VARCHAR(50) NOT NULL,
  data_criacao DATE NOT NULL,
  foreign key (id_pessoa) references pessoa(id)
);