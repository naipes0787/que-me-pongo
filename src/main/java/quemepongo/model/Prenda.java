package quemepongo.model;
import java.awt.Color;
import java.util.Objects;

import quemepongo.exceptions.ColoresRepetidosException;
import quemepongo.exceptions.MaterialInvalidoException;

public class Prenda {
	
	public static class Builder {

		public TipoPrenda tipoPrenda;
		public Material material;
		public Color colorPrincipal;
		public Color colorSecundario;
		public Trama trama = Trama.LISA;

		public Builder() {
			
		}

		public Builder setTipoPrenda(TipoPrenda tipoPrenda) {
			this.tipoPrenda = tipoPrenda;
			return this;
		}

		public Builder setMaterial(Material material) {
			this.material = material;
			return this;
		}

		public Builder setTrama(Trama trama) {
			this.trama = trama;
			return this;
		}

		public Builder setColorPrincipal(Color color) {
			this.colorPrincipal = color;
			return this;
		}

		public Builder setColorSecundario(Color color) {
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
			return new Prenda(tipoPrenda, material, trama, colorPrincipal, colorSecundario);
		}

	}
	
    public TipoPrenda tipo;
    public Material material;
    public Color colorPrincipal;
    public Color colorSecundario;
    public Trama trama;

    private Prenda(TipoPrenda tipo, Material material, Trama trama, Color colorPrincipal, Color colorSecundario){
        this.tipo = tipo;
        this.material = material;
        this.trama = trama;
        this.colorPrincipal = colorPrincipal;
        this.colorSecundario = colorSecundario;
    }

    public Categoria getCategoria(){
        return this.tipo.getCategoria();
    }

}
