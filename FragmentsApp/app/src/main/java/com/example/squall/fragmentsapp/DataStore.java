package com.example.squall.fragmentsapp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Squall on 03/09/2015.
 *
 * Class that simply stores the data used by the application in an Unmodifiable HashMap as well
 * as the keys for the hash map.
 */
public class DataStore {
    public static final Map<String, String> data;

    public static final String[] words = {"Frog", "Jam", "Beans", "Egg", "String", "Scanner"};

    static {
        Map<String, String> aMap = new HashMap<>();
        aMap.put(words[0], "Any tailless, stout-bodied amphibian of the order Anura, including the smooth, moist-skinned frog species that live in a damp or semiaquatic habitat and the warty, drier-skinned toad species that are mostly terrestrial as adults.");
        aMap.put(words[1], "A preserve of whole fruit, slightly crushed, boiled with sugar");
        aMap.put(words[2], "The edible nutritious seed of various plants of the legume family, especially of the genus Phaseolus.");
        aMap.put(words[3], "A very tasty and nutritious snack that can be boiled, scrambled, fried, poached and more!");
        aMap.put(words[4], "A widely used class in Java that represents characters as a String literal. The class is immutable and its full package definition is: " + String.class.toString().substring(5, String.class.toString().length()));
        aMap.put(words[5], "Commonly used class in Java to read user input the full package definition of this class is: " + Scanner.class.getName());
        data = Collections.unmodifiableMap(aMap);
    }
}
