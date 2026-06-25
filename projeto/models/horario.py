from database import get_db

class Horario:
    def criar(self, dia_semana, hora_inicio, hora_fim, id_usuario):
        db = get_db()
        cursor = db.cursor()
        cursor.execute(
            "INSERT INTO horario (dia_semana, hora_inicio, hora_fim, id_usuario) VALUES (%s, %s, %s, %s)",
            (dia_semana, hora_inicio, hora_fim, id_usuario)
        )
        db.commit()
        return cursor.lastrowid

    def listar_por_usuario(self, id_usuario):
        db = get_db()
        cursor = db.cursor(dictionary=True)
        cursor.execute(
            "SELECT * FROM horario WHERE id_usuario = %s ORDER BY hora_inicio",
            (id_usuario,)
        )
        return cursor.fetchall()

    def buscar_por_id(self, id_horario):
        db = get_db()
        cursor = db.cursor(dictionary=True)
        cursor.execute("SELECT * FROM horario WHERE id_horario = %s", (id_horario,))
        return cursor.fetchone()

    def atualizar(self, id_horario, dia_semana, hora_inicio, hora_fim):
        db = get_db()
        cursor = db.cursor()
        cursor.execute(
            "UPDATE horario SET dia_semana=%s, hora_inicio=%s, hora_fim=%s WHERE id_horario=%s",
            (dia_semana, hora_inicio, hora_fim, id_horario)
        )
        db.commit()

    def deletar(self, id_horario):
        db = get_db()
        cursor = db.cursor()
        cursor.execute("DELETE FROM horario WHERE id_horario = %s", (id_horario,))
        db.commit()
