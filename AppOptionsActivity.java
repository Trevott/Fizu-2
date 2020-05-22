package com.trevott.fizu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class AppOptionsActivity extends Fragment {

    private TextView buttonSötét, buttonVilágos;
    private TextView buttonZöld, buttonKék, buttonPiros, buttonSárga, buttonLila, buttonBarna;
    private TextView text1, text2;
    private int háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék, szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék;
    private int gombSzínePiros, gombSzíneZöld, gombSzíneKék;
    private int appTéma, gombTéma, egyébTéma, egyébTéma1, egyébTéma2, egyébTéma3;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.activity_app_options, container, false);

        egyébTéma = 0;
        egyébTéma1 = 0;
        egyébTéma2 = 0;
        egyébTéma3 = 0;

        buttonVilágos = root.findViewById(R.id.textButtonVilágos);
        buttonSötét = root.findViewById(R.id.textButtonSötét);

        buttonZöld = root.findViewById(R.id.textZöld);
        buttonKék = root.findViewById(R.id.textKék);
        buttonPiros = root.findViewById(R.id.textPiros);
        buttonSárga = root.findViewById(R.id.textSárga);
        buttonLila = root.findViewById(R.id.textLila);
        buttonBarna = root.findViewById(R.id.textBarna);

        //belépés után legyen eleve kiválasztott a megfelelő gomb
        try {
            if (MainActivity.controller.getAppTéma() == 0) {
                háttérSzínePiros = 255;
                háttérSzíneZöld = 255;
                háttérSzíneKék = 255;
                szövegSzínePiros = 0;
                szövegSzíneZöld = 0;
                szövegSzíneKék = 0;

                appTéma = 0;
            }
            else if (MainActivity.controller.getAppTéma() == 1) {
                háttérSzínePiros = 0;
                háttérSzíneZöld = 0;
                háttérSzíneKék = 0;
                szövegSzínePiros = 255;
                szövegSzíneZöld = 255;
                szövegSzíneKék = 255;

                appTéma = 1;
            } else {

                háttérSzínePiros = 255;
                háttérSzíneZöld = 255;
                háttérSzíneKék = 255;
                szövegSzínePiros = 0;
                szövegSzíneZöld = 0;
                szövegSzíneKék = 0;

                appTéma = 0;
            }
        } catch (Exception e) {
            háttérSzínePiros = 255;
            háttérSzíneZöld = 255;
            háttérSzíneKék = 255;
            szövegSzínePiros = 0;
            szövegSzíneZöld = 0;
            szövegSzíneKék = 0;

            appTéma = 0;
        }

        //gombok színe
        try {
            if (MainActivity.controller.getGombTéma() == 0) {
                gombSzínePiros = 0;
                gombSzíneZöld = 153;
                gombSzíneKék = 126;

                gombTéma = 0;
            }
            else if (MainActivity.controller.getGombTéma() == 1) {
                gombSzínePiros = 0;
                gombSzíneZöld = 131;
                gombSzíneKék = 161;

                gombTéma = 1;
            }
            else if (MainActivity.controller.getGombTéma() == 2) {
                gombSzínePiros = 171;
                gombSzíneZöld = 15;
                gombSzíneKék = 15;

                gombTéma = 2;
            }
            else if (MainActivity.controller.getGombTéma() == 3) {
                gombSzínePiros = 181;
                gombSzíneZöld = 181;
                gombSzíneKék = 0;

                gombTéma = 3;
            }
            else if (MainActivity.controller.getGombTéma() == 4) {
                gombSzínePiros = 121;
                gombSzíneZöld = 45;
                gombSzíneKék = 121;

                gombTéma = 4;
            }
            else if (MainActivity.controller.getGombTéma() == 5) {
                gombSzínePiros = 151;
                gombSzíneZöld = 71;
                gombSzíneKék = 51;

                gombTéma = 5;
            } else {

                gombSzínePiros = 0;
                gombSzíneZöld = 153;
                gombSzíneKék = 126;

                gombTéma = 0;
            }
        } catch (Exception e) {
            gombSzínePiros = 0;
            gombSzíneZöld = 153;
            gombSzíneKék = 126;

            gombTéma = 0;
        }

        //először megnézzük, hogy mi lett elmentve az app témájának és ez alapján állítjuk be az erre vonatkozó gombokat
        //belépés után legyen eleve kiválasztott a megfelelő gomb
        if (appTéma == 0) {

            buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        }
        else if (appTéma == 1) {

            buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

            buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
        } else {

            buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        }

        if (gombTéma == 0) { //zöld

            //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
            buttonZöld.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonZöld.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            //az app megjelenését beállító gombok színének beállítása
            if (appTéma == 0) {
                buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            } else {
                buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            }
        }
        else if (gombTéma == 1) { //kék

            //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
            buttonKék.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonKék.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            buttonZöld.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            //az app megjelenését beállító gombok színének beállítása
            if (appTéma == 0) {
                buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            } else {
                buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            }
        }
        else if (gombTéma == 2) { //piros

            //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
            buttonPiros.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonPiros.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            buttonZöld.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            //az app megjelenését beállító gombok színének beállítása
            if (appTéma == 0) {
                buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            } else {
                buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            }
        }
        else if (gombTéma == 3) { //SÁRGA

            //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
            buttonSárga.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonSárga.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            buttonZöld.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            //az app megjelenését beállító gombok színének beállítása
            if (appTéma == 0) {
                buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            } else {
                buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            }
        }
        else if (gombTéma == 4) { //lila

            //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
            buttonLila.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonLila.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            buttonZöld.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            //az app megjelenését beállító gombok színének beállítása
            if (appTéma == 0) {
                buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            } else {
                buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            }
        }
        else if (gombTéma == 5) { //barna

            //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
            buttonBarna.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonBarna.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            buttonZöld.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            //az app megjelenését beállító gombok színének beállítása
            if (appTéma == 0) {
                buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            } else {
                buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            }
        } else  {

            //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
            buttonZöld.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonZöld.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
            buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

            //az app megjelenését beállító gombok színének beállítása
            if (appTéma == 0) {
                buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            } else {
                buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
            }
        }

        text1 = root.findViewById(R.id.textMegjelenés);
        text2 = root.findViewById(R.id.textSzín);

        //az oldal témájának beállítása
        root.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
        MainActivity.toolbar.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        MainActivity.navigationView.setItemTextColor(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));
        MainActivity.navigationView.setItemIconTintList(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));
        MainActivity.navigationView.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

        text1.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text2.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));

        buttonVilágos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                appTéma = 0;

                try {
                    MainActivity.controller.delete_téma();
                    MainActivity.controller.insert_téma(appTéma, gombTéma, egyébTéma, egyébTéma1, egyébTéma2, egyébTéma3);

                    háttérSzínePiros = 255;
                    háttérSzíneZöld = 255;
                    háttérSzíneKék = 255;
                    szövegSzínePiros = 0;
                    szövegSzíneZöld = 0;
                    szövegSzíneKék = 0;

                    buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                    //az oldal témájának beállítása
                    root.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    text1.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
                    text2.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));

                    //betöltjük a gombok színére vonatkozó adatokat és ez alapján színezzük őket és be is állítjuk a gombot, ami ezt meghatározza
                    //de előtte minden gomb hátterét beállítjuk a friss háttérszínre
                    buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    switch (gombTéma) {
                        case 0:
                            buttonZöld.setBackgroundColor(Color.rgb(0, 153, 126));
                            buttonZöld.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                        case 1:
                            buttonKék.setBackgroundColor(Color.rgb(0, 131, 161));
                            buttonKék.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                        case 2:
                            buttonPiros.setBackgroundColor(Color.rgb(171, 15, 15));
                            buttonPiros.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                        case 3:
                            buttonSárga.setBackgroundColor(Color.rgb(181, 181, 0));
                            buttonSárga.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                        case 4:
                            buttonLila.setBackgroundColor(Color.rgb(121, 45, 121));
                            buttonLila.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                        case 5:
                            buttonBarna.setBackgroundColor(Color.rgb(151, 71, 51));
                            buttonBarna.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                    }

                    MainActivity.navigationView.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    Toast.makeText(getActivity(), "App megjelenése világos.", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Megjelenés beállítása nem sikerült!", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonSötét.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                appTéma = 1;

                try {
                    MainActivity.controller.delete_téma();
                    MainActivity.controller.insert_téma(appTéma, gombTéma, egyébTéma, egyébTéma1, egyébTéma2, egyébTéma3);

                    háttérSzínePiros = 0;
                    háttérSzíneZöld = 0;
                    háttérSzíneKék = 0;
                    szövegSzínePiros = 255;
                    szövegSzíneZöld = 255;
                    szövegSzíneKék = 255;

                    buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                    //az oldal témájának beállítása
                    root.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    text1.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
                    text2.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));

                    //betöltjük a gombok színére vonatkozó adatokat és ez alapján színezzük őket és be is állítjuk a gombot, ami ezt meghatározza
                    //de előtte minden gomb hátterét beállítjuk a friss háttérszínre
                    buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    switch (gombTéma) {
                        case 0:
                            buttonZöld.setBackgroundColor(Color.rgb(0, 153, 126));
                            buttonZöld.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                        case 1:
                            buttonKék.setBackgroundColor(Color.rgb(0, 131, 161));
                            buttonKék.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                        case 2:
                            buttonPiros.setBackgroundColor(Color.rgb(171, 15, 15));
                            buttonPiros.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                        case 3:
                            buttonSárga.setBackgroundColor(Color.rgb(181, 181, 0));
                            buttonSárga.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                        case 4:
                            buttonLila.setBackgroundColor(Color.rgb(121, 45, 121));
                            buttonLila.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                        case 5:
                            buttonBarna.setBackgroundColor(Color.rgb(151, 71, 51));
                            buttonBarna.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                            break;
                    }

                    MainActivity.navigationView.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    Toast.makeText(getActivity(), "App megjelenése sötét.", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Megjelenés beállítása nem sikerült!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //gomb színét választó gombok működése
        buttonZöld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gombTéma = 0;

                try {
                    MainActivity.controller.delete_téma();
                    MainActivity.controller.insert_téma(appTéma, gombTéma, egyébTéma, egyébTéma1, egyébTéma2, egyébTéma3);

                    gombSzínePiros = 0;
                    gombSzíneZöld = 153;
                    gombSzíneKék = 126;

                    //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
                    buttonZöld.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonZöld.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    //az app megjelenését beállító gombok színének beállítása
                    if (MainActivity.controller.getAppTéma() == 0) {
                        buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    } else {
                        buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    }

                    MainActivity.toolbar.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    MainActivity.navigationView.setItemTextColor(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));
                    MainActivity.navigationView.setItemIconTintList(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));

                    Toast.makeText(getActivity(), "Gombok színe zöld.", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Gombok színének beállítása nem sikerült!", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonKék.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gombTéma = 1;

                try {
                    MainActivity.controller.delete_téma();
                    MainActivity.controller.insert_téma(appTéma, gombTéma, egyébTéma, egyébTéma1, egyébTéma2, egyébTéma3);

                    gombSzínePiros = 0;
                    gombSzíneZöld = 131;
                    gombSzíneKék = 161;

                    //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
                    buttonKék.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonKék.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    buttonZöld.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    //az app megjelenését beállító gombok színének beállítása
                    if (MainActivity.controller.getAppTéma() == 0) {
                        buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    } else {
                        buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    }

                    MainActivity.toolbar.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    MainActivity.navigationView.setItemTextColor(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));
                    MainActivity.navigationView.setItemIconTintList(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));

                    Toast.makeText(getActivity(), "Gombok színe kék.", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Gombok színének beállítása nem sikerült!", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonPiros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gombTéma = 2;

                try {
                    MainActivity.controller.delete_téma();
                    MainActivity.controller.insert_téma(appTéma, gombTéma, egyébTéma, egyébTéma1, egyébTéma2, egyébTéma3);

                    gombSzínePiros = 171;
                    gombSzíneZöld = 15;
                    gombSzíneKék = 15;

                    //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
                    buttonPiros.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonPiros.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    buttonZöld.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    //az app megjelenését beállító gombok színének beállítása
                    if (MainActivity.controller.getAppTéma() == 0) {
                        buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    } else {
                        buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    }

                    MainActivity.toolbar.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    MainActivity.navigationView.setItemTextColor(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));
                    MainActivity.navigationView.setItemIconTintList(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));

                    Toast.makeText(getActivity(), "Gombok színe piros.", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Gombok színének beállítása nem sikerült!", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonSárga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gombTéma = 3;

                try {
                    MainActivity.controller.delete_téma();
                    MainActivity.controller.insert_téma(appTéma, gombTéma, egyébTéma, egyébTéma1, egyébTéma2, egyébTéma3);

                    gombSzínePiros = 181;
                    gombSzíneZöld = 181;
                    gombSzíneKék = 0;

                    //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
                    buttonSárga.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonSárga.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    buttonZöld.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    //az app megjelenését beállító gombok színének beállítása
                    if (MainActivity.controller.getAppTéma() == 0) {
                        buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    } else {
                        buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    }

                    MainActivity.toolbar.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    MainActivity.navigationView.setItemTextColor(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));
                    MainActivity.navigationView.setItemIconTintList(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));

                    Toast.makeText(getActivity(), "Gombok színe sárga.", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Gombok színének beállítása nem sikerült!", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonLila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gombTéma = 4;

                try {
                    MainActivity.controller.delete_téma();
                    MainActivity.controller.insert_téma(appTéma, gombTéma, egyébTéma, egyébTéma1, egyébTéma2, egyébTéma3);

                    gombSzínePiros = 121;
                    gombSzíneZöld = 45;
                    gombSzíneKék = 121;

                    //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
                    buttonLila.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonLila.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    buttonZöld.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonBarna.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonBarna.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    //az app megjelenését beállító gombok színének beállítása
                    if (MainActivity.controller.getAppTéma() == 0) {
                        buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    } else {
                        buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    }

                    MainActivity.toolbar.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    MainActivity.navigationView.setItemTextColor(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));
                    MainActivity.navigationView.setItemIconTintList(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));

                    Toast.makeText(getActivity(), "Gombok színe lila.", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Gombok színének beállítása nem sikerült!", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonBarna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gombTéma = 5;

                try {
                    MainActivity.controller.delete_téma();
                    MainActivity.controller.insert_téma(appTéma, gombTéma, egyébTéma, egyébTéma1, egyébTéma2, egyébTéma3);

                    gombSzínePiros = 151;
                    gombSzíneZöld = 71;
                    gombSzíneKék = 51;

                    //a gomb színét meghatározó gombok színének beállítása (ezt követi majd a témát meghatározó gombok színének beállítása)
                    buttonBarna.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonBarna.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    buttonZöld.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonZöld.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonKék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonKék.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonPiros.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonPiros.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonSárga.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonSárga.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    buttonLila.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    buttonLila.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

                    //az app megjelenését beállító gombok színének beállítása
                    if (MainActivity.controller.getAppTéma() == 0) {
                        buttonVilágos.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonSötét.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    } else {
                        buttonSötét.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                        buttonSötét.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

                        buttonVilágos.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                        buttonVilágos.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                    }

                    MainActivity.toolbar.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                    MainActivity.navigationView.setItemTextColor(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));
                    MainActivity.navigationView.setItemIconTintList(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));

                    Toast.makeText(getActivity(), "Gombok színe barna.", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Gombok színének beállítása nem sikerült!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }
}
