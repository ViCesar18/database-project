import psycopg2

def limpar_tabelas(connection, cursor):
    limpar_usuarios = """DELETE FROM rede_musical.usuario"""
    limpar_usuario_segue_usuario = """DELETE FROM rede_musical.usuario_segue_usuario"""
    limpar_instrumentos_usuario = """DELETE FROM rede_musical.usuario_instrumentos"""
    limpar_posts = """DELETE FROM rede_musical.post"""
    limpar_feed = """DELETE FROM rede_musical.feed"""
    limpar_feed_possui_posts = """DELETE FROM rede_musical.feed_possui_posts"""
    limpar_bandas = """DELETE FROM rede_musical.banda"""
    limpar_eventos = """DELETE FROM rede_musical.evento"""
    limpar_usuario_segue_banda = """DELETE FROM rede_musical.usuario_segue_banda"""
    gerar_usuario = """INSERT INTO rede_musical.usuario VALUES(-1, 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'M', '2000-04-18', 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'nenhum', 'nenhum')"""

    cursor.execute(limpar_usuarios)
    cursor.execute(limpar_bandas)
    cursor.execute(limpar_eventos)
    cursor.execute(limpar_instrumentos_usuario)
    cursor.execute(gerar_usuario)
    cursor.execute(limpar_usuario_segue_usuario)
    cursor.execute(limpar_usuario_segue_banda)
    cursor.execute(limpar_posts)
    cursor.execute(limpar_feed)
    cursor.execute(limpar_feed_possui_posts)

    connection.commit()