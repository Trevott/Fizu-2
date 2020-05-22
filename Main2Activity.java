package com.trevott.fizu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;

public class Main2Activity extends Fragment {

    private EditText workStart, workEnd, overtimeStart, overtimeEnd;
    private TextView dateText, workDayText, dayOffText, sickPayText, feastDayText, addText, resultText;
    private int year, month, day;
    private String dayName;
    private String választottTípus = "Munkanap"; //selected item when start the app
    private String dateString;

    private int háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék, szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék;
    private int gombSzínePiros, gombSzíneZöld, gombSzíneKék;
    private SetTheme téma;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main2, container, false);

        téma = new SetTheme();

        //initialize the correct color of background, words and buttons
        háttérSzínePiros = téma.getHáttérSzínePiros();
        háttérSzíneZöld = téma.getHáttérSzíneZöld();
        háttérSzíneKék = téma.getHáttérSzíneKék();
        szövegSzínePiros = téma.getSzövegSzínePiros();
        szövegSzíneZöld = téma.getSzövegSzíneZöld();
        szövegSzíneKék = téma.getSzövegSzíneKék();
        gombSzínePiros = téma.getGombSzínePiros();
        gombSzíneZöld = téma.getGombSzíneZöld();
        gombSzíneKék = téma.getGombSzíneKék();

        root.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
        MainActivity.toolbar.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        MainActivity.navigationView.setItemTextColor(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));
        MainActivity.navigationView.setItemIconTintList(ColorStateList.valueOf(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék)));
        MainActivity.navigationView.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));

        workStart = root.findViewById(R.id.editKezdés);
        workEnd = root.findViewById(R.id.editVége);
        overtimeStart = root.findViewById(R.id.editKezdésTúlóra);
        overtimeEnd = root.findViewById(R.id.editVégeTúlóra);
        dateText = root.findViewById(R.id.textDátum);
        workDayText = root.findViewById(R.id.textMunkanap);
        dayOffText = root.findViewById(R.id.textSzabadság);
        sickPayText =root.findViewById(R.id.textTáppénz);
        feastDayText = root.findViewById(R.id.textFizetettÜnnep);
        addText = root.findViewById(R.id.textHozzáadás);
        resultText = root.findViewById(R.id.textEredmény);

        //set the correct color of backround, words and buttons
        addText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        dateText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        workStart.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        workStart.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        workEnd.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        workEnd.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        overtimeStart.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        overtimeStart.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        overtimeEnd.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        overtimeEnd.setHintTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

        TextView textEgy = root.findViewById(R.id.textOne);
        textEgy.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        TextView textMunkaidő = root.findViewById(R.id.textMunkaidő);
        textMunkaidő.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        TextView textTúlóra = root.findViewById(R.id.textTúlóra);
        textTúlóra.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        TextView elsőKötőjel = root.findViewById(R.id.firstHyphen);
        elsőKötőjel.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        TextView másodikKötőjel = root.findViewById(R.id.secondHyphen);
        másodikKötőjel.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));

        workDayText.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
        dayOffText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        sickPayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        feastDayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));

        //set date text
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        //write the actual salary on the main page
        resultText.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        resultText.setText("Ez évi szabadságok eddig: " + String.valueOf(MainActivity.controller.getSzabiszám(year)) +
                "\n\nE havi fizetés eddig: " + String.valueOf(MainActivity.controller.getHaviFizu(year, month)) + " Ft");

        dateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        dateText.setText(dateString);
        dayName = setDayName(dateString);

        //set day type selector's color
        workDayText.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));                      //first step
        workDayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set background color on this text green
                workDayText.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                //set text color white
                workDayText.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                //set background color on all other texts white
                dayOffText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));
                sickPayText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));
                feastDayText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));

                dayOffText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                sickPayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                feastDayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                választottTípus = "Munkanap";
            }
        });
        dayOffText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set background color on this text green
                dayOffText.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                //set text color white
                dayOffText.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                //set background color on all other texts white
                workDayText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));
                sickPayText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));
                feastDayText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));

                workDayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                sickPayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                feastDayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                választottTípus = "Szabadság";

            }
        });
        sickPayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set background color on this text green
                sickPayText.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                //set text color white
                sickPayText.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                //set background color on all other texts white
                workDayText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));
                dayOffText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));
                feastDayText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));

                workDayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                dayOffText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                feastDayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                választottTípus = "Táppénz";

            }
        });
        feastDayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set background color on this text green
                feastDayText.setBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                //set text color white
                feastDayText.setTextColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
                //set background color on all other texts white
                workDayText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));
                dayOffText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));
                sickPayText.setBackgroundColor(Color.rgb(háttérSzínePiros,háttérSzíneZöld,háttérSzíneKék));

                workDayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                dayOffText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                sickPayText.setTextColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
                választottTípus = "Fizetett ünnep";

            }
        });

        //set date selector
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year1, int month1, int day1) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year1);
                                calendar.set(Calendar.MONTH, month1);
                                calendar.set(Calendar.DAY_OF_MONTH, day1);

                                dateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

                                year = year1;
                                month = month1;
                                day = day1;

                                //get the name of the day
                                dayName = setDayName(dateString);

                                dateText.setText(dateString);
                            }
                        }, year, month, day);

                dialog.show();
            }
        });

        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double munkaKezd = 0.0;
                double munkaVég = 0.0;
                double túlóraKezd = 0.0;
                double túlóraVég = 0.0;

                double fizetés = 0.0;
                double munkaÓra = 0.0;

                try {
                    double órabér = Double.parseDouble(MainActivity.controller.getÓrabér());
                    double munkaviszony = Double.parseDouble(MainActivity.controller.getMunkaviszony());
                    double hétköznapiPótlék = Double.parseDouble(MainActivity.controller.getHétköznapiPótlék());
                    int hétköznapiPótlékKezdete = Integer.parseInt(MainActivity.controller.getHétköznapiKezd());
                    int hétköznapiPótlékVége = Integer.parseInt(MainActivity.controller.getHétköznapiVég());
                    int szombatiPótlékKezdete = Integer.parseInt(MainActivity.controller.getSzombatiKezd());
                    double szombatiPótlék = Double.parseDouble(MainActivity.controller.getSzombatiPótlék());
                    int vasárnapiPótlékKezdete = Integer.parseInt(MainActivity.controller.getVasárnapiKezd());
                    double vasárnapiPótlék = Double.parseDouble(MainActivity.controller.getVasárnapiPótlék());

                    double hétköznapiTúlóra = Double.parseDouble(MainActivity.controller.getHétköznapiTúlóra());
                    double szombatiTúlóra = Double.parseDouble(MainActivity.controller.getSzombatiTúlóra());
                    double vasárnapiTúlóra = Double.parseDouble(MainActivity.controller.getVasárnapiTúlóra());

                    int munkahelyTávolsága = Integer.parseInt(MainActivity.controller.getMunkahelyTávolsága());
                    int útiköltségTámogatás = Integer.parseInt(MainActivity.controller.getÚtiköltség());


                    //külön kezeljük a munkanap időpontjainak és a túlóra időpontjainak megadásához köthető kivételeket, mert nem minden esetben akar megadni az ember túlórát
                    try {
                        if (választottTípus.equals("Munkanap")) {
                            munkaKezd = Double.parseDouble(workStart.getText().toString());      //ebben a négy sorban a leheséges hibákat a legfontosabb kezelni, ezért ezeknek a kezelésére szolgál
                            munkaVég = Double.parseDouble(workEnd.getText().toString());           //a fő try ciklus
                        }
                        if (munkaVég < munkaKezd) {
                            throw new Exception("Hoppá!");
                        }

                        try {
                            túlóraKezd = Double.parseDouble(overtimeStart.getText().toString());
                            túlóraVég = Double.parseDouble(overtimeEnd.getText().toString());

                        } catch (Exception e) {
                            túlóraKezd = 0;
                            túlóraVég = 0;
                        }

                        try {
                            //kiszámoljuk az adott nap óráit, behívjuk az órabért az adatbázisból és ennek a segítségével kiszámoljuk a naphoz tartozó fizetést is, ezután ezen iformációkat is
                            //hozzáadjuk a munkanap adatbázisunkhoz

                            //azért szorzok minden értéket ezerrel, hogy a felhasználó megadhasson tört számokat is
                            //kezdetben 0.01-es szorzással próbálkoztam, de ekkor rendkívűl hosszú tört számokat kaptam egész számok helyett
                            //viszont a 0.001 lecserélése 1000-re megoldotta ezt a problémát, de így az insert_munkanap meghívásánál kell osztanom őket 1000-el

                            //az adott munkanap fizetésének kiszámolása, később ezt adjuk hozzá az adatbázishoz *********************************************************************************************
                            if (választottTípus.equals("Szabadság") || választottTípus.equals("Fizetett ünnep")) {
                                //ha szabi vagy fiz ünnep, vagy táápénz van kijelölve, akkor nem jó ha be van írva a munka kezd és vég
                                //mert talán ekkor nem jó nap típust válszott véletlen a felhasználó

                                fizetés = munkaviszony * órabér;
                            } else if (választottTípus.equals("Táppénz")) {
                                fizetés = munkaviszony * órabér * 0.65;
                            } else if (választottTípus.equals("Munkanap") && (dayName.equals("Hétfő") || dayName.equals("Kedd") || dayName.equals("Szerda") || dayName.equals("Csütörtök"))) {
                                //először az éjféltől a reggeli pótlékos időszak végéig tartó időszakra vonatkozó fizetést számoljuk ki, mert később a regeli időpontot +24-el fogjuk korrigálni
                                for (double i = munkaKezd * 1000 + 1; i <= munkaVég * 1000; i++) {
                                    if (i <= hétköznapiPótlékVége * 1000) {
                                        fizetés = fizetés + órabér * (hétköznapiPótlék / 100);
                                    }
                                }

                                //korrigáljuk az éjfél utáni időpontokat a munkanap a pótlékok és a túlóra esetében is (de túlóránál figyelni kell arra is, hogy ha éjfél után kezdődik a túlóra
                                //akkor a kezdő és a vég pontját is korrigáljuk +24-el, és ha így a túlóra értékei a műszak értékein kívül esnek, akkor baj van
                                if (munkaKezd >= munkaVég) {
                                    munkaVég = munkaVég + 24;
                                }
                                if (hétköznapiPótlékKezdete >= hétköznapiPótlékVége) {
                                    hétköznapiPótlékVége = hétköznapiPótlékVége +24;
                                }
                                if (túlóraKezd != 0 || túlóraVég != 0) {
                                    if (túlóraKezd < munkaKezd) {
                                        túlóraKezd = túlóraKezd +24;
                                    }
                                    if (túlóraKezd > túlóraVég) {
                                        túlóraVég = túlóraVég +24;
                                    }
                                    if (túlóraVég > munkaVég || túlóraKezd < munkaKezd || túlóraKezd > túlóraVég) { //ha a felhasználó a túlórát nem a munkaidőn belül adja meg és stb...
                                        throw new Exception();
                                    }
                                }

                                for (double i = munkaKezd * 1000 + 1; i <= munkaVég * 1000; i++) {
                                    fizetés = fizetés + órabér;
                                    munkaÓra++;

                                    //pótlékok
                                    if (i > hétköznapiPótlékKezdete * 1000 && i <= hétköznapiPótlékVége * 1000) {
                                        fizetés = fizetés + órabér * (hétköznapiPótlék / 100);
                                    }

                                    //túlóra
                                    if (i > túlóraKezd * 1000 && i <= túlóraVég * 1000) {
                                        fizetés = fizetés + órabér * (hétköznapiTúlóra / 100);
                                    }
                                }

                                //korrigálás minden naptípus esetén, kivéve szabi, táppénz, fiz ünnep
                                fizetés = fizetés / 1000;

                                //az esetleges útiköltség támogatás hozzáadása
                                fizetés = fizetés + (munkahelyTávolsága * 2 * útiköltségTámogatás);

                            } else if (választottTípus.equals("Munkanap") && dayName.equals("Péntek")) {
                                //először az éjféltől a reggeli pótlékos időszak végéig tartó időszakra vonatkozó fizetést számoljuk ki, mert később a regeli időpontot +24-el fogjuk korrigálni
                                for (double i = munkaKezd * 1000 + 1; i <= munkaVég * 1000; i++) {
                                    if (i <= hétköznapiPótlékVége * 1000) {
                                        fizetés = fizetés + órabér * (hétköznapiPótlék / 100);
                                    }
                                }

                                //korrigáljuk az éjfél utáni időpontokat a munkanap a pótlékok és a túlóra esetében is (de túlóránál figyelni kell arra is, hogy ha éjfél után kezdődik a túlóra
                                //akkor a kezdő és a vég pontját is korrigáljuk +24-el, és ha így a túlóra értékei a műszak értékein kívül esnek, akkor baj van
                                if (munkaVég <= munkaKezd) {
                                    munkaVég = munkaVég + 24;
                                }
                                if (hétköznapiPótlékKezdete >= hétköznapiPótlékVége) {
                                    hétköznapiPótlékVége = hétköznapiPótlékVége +24;
                                }
                                if (túlóraKezd != 0 || túlóraVég != 0) {
                                    if (túlóraKezd < munkaKezd) {
                                        túlóraKezd = túlóraKezd +24;
                                    }
                                    if (túlóraKezd > túlóraVég) {
                                        túlóraVég = túlóraVég +24;
                                    }
                                }

                                for (double i = munkaKezd * 1000 + 1; i <= munkaVég * 1000; i++) {
                                    //síma órabér és óraszám számolása
                                    fizetés = fizetés + órabér;
                                    munkaÓra++;

                                    //pótlék hozzáadása, de éjfélig a hétközi jár utána pedig esetleg a szombati
                                    if (i <= 24000 && i > hétköznapiPótlékKezdete * 1000 && i <= hétköznapiPótlékVége * 1000) {
                                        fizetés = fizetés + órabér * (hétköznapiPótlék / 100);
                                    }
                                    if (i > 24000 && i > szombatiPótlékKezdete * 1000) {
                                        fizetés = fizetés + órabér * (szombatiPótlék / 100);
                                    }

                                    //túlóra
                                    if (i > túlóraKezd * 1000 && i <= túlóraVég * 1000) {
                                        if (i <= 24000) {
                                            fizetés = fizetés + órabér * (hétköznapiTúlóra / 100);
                                        } else if (i > 24000) {
                                            fizetés = fizetés + órabér * (szombatiTúlóra / 100);
                                        }
                                    }
                                }

                                //korrigálás minden naptípus esetén, kivéve szabi, táppénz, fiz ünnep
                                fizetés = fizetés / 1000;

                                //az esetleges útiköltség támogatás hozzáadása
                                fizetés = fizetés + (munkahelyTávolsága * 2 * útiköltségTámogatás);

                            } else if (választottTípus.equals("Munkanap") && dayName.equals("Szombat")) {
                                if (munkaVég <= munkaKezd) {
                                    munkaVég = munkaVég + 24;
                                }

                                if (túlóraKezd != 0 || túlóraVég != 0) {
                                    if (túlóraKezd < munkaKezd) {
                                        túlóraKezd = túlóraKezd +24;
                                    }
                                    if (túlóraKezd > túlóraVég) {
                                        túlóraVég = túlóraVég +24;
                                    }
                                }

                                for (double i = munkaKezd * 1000 + 1; i <= munkaVég * 1000; i++) {
                                    //síma órabér és óraszám számolása
                                    fizetés = fizetés + órabér;
                                    munkaÓra++;

                                    //pótlék hozzáadása, de éjfélig a szombati jár utána pedig esetleg a vasárnapi
                                    if (i <= 24000 && i > szombatiPótlékKezdete * 1000) {
                                        fizetés = fizetés + órabér * (szombatiPótlék / 100);
                                    }
                                    if (i > 24000 && i > vasárnapiPótlékKezdete * 1000) {
                                        fizetés = fizetés + órabér * (vasárnapiPótlék / 100);
                                    }

                                    //túlóra
                                    if (i > túlóraKezd * 1000 && i <= túlóraVég * 1000) {
                                        if (i <= 24000) {
                                            fizetés = fizetés + órabér * (szombatiTúlóra / 100);
                                        } else if (i > 24000) {
                                            fizetés = fizetés + órabér * (vasárnapiTúlóra / 100);
                                        }
                                    }
                                }

                                //korrigálás minden naptípus esetén, kivéve szabi, táppénz, fiz ünnep
                                fizetés = fizetés / 1000;

                                //az esetleges útiköltség támogatás hozzáadása
                                fizetés = fizetés + (munkahelyTávolsága * 2 * útiköltségTámogatás);

                            } else if (választottTípus.equals("Munkanap") && dayName.equals("Vasárnap")) {
                                if (munkaVég <= munkaKezd) {
                                    munkaVég = munkaVég + 24;
                                }
                                if (hétköznapiPótlékKezdete >= hétköznapiPótlékVége) {
                                    hétköznapiPótlékVége = hétköznapiPótlékVége +24;
                                }
                                if (túlóraKezd != 0 || túlóraVég != 0) {
                                    if (túlóraKezd < munkaKezd) {
                                        túlóraKezd = túlóraKezd +24;
                                    }
                                    if (túlóraKezd > túlóraVég) {
                                        túlóraVég = túlóraVég +24;
                                    }
                                }

                                for (double i = munkaKezd * 1000 + 1; i <= munkaVég * 1000; i++) {
                                    //síma órabér és óraszám számolása
                                    fizetés = fizetés + órabér;
                                    munkaÓra++;

                                    //pótlék hozzáadása, de éjfélig a hétközi jár utána pedig esetleg a szombati
                                    if (i <= 24000 && i > vasárnapiPótlékKezdete * 1000) {
                                        fizetés = fizetés + órabér * (vasárnapiPótlék / 100);
                                    }
                                    if (i > 24000 && i > hétköznapiPótlékKezdete * 1000 && i <= hétköznapiPótlékVége * 1000) {
                                        fizetés = fizetés + órabér * (hétköznapiPótlék / 100);
                                    }

                                    //túlóra
                                    if (i > túlóraKezd * 1000 && i <= túlóraVég * 1000) {
                                        if (i <= 24000) {
                                            fizetés = fizetés + órabér * (vasárnapiTúlóra / 100);
                                        } else if (i > 24000) {
                                            fizetés = fizetés + órabér * (hétköznapiTúlóra / 100);
                                        }
                                    }
                                }

                                //korrigálás minden naptípus esetén, kivéve szabi, táppénz, fiz ünnep
                                fizetés = fizetés / 1000;

                                //az esetleges útiköltség támogatás hozzáadása
                                fizetés = fizetés + (munkahelyTávolsága * 2 * útiköltségTámogatás);

                            }

                            //visszaállítjuk a munkanap befejező és a túlóra befejező időpontjait az eredetire, hogy a listában majd ezt lássa a felhasználó
                            if (munkaVég > 24) {
                                munkaVég = munkaVég - 24;
                            }
                            if (túlóraVég > 24) {
                                túlóraVég = túlóraVég - 24;
                            }

                            //tovább konvertáljuk az időpiontokat az időformátumnak megfelelően 12.5 helyett szebben mutat a 12:30
                            String szépMunkaKezd = időpontFormázó(munkaKezd);
                            String szépMunkaVég = időpontFormázó(munkaVég);
                            String szépTúlóraKezd = időpontFormázó(túlóraKezd);
                            String szépTúlóraVég = időpontFormázó(túlóraVég);


                            MainActivity.controller.insert_munkanap(0, year, month + 1, day, dayName, választottTípus, szépMunkaKezd, szépMunkaVég, szépTúlóraKezd, szépTúlóraVég,
                                    String.valueOf(fizetés), String.valueOf(munkaÓra / 1000));

                            Toast.makeText(getActivity(), "Munkanap sikeresen hozzáadva.", Toast.LENGTH_LONG).show();
                        } catch (SQLiteException e) {
                            Toast.makeText(getActivity(), "Adatbázis már létezik.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Hiba a bevitt adatokban! Ellenőrizd őket!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Első alkalommal a pótlékok és egyéb opciók elmentése szükséges!", Toast.LENGTH_LONG).show();
                }

                //a gomb lenyomásakor frissíteni kell a fizetés kijelzésére szolgáló textet
                resultText.setText("Ez évi szabadságok eddig: " + MainActivity.controller.getSzabiszám(year) +
                        "\n\nE havi fizetés eddig: " + MainActivity.controller.getHaviFizu(year, month) + " Ft");
            }
        });

        return root;
    }

    private String setDayName(String dátumStringje) {
        //a dateString utolsó karakterei az adott napnak a neve, ezt kell kinyernem, hogy később hozzá tudjam adni az adatbázishoz
        int hossz = dátumStringje.length();
        String dayNameHun = String.valueOf(dateString.charAt(hossz-7));
        dayNameHun = dayNameHun + String.valueOf(dateString.charAt(hossz-6));
        dayNameHun = dayNameHun + String.valueOf(dateString.charAt(hossz-5));
        dayNameHun = dayNameHun + String.valueOf(dateString.charAt(hossz-4));
        dayNameHun = dayNameHun + String.valueOf(dateString.charAt(hossz-3));
        dayNameHun = dayNameHun + String.valueOf(dateString.charAt(hossz-2));
        dayNameHun = dayNameHun + String.valueOf(dateString.charAt(hossz-1));
        //angol eset kezelése:
        String dayNameEn = String.valueOf(dateString.charAt(0));
        dayNameEn = dayNameEn + String.valueOf(dateString.charAt(1));
        dayNameEn = dayNameEn + String.valueOf(dateString.charAt(2));
        dayNameEn = dayNameEn + String.valueOf(dateString.charAt(3));
        dayNameEn = dayNameEn + String.valueOf(dateString.charAt(4));
        dayNameEn = dayNameEn + String.valueOf(dateString.charAt(5));
        dayNameEn = dayNameEn + String.valueOf(dateString.charAt(6));

        if (dayNameHun.equals(", hétfő") || dayNameEn.equals("Monday,")) {
            return "Hétfő";
        }
        else if(dayNameHun.equals("., kedd") || dayNameEn.equals("Tuesday")) {
            return "Kedd";
        }
        else if(dayNameHun.equals(" szerda") || dayNameEn.equals("Wednesd")) {
            return "Szerda";
        }
        else if(dayNameHun.equals("ütörtök") || dayNameEn.equals("Thursda")) {
            return "Csütörtök";
        }
        else if(dayNameHun.equals(" péntek") || dayNameEn.equals("Friday,")) {
            return "Péntek";
        }
        else if(dayNameHun.equals("szombat") || dayNameEn.equals("Saturda")) {
            return "Szombat";
        }
        else if(dayNameHun.equals("asárnap") || dayNameEn.equals("Sunday,")) {
            return "Vasárnap";
        }
        else {
            return "Kedd";
        }
    }

    private String időpontFormázó(double időpont) {
        //az oszály feladata, hogy a felhasználó által megadott időpontokat (melyek double típusúak nem pedig idő formátumúak), alakítsa át a munkanap listában szépen mutató
        //idő formátummá

        String időpontString = String.valueOf(időpont);
        String újIdőpont = "";

        //az időpontStringet egy új stringgé alakítjuk karakterről-karakterre azért, hogy a stringben lévő pontot kettősponttá alakítsuk (így szebb lesz a munkanaplista)
        int hossz = időpontString.length();

        for (int i = 0; i < hossz; i++) {
            try {
                int utolsóSzám = Integer.parseInt(String.valueOf(időpontString.charAt(i)));
                int utolsóElőttiSzám = 999;

                try {
                    utolsóElőttiSzám = Integer.parseInt(String.valueOf(időpontString.charAt(i - 1)));
                } catch (Exception ex) {

                }

                //át kell alakítani a perceket pl 0.5-ről 30-ra
                if (i > 1) {
                    if (utolsóSzám == 5 && utolsóElőttiSzám == 2) {
                        újIdőpont = újIdőpont + "15";
                    } else if (utolsóSzám == 5 && utolsóElőttiSzám == 7) {
                        újIdőpont = újIdőpont + "45";
                    } else if (utolsóSzám == 0 && utolsóElőttiSzám == 0) {
                        újIdőpont = újIdőpont + "00";
                    } else if (utolsóSzám == 5) {
                        újIdőpont = újIdőpont + "30";
                    }
                } else {
                    //de ha az i kisebb mint 1 akkkor még tuti az óráknál tartunk még
                    újIdőpont = újIdőpont + String.valueOf(időpontString.charAt(i));
                }
            } catch (Exception e) {
                if (String.valueOf(időpontString.charAt(i)).equals(".")) {
                    újIdőpont = újIdőpont + ":";
                }
            }
        }
        //ha kettőspont az újIdőpontunk vége, akkor a felhasználó csak egy egész számot adott meg pl:12 ekkor szebb ha 12:00 jelenik meg a munkanaplistában
        int stringHossza = újIdőpont.length() - 1;
        if (String.valueOf(újIdőpont.charAt(stringHossza)).equals(":")) {
            újIdőpont = újIdőpont + "00";
        }

        return újIdőpont;
    }
}
