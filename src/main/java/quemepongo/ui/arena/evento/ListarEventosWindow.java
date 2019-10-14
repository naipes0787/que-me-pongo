package quemepongo.ui.arena.evento;

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

import quemepongo.dominio.evento.Evento;

/**
 * Ventana de listado de eventos.
 * 
 * @see ListarEventos - Modelo del listado de eventos.
 * 
 */
@SuppressWarnings("serial")
public class ListarEventosWindow extends MainWindow<ListarEventos> {

	private final static Integer ANCHO_CAMPOS = 70;
	
	public ListarEventosWindow() {
		super(new ListarEventos());
	}

	@Override
	public void createContents(Panel mainPanel) {
		// TÍTULO
		generarHeader(mainPanel);
		
		// FILTRO DE FECHAS		
		generarFiltro(mainPanel);
		
		// BOTÓN DE BÚSQUEDA
		generarWindowFooter(mainPanel);

		// TABLA DE EVENTOS
		generarTablaEventos(mainPanel);
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
		new Label(filterPanel).setText("Desde:   ");
		new Label(filterPanel)
			.setText("Día ");
		new TextBox(filterPanel)
			.alignRight()
	    	.setWidth(ANCHO_CAMPOS)
	    	.bindValueToProperty("diaDesde");
		new Label(filterPanel)
			.setText("Mes ");
		new TextBox(filterPanel)
			.alignRight()
	    	.setWidth(ANCHO_CAMPOS)
	    	.bindValueToProperty("mesDesde");
		new Label(filterPanel)
			.setText("Año ");
		new TextBox(filterPanel)
			.alignRight()
	    	.setWidth(ANCHO_CAMPOS)
	    	.bindValueToProperty("anioDesde");
		
		Panel filterPanel2 = new Panel(mainPanel);
		filterPanel2.setLayout(new HorizontalLayout());
		new Label(filterPanel2).setText("Hasta:   ");
		new Label(filterPanel2)
			.setText("Día ");
		new TextBox(filterPanel2)
			.alignRight()
	    	.setWidth(ANCHO_CAMPOS)
	    	.bindValueToProperty("diaHasta");
		new Label(filterPanel2)
			.setText("Mes ");
		new TextBox(filterPanel2)
			.alignRight()
	    	.setWidth(ANCHO_CAMPOS)
	    	.bindValueToProperty("mesHasta");
		new Label(filterPanel2)
			.setText("Año ");
		new TextBox(filterPanel2)
			.alignRight()
	    	.setWidth(ANCHO_CAMPOS)
	    	.bindValueToProperty("anioHasta");
	}

	private void generarTablaEventos(Panel mainPanel) {
		Table<Evento> tableEventos = new Table<Evento>(mainPanel, Evento.class);
		tableEventos.bindItemsToProperty("resultados");
		tableEventos.bindValueToProperty("eventoSeleccionado");
		new Column<>(tableEventos)
		    .setTitle("Descripción")
		    .setFixedSize(250)
		    .bindContentsToProperty("titulo");
		new Column<>(tableEventos)
		    .setTitle("Fecha")
		    .setFixedSize(200)
			.bindContentsToProperty("fecha").setTransformer(new FechaToImprimibleTransformer());
		new Column<>(tableEventos)
	    	.setFixedSize(50)
		    .setTitle("Sugerencias")
		    .bindContentsToProperty("sugerenciaAceptada").setTransformer(new AtuendoToSiNoTransformer());
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