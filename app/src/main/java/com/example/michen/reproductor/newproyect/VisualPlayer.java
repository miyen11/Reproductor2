package com.example.michen.reproductor.newproyect;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import com.example.michen.reproductor.R;
import com.example.michen.reproductor.reproducir;

import java.io.File;
import java.util.ArrayList;

public class VisualPlayer extends AppCompatActivity implements View.OnClickListener{

    private SeekBar sb;
    ListView lv;
    ArrayList mlista;
    String[] items;

    Main m = new Main();
    PLaylist Playlist = new PLaylist();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_player);

        sb = (SeekBar)findViewById(R.id.seekBar2);
        lv = (ListView) findViewById(R.id.idList);

        final ArrayList<File> listsong = m.listsong();
        items = new String[listsong.size()];
        for (int i =0;i<listsong.size();i++)
        {
            items[i] = listsong.get(i).getName().toString().replace(".mp3","").replace(".wav","").toLowerCase();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getApplicationContext(),R.layout.canciones,R.id.textView,items);
        lv.setAdapter(adapter);

        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Playlist.playSelectPosition(getBaseContext(),position,listsong);
                    }
                });

            }
        });

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.idNext:
                Playlist.next();
                break;
            case R.id.idPrev:
                Playlist.previus();
                break;
            case  R.id.idPlay:
                Playlist.strarplay();
                break;
        }
    }

    protected void startfromBeginning(){
        //buscar la cacncion actual
    }

    private class progressbar extends AsyncTask<Void,Boolean,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sb.setMax(Playlist.getDuration());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Boolean... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }


    }
}
