package com.example.michen.reproductor;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;

public class Main extends AppCompatActivity {

    private ArrayList sog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        searchsong(Environment.getExternalStorageDirectory());



    }

    protected ArrayList<File> searchsong(File root) {
        ArrayList<File>  song= new ArrayList<File>();
        File[] file = root.listFiles();
        for (File li:file ){
            if (li.isDirectory() && !li.isHidden()){
                song.addAll(searchsong(li));
            }else{
                if (li.getName().endsWith(".mp3")|| li.getName().endsWith(".wav")){
                    song.add(li);
                }
            }

        }
        return song;
    }
}

