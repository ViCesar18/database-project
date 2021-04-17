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

        for j in range(total_likes):
            post_like = random.randint(0, len(lista_posts) - 1)

            cursor.execute(inserir_like_query, (i, lista_posts.pop(post_like)))

    connection.commit()
    count = cursor.rowcount 
    print(count, "Inserção de Likes finalizada")
