package com.trevott.fizu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.Path;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OptionsActivity extends Fragment {

    private EditText munkaviszony, órabér, hétköznapiPótlékKezdőIdő, hétköznapiPótlék, hétköznapiPótlékVége, szombatiPótlékKezdőIdő, szombatiPótlék, vasárnapiPótlékKezdőIdő, vasárnapiPótlék, hétköznapiTúlóraPótlék,
            szombatiTúlóraPótlék, vasárnapiTúlóraPótlék, útiköltségTámogatás, munkahelyTávolsága;

    private Intent ablakFőoldal, ablakFizu, ablakAppOpciók, ablakSúgó;

    private TextView mentés;
    private TextView text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15, text16, text17, text18, text19;
    private TextView text20, text21, text22, text23, text24, text25;
    private TextView pótlékokText, túlóraText, túlóraText2, egyebekText;

    private int háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék, szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék;
    private int gombSzínePiros, gombSzíneZöld, gombSzíneKék;
    private SetTheme téma;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_options, container, false);

        téma = new SetTheme();

        //belépés után legyen eleve kiválasztott a megfelelő gomb
        háttérSzínePiros = téma.getHáttérSzínePiros();
        háttérSzíneZöld = téma.getHáttérSzíneZöld();
        háttérSzíneKék = téma.getHáttérSzíneKék();
        szövegSzínePiros = téma.getSzövegSzínePiros();
        szövegSzíneZöld = téma.getSzövegSzíneZöld();
        szövegSzíneKék = téma.getSzövegSzíneKék();
        gombSzínePiros = téma.getGombSzínePiros();
        gombSzíneZöld = téma.getGombSzíneZöld();
        gombSzíneKék = téma.getGombSzíneKék();

        munkaviszony = root.findViewById(R.id.editMunkaviszony);
        órabér = root.findViewById(R.id.editÓrabér);
        hétköznapiPótlékKezdőIdő = root.findViewById(R.id.editPótlékIdejeHétköznap);
        hétköznapiPótlék = root.findViewById(R.id.editPótlékHétköznap);
        hétköznapiPótlékVége = root.findViewById(R.id.editPótlékVégeHétköznap);
        szombatiPótlékKezdőIdő = root.findViewById(R.id.editPótlékIdejeSzombat);
        szombatiPótlék = root.findViewById(R.id.editPótlékSzombat);
        vasárnapiPótlékKezdőIdő = root.findViewById(R.id.editPótlékIdejeVasárnap);
        vasárnapiPótlék = root.findViewById(R.id.editPótlékVasárnap);
        hétköznapiTúlóraPótlék = root.findViewById(R.id.editTúlóraPótlékHétköznap);
        szombatiTúlóraPótlék = root.findViewById(R.id.editTúlóraPótlékSzombat);
        vasárnapiTúlóraPótlék = root.findViewById(R.id.editTúlóraPótlékVasárnap);
        útiköltségTámogatás = root.findViewById(R.id.editÚtiköltségTámogatás);
        munkahelyTávolsága = root.findViewById(R.id.editMunkahelyTávolsága);

        mentés = root.findViewById(R.id.textOpciókMentése);

        //a festendő címkék
        pótlékokText = root.findViewById(R.id.textPótlékok);
        túlóraText = root.findViewById(R.id.textPótlékokTúlóra);
        túlóraText2 = root.findViewById(R.id.textPótlékokTúlóra2);
        egyebekText = root.findViewById(R.id.textEgyebek);

        //gombszínek beállítása
        pótlékokText.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        túlóraText.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        túlóraText2.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        egyebekText.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        munkaviszony.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        munkaviszony.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        órabér.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        órabér.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        hétköznapiPótlékKezdőIdő.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        hétköznapiPótlékKezdőIdő.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        hétköznapiPótlék.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        hétköznapiPótlék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        hétköznapiPótlékVége.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        hétköznapiPótlékVége.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        szombatiPótlékKezdőIdő.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        szombatiPótlékKezdőIdő.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        szombatiPótlék.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        szombatiPótlék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        vasárnapiPótlékKezdőIdő.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        vasárnapiPótlékKezdőIdő.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        vasárnapiPótlék.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        vasárnapiPótlék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        hétköznapiTúlóraPótlék.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        hétköznapiTúlóraPótlék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        szombatiTúlóraPótlék.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        szombatiTúlóraPótlék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        vasárnapiTúlóraPótlék.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        vasárnapiTúlóraPótlék.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        útiköltségTámogatás.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        útiköltségTámogatás.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        munkahelyTávolsága.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        munkahelyTávolsága.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        mentés.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

        text1 = root.findViewById(R.id.text1);
        text2 = root.findViewById(R.id.text2);
        text3 = root.findViewById(R.id.text3);
        text4 = root.findViewById(R.id.text4);
        text5 = root.findViewById(R.id.text5);
        text6 = root.findViewById(R.id.text6);
        text7 = root.findViewById(R.id.text7);
        text8 = root.findViewById(R.id.text8);
        text9 = root.findViewById(R.id.text9);
        text10 = root.findViewById(R.id.text10);
        text11 = root.findViewById(R.id.text11);
        text12 = root.findViewById(R.id.text12);
        text13 = root.findViewById(R.id.text13);
        text14 = root.findViewById(R.id.text14);
        text15 = root.findViewById(R.id.text15);
        text16 = root.findViewById(R.id.text16);
        text17 = root.findViewById(R.id.text17);
        text18 = root.findViewById(R.id.text18);
        text19 = root.findViewById(R.id.text19);
        text20 = root.findViewById(R.id.text20);
        text21 = root.findViewById(R.id.text21);
        text22 = root.findViewById(R.id.text22);
        text23 = root.findViewById(R.id.text23);
        text24 = root.findViewById(R.id.text24);
        text25 = root.findViewById(R.id.text25);

        //színek beállítása
        root.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(Color.rgb(gombSzínePiros,gombSzíneZöld,gombSzíneKék)));

        text1.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text2.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text3.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text4.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text5.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text6.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text7.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text8.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text9.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text10.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text11.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text12.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text13.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text14.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text15.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text16.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text17.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text18.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text19.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text20.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text21.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text22.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text23.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text24.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        text25.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));

        //minden edittext-et be kell állítani a felhasználó által korábban beállított értékekre
        try {
            munkaviszony.setText(MainActivity.controller.getMunkaviszony());
            órabér.setText(MainActivity.controller.getÓrabér());
            hétköznapiPótlékKezdőIdő.setText(MainActivity.controller.getHétköznapiKezd());
            hétköznapiPótlék.setText(MainActivity.controller.getHétköznapiPótlék());
            hétköznapiPótlékVége.setText(MainActivity.controller.getHétköznapiVég());
            szombatiPótlékKezdőIdő.setText(MainActivity.controller.getSzombatiKezd());
            szombatiPótlék.setText(MainActivity.controller.getSzombatiPótlék());
            vasárnapiPótlékKezdőIdő.setText(MainActivity.controller.getVasárnapiKezd());
            vasárnapiPótlék.setText(MainActivity.controller.getVasárnapiPótlék());
            hétköznapiTúlóraPótlék.setText(MainActivity.controller.getHétköznapiTúlóra());
            szombatiTúlóraPótlék.setText(MainActivity.controller.getSzombatiTúlóra());
            vasárnapiTúlóraPótlék.setText(MainActivity.controller.getVasárnapiTúlóra());
            útiköltségTámogatás.setText(MainActivity.controller.getÚtiköltség());
            munkahelyTávolsága.setText(MainActivity.controller.getMunkahelyTávolsága());
        } catch (Exception e) {

        }

        mentés.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int munkaviszonyInteger = Integer.parseInt(munkaviszony.getText().toString());
                    int órabérInteger = Integer.parseInt(órabér.getText().toString());
                    int hétköznapiPótlékKezdőidőInteger = Integer.parseInt(hétköznapiPótlékKezdőIdő.getText().toString());
                    int hétköznapiPótlékInteger = Integer.parseInt(hétköznapiPótlék.getText().toString());
                    int hétköznapiPótlékVégeInteger = Integer.parseInt(hétköznapiPótlékVége.getText().toString());
                    int szombatiPótlékKezdőidőInteger = Integer.parseInt(szombatiPótlékKezdőIdő.getText().toString());
                    int szombatiPótlékInteger = Integer.parseInt(szombatiPótlék.getText().toString());
                    int vasárnapiPótlékKezdőidőInteger = Integer.parseInt(vasárnapiPótlékKezdőIdő.getText().toString());
                    int vasárnapiPótlékInteger = Integer.parseInt(vasárnapiPótlék.getText().toString());
                    int hétköznapiTúlóraPótlékInteger = Integer.parseInt(hétköznapiTúlóraPótlék.getText().toString());
                    int szombatiTúlóraPótlékInteger = Integer.parseInt(szombatiTúlóraPótlék.getText().toString());
                    int vasárnapiTúlóraPótlékInteger = Integer.parseInt(vasárnapiTúlóraPótlék.getText().toString());
                    int útiköltségTámogatásInteger = Integer.parseInt(útiköltségTámogatás.getText().toString());
                    int munkahelyTávolságaInteger = Integer.parseInt(munkahelyTávolsága.getText().toString());
                    try {
                        MainActivity.controller.delete_opciók();
                    } catch (Exception e) {

                    }
                    try {
                        MainActivity.controller.insert_opciók(
                                munkaviszonyInteger,
                                órabérInteger,
                                hétköznapiPótlékKezdőidőInteger,
                                hétköznapiPótlékInteger,
                                hétköznapiPótlékVégeInteger,
                                szombatiPótlékKezdőidőInteger,
                                szombatiPótlékInteger,
                                vasárnapiPótlékKezdőidőInteger,
                                vasárnapiPótlékInteger,
                                hétköznapiTúlóraPótlékInteger,
                                szombatiTúlóraPótlékInteger,
                                vasárnapiTúlóraPótlékInteger,
                                útiköltségTámogatásInteger,
                                munkahelyTávolságaInteger);

                        Toast.makeText(getActivity(), "Sikeres mentés!", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Mentés nem sikerült!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Minden mezőnek számot kell tartalmaznia!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }
}
