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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import Adapter.eventoAdapter;
import DAO.DaoEvento;
import DAO.NetCallback;
import Models.Evento;

import static com.google.android.gms.internal.zzir.runOnUiThread;

public class InvitacionesFragment extends Fragment {

    Button btnVer;
    private ShareDialog shareDialog;
    com.facebook.share.widget.ShareButton btnShare;
    Button btnRechazar;
    List<Evento> eventoList;
    ListView listView;
    List<Evento> listArrayEventos= new ArrayList<Evento>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        shareDialog = new ShareDialog(this);
        View v = inflater.inflate(R.layout.fragment_invitaciones, container, false);


        //btnVer = (Button)v.findViewById(R.id.btnVer);
        //btnShare =(com.facebook.share.widget.ShareButton ) v.findViewById(R.id.shareFB);
        //btnRechazar=(Button) v.findViewById(R.id.btnRechazar);
        listView=(ListView) v.findViewById(R.id.listView);

        Evento evento = new Evento();
        DaoEvento daoevento = new DaoEvento(getContext());
        daoevento.execute("getEventosPublicos", evento, new NetCallback() {
            @Override
            public void onWorkFinish(Object data) {
                 eventoList = (List<Evento>) data;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getContext(), eventoList.get(0).getNombre(), Toast.LENGTH_SHORT).show();
                        listArrayEventos.addAll(eventoList);
                        ArrayAdapter<Evento> listViewAdapter = new eventoAdapter(getActivity(),android.R.layout.simple_list_item_1,listArrayEventos);
                        listView.setAdapter(listViewAdapter);

                    }
                });
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
