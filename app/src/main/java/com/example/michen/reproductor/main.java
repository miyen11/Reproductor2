package com.example.michen.reproductor;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by cvamedios on 28/06/17.
 */

public class main {


    final ArrayList<File> song = searchsong(Environment.getExternalStorageDirectory());

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
