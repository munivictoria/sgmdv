<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.sun.com/web/ui" prefix="ui"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<ui:table augmentTitle="false"
	binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.table}"
	id="tableAreas" style="width: 100px;" >
	<script>
		<![CDATA[
		/* ----- Functions for Table Preferences Panel ----- */
		/*
		 * Toggle the table preferences panel open or closed
		 */
		function togglePreferencesPanel() {
			var table = document.getElementById("form1:tableAreas");
			table.toggleTblePreferencesPanel();
		}
		/* ----- Functions for Filter Panel ----- */
		/*
		 * Return true if the filter menu has actually changed,
		 * so the corresponding event should be allowed to continue.
		 */
		function filterMenuChanged() {
			var table = document.getElementById("form1:tableAreas");
			return table.filterMenuChanged();
		}
		/*
		 * Toggle the custom filter panel (if any) open or closed.
		 */
		function toggleFilterPanel() {
			var table = document.getElementById("form1:tableAreas");
			return table.toggleTableFilterPanel();
		}
		/* ----- Functions for Table Actions ----- */
		/*
		 * Initialize all rows of the table when the state
		 * of selected rows changes.
		 */
		function initAllRows() {
			var table = document.getElementById("form1:tableAreas");
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
			var table = document.getElementById("form1:tableAreas");
			table.selectGroupRows(rowGroupId, selected);
		}
		/*
		 * Disable all table actions if no rows have been selected.
		 */
		function disableActions() {
			// Determine whether any rows are currently selected
			var table = document.getElementById("form1:tableAreas");
			var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
			// Set disabled state for top actions
			document.getElementById(
					"form1:tableAreas:tableActionsTop:deleteTop").setDisabled(
					disabled);
			// Set disabled state for bottom actions
			document.getElementById(
					"form1:tableAreas:tableActionsBottom:deleteBottom")
					.setDisabled(disabled);
		}
		]]>
	</script>
	<ui:tableRowGroup
		binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.tableRowGroup1}"
		id="tableRowGroup1"
		sourceData="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.objectListDataProvider}"
		sourceVar="currentRow">
		<ui:tableColumn align="center"
			binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.tableColumn1}"
			id="tableColumn1" valign="middle" width="7">
			<ui:radioButton
				binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.radioButton1}"
				id="radioButton1" label="" name="TableAreasResponsablesProcedimientobg"
				selected="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.RBSelected}"
				selectedValue="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.currentRow}" />
		</ui:tableColumn>
		<ui:tableColumn
			binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.tableColumn2}"
			headerText="Area" id="tableColumn2">
			<ui:staticText
				binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.panelResponsable.tableAreas.staticTextNombre}"
				id="staticText2" text="#{currentRow.value['area'].nombre}" />
		</ui:tableColumn>
		<ui:tableColumn
			headerText="Responsabilidad" id="tableColumnResponsabilidad">
			<ui:dropDown id="ddResponsabilidad" styleClass="textField" binding="#{expedientes$ABMProcedimiento$ABMProcedimiento.ddResponsabilidadArea}"
				items="#{expedientes$ABMProcedimiento$ABMProcedimiento.optionsResponsabilidad}"
				selected="#{currentRow.value['responsabilidad']}" converter="EnumConverter" immediate="false" />
		</ui:tableColumn>
	</ui:tableRowGroup>
</ui:table>


