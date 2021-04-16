import psycopg2
import random

i = 0
try:
    connection = psycopg2.connect(user="postgres",
                                  password="3323",
                                  host="localhost",
                                  port="5432",
                                  database="UEL")
    cursor = connection.cursor()

    query = """ INSERT INTO rede_musical.post (ID, TEXTO_POST, DT_PUBLICACAO, USUARIO_ID) VALUES (%s,%s,%s,%s)"""
    query_feed = """ INSERT INTO rede_musical.feed_possui_posts (FEED_ID, POST_ID, ID_USUARIO_COMPARTILHOU) VALUES (%s,%s, -1)"""
    while (i < 99):
        id_usuario = random.randint(1, 98)

        insert = (
                i, 
                'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 
                '2021-01-01', 
                id_usuario
                )

        insert_feed = (
            id_usuario,
            i
        )

        cursor.execute(query, insert)
        cursor.execute(query_feed, insert_feed)
        i+=1


    connection.commit()
    count = cursor.rowcount 
    print(count, "Inserção finalizada")

except (Exception, psycopg2.Error) as error:
    print("Falha ao inserir", error)

finally:
    if connection:
        cursor.close()
        connection.close()
        print("Conexão com PostgreSQL fechada")