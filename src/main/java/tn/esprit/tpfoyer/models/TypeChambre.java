package tn.esprit.tpfoyer.models;

public enum TypeChambre {
    SIMPLE(1), DOUBLE(2), TRIPLE(3);

    private final int capacite;

    TypeChambre(int capacite) {
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }
}
