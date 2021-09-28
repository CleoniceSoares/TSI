/**
 * Carregar os itens de livro do dataset para o LocalStorage.
 * @param {*} newLivros
 */
 function load(newLivros) {
  localStorage.setItem('livros-app:livros', JSON.stringify(newLivros));
}

/**
 * Ler todos os registros de livro.
 * @returns json
 */
function readAll() {
  const stringLivro = localStorage.getItem('livros-app:livros');
  return JSON.parse(stringLivro);
}

function nextId() {
  const livros = readAll();

  const ids = livros.map((livro) => livro.id);

  const maxId = Math.max(...ids);

  return maxId + 1;
}

/**
 * Criar um novo registro de livro.
 * @param {*} livro
 * @returns livro
 */
function create(livro) {
  let id = nextId();

  livro = { id: id, ...livro };

  const livros = readAll();

  const newLivros = [...livros, livro];

  load(newLivros);

  return livro;
}

export default { load, readAll, create };