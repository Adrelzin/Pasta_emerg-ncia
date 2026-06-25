from flask import Flask
from database import close_db

from controllers.auth_controller import auth_bp
from controllers.disciplina_controller import disciplina_bp
from controllers.tarefa_controller import tarefa_bp
from controllers.prova_controller import prova_bp
from controllers.meta_controller import meta_bp
from controllers.horario_controller import horario_bp
from controllers.notificacao_controller import notificacao_bp
from controllers.documento_controller import documento_bp

app = Flask(__name__)
app.secret_key = 'pp3_chave_secreta_trocar_depois'

app.register_blueprint(auth_bp, url_prefix='/api')
app.register_blueprint(disciplina_bp, url_prefix='/api')
app.register_blueprint(tarefa_bp, url_prefix='/api')
app.register_blueprint(prova_bp, url_prefix='/api')
app.register_blueprint(meta_bp, url_prefix='/api')
app.register_blueprint(horario_bp, url_prefix='/api')
app.register_blueprint(notificacao_bp, url_prefix='/api')
app.register_blueprint(documento_bp, url_prefix='/api')

app.teardown_appcontext(close_db)

if __name__ == '__main__':
    app.run(debug=True)
