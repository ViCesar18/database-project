import psycopg2

def limpar_tabelas(connection, cursor):
    limpar_usuarios = """TRUNCATE TABLE rede_musical.usuario RESTART IDENTITY CASCADE"""
    limpar_posts = """TRUNCATE TABLE rede_musical.post RESTART IDENTITY CASCADE"""
    limpar_feed = """TRUNCATE TABLE rede_musical.feed RESTART IDENTITY CASCADE"""
    limpar_bandas = """TRUNCATE TABLE rede_musical.banda RESTART IDENTITY CASCADE"""
    limpar_eventos = """TRUNCATE TABLE rede_musical.evento RESTART IDENTITY CASCADE"""
    gerar_usuario = """INSERT INTO rede_musical.usuario VALUES(-1, 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'M', '2000-04-18', 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'nenhum')"""

    cursor.execute(limpar_usuarios)
    cursor.execute(limpar_bandas)
    cursor.execute(limpar_eventos)
    cursor.execute(gerar_usuario)
    cursor.execute(limpar_posts)
    cursor.execute(limpar_feed)

    connection.commit()