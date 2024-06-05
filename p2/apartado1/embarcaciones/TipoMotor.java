package embarcaciones;

public enum TipoMotor {
	
	// Cada objeto del enumerado inicializa el factor en el constructor
	GASOLINA(2), DIESEL(1.5), ELECTRICO (1);
	private double factor; // factor de coste
	
	TipoMotor(double multip) { // constructor del enumerado
		this.factor = multip;
	}
	
	/**
	 * @return el factor de coste del motor
	 */
	public double factorMotor() { // getter
		return this.factor;
	}
}

