package com.trevott.fizu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
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
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class HelperActivity<GoogleSignInClient> extends Fragment {

    private int háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék, szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék;
    private TextView súgóCímke1, súgóCímke2, súgóCímke3, súgóCímke4, súgóCímke5, súgóCímke6;
    private int gombSzínePiros, gombSzíneZöld, gombSzíneKék;
    private SetTheme téma;

    private GoogleSignInClient mGoogleSignInClient;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_helper, container, false);

        téma = new SetTheme();

        súgóCímke1 = root.findViewById(R.id.textSúgó1);
        súgóCímke2 = root.findViewById(R.id.textSúgó2);
        súgóCímke3 = root.findViewById(R.id.textSúgó3);
        súgóCímke4 = root.findViewById(R.id.textSúgó4);
        súgóCímke5 = root.findViewById(R.id.textSúgó5);
        súgóCímke6 = root.findViewById(R.id.textSúgó6);

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

        root.setBackgroundColor(Color.rgb(háttérSzínePiros, háttérSzíneZöld, háttérSzíneKék));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(Color.rgb(gombSzínePiros,gombSzíneZöld,gombSzíneKék)));

        súgóCímke1.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        súgóCímke2.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        súgóCímke3.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        súgóCímke4.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        súgóCímke5.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));
        súgóCímke6.setTextColor(Color.rgb(szövegSzínePiros, szövegSzíneZöld, szövegSzíneKék));

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);

        return root;
    }
}
