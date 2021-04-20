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
)
LIMIT 1;


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
)
LIMIT 1;


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
)
LIMIT 1;


--Retornar o número de interações
SELECT numero_likes,
       numero_comentarios,
       numero_compartilhamentos,
       numero_likes + numero_comentarios + numero_compartilhamentos AS numero_interacoes
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

SELECT * FROM rede_musical.post u

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


--Gênero preferido dos homens
SELECT genero_favorito, COUNT(*) AS frequencia
FROM rede_musical.usuario u
WHERE sexo = 'M'
GROUP BY sexo, genero_favorito
ORDER BY frequencia DESC;


--Gênero preferido das mulheres
SELECT genero_favorito, COUNT(*) AS frequencia
FROM rede_musical.usuario u
WHERE sexo = 'F'
GROUP BY sexo, genero_favorito
ORDER BY frequencia DESC;


--Gerações Cadastradas na Rede
SELECT *
FROM (
         SELECT COUNT(*) AS geracao_boomer_count
         FROM rede_musical.usuario u
         GROUP BY DATE_PART('year', AGE(dt_nascimento)) >= 60
         HAVING DATE_PART('year', AGE(dt_nascimento)) >= 60
     ) geracao_boomer,
     (
         SELECT COUNT(*) AS geracao_x_count
         FROM rede_musical.usuario u
         GROUP BY DATE_PART('year', AGE(dt_nascimento)) >= 40 AND DATE_PART('year', AGE(dt_nascimento)) < 60
         HAVING DATE_PART('year', AGE(dt_nascimento)) >= 40 AND DATE_PART('year', AGE(dt_nascimento)) < 60
     ) geracao_x,
     (
         SELECT COUNT(*) AS geracao_millennials_count
         FROM rede_musical.usuario u
         GROUP BY DATE_PART('year', AGE(dt_nascimento)) >= 25 AND DATE_PART('year', AGE(dt_nascimento)) < 40
         HAVING DATE_PART('year', AGE(dt_nascimento)) >= 25 AND DATE_PART('year', AGE(dt_nascimento)) < 40
     ) geracao_millennials,
     (
         SELECT COUNT(*) AS geracao_z_count
         FROM rede_musical.usuario u
         GROUP BY DATE_PART('year', AGE(dt_nascimento)) >= 10 AND DATE_PART('year', AGE(dt_nascimento)) < 25
         HAVING DATE_PART('year', AGE(dt_nascimento)) >= 10 AND DATE_PART('year', AGE(dt_nascimento)) < 25
     ) geracao_z;


--Frequencia por Geração
SELECT frequencia_posts + frequencia_likes + frequencia_comentarios + frequencia_compartilhamentos AS frequencia_total_geracao
FROM (
         SELECT SUM(frequencia) AS frequencia_posts
         FROM (
                  SELECT usuario_id, COUNT(*) AS frequencia
                  FROM rede_musical.post p
                  GROUP BY usuario_id
              ) posts JOIN rede_musical.usuario u ON u.id = posts.usuario_id
         WHERE DATE_PART('year', AGE(dt_nascimento)) >= 60 AND DATE_PART('year', AGE(dt_nascimento)) <= 91
     ) posts,
     (
         SELECT SUM(frequencia) AS frequencia_likes
         FROM (
                  SELECT usuario_id, COUNT(*) AS frequencia
                  FROM rede_musical.usuario_da_like_em_post udlep
                  GROUP BY usuario_id
              ) posts JOIN rede_musical.usuario u ON u.id = posts.usuario_id
         WHERE DATE_PART('year', AGE(dt_nascimento)) >= 60 AND DATE_PART('year', AGE(dt_nascimento)) <= 91
     ) likes,
     (
         SELECT SUM(frequencia) AS frequencia_comentarios
         FROM (
                  SELECT usuario_id, COUNT(*) AS frequencia
                  FROM rede_musical.comentario c
                  GROUP BY usuario_id
              ) posts JOIN rede_musical.usuario u ON u.id = posts.usuario_id
         WHERE DATE_PART('year', AGE(dt_nascimento)) >= 60 AND DATE_PART('year', AGE(dt_nascimento)) <= 91
     ) comentarios,
     (
         SELECT SUM(frequencia) AS frequencia_compartilhamentos
         FROM (
                  SELECT usuario_id, COUNT(*) AS frequencia
                  FROM rede_musical.usuario_compartilha_post ucp
                  GROUP BY usuario_id
              ) posts JOIN rede_musical.usuario u ON u.id = posts.usuario_id
         WHERE DATE_PART('year', AGE(dt_nascimento)) >= 60 AND DATE_PART('year', AGE(dt_nascimento)) <= 91
     ) compartilhamentos;