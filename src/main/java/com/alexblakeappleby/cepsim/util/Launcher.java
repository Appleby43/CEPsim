package com.alexblakeappleby.cepsim.util;

/**
 * Launcher is a hack that prevents us from having to load JavaFX components at runtime via VM options
 * This should make setup minimal when developing from machine to machine.
 *
 *
 */
public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
    }
}
