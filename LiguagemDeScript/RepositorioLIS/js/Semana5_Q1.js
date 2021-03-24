function createList(qtdLi, texto){
  console.log("Criando a lista!");
  for(let i = 1; i < qtdLi ; i++){
    let node = document.createElement("li");
    let textnode = document.createTextNode(texto + " " + i);
    node.appendChild(textnode);
    document.getElementById("myList").appendChild(node);
  }
}
createList(6, "Cleo!");
