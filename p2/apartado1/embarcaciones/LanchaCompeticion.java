package embarcaciones;

/**
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class LanchaCompeticion extends EmbarcacionMotor {
	
	public static final int DIMENSION_LANCHA = 49;
	public static final int CAPACIDAD_LANCHA = 1;
	public static final int NUM_MOTORES_LANCHA = 1; 
	public static final double COSTE_LANCHA = 200; 
	public static final double COSTE_CATAMARAN = 50; 
	public static final TipoMotor TIPO_MOTOR_LANCHA = TipoMotor.GASOLINA;
	private String nombre; 
	private boolean esCatamaran; 

	// Constructor 
	public LanchaCompeticion(String nombre, boolean esCatamaran) {
		super(nombre, CAPACIDAD_LANCHA, DIMENSION_LANCHA, NUM_MOTORES_LANCHA, TIPO_MOTOR_LANCHA);
		this.nombre = nombre;
		this.esCatamaran = esCatamaran;
	}
	
	// Getters y setters 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getEsDeCompeticion() {
		return esCatamaran;
	}

	public void setEsDeCompeticion(boolean esDeCompeticion) {
		this.esCatamaran = esDeCompeticion;
	}
	
	/**
	 * @return coste diario de amarre de la embarcacion
	 */
	public double getCoste() {
		if (this.esCatamaran == false)
			return COSTE_LANCHA;
		else 
			return COSTE_LANCHA + COSTE_CATAMARAN; 
	} 

	@Override
	public String toString() {
		if (this.esCatamaran == false)
			return super.toString();
		else 
			return super.toString() +", de competicion (catamaran)";
	}
}
