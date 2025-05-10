package feiticeiros.example.fmbackend.character;

public enum CharacterClasses {

    RESTINGIDO(16, 7, 4),
    ESPECIALISTA_COMBATE(12, 6, 4),
    ESPECIALISTA_TECNICA(10, 5, 6),
    CONTROLADOR(10, 5, 5),
    SUPORTE(10, 5, 5),
    LUTADOR(12, 6, 4);

    private final int hpBase;
    private final int hpPerLevel;
    private final int cursedEnergyPerLevel;

    CharacterClasses(int hpBase, int hpPerLevel, int cursedEnergyPerLevel) {
        this.hpBase = hpBase;
        this.hpPerLevel = hpPerLevel;
        this.cursedEnergyPerLevel = cursedEnergyPerLevel;
    }

    public int getHpBase() {
        return hpBase;
    }

    public int getHpPerLevel() {
        return hpPerLevel;
    }

    public int getCursedEnergyPerLevel() {
        return cursedEnergyPerLevel;
    }
}
