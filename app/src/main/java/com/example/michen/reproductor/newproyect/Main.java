package com.example.michen.reproductor.newproyect;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.michen.reproductor.R;

import java.io.File;
import java.util.ArrayList;

public class Main extends AppCompatActivity {

     Button GotoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        GotoList =(Button) findViewById(R.id.button) ;
        listsong();
        GotoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),VisualPlayer.class).putExtra("listsong",listsong()));
            }
        });
    }

    protected  ArrayList<File> listsong(){
        final ArrayList<File>  li =searchsong(Environment.getExternalStorageDirectory());
         return li;
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
    } //verificar si anda
}
