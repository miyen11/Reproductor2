package com.example.michen.reproductor;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnBufferingUpdateListener;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements OnCompletionListener, OnBufferingUpdateListener,MediaPlayer.OnSeekCompleteListener {

    private Button play, pause;
    private MediaPlayer mediaPlayer;
    private ArrayList<File> canciones;
    private int posicion;
    private Uri uri;

    private ListView lv;
    private String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        main m = new main();
        ArrayList<File> songIU = m.song;


        lv =(ListView)findViewById(R.id.listIU);
        items = new String[songIU.size()];
        for (int i = 0; i<canciones.size();i++){

            items[i] = canciones.get(i).getName().toString().replace(".mp3","").replace(".wav","").toLowerCase();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getApplicationContext(),R.layout.canciones,R.id.textView,items);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity( new Intent(getApplicationContext(),reproducir.class)//cambiamos
                        .putExtra("pos",position).putExtra("canciones",canciones));
            }
        });

        }


    public void prepare(){
        Intent i = getIntent();
        Bundle b = i.getExtras();
        canciones=(ArrayList)b.getParcelableArrayList("canciones");
        posicion = (int)b.getInt("pos",0);
        uri = Uri.parse(canciones.get(posicion).toString());
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getBaseContext(),uri);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void playMusic(){
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    public void stopMusic(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

        uri = Uri.parse(canciones.get(posicion+1).toString());
        try {
            mediaPlayer.setDataSource(getBaseContext(),uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {

    }
}
