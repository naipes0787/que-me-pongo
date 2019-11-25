package quemepongo.dominio.prenda;

import quemepongo.excepcion.ColoresRepetidosException;
import quemepongo.excepcion.MaterialInvalidoException;

import java.util.Objects;

public class CreadorDePrenda {

	private String nombre;
	private TipoPrenda tipoPrenda;
	private Material material;
	private Color colorPrincipal;
	private Color colorSecundario;
	private String urlFoto;

	public CreadorDePrenda setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

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
	
    public CreadorDePrenda setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
		return this;
    }

	public String getNombre() {
		return nombre;
	}

	public TipoPrenda getTipoPrenda() {
		return tipoPrenda;
	}

	public Material getMaterial() {
		return material;
	}

	public Color getColorPrincipal() {
		return colorPrincipal;
	}

	public Color getColorSecundario() {
		return colorSecundario;
	}

	public String getUrlFoto() {
		return urlFoto;
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
		return new Prenda(this.nombre, this.tipoPrenda, this.material, this.colorPrincipal,
				this.colorSecundario, this.urlFoto);
	}

}