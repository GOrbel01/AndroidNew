package com.example.squall.ffcharapp.factory;

import com.example.squall.ffcharapp.chars.Caster;
import com.example.squall.ffcharapp.chars.CharType;
import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.chars.Game;
import com.example.squall.ffcharapp.chars.Mercenary;
import com.example.squall.ffcharapp.chars.Soldier;
import com.example.squall.ffcharapp.chars.Thief;

/**
 * Created by Cloud on 29/08/2015.
 */
public class FFCharMaker {
    public static FFChar createFFChar(String name, Game game, CharType type, String image) {
        switch (type) {
            case SOLDIER: return new Soldier(name, game, type, image);
            case MERCENARY: return new Mercenary(name, game, type, image);
            case THIEF: return new Thief(name, game, type, image);
            case CASTER: return new Caster(name, game, type, image);
            default: throw new IllegalStateException("Failed to Execute");
        }
    }
}
