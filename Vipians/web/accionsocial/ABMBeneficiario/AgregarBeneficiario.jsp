<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.head1}" id="head1" title="Agregar Beneficiario">
                    <ui:link binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.label2}" for="tfPersona" id="label2" styleClass="label" text="Persona"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.tfPersona}" columns="40" id="tfPersona" styleClass="textField" disabled="true"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnSeleccionarPersonaFisica_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnSeleccionarPersonaFisica}" escape="false"
                                                       id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnSeleccionarPersonaJuridica_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnSeleccionarPersonaJuridica}" escape="false"
                                                       id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button"  text="PJ" toolTip="Seleccionar Persona Jurídica"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnLimpiarPersona_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnLimpiarPersona}" escape="false"
                                                       id="btnLimpiarPersona" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>

                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.label5}" for="tfObraSocial" id="label5" styleClass="label" text="Obra Social"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.tfObraSocial}" columns="40" id="tfObraSocial" styleClass="textField" disabled="true"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnSeleccionarObraSocial_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnSeleccionarObraSocial}" escape="false"
                                                       id="btnSeleccionarObraSocial" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Obra Social"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnLimpiarObraSocial_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnLimpiarObraSocial}" escape="false"
                                                       id="btnLimpiarObraSocial" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.label1}"
                                                      for="ddVinculo" id="label1" styleClass="label" text="Vínculo"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="3">
                                            <ui:dropDown binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.ddVinculo}"
                                                         id="ddVinculo"
                                                         items="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.ddVinculoDefaultOptions.options}" styleClass="textField"/>
                                        </td>                                        
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.label6}"
                                                      for="ddInstruccion" id="label6" styleClass="label" text="Instrucción"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:dropDown binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.ddInstruccion}"
                                                         id="ddInstruccion"
                                                         items="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.ddInstruccionDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>  
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.label7}"
                                                      for="tfOcupacion" id="label7" styleClass="label" text="Ocupación"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="3">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.tfOcupacion}"
                                                          id="tfOcupacion" styleClass="textField"/>
                                        </td>                                        
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.label8}"
                                                      for="tfIngresos" id="label8" styleClass="label" text="Ingresos"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.tfIngresos}"
                                                          id="tfIngresos" onKeyPress="return ValidarFloat(event,this)" maxLength="9" styleClass="textField"/>
                                        </td>
                                    </tr> 
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.label9}"
                                                      for="tfSalud" id="label9" styleClass="label" text="Salud"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="3">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.tfSalud}"
                                                          id="tfSalud" styleClass="textField"/>
                                        </td> 

                                    </tr>
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnGuardar_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMBeneficiario$AgregarBeneficiario.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
