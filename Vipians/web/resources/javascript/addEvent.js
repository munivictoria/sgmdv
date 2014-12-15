/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function send(nodo) {
	var id = this.replaceAll(nodo.getAttribute("id"), ":", "/");
	createCookie("selCookie", id, 0);
}

function createCookie(name, value, days) {
	var expires = "";
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		expires = "; expires=" + date.toGMTString();
	}
	document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {

	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for ( var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0)
			return c.substring(nameEQ.length, c.length);
	}
	return null;
}

function replaceAll(text, busca, reemplaza) {
	while (text.toString().indexOf(busca) != -1)
		text = text.toString().replace(busca, reemplaza);
	return text;
}



function addEvent(nodo) {
	nodo.addEventListener("click", function() {
		send(this);
	}, false);
};

function addEventNodes(nodos) {
	for ( var i = 0; i < nodos.length; i++) {
		var nodo = nodos[i];
		var nodoId = nodo.getAttribute("id");
		var sufijo = nodoId.substr(nodoId.length - 9);
		if (sufijo == "_children") {
			addEventNodes(nodo.childNodes);
		} else {
			addEvent(nodo);
		}
	}
}

function addEventTrees(pId) {
	var raiz = document.getElementById(pId);
	var nodo = document.getElementById(pId + "_children");
	if (raiz != null && nodo != null) {
		addEvent(raiz);
		addEventNodes(nodo.childNodes);
	}
}
// / Dom del tree
// raiz-> raiz_children[nodo -> nodo_children[], nodo2 -> nodo2_children[nodoN
// -> nodoN_children]]

// TODO esta función puede ser reemplazada en las  por: addEventTrees(pId) ver Ej
// treeProcedimiento.jsp
function Init2() {
	var raiz = null;
	var pId = null;
	if (raiz == null) {
		pId = "form1:trAgregarPlantillaObligacion:tnRaiz";
		raiz = document.getElementById(pId);
	}
	if (raiz == null) {
		pId = "form1:trAgregarDigesto:tnRaiz";
		raiz = document.getElementById(pId);

	}
	if (raiz == null) {
		pId = "form1:trGrupoBien:tnRaiz";
		raiz = document.getElementById(pId);

	}
	if (raiz == null) {
		pId = "form1:trGrupoProveedor:tnRaiz";
		raiz = document.getElementById(pId);
	}
	addEventTrees(pId);
}
