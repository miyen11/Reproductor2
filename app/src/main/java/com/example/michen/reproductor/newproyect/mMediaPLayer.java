package com.example.michen.reproductor.newproyect;

import android.content.Context;
import android.media.MediaPlayer;

import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by cvamedios on 29/06/17.
 */

public class mMediaPLayer implements OnCompletionListener {

    Context context;



    PLaylist pl;
    MediaPlayer mediaPlayer;
    public boolean complet;
    Uri u;

    public mMediaPLayer(Context context ,PLaylist pl) {
        this.context = context;
        this.pl = pl;
        mediaPlayer = new MediaPlayer();
    }

    public MediaPlayer createMediaPlayer(Uri uri) {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer = MediaPlayer.create(context, uri);
            complet = false;
            mediaPlayer.setOnCompletionListener(this);

        }catch (Exception e){

        }
        return mediaPlayer;
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


   @Override
   public void onCompletion(MediaPlayer mp) {
       if (!mediaPlayer.isPlaying())
       {
           complet = true;
       }
       seCompleto();
   }

   public Boolean seCompleto(){
       Boolean a = false;
       if (complet){
           a = true;
       }
       return a;
   }


}
