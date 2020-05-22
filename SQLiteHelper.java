package com.trevott.fizu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "ADATBÁZIS.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE MUNKANAP( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MYID INTEGER," +
                "ÉV INTEGER," +
                "HÓNAP INTEGER," +
                "NAP INTEGER," +
                "NAPNEVE TEXT," +
                "VÁLASZTOTTTÍPUS TEXT," +
                "NAPKEZDETE TEXT," +
                "NAPVÉGE TEXT," +
                "TÚLÓRAKEZDETE TEXT," +
                "TÚLÓRAVÉGE TEXT," +
                "FIZETES TEXT," +
                "MUNKAORA TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE OPCIOK( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MUNKAVISZONY INTEGER," +
                "ORABER INTEGER," +
                "HETKOZNAPIKEZDOIDO INTEGER," +
                "HETKOZNAPIPOTLEK INTEGER," +
                "HETKOZNAPIVEGE INTEGER," +
                "SZOMBATIKEZDOIDO INTEGER," +
                "SZOMBATIPOTLEK INTEGER," +
                "VASARNAPIKEZDOIDO INTEGER," +
                "VASARNAPIPOTLEK INTEGER," +
                "HETKOZNAPITULORAPOTLEK INTEGER," +
                "SZOMBATITULORAPOTLEK INTEGER," +
                "VASARNAPITULORAPOTLEK INTEGER," +
                "UTIKOLTSEGTAMOGATAS INTEGER," +
                "MUNKAHELYTAVOLSAGA INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE TEMA( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TEXTCOLORRED INTEGER," +
                "TEXTCOLORGREEN INTEGER," +
                "TEXTCOLORBLUE INTEGER," +
                "BACKCOLORRED INTEGER," +
                "BACKCOLORGREEN INTEGER," +
                "BACKCOLORBLUE INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MUNKANAP;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OPCIOK;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TEMA");

        onCreate(sqLiteDatabase);
    }

    public void insert_munkanap(int myID, int év, int hónap, int nap, String napNeve, String választottTípus, String munkaKezde, String munkaVége, String túlóraKezdete, String túlóraVége,
                                String fizetés, String munkaÓra) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MYID", myID);
        contentValues.put("ÉV", év);
        contentValues.put("HÓNAP", hónap);
        contentValues.put("NAP", nap);
        contentValues.put("NAPNEVE", napNeve);
        contentValues.put("VÁLASZTOTTTÍPUS", választottTípus);
        contentValues.put("NAPKEZDETE", munkaKezde);
        contentValues.put("NAPVÉGE", munkaVége);
        contentValues.put("TÚLÓRAKEZDETE", túlóraKezdete);
        contentValues.put("TÚLÓRAVÉGE", túlóraVége);
        contentValues.put("FIZETES", fizetés);
        contentValues.put("MUNKAORA", munkaÓra);

        this.getWritableDatabase().insertOrThrow("MUNKANAP", null, contentValues);
    }

    public void insert_opciók(int munkaviszony, int órabér, int hétköznapiPótlékKezdete, int hétköznapiPótlék, int hétköznapiPótlékVége, int szombatiPótlékKezdete, int szombatiPótlék, int vasárnapiPótlékKezdete,
                              int vasárnapiPótlék, int hétköznapiTúlóraPótlék, int szombatiTúlóraPótlék, int vasárnapiTúlóraPótlék, int útiköltségTámogatás, int munkahelyTávolsága) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MUNKAVISZONY", munkaviszony);
        contentValues.put("ORABER", órabér);
        contentValues.put("HETKOZNAPIKEZDOIDO", hétköznapiPótlékKezdete);
        contentValues.put("HETKOZNAPIPOTLEK", hétköznapiPótlék);
        contentValues.put("HETKOZNAPIVEGE", hétköznapiPótlékVége);
        contentValues.put("SZOMBATIKEZDOIDO", szombatiPótlékKezdete);
        contentValues.put("SZOMBATIPOTLEK", szombatiPótlék);
        contentValues.put("VASARNAPIKEZDOIDO", vasárnapiPótlékKezdete);
        contentValues.put("VASARNAPIPOTLEK", vasárnapiPótlék);
        contentValues.put("HETKOZNAPITULORAPOTLEK", hétköznapiTúlóraPótlék);
        contentValues.put("SZOMBATITULORAPOTLEK", szombatiTúlóraPótlék);
        contentValues.put("VASARNAPITULORAPOTLEK", vasárnapiTúlóraPótlék);
        contentValues.put("UTIKOLTSEGTAMOGATAS", útiköltségTámogatás);
        contentValues.put("MUNKAHELYTAVOLSAGA", munkahelyTávolsága);

        this.getWritableDatabase().insertOrThrow("OPCIOK", null, contentValues);
    }

    public void delete_opciók() {
        this.getWritableDatabase().execSQL("DELETE FROM OPCIOK WHERE ID >= 0;");

    }

    public void insert_téma(int textSzínPiros, int textSzínZöld, int textSzínKék, int háttérSzínPiros, int háttérSzínZöld,  int háttérSzínKék) {
        ContentValues contentValues = new ContentValues();
        //mivel korábban nem gondolkodtram előre és csak a háttér és a betűk színének a beállításához szükséges adatoknak csináltam helyet az adatbázisban, ezért most ezeknek az adatoknak kell új értelmezést adni
        //a textcolorred lesz a téma, ahol a 0 a világos az 1 pedig a sötét
        //a textcolorgreen lesz a gombok színe ahol az 0 a hagyományos zöld, a 1 a kék, a 2 a piros, a 3 a sárga, az 4 a lila és a 5 a barna (ezt később lehet bővíteni, hiszen van még bőven integer...)
        //a textcolorblue lesz a "kapcsolója" annak, hogy a felhasználó az új vagy a régi megjelenésű listát szeretné látni, ahol a 0 a régi az 1 pedig az új nézetnek felel meg
        //marad még 3 érték a táblában, amit a későbbiekben fel lehet használni
        contentValues.put("TEXTCOLORRED", textSzínPiros);
        contentValues.put("TEXTCOLORGREEN", textSzínZöld);
        contentValues.put("TEXTCOLORBLUE", textSzínKék);
        contentValues.put("BACKCOLORRED", háttérSzínPiros);
        contentValues.put("BACKCOLORGREEN", háttérSzínZöld);
        contentValues.put("BACKCOLORBLUE", háttérSzínKék);

        this.getWritableDatabase().insertOrThrow("TEMA", null, contentValues);
    }

    public void delete_téma() {
        this.getWritableDatabase().execSQL("DELETE FROM TEMA WHERE ID >= 0;");
    }

    public int getAppTéma() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT TEXTCOLORRED FROM TEMA", null);
        cursor.moveToFirst();
        int x = cursor.getInt(0);
        cursor.close();
        return x;
    }
    public int getGombTéma() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT TEXTCOLORGREEN FROM TEMA", null);
        cursor.moveToFirst();
        int x = cursor.getInt(0);
        cursor.close();
        return x;
    }
    public int getFizuOldalTéma() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT TEXTCOLORBLUE FROM TEMA", null);
        cursor.moveToFirst();
        int x = cursor.getInt(0);
        cursor.close();
        return x;
    }

    public int getEgyébTéma1() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT BACKCOLORRED FROM TEMA", null);
        cursor.moveToFirst();
        int x = cursor.getInt(0);
        cursor.close();
        return x;
    }
    public int getEgyébTéma2() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT BACKCOLORGREEN FROM TEMA", null);
        cursor.moveToFirst();
        int x = cursor.getInt(0);
        cursor.close();
        return x;
    }
    public int getEgyébTéma3() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT BACKCOLORBLUE FROM TEMA", null);
        cursor.moveToFirst();
        int x = cursor.getInt(0);
        cursor.close();
        return x;
    }

    public void delete_munkanap(int id, ArrayList<String> hónapok, ArrayList<String> évek) {
        int position = -1;
        for (int i = 2050; i  >= 2019; i--) {

            Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE ÉV = '" + i + "'", null);
            if (cursor.moveToFirst()) {
                position++;
            }
            cursor.close();

            for (int j = 12; j >= 1; j--) {
                cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE HÓNAP = '" + j + "' AND ÉV = '" + i + "'", null);
                if (cursor.moveToFirst()) {
                    position++;
                }
                cursor.close();

                cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE ÉV = '" + i + "' AND HÓNAP = '" + j + "' ORDER BY NAP DESC", null);
                while (cursor.moveToNext()) {
                    //most az hónapSzámai és az évSzámai listákbó párosával kinyerem az elemeket és az ezeknek megfelelő hónapok elemeit elrejtem
                    //ehhez deklarálok egy z = 0 változót aminek a segítségével fogok végiglépkedni na hónapok és évek lista elemein
                    //else if ciklus segítségével figyelve arra, hogy a z+ valami nem lehet nagyobb mint a hónapok lista hossza
                    boolean vanAListában = false;

                    for (int z = 0; z < hónapok.size(); z++) {
                        if (z < hónapok.size() && (cursor.getString(2).equals(évek.get(z))) && (cursor.getString(3).equals(hónapok.get(z)))) {
                            vanAListában = true;
                        }
                    }

                    if (!vanAListában) {
                        position++;

                        if (position == id) {
                            this.getWritableDatabase().execSQL("DELETE FROM MUNKANAP WHERE ID = '" + cursor.getString(0) + "';");
                        }
                    }
                }
                cursor.close();
            }
        }
    }

    public int getSzabiszám(int év) {
        int szabi = 0;
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT VÁLASZTOTTTÍPUS FROM MUNKANAP WHERE ÉV = '" + év + "'", null);
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals("Szabadság")) {
                szabi++;
            }
        }
        cursor.close();
        return szabi;
    }

    public int getHaviFizu(int év, int hónap) {
        int fizu = 0;
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT FIZETES FROM MUNKANAP WHERE ÉV = '" + év + "' AND HÓNAP = '" + (hónap + 1) + "'", null);
        while (cursor.moveToNext()) {
            fizu = fizu + cursor.getInt(0);
        }
        cursor.close();
        return fizu;
    }

    //a munkanap tömb azért kell, mert ennek a segítségével tudo a listában majd "átugrani" az év és a hónap kijelzésére szolgáló sorokat
    //ugyanis a munkanap típusa alapján szűrök és ugrom át a nincs típusú sorokat
    public void list_all_munkanap1(ArrayList<String> tömb, ArrayList<Munkanap> munkanapTömb) {     //ez a metódus felel az adatbázisból kinyerni az adatokat és azoknak a segítségével a listviewet feltölteni a megfelelő indormációkkal
        for (int i = 2050; i >= 2019; i--) {

            int szabiSzám = 0;
            Cursor cursor = this.getReadableDatabase().rawQuery("SELECT VÁLASZTOTTTÍPUS FROM MUNKANAP WHERE ÉV = '" + i + "'", null);
            while (cursor.moveToNext()) {
                if (cursor.getString(0).equals("Szabadság")) {
                    szabiSzám++;
                }
            }
            cursor.close();
            //ez a cursor kell azért, hogy csak akkor írja ki a program a listviewbe az év nevét, ha van olyan év az adatbázisban és itt íratjuk a korábban kinyert szabik számát
            cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE ÉV = '" + i + "' ORDER BY ID DESC", null);
            if (cursor.moveToFirst()) {

                munkanapTömb.add(new Munkanap(i, 0, szabiSzám, "NINCS", "Év", "0", "0", "0", "0",
                        0, 0));
                tömb.add("========= " + i + " =========\n\nEddig felhasznált szabdság: " + szabiSzám + "\n\n");
            }

            for (int j = 12; j >= 1; j--) {

                String hónapNeve = "";

                //a hónap nevének meghatározása:
                if (j == 1) {
                    hónapNeve = "Január";
                } else if (j == 2) {
                    hónapNeve = "Február";
                } else if (j == 3) {
                    hónapNeve = "Március";
                } else if (j == 4) {
                    hónapNeve = "Április";
                } else if (j == 5) {
                    hónapNeve = "Május";
                } else if (j == 6) {
                    hónapNeve = "Június";
                } else if (j == 7) {
                    hónapNeve = "Július";
                } else if (j == 8) {
                    hónapNeve = "Augusztus";
                } else if (j == 9) {
                    hónapNeve = "Szeptember";
                } else if (j == 10) {
                    hónapNeve = "Október";
                } else if (j == 11) {
                    hónapNeve = "November";
                } else if (j == 12) {
                    hónapNeve = "December";
                }

                cursor.close();

                double haviFizu = 0.0;
                double haviÓrák = 0.0;
                cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE HÓNAP = '" + j + "' AND ÉV = '" + i + "' ORDER BY ID DESC", null);
                //először kinyerjük a hónapban lévő munkaórákat és fizetést
                while (cursor.moveToNext()) {
                    haviFizu = haviFizu + cursor.getDouble(11);
                    haviÓrák = haviÓrák + cursor.getDouble(12);
                }
                if (cursor.moveToFirst()) {
                    munkanapTömb.add(new Munkanap(i, j, 0, "NINCS", "Hónap", "0", "0", "0", "0", haviFizu, haviÓrák));
                    tömb.add(hónapNeve + "\n\nVárható fizetés: " + haviFizu + " Ft\nMunkaórák:        " + haviÓrák + "\n\n");
                }

                cursor.close();

                cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE ÉV = '" + i + "' AND HÓNAP = '" + j + "' ORDER BY NAP DESC", null);
                while (cursor.moveToNext()) {

                    int év = cursor.getInt(2);
                    int hónap = cursor.getInt(3);
                    int nap = cursor.getInt(4);
                    String napNeve = cursor.getString(5);
                    String napTípusa = cursor.getString(6);
                    String napKezdete = cursor.getString(7);
                    String napVége = cursor.getString(8);
                    String túlóraKezdete = cursor.getString(9);
                    String túlóraVége = cursor.getString(10);
                    double fizetés = cursor.getDouble(11);
                    double munkaÓra = cursor.getDouble(12);
                    munkanapTömb.add(new Munkanap(év, hónap, nap, napNeve, napTípusa, napKezdete, napVége, túlóraKezdete, túlóraVége, fizetés, munkaÓra));
                    tömb.add("Dátum: " + év + "." + hónap + "." + nap + ".  " + napNeve + "\nTípus: " + napTípusa + "\nMunkaidő: " + napKezdete +
                            "-től  " + napVége + "-ig\nMelyből túlóra: " + túlóraKezdete + "-től  " + túlóraVége + "-ig\n");
                }
                cursor.close();
            }
        }
    }

    //ez a metódus felel az adatbázisból kinyerni az adatokat és azoknak a segítségével a listviewet feltölteni a megfelelő indormációkkal
    public void list_all_munkanap1_without_one_month(ArrayList<Munkanap> munkanapTömb, ArrayList<String> hónapok, ArrayList<String> évek) {
        for (int i = 2050; i >= 2019; i--) {

            int szabiSzám = 0;
            Cursor cursor = this.getReadableDatabase().rawQuery("SELECT VÁLASZTOTTTÍPUS FROM MUNKANAP WHERE ÉV = '" + i + "'", null);
            while (cursor.moveToNext()) {
                if (cursor.getString(0).equals("Szabadság")) {
                    szabiSzám++;
                }
            }
            cursor.close();
            //ez a cursor kell azért, hogy csak akkor írja ki a program a listviewbe az év nevét, ha van olyan év az adatbázisban és itt íratjuk a korábban kinyert szabik számát
            cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE ÉV = '" + i + "' ORDER BY ID DESC", null);
            if (cursor.moveToFirst()) {

                munkanapTömb.add(new Munkanap(i, 0, szabiSzám, "NINCS", "Év", "0", "0", "0", "0",
                        0, 0));
            }

            for (int j = 12; j >= 1; j--) {

                String hónapNeve = "";

                //a hónap nevének meghatározása:
                if (j == 1) {
                    hónapNeve = "Január";
                } else if (j == 2) {
                    hónapNeve = "Február";
                } else if (j == 3) {
                    hónapNeve = "Március";
                } else if (j == 4) {
                    hónapNeve = "Április";
                } else if (j == 5) {
                    hónapNeve = "Május";
                } else if (j == 6) {
                    hónapNeve = "Június";
                } else if (j == 7) {
                    hónapNeve = "Július";
                } else if (j == 8) {
                    hónapNeve = "Augusztus";
                } else if (j == 9) {
                    hónapNeve = "Szeptember";
                } else if (j == 10) {
                    hónapNeve = "Október";
                } else if (j == 11) {
                    hónapNeve = "November";
                } else if (j == 12) {
                    hónapNeve = "December";
                }

                cursor.close();

                double haviFizu = 0.0;
                double haviÓrák = 0.0;
                cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE HÓNAP = '" + j + "' AND ÉV = '" + i + "' ORDER BY ID DESC", null);
                //először kinyerjük a hónapban lévő munkaórákat és fizetést
                while (cursor.moveToNext()) {
                    haviFizu = haviFizu + cursor.getDouble(11);
                    haviÓrák = haviÓrák + cursor.getDouble(12);
                }
                if (cursor.moveToFirst()) {
                    boolean vanAListában = false;
                    for (int z = 0; z < hónapok.size(); z++) {
                        if (z < hónapok.size() && (cursor.getString(2).equals(évek.get(z))) && (cursor.getString(3).equals(hónapok.get(z)))) {
                            vanAListában = true;
                        }
                    }

                    if (!vanAListában) {
                        munkanapTömb.add(new Munkanap(i, j, 0, "NINCS", "Hónap", "             Elrejt -",
                                "0", "0", "0", haviFizu, haviÓrák));
                    } else {
                        munkanapTömb.add(new Munkanap(i, j, 0, "NINCS", "Hónap", "             Mutat +",
                                "0", "0", "0", haviFizu, haviÓrák));
                    }
                }

                cursor.close();

                cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE ÉV = '" + i + "' AND HÓNAP = '" + j + "' ORDER BY NAP DESC", null);
                while (cursor.moveToNext()) {
                    //most az hónapSzámai és az évSzámai listákbó párosával kinyerem az elemeket és az ezeknek megfelelő hónapok elemeit elrejtem
                    //ehhez deklarálok egy z = 0 változót aminek a segítségével fogok végiglépkedni na hónapok és évek lista elemein
                    //else if ciklus segítségével figyelve arra, hogy a z+ valami nem lehet nagyobb mint a hónapok lista hossza
                    boolean vanAListában = false;

                    for (int z = 0; z < hónapok.size(); z++) {
                        if (z < hónapok.size() && (cursor.getString(2).equals(évek.get(z))) && (cursor.getString(3).equals(hónapok.get(z)))) {
                            vanAListában = true;
                        }
                    }

                    if (!vanAListában) {
                        int év = cursor.getInt(2);
                        int hónap = cursor.getInt(3);
                        int nap = cursor.getInt(4);
                        String napNeve = cursor.getString(5);
                        String napTípusa = cursor.getString(6);
                        String napKezdete = cursor.getString(7);
                        String napVége = cursor.getString(8);
                        String túlóraKezdete = cursor.getString(9);
                        String túlóraVége = cursor.getString(10);
                        double fizetés = cursor.getDouble(11);
                        double munkaÓra = cursor.getDouble(12);

                        munkanapTömb.add(new Munkanap(év, hónap, nap, napNeve, napTípusa, napKezdete, napVége, túlóraKezdete, túlóraVége, fizetés, munkaÓra));
                    }

                }
                cursor.close();
            }
        }
    }

    /*
    public void reID_munkanap() {  //ezzel az adatbázisunk munkanap táblájának myid-jét "összehozzuk" a listview pozíciókkal és így az új myidsegítségével tudunk törölni
        int newID = -1;
        for (int i = 2050; i  >= 2019; i--) {

            Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE ÉV = '" + i + "'", null);
            if (cursor.moveToFirst()) {
                newID++;
            }

            cursor.close();

            for (int j = 12; j >= 1; j--) {
                cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE HÓNAP = '" + j + "' AND ÉV = '" + i + "'", null);
                if (cursor.moveToFirst()) {
                    newID++;
                }

                cursor.close();

                cursor = this.getReadableDatabase().rawQuery("SELECT * FROM MUNKANAP WHERE ÉV = '" + i + "' AND HÓNAP = '" + j + "' ORDER BY NAP DESC", null);
                while (cursor.moveToNext()) {
                    newID++;

                    this.getWritableDatabase().execSQL("UPDATE MUNKANAP SET MYID = '" + newID + "' WHERE ID = '" + cursor.getString(0) + "';");
                }
                cursor.close();
            }
        }
    }
     */

    public String getMunkaviszony() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT MUNKAVISZONY FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getÓrabér() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT ORABER FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getHétköznapiKezd() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT HETKOZNAPIKEZDOIDO FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getHétköznapiPótlék() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT HETKOZNAPIPOTLEK FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getHétköznapiVég() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT HETKOZNAPIVEGE FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getSzombatiKezd() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT SZOMBATIKEZDOIDO FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getSzombatiPótlék() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT SZOMBATIPOTLEK FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getVasárnapiKezd() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT VASARNAPIKEZDOIDO FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getVasárnapiPótlék() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT VASARNAPIPOTLEK FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getHétköznapiTúlóra() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT HETKOZNAPITULORAPOTLEK FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getSzombatiTúlóra() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT SZOMBATITULORAPOTLEK FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getVasárnapiTúlóra() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT VASARNAPITULORAPOTLEK FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getÚtiköltség() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT UTIKOLTSEGTAMOGATAS FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
    public String getMunkahelyTávolsága() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT MUNKAHELYTAVOLSAGA FROM OPCIOK", null);
        cursor.moveToFirst();
        String x = cursor.getString(0);
        cursor.close();
        return x;
    }
}