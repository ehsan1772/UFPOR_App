package com.ufpor.app.shared.utils;

/**
 * Created by Ehsan Barekati on 6/18/15.
 */
public class TU {
    public static boolean isEmpty(String string) {
        if (string != null && !string.isEmpty()) {
            return false;
        }
        return true;
    }
}
