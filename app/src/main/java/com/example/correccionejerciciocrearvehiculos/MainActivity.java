package com.example.correccionejerciciocrearvehiculos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.correccionejerciciocrearvehiculos.modelos.BiciModel;
import com.example.correccionejerciciocrearvehiculos.modelos.CocheModel;
import com.example.correccionejerciciocrearvehiculos.modelos.MotoModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Variables para la vista
    private TextView lbCantidadCoches;
    private TextView lbCantidadMotos;
    private TextView lbCantidadBicis;

    private Button btnCrearCoche;
    private Button btnCrearMoto;
    private Button btnCrearBici;


    //Variables para la logica
    private ArrayList<CocheModel> listaCoches;
    private ArrayList<MotoModel> listaMotos;
    private ArrayList<BiciModel> listaBicis;


    //ResultLaunchers
    private ActivityResultLauncher<Intent> launcherCrearCoches;
    private ActivityResultLauncher<Intent> launcherCrearMotos;
    private ActivityResultLauncher<Intent> launcherCrearBicis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaDatos();
        inicializaLaunchers();

        btnCrearCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearCoches.launch(new Intent(MainActivity.this, CrearCocheActivity.class));
            }
        });

        btnCrearBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearBicis.launch(new Intent(MainActivity.this, CrearBiciActivity.class));
            }
        });

        btnCrearMoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearMotos.launch(new Intent(MainActivity.this, CrearMotoActivity.class));
            }
        });
    }

    private void inicializaLaunchers() {
        launcherCrearCoches = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                CocheModel coche = (CocheModel) result.getData().getExtras().getSerializable("COCHE");
                                if (coche != null){
                                    listaCoches.add(coche);
                                    lbCantidadCoches.setText("Coches: " + listaCoches.size());
                                }else{
                                    Toast.makeText(MainActivity.this, "No a llegado ningun coche", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "VENTANA CANCELADA", Toast.LENGTH_SHORT).show();
                        }
                        }
                }
        );

        launcherCrearBicis = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                BiciModel bici = (BiciModel) result.getData().getExtras().getSerializable("BICIS");
                                if (bici != null){
                                    listaBicis.add(bici);
                                    lbCantidadBicis.setText("Bicis: " + listaBicis.size());
                                }else{
                                    Toast.makeText(MainActivity.this, "No a llegado ninguna bici", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "VENTANA CANCELADA", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        launcherCrearMotos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                MotoModel moto = (MotoModel) result.getData().getExtras().getSerializable("MOTO");
                                if (moto != null){
                                    listaMotos.add(moto);
                                    lbCantidadMotos.setText("Motos: " + listaMotos.size());
                                }else{
                                    Toast.makeText(MainActivity.this, "No a llegado ninguna moto", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "VENTANA CANCELADA", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void inicializaDatos() {
        lbCantidadCoches = findViewById(R.id.lbCantidadCochesMain);
        lbCantidadMotos = findViewById(R.id.lbCantidadMotosMain);
        lbCantidadBicis = findViewById(R.id.lbCantidadBicisMain);
        btnCrearCoche = findViewById(R.id.btnCrearCocheMain);
        btnCrearMoto = findViewById(R.id.btnCrearMotoMain);
        btnCrearBici = findViewById(R.id.btnCrearBiciMain);
        listaCoches = new ArrayList<>();
        listaMotos = new ArrayList<>();
        listaBicis = new ArrayList<>();

    }
}