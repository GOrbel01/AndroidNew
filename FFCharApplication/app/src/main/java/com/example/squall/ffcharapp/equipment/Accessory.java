package com.example.squall.ffcharapp.equipment;

/**
 * Created by Cloud on 27/08/2015.
 */
public class Accessory {
    private final String name;
    private final int plusAttack;

    public Accessory(String name, int attack) {
        this.name = name;
        this.plusAttack = attack;
    }

    public String getName() {
        return name;
    }

    public int getPlus() {
        return plusAttack;
    }
}
