package com.example.michen.reproductor.newproyect;

import android.content.Context;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by cvamedios on 29/06/17.
 */

public class mMediaPLayer {


   //ver todos los metodos y excepciones de media player incluidos los metodos de error

    Context context;
    MediaPlayer mediaPlayer;



    public mMediaPLayer(Context context ) {
        this.context = context;
        mediaPlayer = new MediaPlayer();
    }

    public void createMediaPlayer(Uri uri) {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.setDataSource(context,uri);
            mediaPlayer.prepare();

        }catch (Exception e){
        }
    }

    public void playMusic() {
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    public void stopMusic(){
            mediaPlayer.stop();
    }

    public void pauseMusic(){
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public boolean MPisplaying() {
        boolean isplaying = false;
        if (mediaPlayer.isPlaying()){
            isplaying = true;
        }
        return isplaying;
    }

    public int getProgress(){
        int pro = mediaPlayer.getCurrentPosition();
        return pro;
    }

    public int getDuration() {
        int duration = mediaPlayer.getDuration();
        return duration;
    }

    public void seekbar(int m) {
        mediaPlayer.seekTo(m);
    }

    public Boolean finished(){
        Boolean var = false;
        if (!mediaPlayer.isPlaying() && mediaPlayer != null)
        {
            var = true;
        }
        return var;
    }

}
