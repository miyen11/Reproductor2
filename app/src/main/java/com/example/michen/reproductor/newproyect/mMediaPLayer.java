package com.example.michen.reproductor.newproyect;

import android.content.Context;
import android.media.MediaPlayer;

import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;

/**
 * Created by cvamedios on 29/06/17.
 */

public class mMediaPLayer implements OnCompletionListener {

    Context context;



    MediaPlayer mediaPlayer;

    public mMediaPLayer(Context context) {
        this.context = context;
        mediaPlayer = new MediaPlayer();
    }

    public void createMediaPlayer(Uri uri) {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer = MediaPlayer.create(context, uri);
            mediaPlayer.isLooping();
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

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }



    //falta hacer el puase




}
