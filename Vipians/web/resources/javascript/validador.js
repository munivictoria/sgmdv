var patronCuil = new Array(2, 8, 1);
var patronFecha = new Array(2, 2, 4);

function Init() {

}

function eventoByCtrlG(e, boton) {
	// Evento CONTROL + G
	if(e.ctrlKey && e.keyCode == 13) {
		e.returnValue = false;
		e.cancel = true;
		var cadena = "form1:";
		var cadena2 = String(boton);
		cadena = cadena + cadena2;
		document.getElementById(cadena).click();
	}
}

function eventoByEnter(e, boton) {
	if(e.keyCode == 13) {
		e.returnValue = false;
		e.cancel = true;
		var elemento = "";

		// Si al apretar el 'enter' esta focuseado el textfield del numero de
		// pagina de la tabla paginada, se direcciona a esa pagina...
		$tfPaginaTablaPaginada = $("#form1").find("input[id *= 'table1:tableRowGroup1:tfPaginaActual']");
		// $tfFiltrarPropiedad =
		// $("form1\\:tbLogsAuditoria\\:tfFiltrarPropiedad");
		// $tfFiltrarUsuario = $("form1\\:tbLogsAuditoria\\:tfFiltrarUsuario");
		// $tfFiltrarFechaDesde =
		// $("form1\\:tbLogsAuditoria\\:tfFiltrarFechaDesde");
		// $tfFiltrarFechaHasta =
		// $("form1\\:tbLogsAuditoria\\:tfFiltrarFechaHasta");

		if($tfPaginaTablaPaginada != null && $tfPaginaTablaPaginada.is(":focus")) {
			var $boton = $("#form1").find("input[id *= 'table1:tableRowGroup1:btnIrPagina']");
			elemento = document.getElementById($boton.attr("id"));
		}
		// else if(($tfFiltrarPropiedad != null
		// || $tfFiltrarUsuario != null
		// || $tfFiltrarFechaDesde != null
		// || $tfFiltrarFechaHasta != null)
		// && ($tfFiltrarPropiedad.is(":focus")
		// || $tfFiltrarUsuario.is(":focus")
		// || $tfFiltrarFechaDesde.is(":focus")
		// || $tfFiltrarFechaHasta.is(":focus"))) {
		// elemento =
		// document.getElementById("form1:tbLogsAuditoria:btnFiltrar");
		// }
		else {
			var cadena = "form1:";
			var cadena2 = String(boton);
			cadena = cadena + cadena2;
			elemento = document.getElementById(cadena);
		}
		elemento.click();

		return false
	}
}

// Inicializadores para captar los 2 "ESC" seguidos en menos de cuarto
// segundo...
var start;
var end;

function eventoByEscape(e, boton) {
	if(e.keyCode === 27) {
		if(typeof start !== "undefined") {
			end = new Date();
			var contMs = end - start;
			if(contMs > 0 && contMs < 251) {
				e.returnValue = false;
				e.cancel = true;
				var nomBoton = String(boton);
				cadena = "form1:" + nomBoton;
				document.getElementById(cadena).click();
			} else {
				start = new Date();
			}
		} else {
			start = new Date();
		}
	}
}

function eventoByPropietario(e, boton) {
	e.returnValue = false;
	e.cancel = true;
	var cadena = "form1:";
	var cadena2 = String(boton);
	cadena = cadena + cadena2;
	document.getElementById(cadena).click();
}

function ValidarNum(e) {
	var tecla = (document.all) ? e.keyCode : e.which;
	if(tecla == 8)
		return true;
	// if (tecla == 17) return true;
	if(tecla == 118 && e.ctrlKey)
		return true; // Para permitir la combinacion Ctrl + v
	if(tecla == 99 && e.ctrlKey)
		return true; // Para permitir la combinacion Ctrl + c
	if(tecla == 120 && e.ctrlKey)
		return true; // Para permitir la combinacion Ctrl + x
	if(tecla == 109)
		return true; // Guion medio, para negativos.
	// if (tecla == 9) return true;
	// if (tecla == 11) return true;
	patron = /[0-9\x00]/; // \x00 es para poder usar tab
	te = String.fromCharCode(tecla);
	return patron.test(te);
}
/*
 * ESTE ESTABA ANTES function ValidarFloat(e){ var tecla = (document.all) ?
 * e.keyCode : e.which; if (tecla == 8) return true; if (tecla == 46) return
 * true; //if (tecla == 9) return true; //if (tecla == 11) return true; patron
 * =/[0-9\x00]/; // \x00 es para poder usar tab te = String.fromCharCode(tecla);
 * return patron.test(te); }
 */
function ValidarFloat(e, obj) {
	tecla = (document.all) ? e.keyCode : e.which;
	if(tecla == 8)
		return true;
	if(tecla == 118 && e.ctrlKey)
		return true; // Para permitir la combinacion Ctrl + v
	if(tecla == 99 && e.ctrlKey)
		return true; // Para permitir la combinacion Ctrl + c
	if(tecla == 120 && e.ctrlKey)
		return true; // Para permitir la combinacion Ctrl + x
	// if (tecla==109) return true; //Guion medio, para negativos.

	Punto = obj.value.split('.');
	if(Punto.length >= 2) {
		patron = /[-0-9\x00]/;
	} else
		patron = /[-0-9.\x00]/;
	te = String.fromCharCode(tecla);

	return patron.test(te);
}

function mascara(valor, separador, pat, nums) {
	if(valor.valant != valor.value) {
		var val = valor.value;
		val = val.split(separador);
		var val2 = '';
		var largo = val.length;
		for( var r = 0; r < largo; r++) {
			val2 += val[r];
		}
		if(nums) {
			for( var z = 0; z < val2.length; z++) {
				if(isNaN(val2.charAt(z))) {
					var letra = new RegExp(val2.charAt(z), "g");
					val2 = val2.replace(letra, "");
				}
			}
		}
		val = '';
		var val3 = new Array();
		for( var s = 0; s < pat.length; s++) {
			val3[s] = val2.substring(0, pat[s]);
			val2 = val2.substr(pat[s]);
		}
		for( var q = 0; q < val3.length; q++) {
			if(q == 0) {
				val = val3[q];
			} else {
				if(val3[q] != "") {
					val += separador + val3[q];
				}
			}
		}
		valor.value = val;
		valor.valant = val;
	}
}
function exportarReporte(titulo) {
	// Valor por defecto de titulo, si no se pasa el parametro toma el valor
	// Reporte
	// Esto hace posible usuarlo sin usar un titulo pasado por el usuario
	var titulo = titulo || "Reporte";
	var llena = isTablaLlena();
	if(llena) {
		newWindow = window.open('/Vipians/faces/ImpresionServlet', titulo);
		return true;
	} else
		return false;
}

function isTablaLlena() {
	var tabla = document.getElementById("form1:table1");
	var cantFilas = tabla.rows.length;
	// mayor a tres filas porque son las filas por defecto de la estructura de
	// la tabla
	// Mayor a tres serian cuando ya habria datos
	if(cantFilas > 3) {
		return true;
	} else {
		return false;
	}
}

function descargarArchivo(titulo) {
	// Valor por defecto de titulo, si no se pasa el parametro toma el valor
	// Reporte
	// Esto hace posible usuarlo sin usar un titulo pasado por el usuario
	var titulo = titulo || "Descargando";
	newWindow = window.open('/Vipians/faces/DescargarArchivoServlet', titulo)
}

function reemplazarClickConConfirmacion(boton, mensaje) {
	var funcionActual = boton.onclick;
	if(mensaje == '')
		mensaje = '\u00BFEst\u00e1 seguro que desea quitar este elemento de la lista?';
	var funcionNueva = (function onClick(e) {
		if(confirm(mensaje)) {
			funcionActual.call();
			return false;
		}
	});
	boton.onclick = funcionNueva;
}

// Hace que el Enter funcione igual al Tab
function tabulador(event) {
	if(event.keyCode == 13) {
		var tabla = document.getElementById('form1');
		var objetoFocus = document.activeElement;
		if(objetoFocus.type == "textarea")
			return true;
		var enFoco = false;

		if(event.shiftKey == false) {
			var siguiente = 0;
			for( var i = 0; i < tabla.elements.length; i++) {
				if(tabla.elements[i] == objetoFocus) {
					siguiente = i + 1;
					enFoco = true;
					break;
				}
			}
			while(enFoco) {
				if(tabla.elements[siguiente].disabled == false && tabla.elements[siguiente].type != 'hidden'
						&& tabla.elements[siguiente].tabIndex != null) {
					tabla.elements[siguiente].focus();
					break;
				} else {
					if(siguiente < tabla.elements.length - 1) {
						siguiente++;
					} else {
						siguiente = 0;
						break;
					}
				}
			}
		} else {
			var anterior = tabla.elements.length - 1;
			for( var i = 0; i < tabla.elements.length; i++) {
				if(tabla.elements[i] == objetoFocus) {
					anterior = i - 1;
					enFoco = true;
					break;
				}
			}
			while(enFoco) {
				if(tabla.elements[anterior].disabled == false && tabla.elements[anterior].type != 'hidden'
						&& tabla.elements[anterior].tabIndex != null) {
					tabla.elements[anterior].focus();
					break;
				} else {
					if(anterior > 0)
						anterior--;
					else {
						anterior = tabla.elements.length - 1;
						break;
					}
				}
			}
		}
	}
}

//
// ***** jQuery **********//
//

// ** Cuando esta listo el documento...
$(document).ready(function() {
	// * Para que focusee al primer textField habilitado y visible...
	var $text = $(':text:enabled:visible:first');
	// solo para saber si encontro algo y esta definido...
	if($text.attr("id")) {
		$text.focus();
	}
	// *

	// * Para cargar el calendario en las fechas dinamicas...
	var $panelesDinamicos = $("#form1").find("span[id *= 'panelAtributoDinamico']");
	for( var i = 0; i < $panelesDinamicos.length; i++) {
		var $fechasDinamicas = $("#" + $panelesDinamicos.eq(i).attr("id").replace(/:/g, "\\:")).find("input[id *= 'textFieldFechaDinamica']");

		for( var j = 0; j < $fechasDinamicas.length; j++) {
			calendarioEnTextField("#" + $fechasDinamicas.eq(j).attr("id"));
		}
	}
		
	// * Para cargar el calendario en las fechas de las tablas de logs y sus placeholder...
	cargarComportamientoEnTablaLogs();
	// *
});

function cargarComportamientoEnTablaLogs() {
	var $pgTablaLogs = $("#form1").find("span[id *= 'tbLogsAuditoria:gpFiltroTablaLogs']");
	
	for( var i = 0; i < $pgTablaLogs.length; i++) {
		var $componentes = $("#" + $pgTablaLogs.eq(i).attr("id").replace(/:/g, "\\:")).find("input[id *= 'tfFiltrar']");

		$("#" + $componentes.eq(0).attr("id").replace(/:/g, "\\:")).attr("placeholder", "Propiedad");
		$("#" + $componentes.eq(1).attr("id").replace(/:/g, "\\:")).attr("placeholder", "Usuario");
		$("#" + $componentes.eq(2).attr("id").replace(/:/g, "\\:")).attr("placeholder", "Fecha desde");
		$("#" + $componentes.eq(3).attr("id").replace(/:/g, "\\:")).attr("placeholder", "Fecha hasta");
		calendarioEnTextField("#" + $componentes.eq(2).attr("id"));
		calendarioEnTextField("#" + $componentes.eq(3).attr("id"));
	}
}

// **

function autoCompletarEnTextField(idComponente, tipoDato, nombreBean, nombreMetodo) {
	autoCompletarEnTextField(idComponente, tipoDato, nombreBean, nombreMetodo, null, null);
}

function autoCompletarEnTextField(idComponente, tipoDato, nombreBean, nombreMetodo, idComponenteFocuseado) {
	autoCompletarEnTextField(idComponente, tipoDato, nombreBean, nombreMetodo, idComponenteFocuseado, null);
}

function autoCompletarEnTextField(idComponente, tipoDato, nombreBean, nombreMetodo, idComponenteFocuseado, idBotonRefrescar) {
	idComponente = idComponente.replace(/:/g, "\\:");
	var vIdSubSesion = $("#form1\\:hidIdSubSesion").attr('value');
	
	$(idComponente).autocomplete({
		minLength: 3,
		autoFocus: true,
		source: function(request, response) {
			$.ajax({
				url: '/Vipians/faces/AutocompletarServlet',
				type: 'get',
				dataType: 'json',
				data: {
					tipo: tipoDato,
					valor: encodeURIComponent(request.term, "UTF-8")
				// Se codifica para mandarlo al servlet
				},
				success: function(resultado) {
					response(resultado);
				}
			});
		},
		select: function(event, ui) {
			$.ajax({
				url: '/Vipians/faces/AutocompletarServlet',
				type: 'get',
				dataType: 'json',
				async: false,
				data: {
					tipo: 'seleccion',
					id: ui.item.id,
					bean: nombreBean,
					metodo: nombreMetodo,
					idSubSesion: vIdSubSesion
				}
			});

			// Se reemplaza el valor seleccionado en el componente, decodificado
			// y reemplazando los '+' con ' '
			$(idComponente).val(decodeURIComponent(ui.item.valor.replace(/\+/g, " ")));

			// Al final, no se deshabilita el componente porque al guardar en un
			// ABM pierde el valor el componente, pero SI esta guardado...
			// $(idComponente).prop('disabled', true);
			
			$(idComponente).prop("readOnly", true);
			$(idComponente).addClass("textFieldDisabled");
			
			// y se focusea a algun componente...
			if(idComponenteFocuseado != null) {
				idComponenteFocuseado = idComponenteFocuseado.replace(/:/g, "\\:");
			} else {
				idComponenteFocuseado = "#form1\\:btnReiniciar";
			}
			$(idComponenteFocuseado).focus();

			if(idBotonRefrescar != null) {
				idBotonRefrescar = idBotonRefrescar.replace(/:/g, "\\:");
				$(idBotonRefrescar).click();
			} 
			
			return false;
		},
		// Se retorna falso para que no borre el contenido del textfield al
		// focusear (palabra de FER) un opcion.
		focus: function(event, ui) {
			return false;
		},
		create: function() {
			$(this).data("ui-autocomplete")._renderItem = function(ul, item) {
				var arreglo = this.element.val().trim().split(" ");
				var cantidad_palabras = arreglo.length;

				// Se reemplazan los valores encontrados en el componente,
				// decodificados y reemplazando los '+' con ' '
				var html = decodeURIComponent(item.valor.replace(/\+/g, " "));

				for( var i = 0; i < cantidad_palabras; i++) {
					var re = new RegExp("(" + arreglo[i] + ")", "gi");
					html = html.replace(re, "<b>$1</b>");
				}

				return $("<li></li>").data("item.autocomplete", item).append($("<a></a>").html(html)).appendTo(ul);
			};
		}
	}).focus(function() {
		// Si focusea el componente, que despliegue el dropdown con la busqueda
		if($(this).val().length > 2) {
			$(this).autocomplete("search");
		}
	});

	$(idComponente).attr("placeholder", $(idComponente).is(':disabled') ? "" : "Comience a escribir para buscar...");
}

function calendarioEnTextField(idComponente) {
	mascaraFechaEnTextField(idComponente);
	idComponente = idComponente.replace(/:/g, "\\:");
	$(idComponente).datepicker({
		onSelect: function() {
			$(idComponente).css("border-color", "#666666");
			$(idComponente).removeAttr("title");
//			$(idComponente).tooltip("destroy");
		}, // al seleccionar se vuelve a poner (por las dudas) el color
		// original del borde; por si se ingreso anteriormente una fecha
		// invalida...
		showOn: $(idComponente).is(':disabled') ? "" : "button",
		buttonText: "Abrir calendario",
		buttonImage: "/Vipians/resources/imagenes/botones/calendario.png",
		buttonImageOnly: true,
		showAnim: "slideDown",
		dateFormat: "dd/mm/yy",
		changeYear: true,
		minDate: "-100y",
		maxDate: "+10y",
		dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
		monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"]
	});

	// Se pone en rojo el borde del componente si se ingresa por teclado una
	// fecha invalida...
	$(idComponente).on("blur", function() {
		var fecha = this.value;
		if(fecha !== undefined && fecha !== "") {
			var valido = true;
			var dia = parseInt(fecha.substring(0, 2), 10);
			var mes = parseInt(fecha.substring(3, 5), 10);
			var anio = parseInt(fecha.substring(6, 10), 10);

			if((dia < 1) || (dia > 31)) {
				valido = false;
			} else if((mes < 1) || (mes > 12)) {
				valido = false;
			} else if(((mes == 4) || (mes == 6) || (mes == 9) || (mes == 11)) && (dia > 30)) {
				valido = false;
			} else if((mes == 2) && (((anio % 400) == 0) || ((anio % 4) == 0)) && ((anio % 100) != 0) && (dia > 29)) {
				valido = false;
			} else if((mes == 2) && ((anio % 100) == 0) && (dia > 29)) {
				valido = false;
			}

			if(!valido) {
				$(idComponente).css("border-color", "red");
				$(idComponente).attr("title", "Formato de fecha incorrecto.");
				$(idComponente).tooltip({
					position: {
						my: "center bottom-20",
						at: "center top",
						using: function(position, feedback) {
							$(this).css(position);
							$("<div>").addClass("arrow").addClass(feedback.vertical).addClass(feedback.horizontal).appendTo(this);
						}
					}
				});
			} else {
				$(idComponente).css("border-color", "#666666");
				$(idComponente).removeAttr("title");
				$(idComponente).tooltip("destroy");
			}
		}
	});
	
	$(idComponente).keypress(function(e) {
		if(e.keyCode == 13) {
			var $paginatedTable = $("#form1").find("table[class *= 'tablaPaginada']");
			if(!$paginatedTable.attr("id") && (this.value == "" || this.value == "  /  /    ")) {
				$(idComponente).val($.datepicker.formatDate('dd/mm/yy', new Date()));
			}
		}
	});
}

function mascaraFechaEnTextField(idComponente) {
	idComponente = idComponente.replace(/:/g, "\\:");
	$(idComponente).mask("99/99/9999", {
		placeholder: " "
	});
}

function mascaraCuimEnTextField(idComponente) {
	idComponente = idComponente.replace(/:/g, "\\:");
	$(idComponente).mask("99-99999999-9", {
		placeholder: " "
	});
}