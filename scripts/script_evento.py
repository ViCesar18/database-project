import psycopg2
import random
from constants import NUMERO_USUARIOS

def gerar_eventos (connection, cursor):
    query_criar_evento = """ INSERT INTO rede_musical.evento (ID, NOME_LOCAL, RUA, NUMERO, BAIRRO, CEP, DATA_INICIO, DATA_TERMINO, NOME, DESCRICAO, NUMERO_PARTICIPANTES, CATEGORIA, USUARIO_ID) VALUES (%s, %s, %s, %s, %s, %s,'2021-04-17 22:00:00', '2021-04-18 05:00:00',%s, %s, %s, %s, %s) """
    
    locais  = ['Morumbi', 'Maracanã', 'Vitrola Bar', 'Beco', 'Valentino', 'Moringão']
    categorias = ['Show', 'Concerto', 'Festival']
    i = 0
    while (i < 21):
        local = locais[random.randint(0, len(locais) - 1)]
        categoria = categorias[random.randint(0, len(categorias) - 1)]
        
        insert_evento = (
            i, 
            local,
            "Rua do " + local,
            "Numero do " + local,
            "Bairro do " + local,
            "CEP do " + local,
            categoria + " no " + local,
            "Vai ter um(a) " + categoria + " no " + local + ". Esperamos todos vocês lá!",
            0,
            categoria,
            random.randint(0, NUMERO_USUARIOS - 1)
        )
        
        cursor.execute(query_criar_evento, insert_evento)
        i+=1

    comparecer_evento(connection, cursor)

    connection.commit()
    count = cursor.rowcount 
    print(count, "Inserção de Eventos finalizada")

def comparecer_evento (connect, cursor):
    query_usuario_comparece_evento = """ INSERT INTO rede_musical.usuario_comparece_em_evento (USUARIO_ID, EVENTO_ID) VALUES (%s, %s) """

    eventos_comparecidos = []
    i = 0
    while(i < 98):
        numero_eventos_comparecidos = random.randint(0, 20)

        for j in range (numero_eventos_comparecidos):
            id_evento_comparecido = random.randint(0, 20)

            if id_evento_comparecido not in eventos_comparecidos:
                eventos_comparecidos.append(id_evento_comparecido)

                insert_comparecer_evento = (
                    i,
                    id_evento_comparecido
                )

                cursor.execute(query_usuario_comparece_evento, insert_comparecer_evento)

        i+=1
        eventos_comparecidos.clear()


