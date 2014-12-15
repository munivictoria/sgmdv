<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui"
xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.page1}" id="page1">
            <ui:html binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.html1}" id="html1">
                <ui:head binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script><![CDATA[
                    
                    var strTrue  = "== 1";
                    var strFalse = "== 0";
                    
                    function agregarVariableAFormula(ddId) {
                        separador = " ";
                        valor = dropDown_getSelectedValue(ddId);
                        if (valor != null) {
                            campo = "form1:taCondicion";
                            txtFormula = document.getElementById(campo);
                            txtFormula.value = txtFormula.value + separador + valor;
                        }
                    }
                    
                    function agregarATextField(btnId) {
                        separador = " ";
                        
                        valor = dropDown_getSelectedValue("form1:lbVariables");
                        if (valor != null) {
                            campo = "form1:" + btnId.substring( btnId.indexOf(":")+1 );
                            txt = document.getElementById(campo);
                            txt.value = txt.value + separador + valor;
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
                        
                        valor = null;
                        campo = "form1:taCondicion";
                        txtFormula = document.getElementById(campo);
                        
                        if (ope1 != "" && tfVal1 != "") {

                            if (ope1 == strTrue || ope1 == strFalse) valor = "(" + var1 + " " + ope1 + ")";
                            else valor = "(" + var1 + " " + ope1 + " (" + tfVal1 + "))";
                            
                            if (uni1 != "" && ope2 != "" && tfVal2 != "") {
                                
                                if (ope2 == strTrue || ope2 == strFalse) valor = valor + " " + uni1 + " " + "(" + var2 + " " + ope2 + ")";
                                else valor = valor + " " + uni1 + " " + "(" + var2 + " " + ope2 + " (" + tfVal2 + "))";
                                
                                if (uni2 != "" && ope3 != "" && tfVal3 != "") {
                                    if (ope2 == strTrue || ope2 == strFalse) valor = valor + " " + uni2 + " " + "(" + var3 + " " + ope3 + ")";
                                    else valor = valor + " " + uni2 + " " + "(" + var3 + " " + ope3 + " (" + tfVal3 + "))";
                                }
                                
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
                    }
                    
                    function trim(str) {
                        return str.replace(/^\s*|\s*$/g,"");
                    }
                    
                   //Eventos para jugar con el disabled de los combos.
                        
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
                    
                    ]]></script>
                </ui:head>
                <ui:body binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();validarDropDown()" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="3">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label4}" for="tfNombre" id="label4"
                                                styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="2">
                                            <ui:textField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tfNombre}" columns="40"
                                                id="tfNombre" onKeyUp="armarNombreVariable(this, 'form1:tfNombreVariable');" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label14}" id="label14"
                                                styleClass="label" text="Tipo"/>
                                        </td>
                                        <td colspan="2">
                                            <ui:dropDown binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddTipoModificador}"
                                                id="ddTipoModificador"
                                                items="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddTipoModificadorDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr><td></td></tr>
                                    <tr>
									<td>
									<td nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.lblVariablesFormula}" id="lblVariablesFormula" styleClass="label3"
											text="Variables de la Fórmula" />
									</td>
									<tr>
										<td>
											<ui:button action="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.btnAgregarVariable_action}"
												binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.btnAgregarVariable}" id="btnAgregarVariable" styleClass="button" text="Agregar" />
										<td>
											<a4j:commandButton action="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.btnQuitarVariable_action}"
												binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.btnQuitarVariable}" id="btnQuitarVariable"
												onmouseup="return (confirm(&quot;¿Está seguro que desea Quitar la Variable?&quot;));" styleClass="btnAjax" reRender="tablaVariables" value="Quitar" />
										</td>
										</td>
									<tr>
										<td colspan="4" nowrap="nowrap">
											<div style="overflow: auto; width: 790px; height: 200px">
												<ui:table augmentTitle="false" binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tablaVariables}" id="tablaVariables">
													<script>
														<![CDATA[
										                /* ----- Functions for Table Preferences Panel ----- */
										                /*
										                 * Toggle the table preferences panel open or closed
										                 */
										                function togglePreferencesPanel() {
										                    var table = document.getElementById("form1:tablaVariables");
										                    table.toggleTblePreferencesPanel();
										                }
										                /* ----- Functions for Filter Panel ----- */
										                /*
										                 * Return true if the filter menu has actually changed,
										                 * so the corresponding event should be allowed to continue.
										                 */
										                function filterMenuChanged() {
										                    var table = document.getElementById("form1:tablaVariables");
										                    return table.filterMenuChanged();
										                }
										                /*
										                 * Toggle the custom filter panel (if any) open or closed.
										                 */
										                function toggleFilterPanel() {
										                    var table = document.getElementById("form1:tablaVariables");
										                    return table.toggleTableFilterPanel();
										                }
										                /* ----- Functions for Table Actions ----- */
										                /*
										                 * Initialize all rows of the table when the state
										                 * of selected rows changes.
										                 */
										                function initAllRows() {
										                    var table = document.getElementById("form1:tablaVariables");
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
										                    var table = document.getElementById("form1:tablaVariables");
										                    table.selectGroupRows(rowGroupId, selected);
										                }
										                /*
										                 * Disable all table actions if no rows have been selected.
										                 */
										                function disableActions() {
										                    // Determine whether any rows are currently selected
										                    var table = document.getElementById("form1:tablaVariables");
										                    var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
										                    // Set disabled state for top actions
										                    document.getElementById("form1:tablaVariables:tableActionsTop:deleteTop").setDisabled(disabled);
										                    // Set disabled state for bottom actions
										                    document.getElementById("form1:tablaVariables:tableActionsBottom:deleteBottom").setDisabled(disabled);
										                }]]></script>
													<ui:tableRowGroup binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.trgVariables}" id="trgVariables"
														sourceData="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ldpVariablesFormula}" sourceVar="currentRow">
														<ui:tableColumn align="center" binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tcRadioButton}" id="tcRadioButton"
															valign="middle" width="10">
															<ui:radioButton binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.rbVariables}" id="rbVariables" label=""
																name="buttonGroup1" selected="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.RBSelected}"
															selectedValue="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.currentRow}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tcNombreVariable}" headerText="Nombre" id="tcNombreVariable" sort="nombre">
														<ui:textField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tfNombreVariable}" id="tfNombreVariable"
															text="#{currentRow.value['nombre']}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tcExpresion}" headerText="Expresion" id="tcExpresion"
														sort="expresion">
														<ui:textArea binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.taExpresion}" id="taExpresion"
															text="#{currentRow.value['expresion']}" columns="60" rows="3" />
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
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label3}" id="label3"
                                                styleClass="label" text="Tipo de Valor"/>
                                        </td>
                                        <td colspan="2">
                                            <ui:radioButton binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.rbFijo}" id="rbFijo" name="rbgTipoValor"/>
                                            <sup>
                                                <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label9}" for="rbFijo" id="label9"
                                                    styleClass="label" text="Fijo"/>
                                            </sup>
                                            <br/>
                                            <ui:radioButton binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.rbPorcentual}" id="rbPorcentual" name="rbgTipoValor"/>
                                            <sup>
                                                <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label10}" for="rbPorcentual"
                                                    id="label10" styleClass="label" text="Porcentual"/>
                                            </sup>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label7}" id="label7"
                                                styleClass="label" text="Se aplica sobre"/>
                                        </td>
                                        <td colspan="2">
                                            <ui:radioButton binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.rbTasa}" id="rbTasa" name="rbgSeAplicaSobre"/>
                                            <sup>
                                                <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label6}" for="rbTasa" id="label6"
                                                    styleClass="label" text="la Tasa"/>
                                            </sup>
                                            <br/>
                                            <ui:radioButton binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.rbSubtotal}" id="rbSubtotal" name="rbgSeAplicaSobre"/>
                                            <sup>
                                                <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label8}" for="rbSubtotal"
                                                    id="label8" styleClass="label" text="el SubTotal (Tasa + otros Modificadores aplicados sobre la Tasa)"/>
                                            </sup>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label11}" id="label11"
                                                styleClass="label2" text="Condición para su aplicación"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label1}" for="tfMesesDesde"
                                                id="label1" styleClass="label" text="Se aplica hasta los"/>
                                        </td>
                                        <td colspan="2">
                                            <ui:textField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tfMesesDesde}" columns="10"
                                                id="tfMesesDesde" onBlur="armarNombreVariable(this, this.id);" styleClass="textField"/>
                                            <ui:staticText binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.stValor1}" escape="false"
                                                id="stValor1" styleClass="label" text=" meses y "/>
                                            <ui:textField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tfDiasDesde}" columns="10"
                                                id="tfDiasDesde" styleClass="textField"/>
                                            <ui:staticText binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.staticText1}" id="staticText1"
                                                styleClass="label" text=" días desde el primer día del período"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label5}" for="tfMesesHasta"
                                                id="label5" styleClass="label" text="hasta los"/>
                                        </td>
                                        <td colspan="2">
                                            <ui:textField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tfMesesHasta}" columns="10"
                                                id="tfMesesHasta" onBlur="armarNombreVariable(this, this.id);" styleClass="textField"/>
                                            <ui:staticText binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.stValor2}" escape="false"
                                                id="stValor2" styleClass="label" text=" meses y "/>
                                            <ui:textField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tfDiasHasta}" columns="10"
                                                id="tfDiasHasta" styleClass="textField"/>
                                            <ui:staticText binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.staticText2}" id="staticText2"
                                                styleClass="label" text=" días."/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td>
                                        </td>
                                        <td colspan="2">
                                            <ui:checkbox binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.cbQuitarReliquidarVencida}" id="cbQuitarReliquidarVencida"/>
                                            <sup>
                                                <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label15}" for="cbQuitarReliquidarVencida"
                                                    id="label15" styleClass="label" text="Quitar al reliquidar vencida"/>
                                            </sup>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td colspan="3">
                                            <ui:staticText binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.staticText3}" id="staticText3"
                                                styleClass="label" text="Se aplicará si cumple la siguiente condición:"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3">
                                            <ui:listbox binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.lbVariables}" id="lbVariables"
                                                items="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.lbVariablesDefaultOptions.options}"
                                                onDblClick="agregarVariableAFormula(this.id);" rows="5" style="width: 100%" styleClass="textField"/>
                                        </td>
                                        <td align="right">
                                            <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label12}" for="ddVariable1"
                                                id="label12" styleClass="label" text="Si"/>
                                        </td>
                                        <td align="left" nowrap="nowrap">
                                            <ui:dropDown binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddVariable1}" id="ddVariable1"
                                                items="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.lbVariablesDefaultOptions.options}" styleClass="textField"/>
                                            <ui:dropDown binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddOperador1}" id="ddOperador1"
                                                items="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddOperador1DefaultOptions.options}"
                                                onChange="cambiarEstadoTextField(this.id);" styleClass="textField"/>
                                            <input class="Btn2Mni" id="btnAddVar:tfValor1" onClick="agregarATextField(this.id);"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor1', defaultButtonStrings, true, true, false);</script>
                                            <ui:textField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tfValor1}" columns="30"
                                                id="tfValor1" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <ui:dropDown binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddUnion1}" id="ddUnion1"
                                                         items="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddUnion1DefaultOptions.options}" styleClass="textField" onChange="onchangeDropDownY()"/>
                                        </td>
                                        <td align="left" nowrap="nowrap">
                                            <ui:dropDown binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddVariable2}" id="ddVariable2"
                                                items="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.lbVariablesDefaultOptions.options}" styleClass="textField"/>
                                            <ui:dropDown binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddOperador2}" id="ddOperador2"
                                                items="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddOperador1DefaultOptions.options}"
                                                onChange="cambiarEstadoTextField(this.id);" styleClass="textField"/>
                                            <input class="Btn2Mni" id="btnAddVar:tfValor2" onClick="agregarATextField(this.id);"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor2', defaultButtonStrings, true, true, false);</script>
                                            <ui:textField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tfValor2}" columns="30"
                                                id="tfValor2" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <ui:dropDown binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddUnion2}" id="ddUnion2"
                                                         items="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddUnion1DefaultOptions.options}" styleClass="textField" onChange="onchangeDropDowno()"/>
                                        </td>
                                        <td align="left" nowrap="nowrap">
                                            <ui:dropDown binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddVariable3}" id="ddVariable3"
                                                items="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.lbVariablesDefaultOptions.options}" styleClass="textField"/>
                                            <ui:dropDown binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddOperador3}" id="ddOperador3"
                                                items="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.ddOperador1DefaultOptions.options}"
                                                onChange="cambiarEstadoTextField(this.id);" styleClass="textField"/>
                                            <input class="Btn2Mni" id="btnAddVar:tfValor3" onClick="agregarATextField(this.id);"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor3', defaultButtonStrings, true, true, false);</script>
                                            <ui:textField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tfValor3}" columns="30"
                                                id="tfValor3" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input class="Btn2Mni button" id="btnAddVar:taCondicion" onClick="agregarVariableAFormula('form1:lbVariables');"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" title="Agregar Parámetro a la Fórmula" type="button" value="Agregar Parámetro"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddVar:taCondicion', defaultButtonStrings, true, true, false);</script>
                                        </td>
                                        <td></td>
                                        <td>
                                            <input class="Btn2Mni button" id="btnAddCond:taCondicion" onClick="agregarCondicionAFormula();"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" title="Agregar Condición" type="button" value="Agregar Condición"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddCond:taCondicion', defaultButtonStrings, true, true, false);</script>
                                            <input class="Btn2Mni buttonLimpiar" id="btnLimpiarCondicion" onClick="limpiarCondiciones();"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" type="button" value="&amp;nbsp;"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnLimpiarCond', defaultButtonStrings, true, true, false);</script>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.label13}" for="taCondicion"
                                                id="label13" styleClass="label" text="Condición:"/>
                                            <ui:textArea binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.taCondicion}" columns="80"
                                                id="taCondicion" rows="4" style="width: 100%;font-size: 11px;" styleClass="textField"/>
                                            <br/>
                                            Si el resultado de la condición es cero, no se aplicará el modificador. Si el resultado es positivo, se aplicará como recargo, si es negativo, se aplicará como descuento.
                                        </td>
                                    </tr>
                                    <tr><td><br/></td></tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.lblNombreColumnaReportes}" for="tfNombreColumnaReportes" id="lblNombreColumnaReportes"
                                                styleClass="label" text="Nombre de la columna en reportes"/>
                                        </td>
                                        <td colspan="2">
                                            <ui:textField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.tfNombreColumnaReportes}" columns="25"
                                                id="tfNombreColumnaReportes" styleClass="textField"/>
                                            <ui:staticText binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.stMensajeNombreColumnaReportes}" escape="false"
                                                id="stMensajeNombreColumnaReportes" styleClass="label" text="&amp;nbsp;[Dejar en blanco para que no se muestre discriminado]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <ui:messageGroup binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <div class="notifABM clrNota">
                                                <b>Nota: </b>Si no se especifican los meses+días <i>desde</i> los cuales se debe
                                            aplicar el Modificador, se tomará el primer día del período.
                                            Si no se especifican los meses+días <i>hasta</i> los cuales se debe aplicar el Modificador, se seguirá
                                            aplicando, si correspondiese, hasta que se cancele la obligación. Si no se especifica una condición
                                            se aplica siempre, dentro del rango de meses+días.</div>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="3" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.btnGuardar_action}"
                                                binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.btnGuardar}" id="btnGuardar"
                                                styleClass="button" text="Aceptar"/>
                                            <ui:staticText binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.btnCancelar_action}"
                                                binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.idSubSesion}"/>
                    <ui:script binding="#{habilitaciones$ABMTipoModificador$ABMTipoModificador.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
