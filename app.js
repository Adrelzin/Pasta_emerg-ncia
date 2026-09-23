# Estéfanas #
function NotasScreen({ navigation }) {
  const [notas, salvar] = useArmazenado('notas', []);
  const [materia, setMateria] = useState('');
  const [valor, setValor] = useState('');
  const [erro, setErro] = useState('');

  const media = notas.length ? notas.reduce((s, n) => s + n.valor, 0) / notas.length : 0;

  const adicionar = () => {
    const v = parseFloat(valor.replace(',', '.'));
    if (!materia.trim()) return setErro('Digite a disciplina.');
    if (isNaN(v) || v < 0 || v > 10) return setErro('A nota deve estar entre 0 e 10.');
    setErro('');
    salvar([...notas, { id: Date.now().toString(), materia: materia.trim(), valor: v }]);
    setMateria('');
    setValor('');
  };
  const remover = (id) => salvar(notas.filter((n) => n.id !== id));

  return (
    <ScrollView style={styles.container} contentContainerStyle={{ paddingBottom: 40 }}>
      <Cabecalho navigation={navigation} titulo="Notas" />
      <View style={styles.card}>
        <Text style={styles.cardDescricao}>Média geral</Text>
        <Text style={styles.mediaValor}>{media.toFixed(1)}</Text>
        <View style={styles.barra}>
          <View style={[styles.barraPreenchida, { width: `${media * 10}%` }]} />
        </View>
      </View>
      <TextInput style={styles.input} placeholder="Disciplina" placeholderTextColor={colors.textSecondary} value={materia} onChangeText={setMateria} />
      <TextInput style={styles.input} placeholder="Nota (0 a 10)" placeholderTextColor={colors.textSecondary} keyboardType="decimal-pad" value={valor} onChangeText={setValor} />
      {erro ? <Text style={styles.erro}>{erro}</Text> : null}
      <TouchableOpacity style={styles.botao} onPress={adicionar}>
        <Text style={styles.botaoTexto}>Adicionar nota</Text>
      </TouchableOpacity>
      <View style={styles.espaco} />
      {notas.length === 0 ? <Text style={styles.cardDescricao}>Nenhuma nota ainda.</Text> : null}
      {notas.map((n) => (
        <View key={n.id} style={styles.card}>
          <View style={styles.linhaEntre}>
            <Text style={styles.cardTitulo}>{n.materia}</Text>
            <Text style={styles.cardTitulo}>{n.valor.toFixed(1)}</Text>
          </View>
          <View style={styles.barra}>
            <View style={[styles.barraPreenchida, { width: `${n.valor * 10}%` }]} />
          </View>
          <TouchableOpacity style={{ marginTop: 10 }} onPress={() => remover(n.id)}>
            <Text style={styles.remover}>Excluir</Text>
          </TouchableOpacity>
        </View>
      ))}
    </ScrollView>
  );
}

function MetasScreen({ navigation }) {
  const [metas, salvar] = useArmazenado('metas', []);
  const [titulo, setTitulo] = useState('');
  const [alvo, setAlvo] = useState('');
  const [erro, setErro] = useState('');

  const adicionar = () => {
    const a = parseInt(alvo, 10);
    if (!titulo.trim()) return setErro('Digite o título da meta.');
    if (!a || a < 1) return setErro('Informe as horas da meta (mínimo 1).');
    setErro('');
    salvar([...metas, { id: Date.now().toString(), titulo: titulo.trim(), alvo: a, feito: 0 }]);
    setTitulo('');
    setAlvo('');
  };
  const somar = (id) => salvar(metas.map((m) => (m.id === id && m.feito < m.alvo ? { ...m, feito: m.feito + 1 } : m)));
  const remover = (id) => salvar(metas.filter((m) => m.id !== id));

  return (
    <ScrollView style={styles.container} contentContainerStyle={{ paddingBottom: 40 }}>
      <Cabecalho navigation={navigation} titulo="Metas" />
      <TextInput style={styles.input} placeholder="Meta (ex: Revisar Cálculo)" placeholderTextColor={colors.textSecondary} value={titulo} onChangeText={setTitulo} />
      <TextInput style={styles.input} placeholder="Horas de estudo" placeholderTextColor={colors.textSecondary} keyboardType="number-pad" value={alvo} onChangeText={setAlvo} />
      {erro ? <Text style={styles.erro}>{erro}</Text> : null}
      <TouchableOpacity style={styles.botao} onPress={adicionar}>
        <Text style={styles.botaoTexto}>Adicionar meta</Text>
      </TouchableOpacity>
      <View style={styles.espaco} />
      {metas.length === 0 ? <Text style={styles.cardDescricao}>Nenhuma meta ainda.</Text> : null}
      {metas.map((m) => (
        <View key={m.id} style={styles.card}>
          <View style={styles.linhaEntre}>
            <Text style={styles.cardTitulo}>{m.titulo}</Text>
            <Text style={styles.cardTipo}>{m.feito}/{m.alvo}h</Text>
          </View>
          <View style={styles.barra}>
            <View style={[styles.barraPreenchida, { width: `${(m.feito / m.alvo) * 100}%` }]} />
          </View>
          <View style={[styles.linhaEntre, { marginTop: 12 }]}>
            <TouchableOpacity onPress={() => somar(m.id)}>
              <Text style={styles.adicionar}>{m.feito >= m.alvo ? 'Meta concluída' : '+1h estudada'}</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => remover(m.id)}>
              <Text style={styles.remover}>Excluir</Text>
            </TouchableOpacity>
          </View>
        </View>
      ))}
    </ScrollView>
  );
}

const DURACOES = [15, 25, 45];

function FocoScreen({ navigation }) {
  const [minutos, setMinutos] = useState(25);
  const [restante, setRestante] = useState(25 * 60);
  const [rodando, setRodando] = useState(false);

  useEffect(() => {
    if (!rodando) return;
    const id = setInterval(() => setRestante((r) => Math.max(r - 1, 0)), 1000);
    return () => clearInterval(id);
  }, [rodando]);

  useEffect(() => {
    if (restante === 0) setRodando(false);
  }, [restante]);

  const escolher = (m) => {
    setMinutos(m);
    setRestante(m * 60);
    setRodando(false);
  };
  const fim = restante === 0;
  const mm = String(Math.floor(restante / 60)).padStart(2, '0');
  const ss = String(restante % 60).padStart(2, '0');

  return (
    <View style={styles.container}>
      <Cabecalho navigation={navigation} titulo="Modo de foco" />
      <View style={[styles.chips, { justifyContent: 'center' }]}>
        {DURACOES.map((m) => (
          <TouchableOpacity key={m} style={[styles.chip, m === minutos && styles.chipAtivo]} onPress={() => escolher(m)}>
            <Text style={[styles.chipTexto, m === minutos && styles.chipTextoAtivo]}>{m} min</Text>
          </TouchableOpacity>
        ))}
      </View>
      <Text style={styles.tempo}>{mm}:{ss}</Text>
      {fim ? <Text style={styles.subtitulo}>Sessão concluída. Faça uma pausa.</Text> : null}
      <TouchableOpacity style={styles.botao} onPress={() => (fim ? escolher(minutos) : setRodando(!rodando))}>
        <Text style={styles.botaoTexto}>{fim ? 'Nova sessão' : rodando ? 'Pausar' : 'Iniciar'}</Text>
      </TouchableOpacity>
      {!fim && restante !== minutos * 60 ? (
        <TouchableOpacity onPress={() => escolher(minutos)}>
          <Text style={styles.link}>Zerar</Text>
        </TouchableOpacity>
      ) : null}
    </View>
  );
}

# Estela #

function LembretesScreen({ navigation }) {
  const [lembretes, salvar] = useArmazenado('lembretes', []);
  const [titulo, setTitulo] = useState('');
  const [hora, setHora] = useState('');
  const [erro, setErro] = useState('');

  const adicionar = () => {
    if (!titulo.trim()) return setErro('Digite o texto do lembrete.');
    if (!horaValida(hora)) return setErro('Use o formato HH:MM (ex: 18:00).');
    setErro('');
    salvar([...lembretes, { id: Date.now().toString(), titulo: titulo.trim(), hora, ativo: true }].sort((a, b) => a.hora.localeCompare(b.hora)));
    setTitulo('');
    setHora('');
  };
  const alternar = (id) => salvar(lembretes.map((l) => (l.id === id ? { ...l, ativo: !l.ativo } : l)));
  const remover = (id) => salvar(lembretes.filter((l) => l.id !== id));

  return (
    <ScrollView style={styles.container} contentContainerStyle={{ paddingBottom: 40 }}>
      <Cabecalho navigation={navigation} titulo="Lembretes" />
      <TextInput style={styles.input} placeholder="Lembrete (ex: Estudar Física)" placeholderTextColor={colors.textSecondary} value={titulo} onChangeText={setTitulo} />
      <TextInput style={styles.input} placeholder="Horário (ex: 18:00)" placeholderTextColor={colors.textSecondary} keyboardType="numbers-and-punctuation" value={hora} onChangeText={setHora} />
      {erro ? <Text style={styles.erro}>{erro}</Text> : null}
      <TouchableOpacity style={styles.botao} onPress={adicionar}>
        <Text style={styles.botaoTexto}>Adicionar lembrete</Text>
      </TouchableOpacity>
      <View style={styles.espaco} />
      {lembretes.length === 0 ? <Text style={styles.cardDescricao}>Nenhum lembrete ainda.</Text> : null}
      {lembretes.map((l) => (
        <View key={l.id} style={[styles.card, styles.linha]}>
          <View style={styles.dataBox}>
            <Text style={styles.dataTexto}>{l.hora}</Text>
          </View>
          <View style={{ flex: 1 }}>
            <Text style={[styles.cardTitulo, !l.ativo && styles.riscado]}>{l.titulo}</Text>
            <TouchableOpacity onPress={() => remover(l.id)}>
              <Text style={styles.remover}>Excluir</Text>
            </TouchableOpacity>
          </View>
          <Switch value={l.ativo} onValueChange={() => alternar(l.id)} trackColor={{ true: colors.olive, false: colors.border }} />
        </View>
      ))}
    </ScrollView>
  );
}

const OPCOES = [
  ['fonteGrande', 'Fonte grande'],
  ['altoContraste', 'Alto contraste'],
];

function PerfilScreen({ navigation }) {
  const [usuario, setUsuario] = useState(null);
  const [acess, salvar] = useArmazenado('acessibilidade', { fonteGrande: false, altoContraste: false });

  useEffect(() => {
    AsyncStorage.getItem('usuario').then((v) => v && setUsuario(JSON.parse(v)));
  }, []);

  const fundo = acess.altoContraste ? '#000000' : colors.surface;
  const texto = acess.altoContraste ? '#FFFFFF' : colors.textPrimary;

  return (
    <ScrollView style={styles.container} contentContainerStyle={{ paddingBottom: 40 }}>
      <Cabecalho navigation={navigation} titulo="Perfil" />
      <View style={styles.card}>
        <Text style={styles.cardTitulo}>{usuario?.nome ?? 'Estudante'}</Text>
        <Text style={styles.cardDescricao}>{usuario?.email ?? 'E-mail não disponível'}</Text>
      </View>
      <Text style={styles.secaoTitulo}>Acessibilidade</Text>
      {OPCOES.map(([chave, rotulo]) => (
        <View key={chave} style={[styles.card, styles.linhaEntre]}>
          <Text style={[styles.cardTitulo, { marginBottom: 0 }]}>{rotulo}</Text>
          <Switch value={acess[chave]} onValueChange={(v) => salvar({ ...acess, [chave]: v })} trackColor={{ true: colors.olive, false: colors.border }} />
        </View>
      ))}
      <Text style={styles.secaoTitulo}>Pré-visualização</Text>
      <View style={[styles.card, { backgroundColor: fundo }]}>
        <Text style={{ color: texto, fontSize: acess.fonteGrande ? 22 : 15 }}>Sua rotina escolar organizada em um só lugar.</Text>
      </View>
    </ScrollView>
  );
}
