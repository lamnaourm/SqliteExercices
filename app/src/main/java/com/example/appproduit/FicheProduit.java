package com.example.appproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class FicheProduit extends AppCompatActivity {

    Spinner s;
    TextView tlib, tfam, tpachat, tpvente;
    MyDBProduit db;
    ArrayList<Produit> prds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_produit);

        db = new MyDBProduit(this);

        s = findViewById(R.id.spfiche);
        tlib = findViewById(R.id.ficheLibelle);
        tfam = findViewById(R.id.fichefamille);
        tpachat = findViewById(R.id.fichepachat);
        tpvente = findViewById(R.id.fichepvente);

        prds = MyDBProduit.getAllProduits(db.getReadableDatabase());

        ArrayList<String> libs = new ArrayList<>();
        for(Produit p : prds)
            libs.add(String.format("%d - %s",p.getId(),p.getLibelle()));

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,libs);
        s.setAdapter(ad);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                Produit p = prds.get(pos);

                tlib.setText("Libelle : " + p.getLibelle());
                tfam.setText("Famille : " + p.getFamille());
                tpachat.setText("Prix Achat : " + p.getPrixAchat());
                tpvente.setText("Prix Vente : " + p.getPrixVente());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}