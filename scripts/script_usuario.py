import psycopg2
import random
from constants import NUMERO_USUARIOS

def gerar_usuarios(connection, cursor):
    i = 1
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

    bandas = (
        ('QNN', 'Queen', 'Rock'),
        ('RHCP', 'Red Hot', 'Rock'),
        ('BDP', 'Barões', 'Forró'),
        ('A7X', 'Avenged Sevenfold', 'Rock'),
        ('TB', 'The Beatles', 'Rock'),
        ('LP', 'Linkin Park', 'Rock'),
        ('JEM', 'Jorge e Matheus', 'Sertanejo'),
        ('PJ', 'Pearl Jam', 'Rock')
    )

    generos = [ 
        'Alternativo',
        'Axé',
        'Blues',
        'Bolero',
        'Bossa Nova',
        'Brega'
        'Clássico',
        'Country',
        'Cuarteto',
        'Cumbia',
        'Dance',
        'Disco',
        'Eletrônica',
        'Emocore',
        'Fado',
        'Folk',
        'Forró',
        'Funk',
        'Funk Internacional',
        'Gospel/Religioso',
        'Grunge',
        'Guarânia',
        'Gótico',
        'Hard Rock',
        'Hardcore',
        'Heavy Metal',
        'Hip Hop/Rap',
        'House',
        'Indie',
        'Industrial',
        'Infantil',
        'Instrumental',
        'J-Pop/J-Rock',
        'Jazz',
        'Jovem Guarda',
        'K-Pop/K-Rock',
        'MPB',
        'Mambo',
        'Marchas/Hinos',
        'Mariachi',
        'Merengue',
        'Música Andina',
        'New Age',
        'New Wave',
        'Pagode',
        'Pop',
        'Pop Rock',
        'Post-Rock',
        'Power-Pop',
        'Psicodelia',
        'Punk Rock',
        'R&B',
        'Ranchera',
        'Reggae',
        'Reggaeton',
        'Regional',
        'Rock',
        'Rock Progressivo',
        'Rockabilly',
        'Romântico',
        'Salsa',
        'Samba',
        'Samba Enredo',
        'Sertanejo',
        'Ska',
        'Soft Rock',
        'Soul',
        'Surf Music',
        'Tango',
        'Tecnopop',
        'Trova',
        'Velha Guarda',
        'World Music',
        'Zamba',
        'Zouk'
    ]

    sobrenome = ['Santos', 'Reis', 'Barusso', 'Kaster']
    email = ['gmail.com', 'outlook.com', 'uel.br']
    instrumentos = ['Violão', 'Piano', 'Teclado', 'Bateria', 'Guitarra', 'Baixo', 'Flauta']

    query = """ INSERT INTO rede_musical.usuario (USERNAME, EMAIL, SENHA, PNOME, SNOME, SEXO, DT_NASCIMENTO, CIDADE, ESTADO, PAIS, BANDA_FAVORITA, MUSICA_FAVORITA, GENERO_FAVORITO, INSTRUMENTO_FAVORITO) VALUES (%s,%s,md5(%s),%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"""
    query_feed = """ INSERT INTO rede_musical.feed (USUARIO_ID) VALUES (%s)"""
    query_instrumentos_usuario = """ INSERT INTO rede_musical.usuario_instrumentos (USUARIO_ID, INSTRUMENTO) VALUES (%s, %s) """

    while i <= NUMERO_USUARIOS:
        index_nome = i % 100

        insert = (
                'username' + str(i), 
                str(nomes[index_nome][0]).lower().replace(' ','_') + str(i) + '@' + email[random.randint(0, 2)], 
                'a', 
                str(nomes[index_nome][0]), 
                sobrenome[random.randint(0,3)],
                str(nomes[index_nome][1]),
                '2021-01-01',
                'cidade' + str(i), 
                'estado' + str(i), 
                'pais' + str(i), 
                bandas[random.randint(0, len(bandas) - 1)][1],
                'musica' + str(i), 
                generos[random.randint(0, len(generos) - 1)], 
                instrumentos[random.randint(0, 5)]
        )
        
        cursor.execute(query, insert)
        cursor.execute(query_feed, [i])

        instrumentos_favoritos = []

        instrumento_favorito = instrumentos[random.randint(0, 5)]
        while instrumento_favorito in instrumentos_favoritos:
            instrumento_favorito = instrumentos[random.randint(0, 5)]
        instrumentos_favoritos.append(instrumento_favorito)

        insert_instrumentos = (
            i,
            instrumento_favorito,
        )
        cursor.execute(query_instrumentos_usuario, insert_instrumentos)

        instrumento_favorito = instrumentos[random.randint(0, 5)]
        while instrumento_favorito in instrumentos_favoritos:
            instrumento_favorito = instrumentos[random.randint(0, 5)]

        insert_instrumentos = (
            i,
            instrumento_favorito,
        )
        cursor.execute(query_instrumentos_usuario, insert_instrumentos)

        i+=1
    
    seguir_usuarios(cursor)
    criar_seguir_bandas(cursor, bandas)

    connection.commit()
    count = cursor.rowcount 
    print(count, "Inserção de Usuários finalizada")


def seguir_usuarios(cursor):
    query_seguir = """ INSERT INTO rede_musical.usuario_segue_usuario (USUARIO_ID, USUARIO_ID_SEGUIDO) VALUES (%s,%s)"""

    i=1

    usuarios_seguidos = []
    while (i <= NUMERO_USUARIOS):
        numero_seguindo = random.randint(0, NUMERO_USUARIOS)

        for j in range (numero_seguindo):
            id_seguido = random.randint(1, NUMERO_USUARIOS)

            if id_seguido != i and id_seguido not in usuarios_seguidos:
                usuarios_seguidos.append(id_seguido)

                insert_seguir = (
                    i,
                    id_seguido
                )

                cursor.execute(query_seguir, insert_seguir)

        i+=1
        usuarios_seguidos.clear()

def criar_seguir_bandas(cursor, bandas):
    query_criar_banda = """ INSERT INTO rede_musical.banda (SIGLA, NOME, GENERO_MUSICAL, USUARIO_ID) VALUES (%s, %s, %s, %s) """
    query_seguir_banda = """ INSERT INTO rede_musical.usuario_segue_banda (USUARIO_ID, BANDA_ID) VALUES (%s, %s) """

    i = 0
    while (i < len(bandas)):
        insert_criar_banda = (
            bandas[i][0],
            bandas[i][1],
            bandas[i][2],
            random.randint(0, NUMERO_USUARIOS),
        )

        cursor.execute(query_criar_banda, insert_criar_banda)
        i+=1
    
    count = cursor.rowcount 
    print(count, "Inserção de Bandas finalizada")

    bandas_seguidas = []
    i = 1
    while (i <= NUMERO_USUARIOS):
        numero_bandas_seguindo = random.randint(0, len(bandas))

        for j in range (numero_bandas_seguindo):
            id_banda_seguida = random.randint(1, len(bandas))

            if id_banda_seguida not in bandas_seguidas:
                bandas_seguidas.append(id_banda_seguida)

                inser_seguir_banda = (
                    i,
                    id_banda_seguida
                )

                cursor.execute(query_seguir_banda, inser_seguir_banda)
        
        i+=1
        bandas_seguidas.clear()