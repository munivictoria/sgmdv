<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false" />
	<f:view>
		<ui:page binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.page1}" id="page1">
			<ui:html binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.html1}" id="html1">
			<ui:head binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.head1}" id="head1">
				<ui:link binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
                        function validarDropDown(){
                            var dropDownY = document.getElementById("form1:ddUnion1");
                            if(dropDownY.selectedIndex == 0){
                               var variableY = document.getElementById("form1:ddVariable2");
                               variableY.disabled = true;
                               variableY.style.background = '#DDDDDD';
                               var operadorY = document.getElementById("form1:ddOperador2");
                               operadorY.disabled = true;
                               operadorY.style.background = '#DDDDDD';
                               var botonY = document.getElementById("btnAddVar:tfValor2");
                               botonY.disabled = true;
                               botonY.style.background = '#DDDDDD';
                               var valorY = document.getElementById("form1:tfValor2");
                               valorY.disabled = true;
                               valorY.style.background = '#DDDDDD';
                            }
                            var dropDownO = document.getElementById("form1:ddUnion2");
                            if(dropDownO.selectedIndex == 0){
                               var variableO = document.getElementById("form1:ddVariable3");
                               variableO.disabled = true;
                               variableO.style.background = '#DDDDDD';
                               var operadorO = document.getElementById("form1:ddOperador3");
                               operadorO.disabled = true;
                               operadorO.style.background = '#DDDDDD';
                               var botonO = document.getElementById("btnAddVar:tfValor3");
                               botonO.disabled = true;
                               botonO.style.background = '#DDDDDD';
                               var valorO = document.getElementById("form1:tfValor3");
                               valorO.disabled = true;
                               valorO.style.background = '#DDDDDD';
                            }
                        }
                        function onchangeDropDownY(){
                            var dropDownY = document.getElementById("form1:ddUnion1");
                            if(dropDownY.selectedIndex == 0){
                               var variableY = document.getElementById("form1:ddVariable2");
                               variableY.disabled = true;
                               variableY.style.background = '#DDDDDD';
                               var operadorY = document.getElementById("form1:ddOperador2");
                               operadorY.disabled = true;
                               operadorY.style.background = '#DDDDDD';
                               var botonY = document.getElementById("btnAddVar:tfValor2");
                               botonY.disabled = true;
                               botonY.style.background = '#DDDDDD';
                               var valorY = document.getElementById("form1:tfValor2");
                               valorY.disabled = true;
                               valorY.style.background = '#DDDDDD';
                            }else{
                               var variableY = document.getElementById("form1:ddVariable2");
                               variableY.disabled = false;
                               variableY.style.background = '#FFFFFF';
                               var operadorY = document.getElementById("form1:ddOperador2");
                               operadorY.disabled = false;
                               operadorY.style.background = '#FFFFFF';
                               var botonY = document.getElementById("btnAddVar:tfValor2");
                               botonY.disabled = false;
                               botonY.style.background = '#FFFFFF';
                               var valorY = document.getElementById("form1:tfValor2");
                               valorY.disabled = false;
                               valorY.style.background = '#FFFFFF';
                            }
                        }
                        function onchangeDropDownO(){
                            var dropDownO = document.getElementById("form1:ddUnion2");
                            if(dropDownO.selectedIndex == 0){
                               var variableO = document.getElementById("form1:ddVariable3");
                               variableO.disabled = true;
                               variableO.style.background = '#DDDDDD';
                               var operadorO = document.getElementById("form1:ddOperador3");
                               operadorO.disabled = true;
                               operadorO.style.background = '#DDDDDD';
                               var botonO = document.getElementById("btnAddVar:tfValor3");
                               botonO.disabled = true;
                               botonO.style.background = '#DDDDDD';
                               var valorO = document.getElementById("form1:tfValor3");
                               valorO.disabled = true;
                               valorO.style.background = '#DDDDDD';
                            }else{
                               var variableO = document.getElementById("form1:ddVariable3");
                               variableO.disabled = false;
                               variableO.style.background = '#FFFFFF';
                               var operadorO = document.getElementById("form1:ddOperador3");
                               operadorO.disabled = false;
                               operadorO.style.background = '#FFFFFF';
                               var botonO = document.getElementById("btnAddVar:tfValor3");
                               botonO.disabled = false;
                               botonO.style.background = '#FFFFFF';
                               var valorO = document.getElementById("form1:tfValor3");
                               valorO.disabled = false;
                               valorO.style.background = '#FFFFFF';
                            }
                        }
                    </script>
				<script>
					<![CDATA[

                        var strTrue  = "== 1";
                        var strFalse = "== 0";

                        function agregarVariableAFormula(ddId) {
                            valor = dropDown_getSelectedValue(ddId);
                            if (valor != null) {
                                campo = "form1:taFormula";
                                txtFormula = document.getElementById(campo);
                                txtFormula.value = txtFormula.value + valor;
                            }
                        }

                        function agregarATextField(btnId) {
                            valor = dropDown_getSelectedValue("form1:lbVariables");
                            if (valor != null) {
                                campo = "form1:" + btnId.substring( btnId.indexOf(":")+1 );
                                txt = document.getElementById(campo);
                                txt.value = txt.value + valor;
                            }
                        }

                        function cambiarLabelCuotas() {
                            cbFija = document.getElementById("form1:cbFija");
                            labelCantCuotas = document.getElementById("form1:labelCantidadCuotas");
                            //alert(etiqueta.innerHTML);
                            if (cbFija.checked) {
                                labelCantCuotas.innerHTML = "Cantidad de Cuotas";
                            }
                            else {
                                labelCantCuotas.innerHTML = "Cantidad de Cuotas por Período";
                            }
                        }

                        function cambiarCantidadCuotas() {
                            ddPeriodicidad = document.getElementById("form1:ddPeriodicidad");
                            ddPeriodicidadCuotas = document.getElementById("form1:ddPeriodicidadCuotas");
                            tfCantidadCuotas = document.getElementById("form1:tfCantidadCuotas");

                            if (ddPeriodicidad.value == ddPeriodicidadCuotas.value) {
                                tfCantidadCuotas.value = "1";
                            }
                        }

                        function cambiarEstadoTextField(ddId) {
                            seleccionado = dropDown_getSelectedValue(ddId);
                            campo = "form1:tfValor" + ddId.substring( ddId.length-1 );
                            boton = "btnAddVar:tfValor" + ddId.substring( ddId.length-1 );
                            btn = document.getElementById(boton);
                            txt = document.getElementById(campo);

                            if (seleccionado == "== 1" || seleccionado == "== 0") {
                                txt.disabled = true;
                                btn.disabled = true;
                                btn.className = "Btn2MniDis";
                                txt.className = "textFieldDisabled";
                                if (seleccionado == strTrue) txt.value = "1";
                                else if (seleccionado == strFalse) txt.value = "0";
                            }
                            else {
                                txt.disabled = false;
                                btn.disabled = false;
                                btn.className = "Btn2Mni";
                                txt.className = "textField";
                            }
                        }

                        function agregarCondicionAFormula() {
                            // variables seleccionadas en los dropdown
                            var1 = dropDown_getSelectedValue("form1:ddVariable1");
                            var2 = dropDown_getSelectedValue("form1:ddVariable2");
                            var3 = dropDown_getSelectedValue("form1:ddVariable3");

                            // uniones
                            uni1 = dropDown_getSelectedValue("form1:ddUnion1");
                            uni2 = dropDown_getSelectedValue("form1:ddUnion2");

                            // operadores usados
                            ope1 = dropDown_getSelectedValue("form1:ddOperador1");
                            ope2 = dropDown_getSelectedValue("form1:ddOperador2");
                            ope3 = dropDown_getSelectedValue("form1:ddOperador3");

                            // valores de resultado de la condicion
                            tfVal1 = trim(document.getElementById("form1:tfValor1").value);
                            tfVal2 = trim(document.getElementById("form1:tfValor2").value);
                            tfVal3 = trim(document.getElementById("form1:tfValor3").value);

                            // valor final
                            tfValFinal = trim(document.getElementById("form1:tfValorFinal").value);

                            // valor final sino
                            tfValFinalSino = trim(document.getElementById("form1:tfValorFinalSino").value);

                            valor = null;
                            campo = "form1:taFormula";
                            txtFormula = document.getElementById(campo);

                            if (ope1 != "" && tfVal1 != "" && tfValFinal != "") {

                                //valor = "A";

                                if (ope1 == strTrue || ope1 == strFalse) valor = "((" + var1 + " " + ope1 + ")";
                                else valor = "((" + var1 + " " + ope1 + " (" + tfVal1 + "))";

                                if (uni1 != "" && ope2 != "" && tfVal2 != "") {

                                    if (ope2 == strTrue || ope2 == strFalse) valor = valor + " " + uni1 + " " + "(" + var2 + " " + ope2 + ")";
                                    else valor = valor + " " + uni1 + " " + "(" + var2 + " " + ope2 + " (" + tfVal2 + "))";

                                    if (uni2 != "" && ope3 != "" && tfVal3 != "") {
                                        if (ope2 == strTrue || ope2 == strFalse) valor = valor + " " + uni2 + " " + "(" + var3 + " " + ope3 + ")";
                                        else valor = valor + " " + uni2 + " " + "(" + var3 + " " + ope3 + " (" + tfVal3 + "))";
                                    }

                                }

                                if (tfValFinalSino != "") {
                                    valor = valor + ") * (" + tfValFinal + ") + !" + valor + ") * (" + tfValFinalSino + ")";
                                }
                                else {
                                    valor = valor + ") * (" + tfValFinal + ")";
                                }

                                valor = "(" + valor + ")";
                                txtFormula.value = txtFormula.value + " " + valor;
                            }

                        }

                        function limpiarCondiciones() {
                            document.getElementById("form1:ddVariable1").selectedIndex = 0;
                            document.getElementById("form1:ddVariable2").selectedIndex = 0;
                            document.getElementById("form1:ddVariable3").selectedIndex = 0;

                            document.getElementById("form1:ddUnion1").selectedIndex = 0;
                            document.getElementById("form1:ddUnion2").selectedIndex = 0;

                            document.getElementById("form1:ddOperador1").selectedIndex = 0;
                            document.getElementById("form1:ddOperador2").selectedIndex = 0;
                            document.getElementById("form1:ddOperador3").selectedIndex = 0;

                            val1 = document.getElementById("form1:tfValor1");
                            val2 = document.getElementById("form1:tfValor2");
                            val3 = document.getElementById("form1:tfValor3");

                            val1.value = null;
                            val1.disabled = false;
                            val1.className = "textField";
                            val2.value = null;
                            val2.disabled = false;
                            val2.className = "textField";
                            val3.value = null;
                            val3.disabled = false;
                            val3.className = "textField";

                            document.getElementById("form1:tfValorFinal").value = null;
                            document.getElementById("form1:tfValorFinalSino").value = null;

                            btn1 = document.getElementById("btnAddVar:tfValor1");
                            btn2 = document.getElementById("btnAddVar:tfValor2");
                            btn3 = document.getElementById("btnAddVar:tfValor3");

                            btn1.disabled = false; btn1.className = "Btn2Mni";
                            btn2.disabled = false; btn2.className = "Btn2Mni";
                            btn3.disabled = false; btn3.className = "Btn2Mni";
                        }

                        function trim(str) {
                            return str.replace(/^\s*|\s*$/g,"");
                        }
                    ]]></script>
			</ui:head>
			<ui:body binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init(); validarDropDown();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<!-- el enter se caco adrede-->
				<ui:form binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label8}" for="tfTipoObligacion" id="label8" styleClass="label"
											text="Fórmula de Cálculo para" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfTipoObligacion}" columns="40" disabled="true"
											id="tfTipoObligacion" styleClass="textFieldDisabled" />
									</td>
								</tr>
								<tr>
									<td colspan="2" align="right">
									<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.lblPlan}" for="ddPlanes" id="lblPlan" styleClass="label" text="Plan" />
									</td>
									<td>
										<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddPlanes}" id="ddPlanes"
											items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddPlanDefaultOptions.options}" styleClass="textField" >
										</ui:dropDown>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td colspan="2">
										<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label2}" for="cbFija" id="label2" styleClass="label"
											text="Cantidad Fija de Cuotas" />
									</td>
									<td colspan="2">
										<ui:checkbox binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.cbFija}" id="cbFija" onClick="cambiarLabelCuotas();" />
										<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText6}" escape="false" id="staticText6" styleClass="label"
											text="&lt;sup&gt;&amp;nbsp;[una vez saldadas las Cuotas, finaliza la Obligación]&lt;/sup&gt;" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="4" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label3}" id="label3" styleClass="label2" text="Interés por Mora" />
									</td>
								</tr>
								<!-- *********************************************************************************************************************************************** -->
								<!--   Probar cambiar table 1 por tblInteres    -->
								<tr>
									<td colspan="4" nowrap="nowrap">
										<ui:table augmentTitle="false" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tblInteres}" id="tblInteres" width="604">
											<script>
												<![CDATA[
                                        /* ----- Functions for Table Preferences Panel ----- */
                                        /*
                                         * Toggle the table preferences panel open or closed
                                         */
                                        function togglePreferencesPanel() {
                                            var table = document.getElementById("form1:table1");
                                            table.toggleTblePreferencesPanel();
                                        }
                                        /* ----- Functions for Filter Panel ----- */
                                        /*
                                         * Return true if the filter menu has actually changed,
                                         * so the corresponding event should be allowed to continue.
                                         */
                                        function filterMenuChanged() {
                                            var table = document.getElementById("form1:table1");
                                            return table.filterMenuChanged();
                                        }
                                        /*
                                         * Toggle the custom filter panel (if any) open or closed.
                                         */
                                        function toggleFilterPanel() {
                                            var table = document.getElementById("form1:table1");
                                            return table.toggleTableFilterPanel();
                                        }
                                        /* ----- Functions for Table Actions ----- */
                                        /*
                                         * Initialize all rows of the table when the state
                                         * of selected rows changes.
                                         */
                                        function initAllRows() {
                                            var table = document.getElementById("form1:table1");
                                            table.initAllRows();
                                        }
                                        /*
                                         * Set the selected state for the given row groups
                                         * displayed in the table.  This functionality requires
                                         * the 'selectId' of the tableColumn to be set.
                                         *
                                         * @param rowGroupId HTML element id of the tableRowGroup component
                                         * @param selected Flag indicating whether components should be selected
                                         */
                                        function selectGroupRows(rowGroupId, selected) {
                                            var table = document.getElementById("form1:table1");
                                            table.selectGroupRows(rowGroupId, selected);
                                        }
                                        /*
                                         * Disable all table actions if no rows have been selected.
                                         */
                                        function disableActions() {
                                            // Determine whether any rows are currently selected
                                            var table = document.getElementById("form1:table1");
                                            var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                                            // Set disabled state for top actions
                                            document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
                                            // Set disabled state for bottom actions
                                            document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
                                        }]]></script>
											<ui:tableRowGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableRowGroup5}" id="tableRowGroup5"
												sourceData="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ldpInteres}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn18}" id="tableColumn18"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.radioButton5}" id="radioButton5" label="" name="buttonGroup5"
														selected="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.RBSelected5}"
														selectedValue="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.currentRow5}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn19}" headerText="Nombre" id="tableColumn19"
													sort="nombre">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText19}" id="staticText19"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn20}" headerText="Fórmula de Cálculo"
													id="tableColumn20" sort="formula">
													<ui:textArea binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.textArea3}" columns="40" disabled="true" id="textArea3"
														styleClass="textFieldDisabled" text="#{currentRow.value['formula']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.groupPanel4}" id="groupPanel4">
													<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarInteres_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarInteres}" id="btnAgregarInteres" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnModificarInteres_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnModificarInteres}" id="btnModificarInteres" styleClass="button"
														text="Modificar" />
													<a4j:commandButton action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarInteres_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarInteres}" id="btnQuitarInteres"
														onmouseup="return (confirm(&quot;¿Está seguro que desea Quitar el Interes?&quot;));" styleClass="btnAjax" reRender="tblInteres" value="Quitar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<!--  ************************************************************************************************************************************************  -->
								<tr>
									<td align="left" colspan="4" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label20}" for="tfRecargo" id="label20" styleClass="label2"
											text="Agregar Recargo" />
										<ui:table augmentTitle="false" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tblRecargo}" id="tblRecargo" width="604">
											<script>
												<![CDATA[
                                /* ----- Functions for Table Preferences Panel ----- */
                                /*
                                 * Toggle the table preferences panel open or closed
                                 */
                                function togglePreferencesPanel() {
                                    var table = document.getElementById("form1:table1");
                                    table.toggleTblePreferencesPanel();
                                }
                                /* ----- Functions for Filter Panel ----- */
                                /*
                                 * Return true if the filter menu has actually changed,
                                 * so the corresponding event should be allowed to continue.
                                 */
                                function filterMenuChanged() {
                                    var table = document.getElementById("form1:table1");
                                    return table.filterMenuChanged();
                                }
                                /*
                                 * Toggle the custom filter panel (if any) open or closed.
                                 */
                                function toggleFilterPanel() {
                                    var table = document.getElementById("form1:table1");
                                    return table.toggleTableFilterPanel();
                                }
                                /* ----- Functions for Table Actions ----- */
                                /*
                                 * Initialize all rows of the table when the state
                                 * of selected rows changes.
                                 */
                                function initAllRows() {
                                    var table = document.getElementById("form1:table1");
                                    table.initAllRows();
                                }
                                /*
                                 * Set the selected state for the given row groups
                                 * displayed in the table.  This functionality requires
                                 * the 'selectId' of the tableColumn to be set.
                                 *
                                 * @param rowGroupId HTML element id of the tableRowGroup component
                                 * @param selected Flag indicating whether components should be selected
                                 */
                                function selectGroupRows(rowGroupId, selected) {
                                    var table = document.getElementById("form1:table1");
                                    table.selectGroupRows(rowGroupId, selected);
                                }
                                /*
                                 * Disable all table actions if no rows have been selected.
                                 */
                                function disableActions() {
                                    // Determine whether any rows are currently selected
                                    var table = document.getElementById("form1:table1");
                                    var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                                    // Set disabled state for top actions
                                    document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
                                    // Set disabled state for bottom actions
                                    document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
                                }]]></script>
											<ui:tableRowGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableRowGroup6}" id="tableRowGroup6"
												sourceData="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ldpRecargo}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn21}" id="tableColumn21"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.radioButton6}" id="radioButton6" label="" name="buttonGroup6"
														selected="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.RBSelected6}"
														selectedValue="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.currentRow6}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn22}" headerText="Nombre" id="tableColumn22"
													sort="nombre">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText20}" id="staticText20"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn23}" headerText="Fórmula de Cálculo"
													id="tableColumn23" sort="formulaCalculo">
													<ui:textArea binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.textArea4}" columns="40" disabled="true" id="textArea4"
														styleClass="textFieldDisabled" text="#{currentRow.value['formula']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.groupPanel5}" id="groupPanel5">
													<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarRecargo_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarRecargo}" id="btnAgregarRecargo" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnModificarRecargo_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnModificarRecargo}" id="btnModificarRecargo" styleClass="button"
														text="Modificar" />
													<a4j:commandButton action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarRecargo_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarRecargo}" id="btnQuitarRecargo"
														onmouseup="return (confirm(&quot;¿Está seguro que desea Quitar el Recargo?&quot;));" styleClass="btnAjax" reRender="tblInteres" value="Quitar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td>
									<td nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.lblVariablesFormula}" id="lblVariablesFormula" styleClass="label3"
											text="Variables de la Fórmula" />
									</td>
								<tr>
									<td>
										<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarVariable_action}"
											binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarVariable}" id="btnAgregarVariable" styleClass="button" text="Agregar" />
									<td>
										<a4j:commandButton action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarVariable_action}"
											binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarVariable}" id="btnQuitarVariable"
											onmouseup="return (confirm(&quot;¿Está seguro que desea Quitar la Variable?&quot;));" styleClass="btnAjax" reRender="table4" value="Quitar" />
									</td>
									</td>
								<tr>
									<td colspan="4" nowrap="nowrap">
										<div style="overflow: auto; width: 790px; height: 200px">
											<ui:table augmentTitle="false" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.table4}" id="table4">
												<script>
													<![CDATA[
                /* ----- Functions for Table Preferences Panel ----- */
                /*
                 * Toggle the table preferences panel open or closed
                 */
                function togglePreferencesPanel() {
                    var table = document.getElementById("form1:table4");
                    table.toggleTblePreferencesPanel();
                }
                /* ----- Functions for Filter Panel ----- */
                /*
                 * Return true if the filter menu has actually changed,
                 * so the corresponding event should be allowed to continue.
                 */
                function filterMenuChanged() {
                    var table = document.getElementById("form1:table4");
                    return table.filterMenuChanged();
                }
                /*
                 * Toggle the custom filter panel (if any) open or closed.
                 */
                function toggleFilterPanel() {
                    var table = document.getElementById("form1:table4");
                    return table.toggleTableFilterPanel();
                }
                /* ----- Functions for Table Actions ----- */
                /*
                 * Initialize all rows of the table when the state
                 * of selected rows changes.
                 */
                function initAllRows() {
                    var table = document.getElementById("form1:table4");
                    table.initAllRows();
                }
                /*
                 * Set the selected state for the given row groups
                 * displayed in the table.  This functionality requires
                 * the 'selectId' of the tableColumn to be set.
                 *
                 * @param rowGroupId HTML element id of the tableRowGroup component
                 * @param selected Flag indicating whether components should be selected
                 */
                function selectGroupRows(rowGroupId, selected) {
                    var table = document.getElementById("form1:table4");
                    table.selectGroupRows(rowGroupId, selected);
                }
                /*
                 * Disable all table actions if no rows have been selected.
                 */
                function disableActions() {
                    // Determine whether any rows are currently selected
                    var table = document.getElementById("form1:table4");
                    var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                    // Set disabled state for top actions
                    document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
                    // Set disabled state for bottom actions
                    document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
                }]]></script>
												<ui:tableRowGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableRowGroup4}" id="tableRowGroup4"
													sourceData="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ldpVariablesFormula}" sourceVar="currentRow4">
													<ui:tableColumn align="center" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn6}" id="tableColumn6"
														valign="middle" width="10">
														<ui:radioButton binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.radioButton4}" id="radioButton4" label=""
															name="buttonGroup7" selected="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.RBSelected4}"
															selectedValue="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.currentRow4}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tcNombre}" headerText="Nombre" id="tcNombre" sort="nombre">
														<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfNombreVariable}" id="tfNombreVariable"
															text="#{currentRow4.value['nombre']}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tcExpresion}" headerText="Expresion" id="tcExpresion"
														sort="expresion">
														<ui:textArea binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.taExpresion}" id="taExpresion"
															text="#{currentRow4.value['expresion']}" columns="60" rows="3" />
													</ui:tableColumn>
												</ui:tableRowGroup>
											</ui:table>
										</div>
									</td>
								</tr>
								</tr>
								</td>
								</tr>
								<tr>
									<td colspan="4" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label5}" id="label5" styleClass="label2"
											text="Composición de la Fórmula" />
									</td>
								</tr>
								<tr>
									<td colspan="2" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label13}" id="label13" styleClass="label3" text="Parámetros" />
									</td>
								</tr>
								<tr>
									<td colspan="2" nowrap="nowrap">
										<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddTiposParametro}" id="ddTiposParametro"
											items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddTiposParametroDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="1" nowrap="nowrap">
										<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnNuevoParametro_action}"
											binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnNuevoParametro}" id="btnNuevoParametro" mini="true" styleClass="button"
											text="Nuevo" />
									</td>
									<td colspan="1" nowrap="nowrap">
										<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarParametro_action}"
											binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarParametro}" escape="false" id="btnQuitarParametro" mini="true"
											styleClass="button" text="Quitar" />
									</td>
								</tr>
								<tr>
									<td colspan="2" nowrap="nowrap">
										<ui:listbox binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.lbVariables}" id="lbVariables"
											items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.lbVariablesDefaultOptions.options}"
											onDblClick="agregarVariableAFormula(this.id);" rows="7" style="width:100%" styleClass="textField"
											toolTip="Haga doble clic para insertar un parámetro a la fórmula." />
									</td>
									<td align="right" nowrap="nowrap">
										<table>
											<tr>
												<td colspan="4" nowrap="nowrap">
													<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label14}" id="label14" styleClass="label3"
														text="Armado de Condiciones" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label7}" for="ddVariable1" id="label7" styleClass="label" text="Si" />
												</td>
												<td align="left" colspan="3" nowrap="nowrap">
													<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddVariable1}" id="ddVariable1"
														items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.lbVariablesDefaultOptions.options}" styleClass="textField" />
													<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddOperador1}" id="ddOperador1"
														items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddOperador1DefaultOptions.options}"
														onChange="cambiarEstadoTextField(this.id);" styleClass="textField" />
													<input class="Btn2Mni" id="btnAddVar:tfValor1" onClick="agregarATextField(this.id);" onblur="return this.myonblur();"
														onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();" onmouseover="return this.myonmouseover();"
														title="Insertar Parámetro Seleccionado" type="button" value="&gt;" />
													<script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor1', defaultButtonStrings, true, true, false);</script>
													<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfValor1}" columns="25" id="tfValor1" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right">
													<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddUnion1}" id="ddUnion1"
														items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddUnion1DefaultOptions.options}" styleClass="textField"
														onChange="onchangeDropDownY()" />
												</td>
												<td align="left" colspan="3" nowrap="nowrap">
													<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddVariable2}" id="ddVariable2"
														items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.lbVariablesDefaultOptions.options}" styleClass="textField" />
													<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddOperador2}" id="ddOperador2"
														items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddOperador1DefaultOptions.options}"
														onChange="cambiarEstadoTextField(this.id);" styleClass="textField" />
													<input class="Btn2Mni" id="btnAddVar:tfValor2" onClick="agregarATextField(this.id);" onblur="return this.myonblur();"
														onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();" onmouseover="return this.myonmouseover();"
														title="Insertar Parámetro Seleccionado" type="button" value="&gt;" />
													<script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor2', defaultButtonStrings, true, true, false);</script>
													<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfValor2}" columns="25" id="tfValor2" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right">
													<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddUnion2}" id="ddUnion2"
														items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddUnion1DefaultOptions.options}" styleClass="textField"
														onChange="onchangeDropDownO()" />
												</td>
												<td align="left" colspan="3" nowrap="nowrap">
													<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddVariable3}" id="ddVariable3"
														items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.lbVariablesDefaultOptions.options}" styleClass="textField" />
													<ui:dropDown binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddOperador3}" id="ddOperador3"
														items="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ddOperador1DefaultOptions.options}"
														onChange="cambiarEstadoTextField(this.id);" styleClass="textField" />
													<input class="Btn2Mni" id="btnAddVar:tfValor3" onClick="agregarATextField(this.id);" onblur="return this.myonblur();"
														onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();" onmouseover="return this.myonmouseover();"
														title="Insertar Parámetro Seleccionado" type="button" value="&gt;" />
													<script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor3', defaultButtonStrings, true, true, false);</script>
													<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfValor3}" columns="25" id="tfValor3" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right">
													<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label6}" for="tfValorFinal" id="label6" styleClass="label"
														text="Entonces" />
												</td>
												<td align="left" colspan="3" nowrap="nowrap">
													<input class="Btn2Mni" id="btnAddVar:tfValorFinal" onClick="agregarATextField(this.id);" onblur="return this.myonblur();"
														onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();" onmouseover="return this.myonmouseover();"
														title="Insertar Parámetro Seleccionado" type="button" value="&gt;" />
													<script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValorFinal', defaultButtonStrings, true, true, false);</script>
													<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfValorFinal}" columns="60" id="tfValorFinal"
														styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label9}" for="tfValorFinalSino" id="label9" styleClass="label"
														text="Sino" />
												</td>
												<td align="left" colspan="3" nowrap="nowrap">
													<input class="Btn2Mni" id="btnAddVar:tfValorFinalSino" onClick="agregarATextField(this.id);" onblur="return this.myonblur();"
														onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();" onmouseover="return this.myonmouseover();"
														title="Insertar Parámetro Seleccionado" type="button" value="&gt;" />
													<script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValorFinalSino', defaultButtonStrings, true, true, false);</script>
													<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfValorFinalSino}" columns="60" id="tfValorFinalSino"
														styleClass="textField" />
												</td>
											<tr>
												<td colspan="4" align="center">
													<input class="Btn2Mni button" id="btnAddCond:taFormula" onClick="agregarCondicionAFormula();" onblur="return this.myonblur();"
														onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();" onmouseover="return this.myonmouseover();"
														title="Agregar Condición a la Fórmula" type="button" value=" Agregar a Fórmula" />
													<script type="text/javascript">sjwuic_assign_button('btnAddCond:taFormula', defaultButtonStrings, true, true, false);</script>
													<input class="Btn2Mni buttonLimpiar" title="Limpiar" id="btnLimpiarCondicion" onClick="limpiarCondiciones();" onblur="return this.myonblur();"
														onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();" onmouseover="return this.myonmouseover();"
														type="button" value="&amp;nbsp;" />
													<script type="text/javascript">sjwuic_assign_button('btnLimpiarCond', defaultButtonStrings, true, true, false);</script>
												</td>
											</tr>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2" nowrap="nowrap"></td>
								</tr>
								<!--   
                                    <ui:panelGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.groupPanel7}" id="groupPanel7">
                                    <tr>
                                        <td nowrap="nowrap">
                                        <input class="Btn2Mni button" id="btnAddVar:taFormula" onClick="agregarVariableAFormula('form1:lbVariables');"
                                               onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                               onmouseover="return this.myonmouseover();" title="Agregar Parámetro a la Fórmula" type="button" value=" Agregar a Fórmula"/>
                                        <script type="text/javascript">sjwuic_assign_button('btnAddVar:taFormula', defaultButtonStrings, true, true, false);</script>
                                    </td>
                                    </tr>
                                    </ui:panelGroup> -->
								<tr>
									<td colspan="4">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label12}" for="taFormula" id="label12" styleClass="label"
											text="Fórmula:" />
										<br />
										<ui:textArea binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.taFormula}" id="taFormula" rows="4"
											style="font-size:11px; width:100%" styleClass="textField" />
										<br />Operadores: + [suma] - [resta] * [multiplicación] / [división] <br />Funciones: redondear(x) - redondear(x,p) [x:
										expresión, p: cantidad de digitos decimales]
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br /> <br />
									</td>
								</tr>
								<tr>
									<td colspan="4" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label21}" id="label21" styleClass="label2" text="Fórmula Alicuota" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarFormulaAlicuota_action}"
											binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarFormulaAlicuota}" id="btnAgregarFormulaAlicuota" styleClass="button"
											text="Agregar/Editar" />
										<!--
                                        <ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnModificarFormulaAlicuota_action}"
                                                   binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnModificarFormulaAlicuota}"
                                                   id="btnModificarFormulaAlicuota"
                                                   styleClass="button" text="Modificar"/>
                                                   -->
										<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarFormulaAlicuota_action}"
											binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarFormulaAlicuota}" id="btnQuitarFormulaAlicuota" styleClass="button"
											text="Quitar" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:textArea binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.taFormulaAlicuota}" id="taFormulaAlicuota" rows="4"
											style="font-size:11px; width:100%" styleClass="textField" disabled="true" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label10}" id="label10" styleClass="label2" text="Modificadores" />
									</td>
								</tr>
								<tr>
									<td colspan="4" nowrap="nowrap">
										<ui:table augmentTitle="false" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.table1}" id="table1" width="604">
											<script>
												<![CDATA[
                        /* ----- Functions for Table Preferences Panel ----- */
                        /*
                         * Toggle the table preferences panel open or closed
                         */
                        function togglePreferencesPanel() {
                            var table = document.getElementById("form1:table1");
                            table.toggleTblePreferencesPanel();
                        }
                        /* ----- Functions for Filter Panel ----- */
                        /*
                         * Return true if the filter menu has actually changed,
                         * so the corresponding event should be allowed to continue.
                         */
                        function filterMenuChanged() {
                            var table = document.getElementById("form1:table1");
                            return table.filterMenuChanged();
                        }
                        /*
                         * Toggle the custom filter panel (if any) open or closed.
                         */
                        function toggleFilterPanel() {
                            var table = document.getElementById("form1:table1");
                            return table.toggleTableFilterPanel();
                        }
                        /* ----- Functions for Table Actions ----- */
                        /*
                         * Initialize all rows of the table when the state
                         * of selected rows changes.
                         */
                        function initAllRows() {
                            var table = document.getElementById("form1:table1");
                            table.initAllRows();
                        }
                        /*
                         * Set the selected state for the given row groups
                         * displayed in the table.  This functionality requires
                         * the 'selectId' of the tableColumn to be set.
                         *
                         * @param rowGroupId HTML element id of the tableRowGroup component
                         * @param selected Flag indicating whether components should be selected
                         */
                        function selectGroupRows(rowGroupId, selected) {
                            var table = document.getElementById("form1:table1");
                            table.selectGroupRows(rowGroupId, selected);
                        }
                        /*
                         * Disable all table actions if no rows have been selected.
                         */
                        function disableActions() {
                            // Determine whether any rows are currently selected
                            var table = document.getElementById("form1:table1");
                            var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                            // Set disabled state for top actions
                            document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
                            
                            // Set disabled state for bottom actions
                            document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
                        }]]></script>
											<ui:tableRowGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableRowGroup2}" id="tableRowGroup2"
												sourceData="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ldpModificador}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.radioButton2}" id="radioButton2" label="" name="buttonGroup2"
														selected="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.RBSelected}"
														selectedValue="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn2}" headerText="Nombre" id="tableColumn2"
													sort="nombre">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText1}" id="staticText1"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<!--<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn3}" headerText="Valor" id="tableColumn3"
													sort="valor">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText2}" id="staticText2"
														text="#{currentRow.value['valor']} #{currentRow.value['simbolo']}" />
												</ui:tableColumn>-->
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn14}" headerText="Desde" id="tableColumn14"
													sort="desdeMeses">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText10}" id="staticText10"
														text="#{currentRow.value['desdeMeses']} meses" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn15}" headerText="Desde" id="tableColumn15"
													sort="desdeDias">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText13}" id="staticText13"
														text="#{currentRow.value['desdeDias']} días" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn16}" headerText="Hasta" id="tableColumn16"
													sort="hastaMeses">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText14}" id="staticText14"
														text="#{currentRow.value['hastaMeses']} meses" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn17}" headerText="Hasta" id="tableColumn17"
													sort="hastaDias">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText15}" id="staticText15"
														text="#{currentRow.value['hastaDias']} días" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Se pierde vencida" id="tcQuitarVencida" sort="stringQuitarReliquidarVencida">
													<ui:staticText id="stQuitarReliqVencida" text="#{currentRow.value['stringQuitarReliquidarVencida']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn7}" headerText="Se aplica sobre" id="tableColumn7"
													sort="aplicableSobre">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText7}" id="staticText7"
														text="#{currentRow.value['aplicableSobre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn8}" headerText="Condición" id="tableColumn8"
													sort="condicion">
													<ui:textArea binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.textArea1}" columns="40" disabled="true" id="textArea1"
														styleClass="textFieldDisabled" text="#{currentRow.value['condicion']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarModificador_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarModificador}" id="btnAgregarModificador" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnModificarModificador_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnModificarModificador}" id="btnModificarModificador" styleClass="button"
														text="Modificar" />
													<a4j:commandButton action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarModificador_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarModificador}" id="btnQuitarModificador"
														onmouseup="return (confirm(&quot;¿Está seguro que desea Quitar el Modificador?&quot;));" styleClass="btnAjax" reRender="table1" value="Quitar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label11}" id="label11" styleClass="label2" text="Vencimientos" />
									</td>
								</tr>
								<tr>
									<td colspan="4" nowrap="nowrap">
										<ui:table augmentTitle="false" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.table2}" id="table2">
											<script>
												<![CDATA[
                /* ----- Functions for Table Preferences Panel ----- */
                /*
                 * Toggle the table preferences panel open or closed
                 */
                function togglePreferencesPanel() {
                    var table = document.getElementById("form1:table1");
                    table.toggleTblePreferencesPanel();
                }
                /* ----- Functions for Filter Panel ----- */
                /*
                 * Return true if the filter menu has actually changed,
                 * so the corresponding event should be allowed to continue.
                 */
                function filterMenuChanged() {
                    var table = document.getElementById("form1:table1");
                    return table.filterMenuChanged();
                }
                /*
                 * Toggle the custom filter panel (if any) open or closed.
                 */
                function toggleFilterPanel() {
                    var table = document.getElementById("form1:table1");
                    return table.toggleTableFilterPanel();
                }
                /* ----- Functions for Table Actions ----- */
                /*
                 * Initialize all rows of the table when the state
                 * of selected rows changes.
                 */
                function initAllRows() {
                    var table = document.getElementById("form1:table1");
                    table.initAllRows();
                }
                /*
                 * Set the selected state for the given row groups
                 * displayed in the table.  This functionality requires
                 * the 'selectId' of the tableColumn to be set.
                 *
                 * @param rowGroupId HTML element id of the tableRowGroup component
                 * @param selected Flag indicating whether components should be selected
                 */
                function selectGroupRows(rowGroupId, selected) {
                    var table = document.getElementById("form1:table1");
                    table.selectGroupRows(rowGroupId, selected);
                }
                /*
                 * Disable all table actions if no rows have been selected.
                 */
                function disableActions() {
                    // Determine whether any rows are currently selected
                    var table = document.getElementById("form1:table1");
                    var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                    // Set disabled state for top actions
                    document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
                    // Set disabled state for bottom actions
                    document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
                }]]></script>
											<ui:tableRowGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableRowGroup3}" id="tableRowGroup3"
												sourceData="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ldpVencimiento}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn4}" id="tableColumn4"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.radioButton3}" id="radioButton3" label="" name="buttonGroup3"
														selected="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.RBSelected3}"
														selectedValue="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn5}" headerText="Nombre" id="tableColumn5"
													sort="nombre">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText3}" id="staticText3"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn10}" headerText="Meses" id="tableColumn10"
													sort="meses">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText5}" id="staticText5"
														text="#{currentRow.value['meses']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn11}" headerText="Días" id="tableColumn11"
													sort="dias">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText8}" id="staticText8"
														text="#{currentRow.value['dias']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn9}" headerText="Fórmula de Cálculo"
													id="tableColumn9" sort="formulaCalculo">
													<ui:textArea binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.textArea2}" columns="40" disabled="true" id="textArea2"
														styleClass="textFieldDisabled" text="#{currentRow.value['formulaCalculo']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.groupPanel2}" id="groupPanel2">
													<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarVencimiento_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnAgregarVencimiento}" id="btnAgregarVencimiento" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnModificarVencimiento_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnModificarVencimiento}" id="btnModificarVencimiento" styleClass="button"
														text="Modificar" />
													<a4j:commandButton action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarVencimiento_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnQuitarVencimiento}" id="btnQuitarVencimiento"
														onmouseup="return (confirm(&quot;¿Está seguro que desea Quitar el Vencimiento?&quot;));" styleClass="btnAjax" value="Quitar" reRender="table2"/>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td  nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.lblCondicionNoLiquidacion}" id="lblCondicionNoLiquidacion" styleClass="label2" text="Condicion no Liquidación" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:textArea binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.taCondicionNoLiquidacion}" rows="4" id="taCondicionNoLiquidacion" style="font-size:11px; width:100%"  styleClass="textField"/>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label15}" id="label15" styleClass="label2" text="Validación y Prueba" />
									</td>
								</tr>
								<tr>
									<td colspan="4" nowrap="nowrap">
										<ui:table augmentTitle="false" binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.table3}" id="table3" width="240">
											<script>
												<![CDATA[
        /* ----- Functions for Table Preferences Panel ----- */
        /*
         * Toggle the table preferences panel open or closed
         */
        function togglePreferencesPanel() {
            var table = document.getElementById("form1:table1");
            table.toggleTblePreferencesPanel();
        }
        /* ----- Functions for Filter Panel ----- */
        /*
         * Return true if the filter menu has actually changed,
         * so the corresponding event should be allowed to continue.
         */
        function filterMenuChanged() {
            var table = document.getElementById("form1:table1");
            return table.filterMenuChanged();
        }
        /*
         * Toggle the custom filter panel (if any) open or closed.
         */
        function toggleFilterPanel() {
            var table = document.getElementById("form1:table1");
            return table.toggleTableFilterPanel();
        }
        /* ----- Functions for Table Actions ----- */
        /*
         * Initialize all rows of the table when the state
         * of selected rows changes.
         */
        function initAllRows() {
            var table = document.getElementById("form1:table1");
            table.initAllRows();
        }
        /*
         * Set the selected state for the given row groups
         * displayed in the table.  This functionality requires
         * the 'selectId' of the tableColumn to be set.
         *
         * @param rowGroupId HTML element id of the tableRowGroup component
         * @param selected Flag indicating whether components should be selected
         */
        function selectGroupRows(rowGroupId, selected) {
            var table = document.getElementById("form1:table1");
            table.selectGroupRows(rowGroupId, selected);
        }
        /*
         * Disable all table actions if no rows have been selected.
         */
        function disableActions() {
            // Determine whether any rows are currently selected
            var table = document.getElementById("form1:table1");
            var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
            // Set disabled state for top actions
            document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
            // Set disabled state for bottom actions
            document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
        }]]></script>
											<ui:tableRowGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableRowGroup7}" id="tableRowGroup7"
												sourceData="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.ldpParametroValor}" sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn12}" headerText="Parámetro" id="tableColumn12"
													sort="parametro">
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.stParametroPrueba}" id="stParametroPrueba"
														text="#{currentRow.value['parametro']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tableColumn13}" headerText="Valor" id="tableColumn13"
													sort="valor">
													<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfValorPrueba}" columns="15" id="tfValorPrueba"
														styleClass="textField" text="#{currentRow.value['valor']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.groupPanel3}" id="groupPanel3">
													<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label16}" for="tfFechaLiquidacion" id="label16" styleClass="label"
														text="Fecha de Liquidación" />
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText12}" escape="false" id="staticText12" styleClass=""
														text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
													<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfFechaLiquidacion}" columns="15" id="tfFechaLiquidacion"
														styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText9}" escape="false" id="staticText9" styleClass=""
														text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label18}" for="tfFechaCobro" id="label18" styleClass="label"
														text="Fecha de Cobro" />
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText4}" escape="false" id="staticText4" styleClass=""
														text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
													<ui:textField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tfFechaCobro}" columns="15" id="tfFechaCobro"
														styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
													<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.staticText11}" escape="false" id="staticText11" styleClass=""
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnProbarFormula_action}"
														binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnProbarFormula}" id="btnProbarFormula" styleClass="button"
														text="Probar Fórmula" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
								<td>
								<br></br>
								</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:panelGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.gdpResultado}" id="gdpResultado"
											rendered="false" >
											<table style=" border: 1px; border-style: dotted;" >
											<tr>
											<td>
											<ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.label17}" id="label17" styleClass="label2"
												text="Resultado de la Prueba" />
											<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.stResultado}" escape="false" id="stResultado"
												style="font-size:11px;" styleClass="staticText" />
											</td>
											</tr>
											</table>
										</ui:panelGroup>
									</td>
								</tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnGuardar_action}"
											binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnCancelar_action}"
											binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.idSubSesion}" />
					<ui:script binding="#{habilitaciones$ABMTipoTasa$ABMTipoTasa.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
