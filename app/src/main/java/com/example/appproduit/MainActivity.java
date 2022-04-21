package com.example.appproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDBProduit db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        db = new MyDBProduit(this);
//        ArrayList<Produit> prds = new ArrayList<>();
//        prds.add(new Produit(1,"Produit 1","Liquide",12.4,22));
//        prds.add(new Produit(1,"Produit 2","Epicerie",22.4,29));
//        prds.add(new Produit(1,"Produit 3","Liquide",15.7,20));
//        prds.add(new Produit(1,"Produit 4","Epicerie",14.9,22.4));
//        prds.add(new Produit(1,"Produit 5","Liquide",17.4,30));
//
//        for(Produit p : prds)
//            MyDBProduit.insertProduit(db.getWritableDatabase(),p);

    }

    public void acceder(View view) {
        Intent i=null;
        switch (view.getId()){
            case R.id.btn1:
                i=new Intent(this,AllProduits.class);
                break;
            case R.id.btn2:
                i=new Intent(this,FicheProduit.class);
                break;
            case R.id.btn3:
                i=new Intent(this,NouveauProduit.class);
                break;
            case R.id.btn4:
                i=new Intent(this,EditerProduit.class);
                break;
        }
        startActivity(i);
    }
}