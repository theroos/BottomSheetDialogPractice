package com.example.theroos.bottomsheetpractice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button main_button;
    private BottomSheetDialog bottomSheetDialog;
    private ImageView mainimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_button = (findViewById(R.id.buttonmain));
        mainimage = (findViewById(R.id.displayimage));
        bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        final View bottomSheetDialogview = getLayoutInflater().inflate(R.layout.bottomsheet_dialog, null);
        bottomSheetDialog.setContentView(bottomSheetDialogview);

        View open_camera_view = bottomSheetDialogview.findViewById(R.id.opencamera_view);
        View open_gallery_view = bottomSheetDialogview.findViewById(R.id.opengallery_view);


        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });

        open_camera_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"It will open the camera",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 100);
                bottomSheetDialog.dismiss();
            }
        });

        open_gallery_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"It will open the gallery",Toast.LENGTH_LONG).show();
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 1);
                bottomSheetDialog.dismiss();
            }
        });

    }

    //OnActivityResult for open gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                Uri imageuri = data.getData();
                mainimage.setImageURI(imageuri);
            }
        } else if (requestCode == 100) {
            if (data != null) {
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                mainimage.setImageBitmap(bitmap);
            }
        }

    }


}

