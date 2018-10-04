package com.example.joseguzman.fechas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText etFechaNacimiento;
    Button btnBuscarDia;
    TextView tvDiaSemNac;
    TextView tvSigno;
    SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatodia = new SimpleDateFormat("EEEE");
    SimpleDateFormat formatodiaaño = new SimpleDateFormat("DDD");
    Calendar calendario;
    ArrayList<ArrayList> Signos = new ArrayList<>();
    ArrayList<String> signo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendario = Calendar.getInstance();
        tvSigno = (TextView) findViewById(R.id.tvSigno);
        etFechaNacimiento = (EditText) findViewById(R.id.etFechaNacimiento);
        btnBuscarDia = (Button) findViewById(R.id.btnBuscarDia);
        tvDiaSemNac = (TextView) findViewById(R.id.tvDiaSemNac);

        signo.add("Acuario");
        signo.add("20/01/2000");
        signo.add("18/02/2000");
        Signos.add((ArrayList) signo.clone());
        signo.clear();

        signo.add("Picis");
        signo.add("19/02/2000");
        signo.add("20/03/2000");
        Signos.add((ArrayList) signo.clone());
        signo.clear();

        signo.add("Aries");
        signo.add("21/03/2000");
        signo.add("19/04/2000");
        Signos.add((ArrayList) signo.clone());
        signo.clear();

        signo.add("Tauro");
        signo.add("20/04/2000");
        signo.add("20/05/2000");
        Signos.add((ArrayList) signo.clone());
        signo.clear();

        signo.add("Geminis");
        signo.add("21/05/2000");
        signo.add("20/06/2000");
        Signos.add((ArrayList) signo.clone());
        signo.clear();

        signo.add("Cancer");
        signo.add("21/06/2000");
        signo.add("22/07/2000");
        Signos.add((ArrayList) signo.clone());
        signo.clear();

        btnBuscarDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fechaStr = etFechaNacimiento.getText().toString();

                try{
                    formatofecha.setLenient(false);
                    Date fecha = formatofecha.parse(fechaStr);

                    tvDiaSemNac.setText(formatodia.format(fecha));

                    for (ArrayList<String> s: Signos
                         ) {
                        Integer num1=Integer.parseInt(formatodiaaño.format(formatofecha.parse(s.get(1))));
                        Integer numele = Integer.parseInt(formatodiaaño.format(fecha));
                        Integer num2=Integer.parseInt(formatodiaaño.format(formatofecha.parse(s.get(2))));
                        if (numele>=num1 && numele<=num2) {
                            tvSigno.setText(s.get(0));
                        }else{
                            tvSigno.setText("Signo no registrado");

                        }
                    }

                }catch (Exception e){
                    etFechaNacimiento.setError("El formato de fecha debe ser dd/mm/aaaa");
                    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    Log.d("TAG_","Signos size "+Signos.size());
                    Log.d("TAG_","signo size "+signo.size());
                }
            }
        });
    }

}
