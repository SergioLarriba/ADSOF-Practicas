package embarcaciones;

/**
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class Velero extends Embarcacion {

	private int numMastiles; 
	private double longMastil; 
	

	public Velero (String nombre, int capacidad, double dimensiones, int numMastiles, double longMastil) {
		super(nombre, capacidad, dimensiones); 
		this.numMastiles = numMastiles; 
		this.longMastil = longMastil; 
	}

	// Getters y setters 
	public double getNumMastiles() {
		return numMastiles;
	}

	public void setNumMastiles(int numMastiles) {
		this.numMastiles = numMastiles;
	}

	public double getLongMastil() {
		return longMastil;
	}

	public void setLongMastil(double longMastil) {
		this.longMastil = longMastil;
	}

	// Métodos de las clase abstracta implementados aqui  
	@Override
	public String toString() {
		return "Velero: "+ super.toString() + this.numMastiles +" mastiles, de "+ this.longMastil +"metros";
	}

	/**
	 * @return coste diario de amarre de la embarcacion
	 */
	@Override
	public double getCoste() {
		return super.getCoste() + this.numMastiles*this.longMastil;
	}
}
