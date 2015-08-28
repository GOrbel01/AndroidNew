package com.example.squall.ffcharapp.chars;

import com.example.squall.ffcharapp.equipment.Weapon;

/**
 * Created by Squall on 22/08/2015.
 */
public class FFChar {
    private String name;
    private Game game;
    private String imageName;
    private Weapon weapon;

    public static final String TAG = "ffchar";

    public FFChar(String name, Game game, String image) {
        this.name = name;
        this.game = game;
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

}