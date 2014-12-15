<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.head1}" id="head1" title="Eliminar Beneficiario">
                    <ui:link binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.body1}" id="body1" onLoad="parent.footer.location.reload();" style="background-color: rgb(242, 236, 236); -rave-layout: grid">
                    <ui:form binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="rojo">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.label2}" for="tfPersona" id="label2" styleClass="label" text="Persona"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.tfPersona}" columns="40" id="tfPersona" styleClass="textField"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnSeleccionarPersonaFisica_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnSeleccionarPersonaFisica}" escape="false"
                                                       id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnSeleccionarPersonaJuridica_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnSeleccionarPersonaJuridica}" escape="false"
                                                       id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button"  text="PJ" toolTip="Seleccionar Persona Jurídica"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnLimpiarPersona_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnLimpiarPersona}" escape="false"
                                                       id="btnLimpiarPersona" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>

                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.label5}" for="tfObraSocial" id="label5" styleClass="label" text="Obra Social"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.tfObraSocial}" columns="40" id="tfObraSocial" styleClass="textField"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnSeleccionarObraSocial_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnSeleccionarObraSocial}" escape="false"
                                                       id="btnSeleccionarObraSocial" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Obra Social"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnLimpiarObraSocial_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnLimpiarObraSocial}" escape="false"
                                                       id="btnLimpiarObraSocial" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.label1}"
                                                      for="ddVinculo" id="label1" styleClass="label" text="Vínculo"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="3">
                                            <ui:dropDown binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.ddVinculo}"
                                                         id="ddVinculo"
                                                         items="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.ddVinculoDefaultOptions.options}" styleClass="textField"/>
                                        </td>                                        
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.label6}"
                                                      for="ddInstruccion" id="label6" styleClass="label" text="Instrucción"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:dropDown binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.ddInstruccion}"
                                                         id="ddInstruccion"
                                                         items="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.ddInstruccionDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>  
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.label7}"
                                                      for="tfOcupacion" id="label7" styleClass="label" text="Ocupación"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="3">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.tfOcupacion}"
                                                          id="tfOcupacion" styleClass="textField"/>
                                        </td>                                        
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.label8}"
                                                      for="tfIngresos" id="label8" styleClass="label" text="Ingresos"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.tfIngresos}"
                                                          id="tfIngresos" styleClass="textField"/>
                                        </td>
                                    </tr> 
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.label9}"
                                                      for="tfSalud" id="label9" styleClass="label" text="Salud"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="3">
                                            <ui:textField binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.tfSalud}"
                                                          id="tfSalud" styleClass="textField"/>
                                        </td> 

                                    </tr>
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnEliminar_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar"/>
                                            <ui:staticText binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMBeneficiario$EliminarBeneficiario.idSubSesion}"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
