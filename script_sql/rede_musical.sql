CREATE SCHEMA rede_musical;

CREATE TABLE rede_musical.usuario (
    id SERIAL,
	username VARCHAR(12) UNIQUE NOT NULL,
	email VARCHAR(50) UNIQUE NOT NULL,
	senha VARCHAR(100) NOT NULL,
	pnome VARCHAR(30) NOT NULL,
	snome VARCHAR(30) NOT NULL,
	dt_nascimento DATE,
	imagem TEXT,
	cidade VARCHAR(50),
	estado VARCHAR(50),
	pais VARCHAR(50) NOT NULL,
	banda_favorita VARCHAR(20) NOT NULL,
	musica_favorita VARCHAR(20) NOT NULL,
	genero_favorito VARCHAR(20) NOT NULL,
	instrumento_favorito VARCHAR(20) NOT NULL,
	CONSTRAINT pk_usuario PRIMARY KEY(id),
	CONSTRAINT uq_usuario_username UNIQUE(username),
	CONSTRAINT uq_usuario_email UNIQUE(email)
);

CREATE TABLE rede_musical.usuario_instrumentos (
	usuario_username VARCHAR(12),
	instrumento VARCHAR(20),
	CONSTRAINT pk_usuario_instrumentos PRIMARY KEY(usuario_username, instrumento),
	CONSTRAINT fk_usuario_instrumentos FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username)
);

CREATE TABLE rede_musical.usuario_segue_usuario (
	usuario_username VARCHAR(12),
	usuario_username_seguido VARCHAR(12),
	CONSTRAINT pk_usuario_segue_usuario PRIMARY KEY(usuario_username, usuario_username_seguido),
	CONSTRAINT fk_usuario_seguindo FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username),
	CONSTRAINT fk_usuario_seguido FOREIGN KEY(usuario_username_seguido)
		REFERENCES rede_musical.usuario(username)
);

CREATE TABLE rede_musical.post (
	id SERIAL,
	texto_post TEXT NOT NULL,
	imagem TEXT,
	dt_publicacao TIMESTAMP NOT NULL,
	n_likes INT NOT NULL,
	n_comentarios INT NOT NULL,
	n_compartilhamentos INT NOT NULL,
	usuario_username VARCHAR(12) NOT NULL,
	CONSTRAINT pk_post PRIMARY KEY(id),
	CONSTRAINT fk_criador_post FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username)
);

CREATE TABLE rede_musical.usuario_da_like_em_post (
	usuario_username VARCHAR(12),
	post_id INT,
	CONSTRAINT pk_usuario_da_like_em_post PRIMARY KEY(usuario_username, post_id),
	CONSTRAINT fk_usuario_like_post FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username),
	CONSTRAINT fk_post_liked_usuario FOREIGN KEY(post_id)
		REFERENCES rede_musical.post(id)
);

CREATE TABLE rede_musical.usuario_compartilha_post (
	usuario_username VARCHAR(12),
	post_id INT,
	CONSTRAINT pk_usuario_compartilha_post PRIMARY KEY(usuario_username, post_id),
	CONSTRAINT fk_usuario_compartilhou FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username),
	CONSTRAINT fk_post_compartilhado FOREIGN KEY(post_id)
		REFERENCES rede_musical.post(id)
);

CREATE TABLE rede_musical.comentario (
	id SERIAL,
	dt_publicacao TIMESTAMP NOT NULL,
	texto_comentario TEXT NOT NULL,
	CONSTRAINT pk_comentario PRIMARY KEY(id)
);

CREATE TABLE rede_musical.usuario_comenta_post (
	usuario_username VARCHAR(12) NOT NULL,
	post_id INT,
	comentario_id INT,
	CONSTRAINT pk_usuario_comenta_post PRIMARY KEY(usuario_username),
	CONSTRAINT fk_usuario_autor FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username),
	CONSTRAINT fk_post_comentado FOREIGN KEY(post_id)
		REFERENCES rede_musical.post(id),
	CONSTRAINT fk_comentario_feito FOREIGN KEY(comentario_id)
		REFERENCES rede_musical.comentario(id)
);

CREATE TABLE rede_musical.feed (
	usuario_username VARCHAR(12),
	CONSTRAINT pk_feed PRIMARY KEY(usuario_username),
	CONSTRAINT fk_feed_usuario FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username)
);

CREATE TABLE rede_musical.feed_possui_posts (
	feed_username VARCHAR(12),
	post_id INT,
	CONSTRAINT pk_feed_possui_posts PRIMARY KEY(feed_username, post_id),
	CONSTRAINT fk_feed_post FOREIGN KEY(feed_username)
		REFERENCES rede_musical.feed(usuario_username),
	CONSTRAINT fk_post_feed FOREIGN KEY(post_id)
		REFERENCES rede_musical.post(id)
);

CREATE TABLE rede_musical.banda  (
    id SERIAL,
	sigla VARCHAR(5) NOT NULL,
	nome VARCHAR(30) NOT NULL,
	imagem TEXT,
	genero_musical VARCHAR(20) NOT NULL,
	usuario_username VARCHAR(12) NOT NULL,
	CONSTRAINT pk_banda PRIMARY KEY(id),
	CONSTRAINT uq_banda_sigla UNIQUE(sigla),
	CONSTRAINT fk_usuario_criador_banda FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username)
);

CREATE TABLE rede_musical.usuario_segue_banda (
	usuario_username VARCHAR(12),
	banda_sigla VARCHAR(5),
	CONSTRAINT pk_usuario_segue_banda PRIMARY KEY(usuario_username, banda_sigla),
	CONSTRAINT fk_usuario_segue_banda FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username),
	CONSTRAINT fk_banda_seguida_usuario FOREIGN KEY(banda_sigla)
		REFERENCES rede_musical.banda(sigla)
);

CREATE TABLE rede_musical.usuario_participa_de_banda (
	usuario_username VARCHAR(12),
	banda_sigla VARCHAR(5),
	instrumento VARCHAR(20),
	CONSTRAINT pk_usuario_participa_banda PRIMARY KEY(usuario_username, banda_sigla),
	CONSTRAINT fk_usuario_participa_banda FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username),
	CONSTRAINT fk_banda_participada_usuario FOREIGN KEY(banda_sigla)
		REFERENCES rede_musical.banda(sigla)
);

CREATE TABLE rede_musical.post_banda (
	post_id INT,
	banda_sigla VARCHAR(5) NOT NULL,
	CONSTRAINT pk_post_banda PRIMARY KEY(post_id),
	CONSTRAINT fk_post_banda FOREIGN KEY(post_id)
		REFERENCES rede_musical.post(id),
	CONSTRAINT fk_banda_post FOREIGN KEY(banda_sigla)
		REFERENCES rede_musical.banda(sigla)
);

CREATE TABLE rede_musical.evento (
	id SERIAL,
	data_inicio TIMESTAMP NOT NULL,
	data_termino TIMESTAMP NOT NULL,
	nome VARCHAR(30) NOT NULL,
	descricao TEXT NOT NULL,
	numero_participantes INT NOT NULL,
	categoria VARCHAR(20) NOT NULL,
	usuario_username VARCHAR(12) NOT NULL,
	CONSTRAINT pk_evento PRIMARY KEY(id),
	CONSTRAINT fk_usuario_criador_evento FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username)
);

CREATE TABLE rede_musical.usuario_comparece_em_evento (
	usuario_username VARCHAR(12),
	evento_id INT,
	CONSTRAINT pk_usuario_comparece_evento PRIMARY KEY(usuario_username, evento_id),
	CONSTRAINT fk_usuario_comparece_evento FOREIGN KEY(usuario_username)
		REFERENCES rede_musical.usuario(username),
	CONSTRAINT fk_evento_comparecido_usuario FOREIGN KEY(evento_id)
		REFERENCES rede_musical.evento(id)
);