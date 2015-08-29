package com.example.squall.ffcharapp.chars;

import com.example.squall.ffcharapp.equipment.Weapon;

import java.util.List;

/**
 * Created by Squall on 22/08/2015.
 */
public abstract class FFChar {

    private String name;
    private Game game;
    private String imageName;
    private Weapon equippedWeapon;
    private CharType type;

    public static final String TAG = "ffchar";

    public FFChar(String name, Game game, CharType type, String image) {
        this.name = name;
        this.game = game;
        this.type = type;
        imageName = image;
    }

    public String getName() {
        return name;
    }

    public Game getGameEnum() {
        return game;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImage(String iName) {
        this.imageName = iName;
    }

    public CharType getType() {
        return type;
    }

    public void equip(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public void unequip() {
        this.equippedWeapon = null;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public List<Weapon> getEquippableWeapons(List<Weapon> weapons) {
        //TODO Implement after sorting subtypes
        return null;
    }

}