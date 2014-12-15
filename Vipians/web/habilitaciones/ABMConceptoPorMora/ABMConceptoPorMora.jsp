<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.page1}" id="page1">
            <ui:html binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.html1}" id="html1">
                <ui:head binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.head1}" id="head1" title="Agregar Modificador">
                    <ui:link binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.link1}" id="link1" url="/resources/stylesheet.css"/>
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

                            // valor final
                            tfValFinal = trim(document.getElementById("form1:tfValorFinal").value);

                            // valor final sino
                            tfValFinalSino = trim(document.getElementById("form1:tfValorFinalSino").value);

                            valor = null;
                            campo = "form1:taCondicion";
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
                <ui:body binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();validarDropDown();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="Concepto Por Mora"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="3">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.label4}" for="tfNombre" id="label4"
                                                styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="2">
                                            <ui:textField binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.tfNombre}" columns="40"
                                                id="tfNombre" onKeyUp="armarNombreVariable(this, 'form1:tfNombreVariable');" styleClass="textField"/>
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
                                            <ui:label binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.label11}" id="label11"
                                                styleClass="label2" text="Condición para su aplicación"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <ui:staticText binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.staticText3}" id="staticText3"
                                                styleClass="label" text="Se aplicará si cumple la siguiente condición:"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3">
                                            <ui:listbox binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.lbVariables}" id="lbVariables"
                                                items="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.lbVariablesDefaultOptions.options}"
                                                onDblClick="agregarVariableAFormula(this.id);" rows="5" style="width: 175px" styleClass="textField"/>
                                        </td>
                                        <td align="right">
                                            <ui:label binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.label12}" for="ddVariable1"
                                                id="label12" styleClass="label" text="Si"/>
                                        </td>
                                        <td align="left" nowrap="nowrap">
                                            <ui:dropDown binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddVariable1}" id="ddVariable1"
                                                items="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.lbVariablesDefaultOptions.options}" styleClass="textField"/>
                                            <ui:dropDown binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddOperador1}" id="ddOperador1"
                                                items="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddOperador1DefaultOptions.options}"
                                                onChange="cambiarEstadoTextField(this.id);" styleClass="textField"/>
                                            <input class="Btn2Mni" id="btnAddVar:tfValor2" onClick="agregarATextField(this.id);"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor1', defaultButtonStrings, true, true, false);</script>
                                            <ui:textField binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.tfValor1}" columns="30"
                                                id="tfValor1" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <ui:dropDown binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddUnion1}" id="ddUnion1"
                                                         items="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddUnion1DefaultOptions.options}" styleClass="textField" onChange="onchangeDropDownY()"/>
                                        </td>
                                        <td align="left" nowrap="nowrap">
                                            <ui:dropDown binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddVariable2}" id="ddVariable2"
                                                items="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.lbVariablesDefaultOptions.options}" styleClass="textField"/>
                                            <ui:dropDown binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddOperador2}" id="ddOperador2"
                                                items="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddOperador1DefaultOptions.options}"
                                                onChange="cambiarEstadoTextField(this.id);" styleClass="textField"/>
                                            <input class="Btn2Mni" id="btnAddVar:tfValor2" onClick="agregarATextField(this.id);"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor2', defaultButtonStrings, true, true, false);</script>
                                            <ui:textField binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.tfValor2}" columns="30"
                                                id="tfValor2" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <ui:dropDown binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddUnion2}" id="ddUnion2"
                                                         items="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddUnion1DefaultOptions.options}" styleClass="textField" onChange="onchangeDropDownO()"/>
                                        </td>
                                        <td align="left" nowrap="nowrap">
                                            <ui:dropDown binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddVariable3}" id="ddVariable3"
                                                items="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.lbVariablesDefaultOptions.options}" styleClass="textField"/>
                                            <ui:dropDown binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddOperador3}" id="ddOperador3"
                                                items="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.ddOperador1DefaultOptions.options}"
                                                onChange="cambiarEstadoTextField(this.id);" styleClass="textField"/>
                                            <input class="Btn2Mni" id="btnAddVar:tfValor3" onClick="agregarATextField(this.id);"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValor3', defaultButtonStrings, true, true, false);</script>
                                            <ui:textField binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.tfValor3}" columns="30"
                                                id="tfValor3" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <!--     ******************************************************************************************************* -->
                                    <tr>
                                        <td align="right">
                                            <ui:label binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.lblEntonces}" for="tfValorFinal"
                                                id="lblEntonces" styleClass="label" text="Entonces"/>
                                        </td>
                                        <td align="left" colspan="3" nowrap="nowrap">
                                            <input class="Btn2Mni" id="btnAddVar:tfValorFinal" onClick="agregarATextField(this.id);"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValorFinal', defaultButtonStrings, true, true, false);</script>
                                            <ui:textField binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.tfValorFinal}" columns="60"
                                                id="tfValorFinal" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.lblSino}" for="tfValorFinalSino"
                                                id="lblSino" styleClass="label" text="Sino"/>
                                        </td>
                                        <td align="left" colspan="3" nowrap="nowrap">
                                            <input class="Btn2Mni" id="btnAddVar:tfValorFinalSino" onClick="agregarATextField(this.id);"
                                                onblur="return this.myonblur();" onfocus="return this.myonfocus();" onmouseout="return this.myonmouseout();"
                                                onmouseover="return this.myonmouseover();" title="Insertar Parámetro Seleccionado" type="button" value="&gt;"/>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddVar:tfValorFinalSino', defaultButtonStrings, true, true, false);</script>
                                            <ui:textField binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.tfValorFinalSino}" columns="60"
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
                                        <td>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddVar:taCondicion', defaultButtonStrings, true, true, false);</script>
                                        </td>
                                        <td></td>
                                        <td>
                                            <script type="text/javascript">sjwuic_assign_button('btnAddCond:taCondicion', defaultButtonStrings, true, true, false);</script>
                                            <script type="text/javascript">sjwuic_assign_button('btnLimpiarCond', defaultButtonStrings, true, true, false);</script>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <ui:label binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.label13}" for="taCondicion"
                                                id="label13" styleClass="label" text="Fórmula:"/>
                                            <ui:textArea binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.taCondicion}" columns="80"
                                                id="taCondicion" rows="4" style="width: 100%;font-size: 11px;" styleClass="textField"/>
                                            <br/>
                                            Si el resultado de la condición es cero o menor, se tomará como falsa, si es un número mayor que cero, se tomará como verdadera.
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <ui:messageGroup binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="3" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.btnGuardar_action}"
                                                binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.btnGuardar}" id="btnGuardar"
                                                styleClass="button" text="Aceptar"/>
                                            <ui:staticText binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.btnCancelar_action}"
                                                binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.idSubSesion}"/>
                    <ui:script binding="#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
