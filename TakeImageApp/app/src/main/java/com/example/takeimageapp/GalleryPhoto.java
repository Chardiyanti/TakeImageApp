package com.example.takeimageapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;

public class GalleryPhoto extends AppCompatActivity implements
        AdapterView.OnItemClickListener, View.OnClickListener {
    ImageAdapter myimageAdapter;

    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_gallery_photo);

        GridView gridView = (GridView) findViewById((R.id.gridview));
        myimageAdapter = new ImageAdapter(this);
        gridView.setAdapter(myimageAdapter);
        gridView.setOnItemClickListener(this);

        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        String targetPath = ExternalStorageDirectoryPath + "/DCIM/Camera/";
        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_SHORT)
                .show();
        File targetDirector = new File(targetPath);
        File [] files = targetDirector.listFiles();
        for (File file : files) {
            myimageAdapter.add(file.getAbsolutePath());
        }
    }

    @Override
    public void onClick(View v){

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(GalleryPhoto.this, "Gambar no " + position +
                "Dipilih", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), SinglePhoto.class);
        i.putExtra("path", myimageAdapter.itemList.get(position));
        startActivity(i);
    }
}
