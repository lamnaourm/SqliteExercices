package com.example.appproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NouveauProduit extends AppCompatActivity {


    EditText e1, e2, e3, e4;
    Button b;
    MyDBProduit db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_produit);

        db = new MyDBProduit(this);

        e1= findViewById(R.id.addlib);
        e2= findViewById(R.id.addfam);
        e3= findViewById(R.id.addprixa);
        e4= findViewById(R.id.addprixv);
        b = findViewById(R.id.btnadd);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().isEmpty()){
                    Toast.makeText(NouveauProduit.this, "Libelle vide", Toast.LENGTH_SHORT).show();
                    e1.requestFocus();
                    return;
                }

                if(e2.getText().toString().isEmpty()){
                    Toast.makeText(NouveauProduit.this, "Famille vide", Toast.LENGTH_SHORT).show();
                    e2.requestFocus();
                    return;
                }

                if(e3.getText().toString().isEmpty()){
                    Toast.makeText(NouveauProduit.this, "Prix achat vide", Toast.LENGTH_SHORT).show();
                    e3.requestFocus();
                    return;
                }

                if(e4.getText().toString().isEmpty()){
                    Toast.makeText(NouveauProduit.this, "Prix vente vide", Toast.LENGTH_SHORT).show();
                    e4.requestFocus();
                    return;
                }

                String lib = e1.getText().toString();
                String fam = e2.getText().toString();
                double achat =Double.parseDouble(e3.getText().toString());
                double vente =Double.parseDouble(e4.getText().toString());

                Produit p = new Produit(1,lib,fam,achat,vente);

                if(MyDBProduit.insertProduit(db.getWritableDatabase(),p)!=-1)
                    Toast.makeText(NouveauProduit.this, "Produit ajoute avec succes", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(NouveauProduit.this, "Ajout produit echoue", Toast.LENGTH_SHORT).show();
            }
        });
    }
}