import dataset from './model/dataset.js';
import livrosModel from './model/livros.js';

const formLivro = document.querySelector('#formLivro');

function loadLivros(){

  if(localStorage.getItem('livros-app:loaded')!== 'OK'){
    livrosModel.load(dataset);
    localStorage.setItem('livros-app:loaded', 'OK');
  }
}

livrosModel.load(dataset);
let livros = livrosModel.readAll();

for (const item of livros){
  const itensDiv = document.getElementById('itens');
  const cardHTML = addItem(item);
  itensDiv.insertAdjacentHTML('beforeend', cardHTML);
}

function addItem(item) {
  let cardHTML = `
    <div class='col'>
      <div class='card' style='width: 18em;'>
        <img src='${'imagens/' + item.image}' class='card-img-top' alt='...'>
        <div class='card-body'>
          <h5 class='card-title'>${item.name}</h5>
          <h5 class='card-title'>${item.autor}</h5>
          <p class='card-text text-justify'>${item.description}</p>
          <a href='#' class='btn btn-primary'>Adicionar Livro</a>
        </div>
      </div>
    </div>`;
  return cardHTML;
}

const livroForm = document.getElementById('formLivro');

function loadFormValue(title, livroName, livroAutor, livroImage, livroDescription) {
  const formLabel = document.querySelector('#formLivroLabel');
  const formNameInput = document.querySelector('#name');
  const formAutorInput = document.querySelector('#autor');
  const formImageInput = document.querySelector('#image');
  const formDescriptionInput = document.querySelector('#description');

  formLabel.innerHTML = title;
  formNameInput.value = livroName;
  formAutorInput.value = livroAutor;
  formImageInput.value = livroImage;
  formDescriptionInput.value = livroDescription;

}

function loadFormCreateLivro(){
  loadFormValue('Adicionar Livro', '', '', '');

  livroForm.onsubmit = function (event) {
    // Previnir que o modal fique abrindo em loop.
    event.preventDefault();

    // Montar o objeto livro baseado nos dados do formulário.
    let livro = Object.fromEntries(new FormData(formLivro));

    // Adicionar o item (livro) no LocalStorage.
    const newLivro = livrosModel.create(livro);

    const itensDiv = document.getElementById('itens');
    const cardHTML = addItem(newLivro);
    itensDiv.insertAdjacentHTML('beforeend', cardHTML);

    // Fechar o livroModal
    var myModalEl = document.getElementById('livroModal');
    var livroModal = bootstrap.Modal.getInstance(myModalEl);
    livroModal.toggle();
  };
}

window.loadFormCreateLivro = loadFormCreateLivro;

loadLivros();