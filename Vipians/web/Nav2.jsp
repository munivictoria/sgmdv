<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{Nav2.page1}" id="page1">
			<ui:html binding="#{Nav2.html1}" id="html1">
			<ui:head binding="#{Nav2.head1}" id="head1" title="Nav2">
				<ui:link binding="#{Nav2.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{Nav2.body1}" id="body1" style="background-color: rgb(240, 240, 240); -rave-layout: grid;">
				<ui:form binding="#{Nav2.form1}" id="form1">
					<div class="nav">
						<table cellspacing="0">
							<thead>
								<tr valign="middle">
									<td>
										<ui:label binding="#{Nav2.label4}" id="label4" text="Información de la Sesión" />
									</td>
								</tr>
							</thead>
							<tr>
								<td></td>
							</tr>
							<tr valign="middle">
								<td align="center" height="28px">
									<ui:staticText binding="#{Nav2.stPersona}" escape="false" id="stPersona"
										style="font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 10px; font-weight: bold; margin-top:10px;" />
								</td>
							</tr>
							<tr valign="bottom">
								<td align="center" height="28px">
									<ui:hyperlink binding="#{Nav2.hypInicio}" id="hypInicio"
										onClick="return(confirm(&quot;Si tiene iniciada una Operación se CANCELARÁ.\n\n¿Está seguro?&quot;));"
										onMouseDown="window.status=&quot;&quot;; return true;" onMouseOut="window.status=&quot;&quot;; return true;"
										onMouseOver="window.status=&quot;&quot;; return true;" onMouseUp="window.status=&quot;&quot;; return true;" styleClass="label"
										target="main" text="Inicio" url="/faces/Main.jsp" />
									&amp;nbsp;|&amp;nbsp;
									<ui:hyperlink binding="#{Nav2.hypOpciones}" id="hypOpciones" onMouseDown="window.status=&quot;&quot;; return true;"
										onMouseOut="window.status=&quot;&quot;; return true;" onMouseOver="window.status=&quot;&quot;; return true;"
										onMouseUp="window.status=&quot;&quot;; return true;" styleClass="label" target="main" text="Opciones"
										url="/faces/comunes/OpcionesUsuario.jsp" />
									&amp;nbsp;|&amp;nbsp;
									<ui:staticText text="Ayuda" styleClass="label"></ui:staticText>
									<!--<ui:hyperlink
                                            binding="#{Nav2.hypAyuda}" id="hypAyuda" onMouseDown="window.status=&quot;&quot;; return true;"
                                            onMouseOut="window.status=&quot;&quot;; return true;" onMouseOver="window.status=&quot;&quot;; return true;"
                                            onMouseUp="window.status=&quot;&quot;; return true;" styleClass="label" target="_blank"
                                            text="Ayuda"/>-->
									&amp;nbsp;|&amp;nbsp;
									<ui:hyperlink action="#{Nav2.salir_action}" binding="#{Nav2.hypSalir}" id="hypSalir"
										onMouseDown="window.status=&quot;&quot;; return true;" onMouseOut="window.status=&quot;&quot;; return true;"
										onMouseOver="window.status=&quot;&quot;; return true;" onMouseUp="window.status=&quot;&quot;; return true;" styleClass="label"
										target="_top" text="Salir" />
								</td>
							</tr>
						</table>
					</div>
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
