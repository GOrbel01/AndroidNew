package com.example.squall.ffcharapp.equipment;

/**
 * Created by Cloud on 27/08/2015.
 */
public class Weapon {
    private String name;
    private int power;
    private WeaponType type;

    public static final String TAG = "weapon";

    public Weapon(String name, int power, WeaponType type) {
        this.name = name;
        this.power = power;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public WeaponType getType() {
        return type;
    }

    public String toString() {
        return name;
    }
}
