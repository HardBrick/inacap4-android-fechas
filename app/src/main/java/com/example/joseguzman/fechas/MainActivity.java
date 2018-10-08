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
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText etFechaNacimiento;
    Button btnBuscarDia;
    TextView tvDiaSemNac;
    TextView tvEdadCompleta;
    TextView tvProximoCumple;
    SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/yyyy"); //SDF formato de fecha dd/mm/aaaa
    SimpleDateFormat formatodia = new SimpleDateFormat("EEEE"); //SDF formato de fecha dia semana (lunes,martes,etc)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFechaNacimiento = (EditText) findViewById(R.id.etFechaNacimiento);
        btnBuscarDia = (Button) findViewById(R.id.btnBuscarDia);
        tvDiaSemNac = (TextView) findViewById(R.id.tvDiaSemNac);
        tvEdadCompleta = (TextView) findViewById(R.id.tvEdadCompleta);
        tvProximoCumple = (TextView) findViewById(R.id.tvProximoCumple);
        btnBuscarDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fechaStr = etFechaNacimiento.getText().toString();


                try{
                    formatofecha.setLenient(false);
                    Date fecha = formatofecha.parse(fechaStr);
                    if(fecha.compareTo(new Date())<0){
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(fecha);
                        tvDiaSemNac.setText("Naciste un día " + formatodia.format(fecha));
                        tvEdadCompleta.setText(obtenerEdadMasDias(cal));
                        tvProximoCumple.setText(proximoCumple(cal));
                    }else{
                        etFechaNacimiento.setError("La fecha ingresada debe ser antes de la fecha actual!");
                        tvProximoCumple.setText("");
                        tvEdadCompleta.setText("");
                        tvDiaSemNac.setText("");
                    }


                }catch (Exception e){
                    etFechaNacimiento.setError("El formato de fecha debe ser dd/mm/aaaa");
                    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String obtenerEdadCompleta(Calendar fecha){
        int mesNac, anoNac, diaNac;
        diaNac = formatofecha.getCalendar().get(Calendar.DAY_OF_MONTH);
        mesNac = formatofecha.getCalendar().get(Calendar.MONTH)+1;
        anoNac = formatofecha.getCalendar().get(Calendar.YEAR);

        int actualMes, actualAno, actualDia;
        Calendar cal = Calendar.getInstance();
        actualDia = cal.get(Calendar.DAY_OF_MONTH);
        actualMes = cal.get(Calendar.MONTH)+1;
        actualAno = cal.get(Calendar.YEAR);

        int edadDia=0, edadAno=0, edadMes=0;

        edadAno = actualAno - anoNac;
        if(mesNac>actualMes){ //Aun no cumple
            edadAno=edadAno-1;
            edadMes = 12 - (mesNac - actualMes);
            edadDia = actualDia;

        }else if(mesNac==actualMes){

            if(diaNac>actualDia){ // Aun no cumple
                edadAno = edadAno-1;
                edadMes=11;
                edadDia = actualDia ; //cal.getActualMaximum(actualMes-1) - (diaNac - actualDia);
            }else{ // Ya cumplió
                edadMes = 0;
                edadDia = actualDia-diaNac;
            }
        }else{ // Ya cumplió
            edadMes = actualMes - mesNac;
            edadDia = actualDia;
        }

        String edadCompleta = "Usted tiene " + edadAno + " año(s), " + edadMes + " mes(es)  y " + edadDia + " dia(s)."  ;
        return (edadCompleta);
    }


    public String obtenerEdadMasDias(Calendar fechaNac){
        int anos = Calendar.getInstance().get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int diaNac = fechaNac.get(Calendar.DAY_OF_YEAR);
        int hoy = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        int dias = 0;

        if (hoy<diaNac){ // aun no cumple
                   anos=anos-1;
                   dias = ( Calendar.getInstance().getMaximum(Calendar.DAY_OF_YEAR) - diaNac ) + hoy;
        }else{
            dias = hoy - diaNac;
        }
        return "Tienes " + anos + " años y " + dias + " dias.";
    }

    public String proximoCumple(Calendar fechaNac){
        int diasProximoCumple=0;
        int diaNac = fechaNac.get(Calendar.DAY_OF_YEAR);
        int hoy = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        int anoprox=0;
        String proxFecha = "";

        if(hoy<diaNac){ //AUN NO CUMPLE
            diasProximoCumple = diaNac - hoy;

        }else{ //YA CUMPLIO

           String fechaStr = formatofecha.format(Calendar.getInstance().getTime());
           anoprox = Integer.parseInt(fechaStr.substring(fechaStr.length()-1, fechaStr.length()))+1;
           proxFecha = fechaStr.substring(0, fechaStr.length()-1) + anoprox;

           try {
               Date d = formatofecha.parse(proxFecha);
               Calendar cal = Calendar.getInstance();
               cal.setTime(d);

               diasProximoCumple = ( (Calendar.getInstance().getMaximum(Calendar.DAY_OF_YEAR) - hoy) + cal.get(Calendar.DAY_OF_YEAR) )-1;
           }catch (Exception e){
               Log.d("TAG_", e.getMessage());
           }
        }

        return "Faltan " + diasProximoCumple + " dias para tu próximo cumpleaños!" ;
    }



}
