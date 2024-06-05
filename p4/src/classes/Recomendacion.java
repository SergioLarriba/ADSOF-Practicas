package classes;

/**
 * Implementa la clase Recomendacion.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 * 
 * @see Comparable
 */
public class Recomendacion implements Comparable<Recomendacion> {
    private IRecomendable elemento;
    private double confianza;

    public Recomendacion(IRecomendable elemento, double confianza) {
        this.elemento = elemento;
        this.confianza = confianza;
    }

    @Override
    public int compareTo(Recomendacion obj) {
        if (obj == null) {
            return 1;
        }

        if (this.equals(obj)) {
            return 0;
        } else if (this.confianza == obj.confianza) {
            return 0;
        } else if (this.confianza > obj.confianza) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((elemento == null) ? 0 : elemento.hashCode());
        long temp;
        temp = Double.doubleToLongBits(confianza);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Recomendacion other = (Recomendacion) obj;
        if (elemento == null) {
            if (other.elemento != null)
                return false;
        } else if (!elemento.equals(other.elemento))
            return false;
        if (Double.doubleToLongBits(confianza) != Double.doubleToLongBits(other.confianza))
            return false;
        return true;
    }

    public void setConfianza(double confianza) {
        this.confianza = confianza;
    }

    public double getConfianza() {
        return this.confianza;
    }

    public IRecomendable getElemento() {
        return this.elemento;
    }

    /**
     * Añade una nueva valoración, cambiando así la confianza de la recomendación.
     * 
     * @param valoracion valoración añadida
     */
    public void addValoracion(Valoracion valoracion) {
        if (valoracion == null) {
            return;
        }

        this.confianza += valoracion.getValue();
    }

    @Override
    public String toString() {
        return this.elemento.getClass().getSimpleName().toUpperCase() + ": " + this.elemento.getDescripcion() + " ["
                + this.confianza + "]";
    }

}