package com.example.michen.reproductor.newproyect;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.michen.reproductor.reproducir;

import java.io.File;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by cvamedios on 29/06/17.
 */

public class PLaylist implements MediaPlayer.OnCompletionListener {


    public ArrayList<File> song;
    public  int position = 0;
    public Context context;
    public  VisualPlayer vp;
    public Uri uri;

    mMediaPLayer mmp;

    public PLaylist(ArrayList song, Context context) {
        this.song = song;
        this.context = context;
        this.vp = vp;
        mmp = new mMediaPLayer(context);

    }

    public void playSelectPosition(int i)
    {
        position = i;
        uri = Uri.parse(song.get(position).toString());
        mmp.createMediaPlayer(uri);
        mmp.playMusic();
    }

    public void previus(){
        if(position -1 <0)
        {
            position = song.size()-1;
        }else {
            position = position -1;
        }
        uri = Uri.parse(song.get(position).toString());
        mmp.createMediaPlayer(uri);
        mmp.playMusic();
    }

    public void next(){
        if (position == song.size() - 1) {
            position = 0;
        } else {
            position = position + 1;
        }
        uri = Uri.parse(song.get(position).toString());
        mmp.createMediaPlayer(uri);
        mmp.playMusic();

    }

    public void play(){
        uri = Uri.parse(song.get(position).toString());
        mmp.createMediaPlayer(uri);
        mmp.playMusic();

    }

    public  void PLpauseMusic(){

        mmp.pauseMusic();
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

    public Boolean PLFinished(){
        Boolean var = false;
         if (mmp.finished()){
             var = true;
         }
         return var;
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Toast.makeText(context,"se completo",Toast.LENGTH_LONG).show();
    }
}
