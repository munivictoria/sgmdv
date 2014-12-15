function procesarDatosLabelUpload(componente) {
	var name = componente.id;
	var indice = name.indexOf("-");
	indice += 1;
	var subName = name.substring(indice);
	var upload = document.getElementById("form1:upload-" + subName);
	upload.click();
	upload.addEventListener('change', cambiarEstado, false);
}

function cambiarEstado(evt) {
	var name = this.id;
	var indice = name.indexOf("-");
	indice += 1;
	var subName = name.substring(indice);
	var files = evt.target.files;

	for( var i = 0, f; f = files[i]; i++) {

		var reader = new FileReader();
		var nombre;

		reader.onload = (function(theFile) {
			return function(e) {
				nombre = f.name;
			};
		})(f);

		var lab = document.getElementById("form1:archivo-" + subName);

		lab.value = "";
		lab.innerHTML = "";
		lab.value = f.name;

		reader.readAsDataURL(f);
	}
}

function borrarArchivo(componente) {
	var name = componente.id;
	var indice = name.indexOf("-");
	indice += 1;
	var subName = name.substring(indice);
	var lab = document.getElementById("form1:archivo-" + subName);
	lab.innerHTML = "";
	lab.value = "";
}

function descargarArchivoDinamico(titulo, componente) {
	var name = componente.id;
	var indice = name.indexOf("-");
	indice += 1;
	var subName = name.substring(indice);
	var lab = document.getElementById("form1:archivo-" + subName);

	if(lab.value != "") {
		var titulo = titulo || "Descargando";
		newWindow = window.open('/Vipians/faces/DescargarArchivoServlet', titulo);
	}
}