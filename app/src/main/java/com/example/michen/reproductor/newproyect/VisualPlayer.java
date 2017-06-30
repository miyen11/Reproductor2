package com.example.michen.reproductor.newproyect;

import android.content.Context;
import android.content.Intent;
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
import com.example.michen.reproductor.newproyect.PLaylist;
import com.example.michen.reproductor.reproducir;

import java.io.File;
import java.util.ArrayList;

public class VisualPlayer extends AppCompatActivity implements View.OnClickListener{

    SeekBar sb;
    ListView lv;
    Button play, next,previus;
    TextView name, totalprogress,timeprogress;
    ArrayList mlista;
    String[] items;

    PLaylist pl ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_player);


        sb = (SeekBar)findViewById(R.id.seekBar2);
        lv = (ListView) findViewById(R.id.idList);
        play = (Button) findViewById(R.id.idPlay);
        next =(Button)findViewById(R.id.idNext);
        previus =(Button)findViewById(R.id.idPrev);
        name = (TextView)findViewById(R.id.idName);
        totalprogress =(TextView)findViewById(R.id.idTvTotalDuration);
        timeprogress= (TextView)findViewById(R.id.idTimeProgress);

        play.setOnClickListener(this);
        next.setOnClickListener(this);
        previus.setOnClickListener(this);

        final ArrayList<File> listsong = (ArrayList<File>) getIntent().getSerializableExtra("listsong");
        items = new String[listsong.size()];
        for (int i =0;i<listsong.size();i++)
        {
            items[i] = listsong.get(i).getName().toString().replace(".mp3","").replace(".wav","").toLowerCase();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getApplicationContext(),R.layout.canciones,R.id.textView,items);
        lv.setAdapter(adapter);
        final Context c = getApplicationContext();

        pl = new PLaylist(listsong,0,c);

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
                //mediaPlayer.seekTo(seekBar.getProgress());//
                pl.PLseekbar(seekBar.getProgress());

            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pl.playSelectPosition(i);
                new progressbar().execute();
            }
        });
    }



    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.idNext:
                pl.next();
                break;
            case R.id.idPrev:
                pl.previus();
                break;
            case  R.id.idPlay:
                if(pl.PLisplaying()){
                    pl.strarplay();
                }
                break;
        }
    }

    protected void startfromBeginning(){
        //buscar la cacncion actual
    }

    private class progressbar extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sb.setMax(pl.getDuration());
            sb.setProgress(0);
            int duracion = pl.getDuration();
            totalprogress.setText(DateUtils.formatElapsedTime(duracion/1000));
            name.setText(pl.getName());

        }
        @Override
        protected Boolean doInBackground(Void... params) {

            int duraciontotal = pl.getDuration();
            int posicionActual = 0;

            while (posicionActual<duraciontotal){
                try {

                    posicionActual =pl.getprogress();
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
