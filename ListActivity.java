package com.trevott.fizu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ListActivity extends Fragment {

    private Intent ablakFőoldal, ablakOpciók, ablakAppOpciók, ablakSúgó;

    private ArrayList<Munkanap> munkanapTömb;
    private ListAdapter adapter = null;
    private ListView listaMegjelenítő;
    private int háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék, szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék;
    private int gombSzínePiros, gombSzíneZöld, gombSzíneKék;
    private SetTheme téma;

    private ArrayList<String> évek;
    private ArrayList<String> hónapok;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_list, container, false);

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

        if (háttérSzínePiros == 0) {
            root.setBackgroundColor(Color.rgb(50, 50, 50));
        } else {
            root.setBackgroundColor(Color.rgb(200, 200, 200));
        }
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(Color.rgb(gombSzínePiros,gombSzíneZöld,gombSzíneKék)));

        listaMegjelenítő = root.findViewById(R.id.listView);
        listaMegjelenítő.setSelector(new ColorDrawable(Color.rgb(gombSzínePiros, gombSzíneZöld, gombSzíneKék))); //ezzel színezzük kedvünk szerint a kattintott elemet
        munkanapTömb = new ArrayList<>();

        évek = new ArrayList<>();
        hónapok = new ArrayList<>();

        adapter = new ListAdapter(getActivity(), R.layout.row_munkanap, munkanapTömb);
        listaMegjelenítő.setAdapter(adapter);
        //MainActivity.controller.list_all_munkanap1(munkanapLista, munkanapTömb);
        MainActivity.controller.list_all_munkanap1_without_one_month(munkanapTömb, hónapok, évek);

        //munkanapTömb.add("");
        listaMegjelenítő.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long l) {

                if (munkanapTömb.get(position).getNapNeve().equals("NINCS") && munkanapTömb.get(position).getNapTípusa().equals("Év")) {

                } else if (munkanapTömb.get(position).getNapNeve().equals("NINCS") && munkanapTömb.get(position).getNapTípusa().equals("Hónap")) {
                    //amikor a felhasználó egy hónapra nyom hossza, akkor a hónap munkanapjait elrejtjük vagy ha már elrejtettek, akkor újra mutatjuk
                    //ehhez kell egy szűrés: ha a hónapok és évek lista már tartalmazza az adot hónapra vonatkozó adatokat, akkor az a hónap el van rejtve ezért fel kell fedni
                    //és presze ez fordítva is igaz
                    boolean vanMárAListában = false;
                    String lokálisHónap = munkanapTömb.get(position).getHónap();
                    String lokálisÉv = munkanapTömb.get(position).getÉv();

                    for (int z = 0; z < hónapok.size(); z++) {
                        if (z < hónapok.size() && hónapok.get(z).equals(lokálisHónap) && évek.get(z).equals(lokálisÉv)) {
                            vanMárAListában = true;
                            hónapok.remove(z);
                            évek.remove(z);
                        }
                    }

                    if (!vanMárAListában) {
                        hónapok.add(lokálisHónap);
                        évek.add(lokálisÉv);
                    }
                    munkanapTömb.clear();
                    MainActivity.controller.list_all_munkanap1_without_one_month(munkanapTömb, hónapok, évek);

                    adapter.notifyDataSetChanged();

                } else { //ezzel azt érem el, hogy a felhasználó nem tud hónap vagy év kijelzésére szoláló sort törölni, persze ehhez az kell, hogy az sqlitehelper osztályban ezekben az esetekben
                    AlertDialog.Builder figyelmeztetőAblak = new AlertDialog.Builder(getActivity());        //a nap neve a NINCS legyen

                    figyelmeztetőAblak.setTitle("Kívánt művelet:");

                    figyelmeztetőAblak.setMessage("Válassz egy műveletet!").setCancelable(true).setPositiveButton("Mégse", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                        }
                    }).setNegativeButton("Törlés", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Main2Activity.controller.reID_munkanap();
                            munkanapTömb.clear();
                            MainActivity.controller.delete_munkanap(position, hónapok, évek);

                            //a kovetkező két sor felel a teljes lista frissítésért, ez azért kell, hogy az év és a hónap neve melletti adatok is frissüljenek az egyik sor törlésénél
                            //ennek érdekében töröltük a munkanaptömböt
                            //MainActivity.controller.list_all_munkanap1(munkanapLista, munkanapTömb);
                            MainActivity.controller.list_all_munkanap1_without_one_month(munkanapTömb, hónapok, évek);

                            adapter.notifyDataSetChanged();

                            Toast.makeText(getActivity(), "Sikeresen törölted a kiválasztott napot.", Toast.LENGTH_LONG).show();
                        }
                    });

                    AlertDialog alertDialog = figyelmeztetőAblak.create();
                    alertDialog.show();
                }
            }
        });

        return root;
    }
}
