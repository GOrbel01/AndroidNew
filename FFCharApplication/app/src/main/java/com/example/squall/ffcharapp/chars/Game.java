package com.example.squall.ffcharapp.chars;

/**
 * Created by Cloud on 27/08/2015.
 */
public enum Game {
    FFVII, FFVIII, FFIX, FFX, INVALID;

    public static Game getGame(String game) {
        switch(game) {
            case "Final Fantasy VII": {
                return Game.FFVII;
            }
            case "Final Fantasy VIII": {
                return Game.FFVIII;
            }
            case "Final Fantasy IX": {
                return Game.FFIX;
            }
            case "Final Fantasy X": {
                return Game.FFX;
            }
            default: {
                return Game.INVALID;
            }
        }
    }

    public static String getGame(Game game) {
        switch(game) {
            case FFVII: {
                return "Final Fantasy VII";
            }
            case FFVIII: {
                return "Final Fantasy VIII";
            }
            case FFIX: {
                return "Final Fantasy IX";
            }
            case FFX: {
                return "Final Fantasy X";
            }
            default: {
                return "Invalid";
            }
        }
    }
}
