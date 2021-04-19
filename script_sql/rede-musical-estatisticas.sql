--Retorna o post com maior número de likes
SELECT p.id, COUNT(p.id) AS numero_likes
FROM rede_musical.post p 
JOIN rede_musical.usuario_da_like_em_post udlep ON p.id = udlep.post_id
GROUP BY p.id
HAVING COUNT(p.id) >= (
	SELECT MAX(maior_numero_likes.numero_likes)
	FROM (
		SELECT p.id AS id, COUNT(p.id) AS numero_likes
		FROM rede_musical.post p 
		JOIN rede_musical.usuario_da_like_em_post udlep ON p.id = udlep.post_id
		GROUP BY p.id
	) maior_numero_likes
);


--Retorna o post com maior número de comentários
SELECT p.id, COUNT(p.id) AS numero_comentarios
FROM rede_musical.post p 
JOIN rede_musical.comentario c ON p.id = c.post_id
GROUP BY p.id
HAVING COUNT(p.id) >= (
	SELECT MAX(maior_numero_comentarios.numero_comentarios)
	FROM (
		SELECT p.id AS id, COUNT(p.id) AS numero_comentarios
		FROM rede_musical.post p 
		JOIN rede_musical.comentario c ON p.id = c.post_id
		GROUP BY p.id
	) maior_numero_comentarios
);


--Retorna o post com maior número de compartilhamentos
SELECT p.id, COUNT(p.id) AS numero_compartilhamentos
FROM rede_musical.post p 
JOIN rede_musical.usuario_compartilha_post ucp ON p.id = ucp.post_id
GROUP BY p.id
HAVING COUNT(p.id) >= (
	SELECT MAX(maior_numero_compartilhamentos.numero_compartilhamentos)
	FROM (
		SELECT p.id AS id, COUNT(p.id) AS numero_compartilhamentos
		FROM rede_musical.post p 
		JOIN rede_musical.usuario_compartilha_post ucp ON p.id = ucp.post_id
		GROUP BY p.id
	) maior_numero_compartilhamentos
);


--Retornar o número de interações
SELECT numero_likes + numero_comentarios + numero_compartilhamentos AS numero_interacoes
FROM (
	SELECT COUNT(*) numero_likes
	FROM rede_musical.usuario_da_like_em_post
) likes,
(
	SELECT COUNT(*) numero_comentarios
	FROM rede_musical.comentario
) comentarios,
(
	SELECT COUNT(*) numero_compartilhamentos
	FROM rede_musical.usuario_compartilha_post
) compartilhamentos;


--Instrumento mais tocados por mulheres
SELECT ui.instrumento, COUNT(*) AS frequencia
FROM rede_musical.usuario_instrumentos ui
	JOIN rede_musical.usuario u ON ui.usuario_id = u.id
WHERE u.sexo = 'F'
GROUP BY u.sexo, ui.instrumento
ORDER BY frequencia DESC;


--Instrumento mais tocado por homens
SELECT ui.instrumento, COUNT(*) AS frequencia
FROM rede_musical.usuario_instrumentos ui
	JOIN rede_musical.usuario u ON ui.usuario_id = u.id
WHERE u.sexo = 'M'
GROUP BY u.sexo, ui.instrumento
ORDER BY frequencia DESC;


--G�nero preferido dos homens
SELECT genero_favorito, COUNT(*) AS frequencia
FROM rede_musical.usuario u 
WHERE sexo = 'M'
GROUP BY sexo, genero_favorito
ORDER BY frequencia DESC;


--G�nero preferido das mulheres
SELECT genero_favorito, COUNT(*) AS frequencia
FROM rede_musical.usuario u 
WHERE sexo = 'F'
GROUP BY sexo, genero_favorito
ORDER BY frequencia DESC;






