<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored ="false" deferredSyntaxAllowedAsLiteral="false"/>
    <f:view>
        <ui:page binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.page1}" id="page1">
            <ui:html binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.html1}" id="html1">
                <ui:head binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.head1}" id="head1" title="Fórmula de Cálculo Alicuota">
                    <ui:link binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script><![CDATA[

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

                       // function cambiarLabelCuotas() {
                       //     cbFija = document.getElementById("form1:cbFija");
                       //     labelCantCuotas = document.getElementById("form1:labelCantidadCuotas");
                            //alert(etiqueta.innerHTML);
                      //      if (cbFija.checked) {
                      //          labelCantCuotas.innerHTML = "Cantidad de Cuotas";
                      //      }
                      //      else {
                      //          labelCantCuotas.innerHTML = "Cantidad de Cuotas por Período";
                     //       }
                     //   }

                  //      function cambiarCantidadCuotas() {
                  //          ddPeriodicidad = document.getElementById("form1:ddPeriodicidad");
                  //          ddPeriodicidadCuotas = document.getElementById("form1:ddPeriodicidadCuotas");
                  //          tfCantidadCuotas = document.getElementById("form1:tfCantidadCuotas");

                  //          if (ddPeriodicidad.value == ddPeriodicidadCuotas.value) {
                   //             tfCantidadCuotas.value = "1";
                   //         }
                  //      }

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
                    <ui:body binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.body1}" focus="form1:lbVariables" id="body1"
                             onLoad="parent.footer.location.reload();Init();validarDropDown()" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')"> <!-- el enter se caco adrede-->
                        <ui:form binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.stTitulo}" id="stTitulo" styleClass="tituloABM" text="Fórmula de Cálculo Alicuota"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                    <td colspan="4" nowrap="nowrap">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label1}" id="label1" styleClass="label2" text="Composición de la Fórmula"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" nowrap="nowrap">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label2}" id="label2" styleClass="label3" text="Parámetros"/>
                                    </td>
                                    <td nowrap="nowrap">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.lblVariablesFormula}" id="lblVariablesFormula" styleClass="label3" text="Variables Simples"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" nowrap="nowrap">
                                        <ui:dropDown binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddTiposParametro}" id="ddTiposParametro"
                                                     items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddTiposParametroDefaultOptions.options}" styleClass="textField"/>
                                    </td>
                                    <td>
                                            <ui:panelGroup binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.groupPanel6}" id="groupPanel6">
                                                <ui:button action="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnAgregarVariable_action}"
                                                           binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnAgregarVariable}"
                                                           id="btnAgregarVariable" styleClass="button" text="Agregar"/>
                                                <ui:button action="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnQuitarVariable_action}"
                                                           binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnQuitarVariable}"
                                                           id="btnQuitarVariable"
                                                           onClick="return (confirm(&quot;¿Está seguro que desea Quitar la Variable?&quot;));"
                                                           styleClass="button" text="Quitar"/>
                                            </ui:panelGroup>
                                        </td>
                                </tr>
                                <tr>
                                    <td nowrap="nowrap">
                                        <ui:button action="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnNuevoParametro_action}"
                                                   binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnNuevoParametro}" id="btnNuevoParametro" mini="true"
                                                   styleClass="button" text="Nuevo"/>
                                    </td>
                                    <td colspan="2" nowrap="nowrap">
                                        <ui:button action="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnQuitarParametro_action}"
                                                   binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnQuitarParametro}" escape="false"
                                                   id="btnQuitarParametro" mini="true" styleClass="button" text="Quitar"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <ui:listbox binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.lbVariables}" id="lbVariables"
                                                    items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.lbVariablesDefaultOptions.options}"
                                                    onDblClick="agregarVariableAFormula(this.id);" rows="7" style="width:100%" styleClass="textField" toolTip="Haga doble clic para insertar un parámetro a la fórmula."/>
                                    </td>
                                    <td nowrap="nowrap" colspan="2">
                                        <div style="overflow: auto; width: 790px; height: 200px">
                                        <ui:table augmentTitle="false" binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.table4}" id="table4">
                                            <script><![CDATA[
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
                                                <ui:tableRowGroup binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tableRowGroup1}" id="tableRowGroup1"
                                                                  sourceData="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ldpVariablesSimples}" sourceVar="currentRow4">
                                                    <ui:tableColumn align="center" binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tableColumn6}"
                                                                    id="tableColumn6" valign="middle" width="10">
                                                        <ui:radioButton binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.radioButton4}" id="radioButton4"
                                                                        label="" name="buttonGroup7" selected="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.RBSelected4}" selectedValue="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.currentRow4}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tcNombre}" headerText="Nombre"
                                                                    id="tcNombre" sort="nombre">
                                                        <ui:textField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tfNombreVariable}" id="tfNombreVariable" text="#{currentRow4.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tcExpresion}" headerText="Expresion"
                                                                    id="tcExpresion" sort="expresion">
                                                        <ui:textArea binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.taExpresion}" id="taExpresion" text="#{currentRow4.value['expresion']}" columns="60" rows="3"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                    </ui:table>
                                            </div>
                            </td>
                                </tr>
                                <tr>
                                    <td colspan="3" nowrap="nowrap"></td>
                                    <td nowrap="nowrap">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.lblVariablesCompuestas}" id="lblVariablesCompuestas" styleClass="label3" text="Variables Compuestas"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" nowrap="nowrap"></td>
                                    <td colspan="1" nowrap="nowrap"></td>
                                    <td nowrap="nowrap">
                                        <ui:panelGroup binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.groupPanel7}" id="groupPanel7">
                                            <ui:button action="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnAgregarVariableCompuesta_action}"
                                                       binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnAgregarVariableCompuesta}"
                                                       id="btnAgregarVariableCompuesta" styleClass="button" text="Agregar"/>
                                            <ui:button action="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnQuitarVariableCompuesta_action}"
                                                       binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnQuitarVariableCompuesta}"
                                                       id="btnQuitarVariableCompuesta"
                                                       onClick="return (confirm(&quot;¿Está seguro que desea Quitar la Variable?&quot;));"
                                                       styleClass="button" text="Quitar"/>
                                        </ui:panelGroup>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3"></td>
                                    <td nowrap="nowrap" colspan="2">
                                        <div style="overflow: auto; width: 790px; height: 200px">
                                        <ui:table augmentTitle="false" binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.table5}" id="table5">
                                            <script><![CDATA[
                /* ----- Functions for Table Preferences Panel ----- */
                /*
                 * Toggle the table preferences panel open or closed
                 */
                function togglePreferencesPanel() {
                    var table = document.getElementById("form1:table5");
                    table.toggleTblePreferencesPanel();
                }
                /* ----- Functions for Filter Panel ----- */
                /*
                 * Return true if the filter menu has actually changed,
                 * so the corresponding event should be allowed to continue.
                 */
                function filterMenuChanged() {
                    var table = document.getElementById("form1:table5");
                    return table.filterMenuChanged();
                }
                /*
                 * Toggle the custom filter panel (if any) open or closed.
                 */
                function toggleFilterPanel() {
                    var table = document.getElementById("form1:table5");
                    return table.toggleTableFilterPanel();
                }
                /* ----- Functions for Table Actions ----- */
                /*
                 * Initialize all rows of the table when the state
                 * of selected rows changes.
                 */
                function initAllRows() {
                    var table = document.getElementById("form1:table5");
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
                    var table = document.getElementById("form1:table5");
                    table.selectGroupRows(rowGroupId, selected);
                }
                /*
                 * Disable all table actions if no rows have been selected.
                 */
                function disableActions() {
                    // Determine whether any rows are currently selected
                    var table = document.getElementById("form1:table5");
                    var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                    // Set disabled state for top actions
                    document.getElementById("form1:table5:tableActionsTop:deleteTop").setDisabled(disabled);
                    // Set disabled state for bottom actions
                    document.getElementById("form1:table5:tableActionsBottom:deleteBottom").setDisabled(disabled);
                }]]></script>
                                                <ui:tableRowGroup binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tableRowGroup2}" id="tableRowGroup2"
                                                                  sourceData="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ldpVariablesCompuestas}" sourceVar="currentRow5">
                                                    <ui:tableColumn align="center" binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tableColumn7}"
                                                                    id="tableColumn7" valign="middle" width="10">
                                                        <ui:radioButton binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.radioButton5}" id="radioButton5"
                                                                        label="" name="buttonGroup8" selected="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.RBSelected5}" selectedValue="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.currentRow5}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tcNombre2}" headerText="Nombre"
                                                                    id="tcNombre2" sort="nombre">
                                                        <ui:textField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tfNombreVariable2}" id="tfNombreVariable2" text="#{currentRow5.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tcTipo}" headerText="Tipo"
                                                                    id="tcTipo" sort="tipo">
                                                        <ui:dropDown binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddTipo}" id="ddTipo" items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddTipoDefaultOptions.options}" 
                                                                         styleClass="textField" selected="#{currentRow5.value['tipo']}" converter="EnumConverter"
                                                                         immediate="false"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tcExpresion2}" headerText="Expresion"
                                                                    id="tcExpresion" sort="expresion">
                                                        <ui:textArea binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.taExpresion2}" id="taExpresion2" text="#{currentRow5.value['expresion']}" columns="60" rows="3"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                    </ui:table>
                                            </div>
                            </td>
                                </tr>
                                <tr>
                                    <td colspan="2" nowrap="nowrap">
                                        <input class="Btn2Mni" id="btnAddVar:taFormula" onClick="agregarVariableAFormula('form1:lbVariables');"
                                               onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                               onmouseover="return this.myonmouseover();" title="Agregar Parámetro a la Fórmula" type="button" value=" Agregar a Fórmula"/>
                                        <script type="text/javascript">sjwuic_assign_button('btnAddVar:taFormula', defaultButtonStrings, true, true, false);</script>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" nowrap="nowrap">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label3}" id="label3" styleClass="label3" text="Armado de Condiciones"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" nowrap="nowrap">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label4}" for="ddVariable1" id="label4"
                                                  styleClass="label" text="Si"/>
                                    </td>
                                    <td align="left" colspan="3" nowrap="nowrap">
                                        <ui:dropDown binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddVariable1}" id="ddVariable1"
                                                     items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.lbVariablesDefaultOptions.options}" styleClass="textField"/>
                                        <ui:dropDown binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddOperador1}" id="ddOperador1"
                                                     items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddOperador1DefaultOptions.options}"
                                                     onChange="cambiarEstadoTextField(this.id);" styleClass="textField"/>
                                        <input class="Btn2Mni" id="btnAddVar:tfValor1" onClick="agregarATextField(this.id);"
                                               onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                               onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                        <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor1', defaultButtonStrings, true, true, false);</script>
                                        <ui:textField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tfValor1}" columns="25" id="tfValor1" styleClass="textField"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">
                                        <ui:dropDown binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddUnion1}" id="ddUnion1"
                                                     items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddUnion1DefaultOptions.options}" styleClass="textField" onChange="onchangeDropDownY()"/>
                                    </td>
                                    <td align="left" colspan="3" nowrap="nowrap">
                                        <ui:dropDown binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddVariable2}" id="ddVariable2"
                                                     items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.lbVariablesDefaultOptions.options}" styleClass="textField"/>
                                        <ui:dropDown binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddOperador2}" id="ddOperador2"
                                                     items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddOperador1DefaultOptions.options}"
                                                     onChange="cambiarEstadoTextField(this.id);" styleClass="textField"/>
                                        <input class="Btn2Mni" id="btnAddVar:tfValor2" onClick="agregarATextField(this.id);"
                                               onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                               onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                        <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor2', defaultButtonStrings, true, true, false);</script>
                                        <ui:textField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tfValor2}" columns="25" id="tfValor2" styleClass="textField"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">
                                        <ui:dropDown binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddUnion2}" id="ddUnion2"
                                                     items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddUnion1DefaultOptions.options}" styleClass="textField" onChange="onchangeDropDownO()"/>
                                    </td>
                                    <td align="left" colspan="3" nowrap="nowrap">
                                        <ui:dropDown binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddVariable3}" id="ddVariable3"
                                                     items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.lbVariablesDefaultOptions.options}" styleClass="textField"/>
                                        <ui:dropDown binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddOperador3}" id="ddOperador3"
                                                     items="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ddOperador1DefaultOptions.options}"
                                                     onChange="cambiarEstadoTextField(this.id);" styleClass="textField"/>
                                        <input class="Btn2Mni" id="btnAddVar:tfValor3" onClick="agregarATextField(this.id);"
                                               onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                               onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                        <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor3', defaultButtonStrings, true, true, false);</script>
                                        <ui:textField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tfValor3}" columns="25" id="tfValor3" styleClass="textField"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label5}" for="tfValorFinal" id="label5"
                                                  styleClass="label" text="Entonces"/>
                                    </td>
                                    <td align="left" colspan="3" nowrap="nowrap">
                                        <input class="Btn2Mni" id="btnAddVar:tfValorFinal" onClick="agregarATextField(this.id);"
                                               onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                               onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                        <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValorFinal', defaultButtonStrings, true, true, false);</script>
                                        <ui:textField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tfValorFinal}" columns="60" id="tfValorFinal" styleClass="textField"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" nowrap="nowrap">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label6}" for="tfValorFinalSino" id="label6"
                                                  styleClass="label" text="Sino"/>
                                    </td>
                                    <td align="left" colspan="3" nowrap="nowrap">
                                        <input class="Btn2Mni" id="btnAddVar:tfValorFinalSino" onClick="agregarATextField(this.id);"
                                               onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                               onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                        <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValorFinalSino', defaultButtonStrings, true, true, false);</script>
                                        <ui:textField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tfValorFinalSino}" columns="60"
                                                      id="tfValorFinalSino" styleClass="textField"/>
                                        <input class="Btn2Mni button" id="btnAddCond:taFormula" onClick="agregarCondicionAFormula();"
                                               onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                               onmouseover="return this.myonmouseover();" title="Agregar Condición a la Fórmula" type="button" value=" Agregar a Fórmula"/>
                                        <script type="text/javascript">sjwuic_assign_button('btnAddCond:taFormula', defaultButtonStrings, true, true, false);</script>
                                        <input class="Btn2Mni buttonLimpiar" id="btnLimpiarCondicion" onClick="limpiarCondiciones();"
                                               onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                               onmouseover="return this.myonmouseover();" type="button" value="&amp;nbsp;"/>
                                        <script type="text/javascript">sjwuic_assign_button('btnLimpiarCond', defaultButtonStrings, true, true, false);</script>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label7}" for="taFormula" id="label7"
                                                  styleClass="label" text="Fórmula:"/>
                                        <br/>
                                        <ui:textArea binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.taFormula}" id="taFormula" rows="4"
                                                     style="font-size:11px; width:100%" styleClass="textField"/>
                                        <br/>Operadores: + [suma] - [resta] * [multiplicación] / [división]
                                        <br/>Funciones: redondear(x) - redondear(x,p)     [x: expresión, p: cantidad de digitos decimales]
                                    </td>
                                </tr>
                                <tr>
                                    <td nowrap="nowrap">
                                        <br/>
                                        <ui:label styleClass="label2" for="cbIteraSobreAlicuotas" id="lblIteraSobreAlicuotas" text="Iterar sobre Alícuotas"/>
                                        <ui:checkbox binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.cbIteraSobreAlicuotas}"
                                                     id="cbIteraSobreAlicuotas"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">                                        
                                        <br/>
                                    </td>
                                </tr>
                                 <tr>
                                    <td colspan="4">
                                        <hr/>
                                        <br/>
                                    </td>
                                    </tr>
                                    <tr>
                                    <td colspan="4" nowrap="nowrap">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label8}" id="label8" styleClass="label2" text="Validación y Prueba"/>
                                    </td>
                                    </tr>
                                    <tr>
                                    <td colspan="4" nowrap="nowrap">
                                        <ui:table augmentTitle="false" binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.table1}" id="table1" width="240">
                                            <script><![CDATA[
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
                                                <ui:tableRowGroup binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tableRowGroup4}" id="tableRowGroup4"
                                                                  sourceData="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.ldpParametroValor}" sourceVar="currentRow">
                                                    <ui:tableColumn binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tableColumn1}" headerText="Parámetro"
                                                                    id="tableColumn1" sort="parametro">
                                                        <ui:staticText binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.stParametroPrueba}"
                                                                       id="stParametroPrueba" text="#{currentRow.value['parametro']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tableColumn2}" headerText="Valor"
                                                                    id="tableColumn2" sort="valor">
                                                        <ui:textField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tfValorPrueba}" columns="15"
                                                                      id="tfValorPrueba" styleClass="textField" text="#{currentRow.value['valor']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                        <f:facet name="actionsTop">
                                            <ui:panelGroup binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.groupPanel1}" id="groupPanel1">
                                                <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label9}" for="tfFechaLiquidacion"
                                                          id="label9" styleClass="label" text="Fecha de Liquidación"/>
                                                <ui:staticText binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.staticText1}" escape="false"
                                                               id="staticText1" styleClass="" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;"/>
                                                <ui:textField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tfFechaLiquidacion}" columns="15"
                                                              id="tfFechaLiquidacion" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                                <ui:staticText binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.staticText2}" escape="false"
                                                               id="staticText2" styleClass="" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;"/>
                                                <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label10}" for="tfFechaCobro"
                                                          id="label10" styleClass="label" text="Fecha de Cobro"/>
                                                <ui:staticText binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.staticText3}" escape="false"
                                                               id="staticText3" styleClass="" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;"/>
                                                <ui:textField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.tfFechaCobro}" columns="15"
                                                              id="tfFechaCobro" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                                <ui:staticText binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.staticText4}" escape="false"
                                                               id="staticText4" styleClass="" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnProbarFormula_action}"
                                                           binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnProbarFormula}" id="btnProbarFormula"
                                                           styleClass="button" text="Probar Fórmula"/>
                                            </ui:panelGroup>
                                        </f:facet>
                                    </ui:table>
                                    </td>
                                    </tr>
                                    <tr>
                                    <td colspan="4">
                                    <h:panelGrid bgcolor="#DCDCDC" binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.gdpResultado}" columns="1"
                                                 id="gdpResultado" rendered="false"
                                                 style="border: 1px dotted rgb(102, 102, 102); font-size: 12px; margin-top: 10px;" title="Resultado de la Prueba">
                                        <ui:label binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.label11}" id="label11" styleClass="label2" text="Resultado de la Prueba"/>
                                        <ui:staticText binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.stResultado}" escape="false"
                                                       id="stResultado" style="font-size:11px;" styleClass="staticText"/>
                                    </h:panelGrid>
                                    </td>
                                    </tr>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnGuardar_action}"
                                                       binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnCancelar_action}"
                                                       binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$ABMTipoTasaFormulaAlicuota$AgregarTipoTasaFormulaAlicuota.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
