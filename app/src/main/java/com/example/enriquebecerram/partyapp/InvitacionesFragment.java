package com.example.enriquebecerram.partyapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.ByteArrayOutputStream;

public class InvitacionesFragment extends Fragment {

    Button btnVer;
    private ShareDialog shareDialog;
    com.facebook.share.widget.ShareButton btnShare;
    Button share2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        shareDialog = new ShareDialog(this);



        View v = inflater.inflate(R.layout.fragment_invitaciones, container, false);

        Button btnVer = (Button)v.findViewById(R.id.btnVer);
        btnShare =(com.facebook.share.widget.ShareButton ) v.findViewById(R.id.shareFB);
        share2=(Button) v.findViewById(R.id.btnRechazar);




        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                //Intent intent = new Intent(getApplicationContext(), Login.class);
                //Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });

        share2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                //ShareLinkContent content = new ShareLinkContent.Builder().build();


                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivityForResult(intent, 1);
                }

            }
        });
        btnShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                //ShareLinkContent content = new ShareLinkContent.Builder().build();


                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivityForResult(intent, 1);
                }



            }
        });

        return v;
    }

    private void sharePhotoToFacebook(Bitmap bitmap){
        Bitmap image = bitmap;

        if (shareDialog.canShow(ShareLinkContent.class)) {
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .setCaption("Latest score")
                    .build();

            SharePhotoContent content= new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            shareDialog.show(content);
            Toast.makeText(getContext(), "you can share photos ", Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(getContext(), "you cannot share photos ", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK){



            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            sharePhotoToFacebook(thumbnail);

        }

    }


}
