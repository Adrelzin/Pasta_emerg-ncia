from flask import Blueprint, request, jsonify, session
from models.documento import Documento
from models.tarefa import Tarefa
from models.disciplina import Disciplina

documento_bp = Blueprint('documento', __name__)
documento_model = Documento()
tarefa_model = Tarefa()
disciplina_model = Disciplina()

TIPOS_ARQUIVO_VALIDOS = ['pdf', 'doc', 'docx', 'png', 'jpg', 'jpeg', 'txt']

def requer_login():
    if 'id_usuario' not in session:
        return jsonify({'erro': 'Nao autenticado'}), 401
    return None

def tarefa_pertence_ao_usuario(id_tarefa, id_usuario):
    tarefa = tarefa_model.buscar_por_id(id_tarefa)
    if not tarefa:
        return False
    disciplina = disciplina_model.buscar_por_id(tarefa['id_disciplina'])
    return bool(disciplina and disciplina['id_usuario'] == id_usuario)

def documento_pertence_ao_usuario(id_documento, id_usuario):
    documento = documento_model.buscar_por_id(id_documento)
    if not documento:
        return None
    if not tarefa_pertence_ao_usuario(documento['id_tarefa'], id_usuario):
        return False
    return documento

@documento_bp.route('/documentos/tarefa/<int:id_tarefa>', methods=['GET'])
def listar(id_tarefa):
    erro = requer_login()
    if erro: return erro
    if not tarefa_pertence_ao_usuario(id_tarefa, session['id_usuario']):
        return jsonify({'erro': 'Acesso negado'}), 403
    return jsonify(documento_model.listar_por_tarefa(id_tarefa))

@documento_bp.route('/documentos', methods=['POST'])
def criar():
    erro = requer_login()
    if erro: return erro
    dados = request.json
    for campo in ['nome_arquivo', 'tipo_arquivo', 'caminho_arquivo', 'id_tarefa']:
        if not dados.get(campo):
            return jsonify({'erro': f'Campo {campo} obrigatorio'}), 400

    if not tarefa_pertence_ao_usuario(dados['id_tarefa'], session['id_usuario']):
        return jsonify({'erro': 'Tarefa invalida'}), 403

    if dados['tipo_arquivo'].lower() not in TIPOS_ARQUIVO_VALIDOS:
        return jsonify({'erro': f'Tipo de arquivo deve ser um de: {TIPOS_ARQUIVO_VALIDOS}'}), 400

    id_d = documento_model.criar(dados['nome_arquivo'], dados['tipo_arquivo'], dados['caminho_arquivo'], dados['id_tarefa'])
    return jsonify({'mensagem': 'Documento registrado', 'id_documento': id_d}), 201

@documento_bp.route('/documentos/<int:id_documento>', methods=['DELETE'])
def deletar(id_documento):
    erro = requer_login()
    if erro: return erro
    documento = documento_pertence_ao_usuario(id_documento, session['id_usuario'])
    if documento is None:
        return jsonify({'erro': 'Documento nao encontrado'}), 404
    if documento is False:
        return jsonify({'erro': 'Acesso negado'}), 403

    documento_model.deletar(id_documento)
    return jsonify({'mensagem': 'Documento removido'})
