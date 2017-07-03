package com.example.michen.reproductor.newproyect;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michen.reproductor.R;

import java.io.File;
import java.util.ArrayList;

public class VisualPlayer extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    SeekBar sb;
    ListView lv;
    Button play, next, previus;
    TextView name, totalprogress, timeprogress;
    String[] items;
    PLaylist pl;
    public Boolean siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_player);

        final Context c = getApplicationContext();

        sb = (SeekBar) findViewById(R.id.seekBar2);
        lv = (ListView) findViewById(R.id.idList);
        play = (Button) findViewById(R.id.idPlay);
        next = (Button) findViewById(R.id.idNext);
        previus = (Button) findViewById(R.id.idPrev);
        name = (TextView) findViewById(R.id.idName);
        totalprogress = (TextView) findViewById(R.id.idTvTotalDuration);
        timeprogress = (TextView) findViewById(R.id.idTimeProgress);

        play.setOnClickListener(this);
        next.setOnClickListener(this);
        previus.setOnClickListener(this);
        lv.setOnItemClickListener(this);

        final ArrayList<File> listsong = (ArrayList<File>) getIntent().getSerializableExtra("listsong");
        items = new String[listsong.size()];
        for (int i = 0; i < listsong.size(); i++) {
            items[i] = listsong.get(i).getName().toString().replace(".mp3", "").replace(".wav", "").toLowerCase();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getApplicationContext(), R.layout.canciones, R.id.textView, items);
        lv.setAdapter(adapter);

        pl = new PLaylist(listsong, c);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {//manejo de la barra
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeprogress.setText(DateUtils.formatElapsedTime(progress / 1000));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pl.PLseekbar(seekBar.getProgress());

            }
        });


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.idPlay:
                if (pl.PLisplaying()) {
                    play.setText("PLAY");
                    pl.PLpauseMusic();
                }
                if (!pl.PLisplaying()) {
                    pl.play();
                    play.setText("PAUSE");
                    new progressbar().execute();
                }

                new progressbar().execute();
                break;
            case R.id.idNext:
                pl.next();
                new progressbar().execute();
                break;
            case R.id.idPrev:
                pl.previus();
                new progressbar().execute();
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        pl.playSelectPosition(i);
        newprogressbar();
    }

    public void newprogressbar(){
        new progressbar().execute();
    }


    public class progressbar extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sb.setMax(pl.getDuration());
            sb.setProgress(0);
            int duracion = pl.getDuration();
            totalprogress.setText(DateUtils.formatElapsedTime(duracion / 1000));
            name.setText(pl.getName());
            siguiente = false;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            int duraciontotal = pl.getDuration();
            int posicionActual = 0;

            while (posicionActual < duraciontotal) {
                try {
                    posicionActual = pl.getprogress();
                    publishProgress(posicionActual);
                    SystemClock.sleep(60);//ver
                } catch (Exception e) {
                }
            }
            return true;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            sb.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

        }
    }

}
