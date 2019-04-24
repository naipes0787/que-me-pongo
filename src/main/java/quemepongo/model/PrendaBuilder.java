package quemepongo.model;

import java.awt.Color;
import java.util.Objects;

public class PrendaBuilder {

	public TipoPrenda tipoPrenda;
	public Material material;
	public Color colorPrincipal;
	public Color colorSecundario;
	public Trama trama = Trama.LISA;

	public PrendaBuilder() {
		
	}

	public PrendaBuilder setTipoPrenda(TipoPrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
		return this;
	}

	public PrendaBuilder setMaterial(Material material) {
		this.material = material;
		return this;
	}

	public PrendaBuilder setTrama(Trama trama) {
		this.trama = trama;
		return this;
	}

	public PrendaBuilder setColorPrincipal(Color color) {
		this.colorPrincipal = color;
		return this;
	}

	public PrendaBuilder setColorSecundario(Color color) {
		this.colorSecundario = color;
		return this;
	}

	public Prenda buildPrenda() {
		Objects.requireNonNull(this.tipoPrenda, "El tipo de la prenda es obligatorio");
		Objects.requireNonNull(this.colorPrincipal, "El color principal de la prenda es obligatorio");
		Objects.requireNonNull(this.material, "El material de la prenda es obligatorio");
		/* 
		 * TODO: Validar que el tipo de prenda se corresponda con la categoría y también con el material,
		 * 		 si es que no lo hace la clase Prenda - Pendiente de respuesta del ayudante
		 */
		return new Prenda(tipoPrenda, material, trama, colorPrincipal, colorSecundario);
	}

}
