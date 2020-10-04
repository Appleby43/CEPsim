package com.alexblakeappleby.cepsim.util;

import java.util.Random;

public class Util {
    private static Random random = new Random();

    /**
     * For testing, set random to a particular seeded value for determinism.
     */
    public static void setRandom(){
        random = new Random(0);
    }

    public static Random getRandom(){
        return random;
    }
}
