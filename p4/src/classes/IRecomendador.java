package classes;

import java.util.Collection;

public interface IRecomendador extends IAlmacenValoraciones {
    Collection<Recomendacion> getRecomendaciones(IUsuario usuario);

    void setCorte(double corte);
}