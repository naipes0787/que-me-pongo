package quemepongo.ui.arena;

import java.awt.Color;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;

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
		this.setTitle("Conversor de millas a kilómetros");
		mainPanel.setLayout(new VerticalLayout());

		new Label(mainPanel).setText("Ingrese la longitud en millas");

		new NumericField(mainPanel).bindValueToProperty("localizacion");

		new Button(mainPanel)
			.setCaption("Convertir a kilómetros")
			.onClick(()-> this.getModelObject().search());

		new Label(mainPanel) //
			.setBackground(Color.ORANGE)
			.bindValueToProperty("fecha");

		new Label(mainPanel).setText(" kilómetros");
	}
	
	public static void main(String[] args) {
		new ListarEventosWindow().startApplication();
	}

}