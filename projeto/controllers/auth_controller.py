from flask import Blueprint, request, jsonify, session
from models.usuario import Usuario
from validacoes import email_valido, senha_valida, TIPOS_USUARIO_VALIDOS
import hashlib

auth_bp = Blueprint('auth', __name__)
usuario_model = Usuario()

def hash_senha(senha):
    return hashlib.sha256(senha.encode()).hexdigest()

@auth_bp.route('/cadastro', methods=['POST'])
def cadastro():
    dados = request.json
    campos = ['nome', 'email', 'senha', 'tipo_usuario']
    for campo in campos:
        if not dados.get(campo):
            return jsonify({'erro': f'Campo {campo} obrigatorio'}), 400

    if not email_valido(dados['email']):
        return jsonify({'erro': 'Email invalido'}), 400

    if not senha_valida(dados['senha']):
        return jsonify({'erro': 'Senha deve ter no minimo 6 caracteres'}), 400

    if dados['tipo_usuario'] not in TIPOS_USUARIO_VALIDOS:
        return jsonify({'erro': f'Tipo de usuario deve ser um de: {TIPOS_USUARIO_VALIDOS}'}), 400

    if usuario_model.buscar_por_email(dados['email']):
        return jsonify({'erro': 'Email ja cadastrado'}), 409

    id_usuario = usuario_model.criar(
        dados['nome'],
        dados['email'],
        hash_senha(dados['senha']),
        dados['tipo_usuario'],
        dados.get('foto_perfil')
    )
    return jsonify({'mensagem': 'Usuario criado com sucesso', 'id_usuario': id_usuario}), 201

@auth_bp.route('/login', methods=['POST'])
def login():
    dados = request.json
    if not dados.get('email') or not dados.get('senha'):
        return jsonify({'erro': 'Email e senha obrigatorios'}), 400

    usuario = usuario_model.buscar_por_email(dados['email'])
    if not usuario or usuario['senha'] != hash_senha(dados['senha']):
        return jsonify({'erro': 'Email ou senha incorretos'}), 401

    session['id_usuario'] = usuario['id_usuario']
    session['nome'] = usuario['nome']
    session['tipo_usuario'] = usuario['tipo_usuario']

    return jsonify({
        'mensagem': 'Login realizado com sucesso',
        'usuario': {
            'id_usuario': usuario['id_usuario'],
            'nome': usuario['nome'],
            'email': usuario['email'],
            'tipo_usuario': usuario['tipo_usuario']
        }
    })

@auth_bp.route('/logout', methods=['POST'])
def logout():
    session.clear()
    return jsonify({'mensagem': 'Logout realizado'})

@auth_bp.route('/perfil', methods=['GET'])
def perfil():
    if 'id_usuario' not in session:
        return jsonify({'erro': 'Nao autenticado'}), 401
    usuario = usuario_model.buscar_por_id(session['id_usuario'])
    usuario.pop('senha', None)
    return jsonify(usuario)
