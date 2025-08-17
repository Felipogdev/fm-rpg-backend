package com.fmrpg.fmbackend.enums;

public enum CursedAbilityCosts {
    TIER0COST(0),
    TIER1COST(2),
    TIER2COST(5),
    TIER3COST(8),
    TIER4COST(12),
    TIER5COST(20);

    private int cost;

    CursedAbilityCosts(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }
}
