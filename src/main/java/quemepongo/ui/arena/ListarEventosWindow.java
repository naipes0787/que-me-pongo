package quemepongo.ui.arena;

import java.awt.Color;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MainWindow;

import quemepongo.model.evento.Evento;

/**
 * Ventana de listado de eventos.
 * 
 * @see ListarEventos - El modelo subyacente.
 * 
 */
@SuppressWarnings("serial")
public class ListarEventosWindow extends MainWindow<ListarEventos> {

	public ListarEventosWindow() {
		super(new ListarEventos());
	}

	@Override
	public void createContents(Panel mainPanel) {
		// TÍTULO
		generarHeader(mainPanel);
		
		// FILTRO DE FECHAS		
		generarFiltro(mainPanel);

		// TABLA DE EVENTOS
		generarTablaEventos(mainPanel);

		// BOTÓN DE BÚSQUEDA
		generarWindowFooter(mainPanel);
	}

	private void generarHeader(Panel mainPanel) {
		this.setTitle("¿Qué me pongo?");
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setText("Eventos").setForeground(Color.BLUE);
		new Label(mainPanel).setText("Filtro de eventos").setForeground(Color.BLUE);
		new Label(mainPanel).setText("");
	}

	private void generarFiltro(Panel mainPanel) {
		Panel filterPanel = new Panel(mainPanel);
		filterPanel.setLayout(new HorizontalLayout());
		new Label(filterPanel).setText("Desde:");
		new Label(filterPanel)
			.setText("Día");
		new TextBox(filterPanel)
			.alignRight()
	    	.setWidth(30)
	    	.bindValueToProperty("diaDesde");
		new Label(filterPanel)
			.setText("Mes");
		new TextBox(filterPanel)
			.alignRight()
	    	.setWidth(30)
	    	.bindValueToProperty("mesDesde");
		new Label(filterPanel)
			.setText("Año");
		new TextBox(filterPanel)
			.alignRight()
	    	.setWidth(30)
	    	.bindValueToProperty("anioDesde");
		
		Panel filterPanel2 = new Panel(mainPanel);
		filterPanel2.setLayout(new HorizontalLayout());
		new Label(filterPanel2).setText("Hasta:");
		new Label(filterPanel2)
			.setText("Día");
		new TextBox(filterPanel2)
			.alignRight()
	    	.setWidth(30)
	    	.bindValueToProperty("diaHasta");
		new Label(filterPanel2)
			.setText("Mes");
		new TextBox(filterPanel2)
			.alignRight()
	    	.setWidth(30)
	    	.bindValueToProperty("mesHasta");
		new Label(filterPanel2)
			.setText("Año");
		new TextBox(filterPanel2)
			.alignRight()
	    	.setWidth(30)
	    	.bindValueToProperty("anioHasta");
	}

	private void generarTablaEventos(Panel mainPanel) {
		Table<Evento> tableEventos = new Table<Evento>(mainPanel, Evento.class);
		tableEventos.bindItemsToProperty("resultados");
		tableEventos.bindValueToProperty("eventoSeleccionado");
		new Column<Evento>(tableEventos) //
		    .setTitle("Descripción")
		    .setFixedSize(250)
		    .bindContentsToProperty("descripcion");
		new Column<Evento>(tableEventos) //
		    .setTitle("Fecha")
		    .setFixedSize(250)
		    .bindContentsToProperty("fecha");
		new Column<Evento>(tableEventos) //
		    .setTitle("Sugerencias")
		    .setFixedSize(250)
		    .bindContentsToProperty("descripcion");
	}
	
	private void generarWindowFooter(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());
		new Button(actionsPanel)
			.setCaption("Buscar")
			.onClick(()-> this.getModelObject().search());
		new Button(actionsPanel)
			.setCaption("Limpiar")
			.onClick(()-> this.getModelObject().clear());
	}

}