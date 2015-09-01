package com.example.squall.ffcharapp.equipment;

/**
 * Created by Cloud on 27/08/2015.
 */
public abstract class Armour {
    private final String name;
    private final int defense;
    private final ArmourSlot slot;

    public Armour(String name, int defense, ArmourSlot slot) {
        this.name = name;
        this.defense = defense;
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }

    public ArmourSlot getSlot() {
        return slot;
    }

    public String toString() {
        return name;
    }
}
