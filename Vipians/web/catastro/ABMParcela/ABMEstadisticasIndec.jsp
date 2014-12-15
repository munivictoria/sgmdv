<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMParcela$ABMEstadisticasIndec.page1}" id="page1">
			<ui:html binding="#{catastro$ABMParcela$ABMEstadisticasIndec.html1}" id="html1">
			<ui:head binding="#{catastro$ABMParcela$ABMEstadisticasIndec.head1}" id="head1">
				<ui:link binding="#{catastro$ABMParcela$ABMEstadisticasIndec.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMParcela$ABMEstadisticasIndec.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMParcela$ABMEstadisticasIndec.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMParcela$ABMEstadisticasIndec.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMParcela$ABMEstadisticasIndec.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td>
										<h:panelGrid columns="6">
											<ui:label binding="#{catastro$ABMParcela$ABMEstadisticasIndec.lblCantidadViviendas}"  id="lblCantidadViviendas" styleClass="label"
												text="Cantidad Viviendas"/>
											<ui:textField binding="#{catastro$ABMParcela$ABMEstadisticasIndec.tfCantidadViviendas}" id="tfCantidadViviendas" styleClass="textField" onKeyPress="return ValidarNum(event,this)" columns="10"/>
											<ui:label binding="#{catastro$ABMParcela$ABMEstadisticasIndec.lblCantidadHabitaciones}" id="lblCantidadHabitaciones" styleClass="label"
												text="Cantidad Habitaciones" />
											<ui:textField binding="#{catastro$ABMParcela$ABMEstadisticasIndec.tfCantidadHabitaciones}" id="tfCantidadHabitaciones" styleClass="textField" onKeyPress="return ValidarNum(event,this)" columns="10"/>
											<ui:label binding="#{catastro$ABMParcela$ABMEstadisticasIndec.lblCantidadBanios}" id="lblCantidadBanios" styleClass="label"
												text="Cantidad Baños" />
											<ui:textField binding="#{catastro$ABMParcela$ABMEstadisticasIndec.tfCantidadBanios}" id="tfCantidadBanios" styleClass="textField" onKeyPress="return ValidarNum(event,this)" columns="10"/>
											<ui:label binding="#{catastro$ABMParcela$ABMEstadisticasIndec.lblCantidadCocinas}" id="lblCantidadCocinas" styleClass="label"
												text="Cantidad Cocinas" />
											<ui:textField binding="#{catastro$ABMParcela$ABMEstadisticasIndec.tfCantidadCocinas}" id="tfCantidadCocinas" styleClass="textField" onKeyPress="return ValidarNum(event,this)" columns="10"/>
											<ui:label binding="#{catastro$ABMParcela$ABMEstadisticasIndec.lblCantidadGarages}" id="lblCantidadGarages" styleClass="label"
												text="Cantidad Garages" />
											<ui:textField binding="#{catastro$ABMParcela$ABMEstadisticasIndec.tfCantidadGarages}" id="tfCantidadGarages" styleClass="textField" onKeyPress="return ValidarNum(event,this)" columns="10"/>
											<ui:label binding="#{catastro$ABMParcela$ABMEstadisticasIndec.lblLocalesComerciales}" id="lblLocalesComerciales" styleClass="label"
												text="Locales Comerciales" />
											<ui:textField binding="#{catastro$ABMParcela$ABMEstadisticasIndec.tfLocalesComerciales}" id="tfLocalesComerciales" styleClass="textField" onKeyPress="return ValidarNum(event,this)" columns="10"/>
											<ui:label binding="#{catastro$ABMParcela$ABMEstadisticasIndec.lblOtros}" id="lblOtros" styleClass="label"
												text="Otros" />
											<ui:textField binding="#{catastro$ABMParcela$ABMEstadisticasIndec.tfOtros}" id="tfOtros" styleClass="textField" onKeyPress="return ValidarNum(event,this)" columns="10"/>
										</h:panelGrid>
									</td>
								</tr>
							</tbody>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{catastro$ABMParcela$ABMEstadisticasIndec.messageGroup1}" id="messageGroup1"
										styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="nowrap">
										<ui:button action="#{catastro$ABMParcela$ABMEstadisticasIndec.btnGuardar_action}"
											binding="#{catastro$ABMParcela$ABMEstadisticasIndec.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMParcela$ABMEstadisticasIndec.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMParcela$ABMEstadisticasIndec.btnCancelar_action}"
											binding="#{catastro$ABMParcela$ABMEstadisticasIndec.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMParcela$ABMEstadisticasIndec.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMParcela$ABMEstadisticasIndec.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMParcela$ABMEstadisticasIndec.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMParcela$ABMEstadisticasIndec.idSubSesion}" />
					<ui:script binding="#{catastro$ABMParcela$ABMEstadisticasIndec.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
