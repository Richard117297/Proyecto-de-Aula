package co.edu.upb.trenes.models.trenes;

public enum TipoTren {
    ARNOLD(32),
    MERCEDES_BENZ(28);

    private final int capacidadMaximaVagones;

    TipoTren(int capacidadMaximaVagones) {
        this.capacidadMaximaVagones = capacidadMaximaVagones;
    }

    public int getCapacidadMaximaVagones() {
        return capacidadMaximaVagones;
    }
}
