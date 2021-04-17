import psycopg2
import random

def gerar_usuarios(connection, cursor):
    i = 0
    nomes = (
        ('Laura','F'),
        ('Maria Clara','F'),
        ('Heloísa','F'),
        ('Mariana','F'),
        ('Ana Luiza','F'),
        ('Gabriela','F'),
        ('Melissa','F'),
        ('Esther','F'),
        ('Emanuelly','F'),
        ('Rebeca','F'),
        ('Ana Beatriz','F'),
        ('Juliana','F'),
        ('Maria Fernanda','F'),
        ('Catarina','F'),
        ('Alícia','F'),
        ('Ana','F'),
        ('Ágata','F'),
        ('Valentina','F'),
        ('Helena','F'),
        ('Claudia','F'),
        ('Lara','F'),
        ('Manuela','F'),
        ('Alana','F'),
        ('Clara','F'),
        ('Isadora','F'),
        ('Yasmin','F'),
        ('Pietra','F'),
        ('Bárbara','F'),
        ('Maria','F'),
        ('Leticia','F'),
        ('Bruna','F'),
        ('Vitória','F'),
        ('Rafaela','F'),
        ('Agnes','F'),
        ('Maísa','F'),
        ('Carolina','F'),
        ('Anabela','F'),
        ('Jade','F'),
        ('Iara','F'),
        ('Brenda','F'),
        ('Isabel','F'),
        ('Estela','F'),
        ('Micaela','F'),
        ('Eva','F'),
        ('Paloma','F'),
        ('Isa','F'),
        ('Sabrina','F'),
        ('Belinda','F'),
        ('Ingrid','F'),
        ('Clarissa','F'),
        ('Alessandra','F'),
        ('Dulce','F'),
        ('Flôr','F'),
        ('Davi','M'),
        ('Lucas','M'),
        ('Guilherme','M'),
        ('Gustavo','M'),
        ('Henrique','M'),
        ('João Pedro','M'),
        ('Murilo','M'),
        ('Pietro','M'),
        ('Benjamin','M'),
        ('Lucca','M'),
        ('Matheus','M'),
        ('João Miguel','M'),
        ('Vicente','M'),
        ('Antônio','M'),
        ('Artur','M'),
        ('Miguel','M'),
        ('Lorenzo','M'),
        ('Theo','M'),
        ('Heitor','M'),
        ('Paulo','M'),
        ('Vinicius','M'),
        ('Erick','M'),
        ('Nicolas','M'),
        ('Mateus','M'),
        ('Bruno','M'),
        ('Hugo','M'),
        ('Igor','M'),
        ('Emanuel','M'),
        ('Aarão','M'),
        ('João','M'),
        ('Rodrigo','M'),
        ('Vinicios','M'),
        ('António','M'),
        ('Lourenço','M'),
        ('Fernando','M'),
        ('Leandro','M'),
        ('Álvaro','M'),
        ('Aquiles','M'),
        ('Roberto','M'),
        ('Pablo','M'),
        ('Santiago','M'),
        ('Muriel','M'),
        ('Abraão','M'),
        ('Alexander','M'),
        ('Jonas','M'),
        ('Michael','M'),
        ('Juliano','M')
    )

    sobrenome = ['Santos', 'Reis', 'Barusso', 'Kaster']

    email = ['gmail.com', 'outlook.com', 'uel.br']
    instrumentos = ['Violão', 'Piano', 'Teclado', 'Bateria', 'Guitarra', 'Baixo', 'Flauta']

    query = """ INSERT INTO rede_musical.usuario (ID, USERNAME, EMAIL, SENHA, PNOME, SNOME, SEXO, DT_NASCIMENTO, CIDADE, ESTADO, PAIS, BANDA_FAVORITA, MUSICA_FAVORITA, GENERO_FAVORITO, INSTRUMENTO_FAVORITO) VALUES (%s,%s,%s,md5(%s),%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"""
    query_feed = """ INSERT INTO rede_musical.feed (USUARIO_ID) VALUES (%s)"""

    while (i < 99):
        insert = (
                i, 
                'username' + str(i), 
                str(nomes[i][0]).lower().replace(' ','_') + '@' + email[random.randint(0, 2)], 
                'a', 
                str(nomes[i][0]), 
                sobrenome[random.randint(0,3)],
                str(nomes[i][1]),
                '2021-01-01',
                'cidade' + str(i), 
                'estado' + str(i), 
                'pais' + str(i), 
                'banda' + str(i), 
                'musica' + str(i), 
                'genero' + str(i), 
                instrumentos[random.randint(0, 5)]
                )
        
        cursor.execute(query, insert)
        cursor.execute(query_feed, [i])
        i+=1

    seguir_usuarios(cursor)

    connection.commit()
    count = cursor.rowcount 
    print(count, "Inserção de Usuários finalizada")


def seguir_usuarios(cursor):
    query_seguir = """ INSERT INTO rede_musical.usuario_segue_usuario (USUARIO_ID, USUARIO_ID_SEGUIDO) VALUES (%s,%s)"""

    i=0

    usuarios_seguidos = []
    while (i < 98):
        numero_seguindo = random.randint(0,98)

        for j in range (numero_seguindo):
            id_seguido = random.randint(0,98)

            if id_seguido != i and id_seguido not in usuarios_seguidos:
                usuarios_seguidos.append(id_seguido)

                insert_seguir = (
                    i,
                    id_seguido
                )

                cursor.execute(query_seguir, insert_seguir)

        i+=1
        usuarios_seguidos.clear()