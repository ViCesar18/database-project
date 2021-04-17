import psycopg2
import random

def gerar_likes(connection, cursor):
    buscar_posts_query = """SELECT post_id FROM rede_musical.feed_possui_posts fpp WHERE feed_id = %s"""

    inserir_like_query = """INSERT INTO rede_musical.usuario_da_like_em_post (usuario_id , post_id) VALUES (%s, %s)"""

    for i in range(100):
        cursor.execute(buscar_posts_query, [i])
        row = cursor.fetchone()

        lista_posts = []
        while row is not None:
            lista_posts.append(row[0])
            row = cursor.fetchone()

        total_likes = random.randint(0, len(lista_posts))

        for _ in range(total_likes):
            post_like = random.choice(lista_posts)

            cursor.execute(inserir_like_query, (i, post_like))

            lista_posts.remove(post_like)

    connection.commit()
    count = cursor.rowcount 
    print(count, "Inserção de Likes finalizada")


def gerar_comentarios(connection, cursor):
    buscar_posts_query = """SELECT post_id FROM rede_musical.feed_possui_posts fpp WHERE feed_id = %s"""

    inserir_comentario_query = """INSERT INTO rede_musical.comentario (dt_publicacao, texto_comentario, usuario_id, post_id) VALUES (%s, %s, %s, %s)"""

    for i in range(100):
        cursor.execute(buscar_posts_query, [i])
        row = cursor.fetchone()

        lista_posts = []
        while row is not None:
            lista_posts.append(row[0])
            row = cursor.fetchone()

        total_comentarios = random.randint(0, len(lista_posts))

        for _ in range(total_comentarios):
            post_comentario = random.choice(lista_posts)

            comentario = (
                '2021-01-01',
                'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse a neque dolor. Nam et interdum.',
                i,
                post_comentario
            )

            cursor.execute(inserir_comentario_query, comentario)

            lista_posts.remove(post_comentario)

    connection.commit()
    count = cursor.rowcount 
    print(count, "Inserção de Comentários finalizada")


def gerar_compartilhamentos(connection, cursor):
    buscar_posts_query = """SELECT post_id FROM rede_musical.feed_possui_posts fpp WHERE feed_id = %s"""

    inserir_compartilhamento_query = """INSERT INTO rede_musical.usuario_compartilha_post (usuario_id , post_id) VALUES (%s, %s)"""

    for i in range(100):
        cursor.execute(buscar_posts_query, [i])
        row = cursor.fetchone()

        lista_posts = []
        while row is not None:
            lista_posts.append(row[0])
            row = cursor.fetchone()

        total_compartilhamentos = random.randint(0, len(lista_posts))

        for _ in range(total_compartilhamentos):
            post_compartilhamento = random.choice(lista_posts)

            cursor.execute(inserir_compartilhamento_query, (i, post_compartilhamento))

            lista_posts.remove(post_compartilhamento)

    connection.commit()
    count = cursor.rowcount 
    print(count, "Inserção de Compartilhamentos finalizada")