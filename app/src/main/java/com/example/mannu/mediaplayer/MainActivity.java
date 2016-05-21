package com.example.mannu.mediaplayer;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

Button gitButton;

class Mp3Filter implements FilenameFilter{
    public boolean accept(File dir, String name){
        return(name.endsWith(".mp3") || name.endsWith(".MP3"));
    }
}


public class MainActivity extends ListActivity {
    private ListView lv;

    private static final String SD_PATH = new String("/sdcard/");

    Button stop;

    private List<String> songs = new ArrayList<>();
    private MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updatePlaylist();


        stop = (Button)findViewById(R.id.buttonStop);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
            }
        });

    }
//
    public void updatePlaylist(){

       // File home = new File(String.valueOf(Environment.getExternalStorageDirectory()));      //This can b used for all files not only sd_card

        File home = new File(SD_PATH);
        if(home.listFiles(new Mp3Filter()).length>0){
            for (File file : home.listFiles(new Mp3Filter()))
                songs.add(file.getName());
            ArrayAdapter<String> songList = new ArrayAdapter<String>(this,R.layout.activity_player,songs);
            setListAdapter(songList);
        }
    }

}
