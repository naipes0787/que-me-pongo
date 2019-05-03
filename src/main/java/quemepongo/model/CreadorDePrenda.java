package quemepongo.model;

import quemepongo.exceptions.ColoresRepetidosException;
import quemepongo.exceptions.MaterialInvalidoException;

import java.awt.*;
import java.util.Objects;

public class CreadorDePrenda {

	public TipoPrenda tipoPrenda;
	public Material material;
	public Color colorPrincipal;
	public Color colorSecundario;

	public CreadorDePrenda setTipoPrenda(TipoPrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
		return this;
	}

	public CreadorDePrenda setMaterial(Material material) {
		this.material = material;
		return this;
	}

	public CreadorDePrenda setColorPrincipal(Color color) {
		this.colorPrincipal = color;
		return this;
	}

	public CreadorDePrenda setColorSecundario(Color color) {
		this.colorSecundario = color;
		return this;
	}

	/**
	 * Se valida que el material elegido sea de los permitidos por el tipo
	 * de prenda.
	 * @throws MaterialInvalidoException
	 */
	private void validarMaterial() {
		if(this.tipoPrenda.getMaterialesValidos().stream().
				noneMatch(material -> material.equals(this.material))) {
			throw new MaterialInvalidoException(this.material);
		}
	}

	/**
	 * Se valida que el color principal no sea el mismo que el color
	 * secundario.
	 * @throws ColoresRepetidosException
	 */
	private void validarColores() {
		if(this.colorPrincipal.equals(this.colorSecundario)) {
			throw new ColoresRepetidosException();
		}
	}

	public Prenda build() {
		Objects.requireNonNull(this.tipoPrenda, "El tipo de la prenda es obligatorio");
		Objects.requireNonNull(this.colorPrincipal, "El color principal de la prenda es obligatorio");
		Objects.requireNonNull(this.material, "El material de la prenda es obligatorio");
		this.validarMaterial();
		this.validarColores();
		return new Prenda(tipoPrenda, material, colorPrincipal, colorSecundario);
	}

}