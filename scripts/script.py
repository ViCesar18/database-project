import psycopg2
from delete_script import limpar_tabelas
from script_usuario import gerar_usuarios

connection = psycopg2.connect(user="postgres",
                                    password="8468",
                                    host="localhost",
                                    port="5432",
                                    database="uel")

cursor = connection.cursor()

try:
    limpar_tabelas(connection, cursor)

    gerar_usuarios(connection, cursor)

except (Exception, psycopg2.Error) as error:
        print("Falha ao inserir", error)

finally:
    if connection:
        cursor.close()
        connection.close()
        print("Conexão com PostgreSQL fechada")