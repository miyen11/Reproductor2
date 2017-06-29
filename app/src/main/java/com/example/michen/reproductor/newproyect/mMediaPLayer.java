package com.example.michen.reproductor.newproyect;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Created by cvamedios on 29/06/17.
 */

public class mMediaPLayer {


    MediaPlayer mediaPlayer;


    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void playMusic()
    {
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    public void stopMusic(){
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
    }

    public int getProgress(){
        int pro = mediaPlayer.getCurrentPosition();
        return pro;
    }

    public int getDuration()
    {
        int duration = mediaPlayer.getDuration();
        return duration;
    }

    public void createMediPlayer(Context c , Uri u)
    {
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(c,u);
    }


    //falta hacer el puase




}
