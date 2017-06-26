package com.example.michen.reproductor;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class reproducir extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    ArrayList<File> canciones;
    int posicion;
    Uri uri;
    String aux = "";
    Button dere , izqui, play, strop, pause, btlista;
    SeekBar sb, sbVolumen;
    TextView nombre , tiempotrascurrido, tiempototal;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproducir);


        nombre=(TextView)findViewById(R.id.tvnombre);
        tiempotrascurrido= (TextView)findViewById(R.id.tiempo1);
        tiempototal = (TextView)findViewById(R.id.tiempo2);

        dere = (Button)findViewById(R.id.derecha);
        izqui =(Button)findViewById(R.id.izquierda);
        play = (Button)findViewById(R.id.play);

        pause =(Button)findViewById(R.id.pausa);
        btlista=(Button)findViewById(R.id.btlist);

        dere.setOnClickListener(this);
        izqui.setOnClickListener(this);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        btlista.setOnClickListener(this);

        sb = (SeekBar)findViewById(R.id.seekBar);
        sbVolumen=(SeekBar)findViewById(R.id.seekBarVolumen);


        Inicio();
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {//manejo de la barra
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tiempotrascurrido.setText(DateUtils.formatElapsedTime(progress / 1000));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.play:
                mediaPlayer.start();
                break;
            case R.id.izquierda:
                anterior();
                break;
            case R.id.derecha:
                siguiente();
                break;
            case R.id.pausa:
                mediaPlayer.pause();
                break;
            case R.id.lista:
                mediaPlayer.stop();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

        }
    }

    public void Inicio()
    {
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        try {
            Intent intent = getIntent();
            Bundle b = intent.getExtras();
            canciones=(ArrayList)b.getParcelableArrayList("canciones");
            posicion =(int)b.getInt("pos",0);
            uri = Uri.parse(canciones.get(posicion).toString());
            nombre.setText(canciones.get(posicion).getName().toString());
            mediaPlayer = MediaPlayer.create(getApplication(),uri);
            new mAsyncTask().execute();
            mediaPlayer.start();
            volumen();
        }catch (Exception  e){
        }
    }

    public class mAsyncTask extends AsyncTask<Void,Integer,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sb.setMax(mediaPlayer.getDuration());
            sb.setProgress(0);
            int duracion = mediaPlayer.getDuration();
            tiempototal.setText(DateUtils.formatElapsedTime(duracion/1000));

        }
        @Override
        protected Boolean doInBackground(Void... params) {

            int duraciontotal = mediaPlayer.getDuration();
            int posicionActual = 0;

            while (posicionActual<duraciontotal){
                try {

                    posicionActual = mediaPlayer.getCurrentPosition();
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
            if (aBoolean)
            {
                Toast.makeText(getBaseContext(),"exito",Toast.LENGTH_LONG).show();
            }
            siguiente();
        }



    }//falta ver la parte del tiempo

    private void anterior(){
        mediaPlayer.stop();

        if(posicion-1 < 0){
            posicion = canciones.size()-1;
        }else {
            posicion = posicion -1;
        }
        nombre.setText(canciones.get(posicion).getName().toString());
        uri = Uri.parse(canciones.get(posicion).toString());
        mediaPlayer =  MediaPlayer.create(getApplication(),uri);
        new mAsyncTask().execute();
        mediaPlayer.start();
    }

    private void siguiente(){
        mediaPlayer.stop();
        posicion =(posicion +1)% canciones.size();
        nombre.setText(canciones.get(posicion).getName().toString());
        uri = Uri.parse(canciones.get(posicion).toString());
        mediaPlayer = MediaPlayer.create(getApplication(),uri);
        new mAsyncTask().execute();
        mediaPlayer.start();
    }

    public void volumen(){
        try {
            sbVolumen = (SeekBar)findViewById(R.id.seekBarVolumen);
            audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            sbVolumen.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));// Volumen maximo que soporta el telefono
            sbVolumen.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));//posicion del volumen que se ha dejado el telefono
            sbVolumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }



    }




}
