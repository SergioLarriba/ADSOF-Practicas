package classes;

public class RecomendadorAfinidad extends RecomendadorGeneral {

    public RecomendadorAfinidad(double corte) {
        super(corte);
    }

    @Override
    public void addValoracion(IUsuario usuario, IRecomendable elemento, Valoracion valoracion) {
        throw new UnsupportedOperationException("Unimplemented method 'addValoracion'");
    }

}
