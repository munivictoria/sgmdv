/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function(){
    var opciones = document.getElementsByName("form1:rbgTipoBusqueda");
    
    var basica = opciones[0];
    var avanzada = opciones[1];
    
    if(basica.checked){
        mostrar();
    }
    if(avanzada.cheked){
        mostrar();
    }
    
    switch(basica){
        case 'onchange':
            basica.onchange = mostrar;
            break;
    }
    switch(avanzada){
        case 'onchange':
            avanzada.onchange = mostrar;
            break;
    }
}
function mostrar() {
    ver = "none"; 
    radio0 = document.getElementById('form1:rbgTipoBusqueda_0');
    radio1 = document.getElementById('form1:rbgTipoBusqueda_1');
    if (radio1.checked){
        ver = "";                                                                                        
    }                                                                                    
    trLabel = document.getElementById('trLabel');
    trOpcionesAvanzadas = document.getElementById('trOpcionesAvanzadas');
    trOpcionesAvanzadas2 = document.getElementById('trOpcionesAvanzadas2');
    trCuadra = document.getElementById('trCuadra');
    trManzana = document.getElementById('trManzana');
    trLabel.style.display = ver;
    trOpcionesAvanzadas.style.display = ver;
    trOpcionesAvanzadas2.style.display = ver;                            
    trCuadra.style.display = ver;                            
    trManzana.style.display = ver;                            
}


