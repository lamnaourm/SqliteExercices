package com.example.appproduit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.shape.ShapePath;

import java.util.ArrayList;

public class EditerProduit extends AppCompatActivity {

    MyDBProduit db;
    Spinner s;
    EditText e1,e2,e3,e4;
    ArrayList<Produit> prds;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editer_produit);


        db = new MyDBProduit(this);

        s = findViewById(R.id.spedit);
        e1 = findViewById(R.id.editlib);
        e2 = findViewById(R.id.editfam);
        e3 = findViewById(R.id.editprixa);
        e4 = findViewById(R.id.editprixv);
        b1 = findViewById(R.id.btnupdate);
        b2 = findViewById(R.id.btndelete);

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

                e1.setText(p.getLibelle());
                e2.setText(p.getFamille());
                e3.setText(String.valueOf(p.getPrixAchat()));
                e4.setText(String.valueOf(p.getPrixVente()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EditerProduit.this);
                alert.setTitle("Mise a jour produit");
                alert.setMessage("Voulez-vous modifier ce produit ?");
                
                alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String lib = e1.getText().toString();
                        String fam = e2.getText().toString();
                        double prixachat = Double.valueOf(e3.getText().toString());
                        double prixvente = Double.valueOf(e4.getText().toString());
                        int pos = s.getSelectedItemPosition();
                        int id = prds.get(pos).getId();

                        Produit p = new Produit(id,lib,fam,prixachat, prixvente);

                        if(MyDBProduit.updateProduit(db.getWritableDatabase(),p)!=-1)
                            Toast.makeText(EditerProduit.this, "Modification effectue", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(EditerProduit.this, "Modification echoue", Toast.LENGTH_SHORT).show();


                    }
                });
                
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditerProduit.this, "Modification Annulee", Toast.LENGTH_SHORT).show();
                    }
                });
                                
                alert.show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EditerProduit.this);
                alert.setTitle("Suppression produit");
                alert.setMessage("Voulez-vous supprimer ce produit ?");

                alert.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int pos = s.getSelectedItemPosition();
                        int id = prds.get(pos).getId();

                        if(MyDBProduit.deleteProduit(db.getWritableDatabase(),id)!=-1)
                            Toast.makeText(EditerProduit.this, "Suppresion effectue",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EditerProduit.this, "Suppresion echoue",Toast.LENGTH_LONG).show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditerProduit.this,"Suppression annule",Toast.LENGTH_LONG).show();
                    }
                });


                alert.show();
            }
        });
    }
}