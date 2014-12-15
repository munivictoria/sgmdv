<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.head1}" id="head1" title="Modificar Beneficiario">
                    <ui:link binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.body1}" focus="form1:tfCodigo" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.label2}" for="tfPersona" id="label2" styleClass="label" text="Persona"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.tfPersona}" columns="40" id="tfPersona" styleClass="textField" disabled="true"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnSeleccionarPersonaFisica_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnSeleccionarPersonaFisica}" escape="false"
                                                       id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnSeleccionarPersonaJuridica_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnSeleccionarPersonaJuridica}" escape="false"
                                                       id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button"  text="PJ" toolTip="Seleccionar Persona Jurídica"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnLimpiarPersona_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnLimpiarPersona}" escape="false"
                                                       id="btnLimpiarPersona" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>

                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.label5}" for="tfObraSocial" id="label5" styleClass="label" text="Obra Social"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.tfObraSocial}" columns="40" id="tfObraSocial" styleClass="textField" disabled="true"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnSeleccionarObraSocial_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnSeleccionarObraSocial}" escape="false"
                                                       id="btnSeleccionarObraSocial" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Obra Social"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnLimpiarObraSocial_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnLimpiarObraSocial}" escape="false"
                                                       id="btnLimpiarObraSocial" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.label1}"
                                                      for="ddVinculo" id="label1" styleClass="label" text="Vínculo"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="3">
                                            <ui:dropDown binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.ddVinculo}"
                                                         id="ddVinculo"
                                                         items="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.ddVinculoDefaultOptions.options}" styleClass="textField"/>
                                        </td>                                        
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.label6}"
                                                      for="ddInstruccion" id="label6" styleClass="label" text="Instrucción"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:dropDown binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.ddInstruccion}"
                                                         id="ddInstruccion"
                                                         items="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.ddInstruccionDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>  
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.label7}"
                                                      for="tfOcupacion" id="label7" styleClass="label" text="Ocupación"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="3">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.tfOcupacion}"
                                                          id="tfOcupacion" styleClass="textField"/>
                                        </td>                                        
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.label8}"
                                                      for="tfIngresos" id="label8" styleClass="label" text="Ingresos"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.tfIngresos}"
                                                          id="tfIngresos" onKeyPress="return ValidarFloat(event,this)" maxLength="9" styleClass="textField"/>
                                        </td>
                                    </tr> 
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.label9}"
                                                      for="tfSalud" id="label9" styleClass="label" text="Salud"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="3">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.tfSalud}"
                                                          id="tfSalud" styleClass="textField"/>
                                        </td> 

                                    </tr>
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnGuardar_action}"
                                                binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnCancelar_action}"
                                                binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMBeneficiario$ModificarBeneficiario.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
