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


    public ArrayList<File> song;
    public  int position = 0;
    public Context context;

    public Uri uri;

    mMediaPLayer mmp;


    public PLaylist(ArrayList song, int position, Context context) {
        this.song = song;
        this.position = position;
        this.context = context;
        mmp = new mMediaPLayer(context);

    }

    public void playSelectPosition(int i)//,int pos, ArrayList<File> arrayList)
    {
        position = i;
        uri = Uri.parse(song.get(position).toString());
        mmp.createMediPlayer(uri);
        mmp.playMusic();

    }

    public void previus(){

        if(position -1 <0)
        {
            position = song.size()-1;
        }else {
            position = position -1;
        }
        //mmp.stopMusic();
        uri = Uri.parse(song.get(position).toString());
        mmp.createMediPlayer(uri);
        mmp.playMusic();

    }


    public void next(){
        position = position + 1;
//verificar si esta una playlist y detenerla
        uri = Uri.parse(song.get(position).toString());
        mmp.createMediPlayer(uri);
        mmp.playMusic();


    }

    public void play(){
        uri = Uri.parse(song.get(position).toString());

    }
    public String getName(){

        String name = song.get(position).getName().toString();
        return name;
    }

    public boolean PLisplaying(){
        boolean PLisplaing = false;
       if (mmp.MPisplaying())
       {
           PLisplaing = true;
       }
       return PLisplaing;
    }

    public void strarplay(){
        //if() ver condiciones para que no esten reproduciendo dos canciuones al mismo tiempo
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

    public void PLseekbar(int i) {
          mmp.seekbar(i);
    }
}
