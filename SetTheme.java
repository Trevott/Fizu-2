package com.trevott.fizu;

public class SetTheme {

    private int háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék;
    private int szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék;
    private int gombSzínePiros, gombSzíneZöld, gombSzíneKék;

    public SetTheme() {
        try {
            if (MainActivity.controller.getAppTéma() == 0) {
                háttérSzínePiros = 255;
                háttérSzíneZöld = 255;
                háttérSzíneKék = 255;
                szövegSzínePiros = 0;
                szövegSzíneZöld = 0;
                szövegSzíneKék = 0;
            }
            else if (MainActivity.controller.getAppTéma() == 1) {
                háttérSzínePiros = 0;
                háttérSzíneZöld = 0;
                háttérSzíneKék = 0;
                szövegSzínePiros = 255;
                szövegSzíneZöld = 255;
                szövegSzíneKék = 255;
            } else {

                háttérSzínePiros = 255;
                háttérSzíneZöld = 255;
                háttérSzíneKék = 255;
                szövegSzínePiros = 0;
                szövegSzíneZöld = 0;
                szövegSzíneKék = 0;
            }
        } catch (Exception e) {
            háttérSzínePiros = 255;
            háttérSzíneZöld = 255;
            háttérSzíneKék = 255;
            szövegSzínePiros = 0;
            szövegSzíneZöld = 0;
            szövegSzíneKék = 0;
        }

        //gombok színe
        try {
            if (MainActivity.controller.getGombTéma() == 0) {
                gombSzínePiros = 0;
                gombSzíneZöld = 153;
                gombSzíneKék = 126;
            }
            else if (MainActivity.controller.getGombTéma() == 1) {
                gombSzínePiros = 0;
                gombSzíneZöld = 131;
                gombSzíneKék = 161;
            }
            else if (MainActivity.controller.getGombTéma() == 2) {
                gombSzínePiros = 171;
                gombSzíneZöld = 15;
                gombSzíneKék = 15;
            }
            else if (MainActivity.controller.getGombTéma() == 3) {
                gombSzínePiros = 181;
                gombSzíneZöld = 181;
                gombSzíneKék = 0;
            }
            else if (MainActivity.controller.getGombTéma() == 4) {
                gombSzínePiros = 121;
                gombSzíneZöld = 45;
                gombSzíneKék = 121;
            }
            else if (MainActivity.controller.getGombTéma() == 5) {
                gombSzínePiros = 151;
                gombSzíneZöld = 71;
                gombSzíneKék = 51;
            } else {

                gombSzínePiros = 0;
                gombSzíneZöld = 153;
                gombSzíneKék = 126;
            }
        } catch (Exception e) {
            gombSzínePiros = 0;
            gombSzíneZöld = 153;
            gombSzíneKék = 126;
        }
    }

    public int getHáttérSzínePiros() {
        return háttérSzínePiros;
    }

    public int getHáttérSzíneZöld() {
        return háttérSzíneZöld;
    }

    public int getHáttérSzíneKék() {
        return háttérSzíneKék;
    }

    public int getSzövegSzínePiros() {
        return szövegSzínePiros;
    }

    public int getSzövegSzíneZöld() {
        return szövegSzíneZöld;
    }

    public int getSzövegSzíneKék() {
        return szövegSzíneKék;
    }

    public int getGombSzínePiros() {
        return gombSzínePiros;
    }

    public int getGombSzíneZöld() {
        return gombSzíneZöld;
    }

    public int getGombSzíneKék() {
        return gombSzíneKék;
    }
}