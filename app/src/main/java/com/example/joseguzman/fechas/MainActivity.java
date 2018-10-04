package com.example.joseguzman.fechas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText etFechaNacimiento;
    Button btnBuscarDia;
    TextView tvDiaSemNac;
    SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatodia = new SimpleDateFormat("EEEE");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFechaNacimiento = (EditText) findViewById(R.id.etFechaNacimiento);
        btnBuscarDia = (Button) findViewById(R.id.btnBuscarDia);
        tvDiaSemNac = (TextView) findViewById(R.id.tvDiaSemNac);

        btnBuscarDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fechaStr = etFechaNacimiento.getText().toString();

                try{
                    formatofecha.setLenient(false);
                    Date fecha = formatofecha.parse(fechaStr);
                    tvDiaSemNac.setText(formatodia.format(fecha));
                }catch (Exception e){
                    etFechaNacimiento.setError("El formato de fecha debe ser dd/mm/aaaa");
                    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
