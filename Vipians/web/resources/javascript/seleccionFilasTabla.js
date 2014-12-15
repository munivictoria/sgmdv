function jsRowMouseOver(row) {
	if (getRbtn(row).checked == false) {
		for ( var i = 0; i < row.cells.length; i++) {
			row.cells[i].style.backgroundColor = '#58ACFA ';
		}
	}
}

function jsRowMouseOut(row) {
	if (getRbtn(row).checked == false) {
		for ( var i = 0; i < row.cells.length; i++) {
			row.cells[i].style.backgroundColor = '#FFFFFF ';
		}
	}
}

function getRbtn(row) {
	var cell = row.cells[0];
	var rb = cell.getElementsByTagName('input');
	return rb[0];
}

function jsRowMouseClick(row) {
	var tabla = row.parentNode;
	var rows = tabla.rows;
	if (getRbtn(row).checked == false) {
		var cell;
		var rb;

		for ( var i = 2; i < rows.length; i++) {
			rows[i].cells[0].getElementsByTagName('input').checked = false;
		}

		cell = row.cells[0];
		rb = cell.getElementsByTagName('input');
		rb[0].checked = true;

		for ( var i = 0; i < row.cells.length; i++) {
			row.cells[i].style.backgroundColor = '#BDBDBD ';
		}

	} else if (getRbtn(row).checked == true && getRbtn(row).type == 'checkbox') {
		cell = row.cells[0];
		rb = cell.getElementsByTagName('input');
		rb[0].checked = false;
		jsRowMouseOver(row);
	}

	for ( var i = 2; i < rows.length - 1; i++) {
		if (getRbtn(rows[i]).checked == false) {
			var fila = rows[i];
			jsRowMouseOut(fila);
		}
	}
}

function checkUncheck(input) {
	if (input.checked == true) {
		input.checked = false;
	} else if (input.type == "checkbox") {
		input.checked = true;
	}
}

function changeStyleAlIngresar() {
	var tabla = document.getElementById("form1:table1");
	var filas = tabla.rows;

	for ( var i = 2; i < filas.length; i++) {
		if (getRbtn(filas[i]).checked == true) {
			for ( var j = 0; j < filas[i].cells.length; j++) {
				filas[i].cells[j].style.backgroundColor = '#BDBDBD ';
			}
		}
	}
}

function funcionSeleccionar() {
	document.getElementById("form1:table1:btnSeleccionar").click();
}