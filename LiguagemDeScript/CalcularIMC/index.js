function calculadoraDeIMC() {
    const altura = document.getElementById('altura').value;
    const peso = document.getElementById('peso').value;
    const resultado = document.getElementById('resultado');

    if (altura == '' || peso == '') {
        alert('Os campos devem ser preenchidos!');
    } else if (altura <= 0 || peso <= 0) {
        alert('Campos com valores invalidos, preencha-os corretamente!')
    } else if (altura > 0 && peso > 0) {

        let imc = (peso / (altura * altura)).toFixed(1);
        let sexo = document.getElementsByName('sexo');

        if (sexo[0].checked){
            if (imc < 19.1) {
                resultado.textContent = 'Abaixo do Peso';
                document.querySelector('#resultado').style.background = "#f19f3e";
                
            }
            else if (imc >= 19.1 && imc < 25.8){
                resultado.textContent = 'Peso Normal';
                document.querySelector('#resultado').style.background = "#51f788";
            }
            else if (imc >= 25.8 && imc < 27.3){
                resultado.textContent = 'Marginalmente Acima do Peso';
                document.querySelector('#resultado').style.background = "#f1f335";
            }
            else if (imc >= 27.3 && imc < 32.3){
                resultado.textContent = 'Acima do Peso Ideal';
                 document.querySelector('#resultado').style.background = "#f19f3e";
            }
            else {
                resultado.textContent = 'Obeso';
                document.querySelector('#resultado').style.background = "#dd4d4d";
            }
        }

        
        else{
            if (imc < 20.7) {
                resultado.textContent = 'Abaixo do Peso';
                document.querySelector('#resultado').style.background = "#f19f3e";
                
            }
            else if (imc >= 20.7 && imc < 26.4){
                resultado.textContent = 'Peso Normal';
                document.querySelector('#resultado').style.background = "#51f788";
            }
            else if (imc >= 26.4 && imc < 27.8){
                resultado.textContent = 'Marginalmente Acima do Peso';
                document.querySelector('#resultado').style.background = "#f1f335";
            }
            else if (imc >= 27.8 && imc < 31.1){
                resultado.textContent = 'Acima do Peso Ideal';
                 document.querySelector('#resultado').style.background = "#f19f3e";
            }
            else {
                resultado.textContent = 'Obeso';
                document.querySelector('#resultado').style.background = "#dd4d4d";
            } 
        }
        
    }
}