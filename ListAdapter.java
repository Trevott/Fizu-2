package com.trevott.fizu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Munkanap> recordList;
    private int háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék, szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék;
    private int gombSzínePiros, gombSzíneZöld, gombSzíneKék;

    public ListAdapter(Context context, int layout, ArrayList<Munkanap> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;

        //belépés után legyen eleve kiválasztott a megfelelő gomb
        try {
            if (MainActivity.controller.getAppTéma() == 0) {
                háttérSzínePiros = 255;
                háttérSzíneZöld = 255;
                háttérSzíneKék = 255;
                szövegSzínePiros = 0;
                szövegSzíneZöld = 0;
                szövegSzíneKék = 0;
            } else if (MainActivity.controller.getAppTéma() == 1) {
                háttérSzínePiros = 0;
                háttérSzíneZöld = 0;
                háttérSzíneKék = 0;
                szövegSzínePiros = 255;
                szövegSzíneZöld = 255;
                szövegSzíneKék = 255;
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
            } else if (MainActivity.controller.getGombTéma() == 1) {
                gombSzínePiros = 0;
                gombSzíneZöld = 131;
                gombSzíneKék = 161;
            } else if (MainActivity.controller.getGombTéma() == 2) {
                gombSzínePiros = 171;
                gombSzíneZöld = 15;
                gombSzíneKék = 15;
            } else if (MainActivity.controller.getGombTéma() == 3) {
                gombSzínePiros = 181;
                gombSzíneZöld = 181;
                gombSzíneKék = 0;
            } else if (MainActivity.controller.getGombTéma() == 4) {
                gombSzínePiros = 121;
                gombSzíneZöld = 45;
                gombSzíneKék = 121;
            } else if (MainActivity.controller.getGombTéma() == 5) {
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

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        TextView text1, text2, text3, text4;
        TextView text1_1, text2_2, text3_3, text4_4;
        CardView cardView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            row = inflater.inflate(layout, null);
            holder.text1 = row.findViewById(R.id.textDátum);
            holder.text2 = row.findViewById(R.id.textNapTípus);
            holder.text3 = row.findViewById(R.id.textMunkaidő);
            holder.text4 = row.findViewById(R.id.textTúlóra);
            holder.text1_1 = row.findViewById(R.id.textDátumText);
            holder.text2_2 = row.findViewById(R.id.textNapTípusText);
            holder.text3_3 = row.findViewById(R.id.textMunkaidőText);
            holder.text4_4 = row.findViewById(R.id.textTúlóraText);
            holder.cardView = row.findViewById(R.id.cardView);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        Munkanap munkanap = recordList.get(i);
        if(munkanap.getNapTípusa().equals("Munkanap")) {
            holder.text1_1.setText("Dátum: ");
            holder.text1_1.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text1.setText(munkanap.getÉv() + "." + munkanap.getHónap() + "." + munkanap.getNap() + "  " + munkanap.getNapNeve());
            holder.text1.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text2_2.setText("Típus: ");
            holder.text2_2.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text2.setText(munkanap.getNapTípusa());
            holder.text2.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text3_3.setText("Munkaidő: ");
            holder.text3_3.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text3.setText(munkanap.getNapKezdete() + " - " + munkanap.getNapVége());
            holder.text3.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text4_4.setText("Melyből túlóra: ");
            holder.text4_4.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text4.setText(munkanap.getTúlóraKezdete() + " - " + munkanap.getTúlóraVége());
            holder.text4.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.cardView.setCardBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
        } else if (munkanap.getNapTípusa().equals("Hónap")) {
            if (munkanap.getHónap().equals("1")) {
                holder.text1_1.setText("Január");  //a munkanap kezdetének a helyére mentettem el a hónapok esetén a lista elrejtés vagy éppen a mutatás
            } else if (munkanap.getHónap().equals("2")) {
                holder.text1_1.setText("Február"); // esetén mutatandó le vagy fel nyilat és ezeket most berakom a hónap neve mellé
            } else if (munkanap.getHónap().equals("3")) {
                holder.text1_1.setText("Március");
            } else if (munkanap.getHónap().equals("4")) {
                holder.text1_1.setText("Április");
            } else if (munkanap.getHónap().equals("5")) {
                holder.text1_1.setText("Május");
            } else if (munkanap.getHónap().equals("6")) {
                holder.text1_1.setText("Június");
            } else if (munkanap.getHónap().equals("7")) {
                holder.text1_1.setText("Július");
            } else if (munkanap.getHónap().equals("8")) {
                holder.text1_1.setText("Augusztus");
            } else if (munkanap.getHónap().equals("9")) {
                holder.text1_1.setText("Szeptember");
            } else if (munkanap.getHónap().equals("10")) {
                holder.text1_1.setText("Október");
            } else if (munkanap.getHónap().equals("11")) {
                holder.text1_1.setText("November");
            } else if (munkanap.getHónap().equals("12")) {
                holder.text1_1.setText("December");
            }
            holder.text1_1.setTextColor(Color.WHITE);
            holder.text1.setTextColor(Color.BLACK);
            holder.text1.setText(munkanap.getNapKezdete());
            holder.text2_2.setText("");
            holder.text2.setText("");
            holder.text3_3.setText("Várható fizetés: ");
            holder.text3_3.setTextColor(Color.WHITE);
            holder.text3.setText(munkanap.getFizetés() + " Ft");
            holder.text3.setTextColor(Color.WHITE);
            holder.text4_4.setText("Munkaórák: ");
            holder.text4_4.setTextColor(Color.WHITE);
            holder.text4.setText(munkanap.getMunkaÓra());
            holder.text4.setTextColor(Color.WHITE);
            holder.text4.setTextColor(Color.WHITE);
            holder.cardView.setCardBackgroundColor(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék));
        } else if (munkanap.getNapTípusa().equals("Év")) {
            int gombPiros = 0;
            int gombZöld = 0;
            int gombKék = 0;              //szeretném, hogy az év sora a munkanaplistában legyen sötétebb ezért határozok meg új színeket
            if(gombSzínePiros>=50){gombPiros=gombSzínePiros-50;}
            if(gombSzíneZöld>=50){gombZöld=gombSzíneZöld-50;}
            if(gombSzíneKék>=50){gombKék=gombSzíneKék-50;}
            holder.text1_1.setText(munkanap.getÉv());
            holder.text1_1.setTextColor(Color.WHITE);
            holder.text1.setText("");
            holder.text2_2.setText("");
            holder.text2.setText("");
            holder.text3_3.setText("Eddig felhasznált szabadság: " + munkanap.getNap());
            holder.text3_3.setTextColor(Color.WHITE);
            holder.text3.setText("");
            holder.text4_4.setText("");
            holder.text4.setText("");
            if(gombSzínePiros>=50){gombPiros=gombSzínePiros-50;}
            if(gombSzínePiros>=50){gombPiros=gombSzínePiros-50;}
            if(gombSzínePiros>=50){gombPiros=gombSzínePiros-50;}
            holder.cardView.setCardBackgroundColor(Color.rgb(gombPiros, gombZöld, gombKék));
        } else if (munkanap.getNapTípusa().equals("Szabadság") || munkanap.getNapTípusa().equals("Fizetett ünnep") || munkanap.getNapTípusa().equals("Táppénz")) {
            holder.text1_1.setText("Dátum: ");
            holder.text1_1.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text1.setText(munkanap.getÉv() + "." + munkanap.getHónap() + "." + munkanap.getNap() + "  " + munkanap.getNapNeve());
            holder.text1.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text2_2.setText("Típus: ");
            holder.text2_2.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text2.setText(munkanap.getNapTípusa());
            holder.text2.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
            holder.text3_3.setText("Munkaidő: ");
            holder.text3_3.setTextColor(Color.GRAY);
            holder.text3.setText("");
            holder.text4_4.setText("Melyből túlóra: ");
            holder.text4_4.setTextColor(Color.GRAY);
            holder.text4.setText("");
            holder.cardView.setCardBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
        }

        return row;
    }
}
