function tabulador(event) {
	if (event.keyCode == 13) {
		var tabla = document.getElementById('form1');
		var objetoFocus = document.activeElement;
		var siguiente = 0;
		var enFoco = false;
		
		for(var i=0;i<tabla.elements.length;i++)  {
			if(tabla.elements[i]==objetoFocus) {
				siguiente = i + 1;
				enFoco = true;
				break;
			}
		}
		while(enFoco){
			if(tabla.elements[siguiente].disabled == false && tabla.elements[siguiente].type != 'hidden' && tabla.elements[siguiente].tabIndex != null){
				tabla.elements[siguiente].focus();
				break;
			}
			else{
				if(siguiente < tabla.elements.length )
					siguiente++;
				else{ siguiente = 0; break;}
			}
				
		}
	}
}