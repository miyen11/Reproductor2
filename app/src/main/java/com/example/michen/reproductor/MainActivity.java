package com.example.michen.reproductor;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<File> canciones =BuscarCanciones(Environment.getExternalStorageDirectory());

        lv =(ListView)findViewById(R.id.lista);
        items = new String[canciones.size()];
        for (int i = 0; i<canciones.size();i++){

            items[i] = canciones.get(i).getName().toString().replace(".mp3","").replace(".wav","").toLowerCase();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getApplicationContext(),R.layout.canciones,R.id.textView,items);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity( new Intent(getApplicationContext(),reproducir.class)
                        .putExtra("pos",position).putExtra("canciones",canciones));
            }
        });
    }

    public ArrayList<File> BuscarCanciones(File root) {
        ArrayList<File> canciones = new ArrayList<File>();
        File[] archivos = root.listFiles();
        for (File li:archivos)
        {
            if(li.isDirectory() && !li.isHidden())
            {
                canciones.addAll(BuscarCanciones(li));// se usa recursividad
            }else {
                if(li.getName().endsWith(".mp3") || li.getName().endsWith(".wav") ){
                    canciones.add(li);
                }
            }
        }
        return canciones;
    }
}
