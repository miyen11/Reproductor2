package com.example.michen.reproductor.newproyect;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.michen.reproductor.reproducir;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by cvamedios on 29/06/17.
 */

public class PLaylist {
    mMediaPLayer mmp = new mMediaPLayer();
    private ArrayList song;
    private int position ;
    Uri uri;
    private ArrayList<File> manejoList;//entender la logica para ver como ver incluir el arraylista
    private Context context;


    public PLaylist() {
        VisualPlayer vp = new VisualPlayer();
        manejoList = vp.mlista;

    }

    public void playSelectPosition(Context c,int pos,ArrayList<File> arrayList)
    {
        context = c;
        position = pos;
        song = arrayList;
        uri = Uri.parse(song.get(position).toString());
        startfrombeginnin(context,uri);
    }


    public void previus(){

        mmp.stopMusic();
        position = position-1;
        uri = Uri.parse(song.get(position).toString());
        startfrombeginnin(context,uri);

    }


    public void next(){
        mmp.stopMusic();
        position = position+ 1;
        uri = Uri.parse(song.get(position).toString());
        startfrombeginnin(context,uri);
    }

    public void startfrombeginnin(Context c ,Uri u){

        mmp.createMediPlayer(c,u);
        mmp.playMusic();
    }

    public void strarplay(){
        mmp.playMusic();
    }

    public int getDuration(){
       int totalDuration = mmp.getDuration();

        return totalDuration;
    }
    public int getprogress(){
          int progres =mmp.getProgress();
        return progres;
    }

}
