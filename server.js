const express = require('express');
const cors = require('cors'); 
const app = express();

app.use(cors()); 
app.use(express.json());

let plantas = [
  { id: 1, nome: 'Samambaia', preco: 25.00, estoque: 10 },
  { id: 2, nome: 'Cacto', preco: 15.50, estoque: 5 },
  { id: 3, nome: 'Orquídea', preco: 45.00, estoque: 3 },
  { id: 4, nome: 'Suculenta', preco: 12.00, estoque: 20 },
  { id: 5, nome: 'Bromélia', preco: 30.00, estoque: 7 }
];

let idCounter = 6; 

app.get('/plantas', (req, res) => {
  res.json(plantas);
});

app.get('/plantas/filtro', (req, res) => {
  const precoMax = parseFloat(req.query.precoMax);
  const filtradas = plantas.filter(p => p.preco <= precoMax);
  res.json(filtradas);
});

app.get('/plantas/quantidade', (req, res) => {
  res.json({ quantidade: plantas.length });
});

app.get('/plantas/primeira', (req, res) => {
  res.json(plantas[0] || {});
});

app.get('/plantas/ultima', (req, res) => {
  res.json(plantas[plantas.length - 1] || {});
});

app.get('/plantas/estatisticas', (req, res) => {
  if (plantas.length === 0) return res.json({});
  const precoMedio = plantas.reduce((acc, p) => acc + p.preco, 0) / plantas.length;
  const estoqueTotal = plantas.reduce((acc, p) => acc + (p.estoque || 0), 0);
  res.json({ precoMedio, estoqueTotal });
});

app.get('/plantas/:id', (req, res) => {
  const planta = plantas.find(p => p.id === parseInt(req.params.id));
  if (!planta) return res.status(404).send('Planta não encontrada');
  res.json(planta);
});

app.post('/plantas', (req, res) => {
  const nova = { id: idCounter++, ...req.body };
  plantas.push(nova);
  res.status(201).json(nova);
});

app.post('/plantas/lote', (req, res) => {
  const novas = req.body.map(p => ({ id: idCounter++, ...p }));
  plantas.push(...novas);
  res.status(201).json(novas);
});

app.put('/plantas/:id', (req, res) => {
  const index = plantas.findIndex(p => p.id === parseInt(req.params.id));
  if (index === -1) return res.status(404).send('Planta não encontrada');
  plantas[index] = { id: plantas[index].id, ...req.body };
  res.json(plantas[index]);
});

app.delete('/plantas/:id', (req, res) => {
  const index = plantas.findIndex(p => p.id === parseInt(req.params.id));
  if (index === -1) return res.status(404).send('Planta não encontrada');
  const deletada = plantas.splice(index, 1);
  res.json(deletada[0]);
});

app.listen(3000, () => {
  console.log('API de plantas rodando na porta 3000');
});