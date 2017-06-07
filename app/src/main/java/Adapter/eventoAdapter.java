package Adapter;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enriquebecerram.partyapp.MainActivity;
import com.example.enriquebecerram.partyapp.R;

import java.util.List;

import Models.Evento;

/**
 * Created by Ayrton on 06/06/2017.
 */

public class eventoAdapter extends ArrayAdapter<Evento> {

    public eventoAdapter(Context context, int resource, List<Evento> objects) {
        super(context, resource, objects);
    }

    ImageView image;
    TextView txtnombreEvento;
    TextView txtCreador;
    Button btnVer;
    Button btnRechazar;
    TextView txtFecha;
    TextView txtidEvento;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater)getContext().getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        if(null==convertView){
            convertView=inflater.inflate(R.layout.item_evento,parent,false );
        }

         image=(ImageView) convertView.findViewById(R.id.imageView);
         txtnombreEvento=(TextView) convertView.findViewById(R.id.txtEvento);
         txtCreador=(TextView) convertView.findViewById(R.id.txtCreador);
         btnVer =(Button) convertView.findViewById(R.id.btnVer);
         btnRechazar=(Button) convertView.findViewById(R.id.btnRechazar);
        txtFecha=(TextView) convertView.findViewById(R.id.txtFecha);
        txtidEvento=(TextView) convertView.findViewById(R.id.txtIdEvento);

        Evento evento=getItem(position);
        bindData(evento);

        //evento.setNombre(txtnombreEvento.getText().toString());

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                //Intent intent = new Intent(getApplicationContext(), Login.class);
                //Intent intent = new Intent(this, MainActivity.class);
                getContext().startActivity(intent);
            }
        });

        btnRechazar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getContext().getPackageManager()) != null){
                    //startActivityForResult(intent, 1);
                }

            }
        });

        return convertView;
        //return super.getView(position, convertView, parent);


    }

    public void bindData(Evento evento) {
        txtnombreEvento.setText(evento.getNombre());
        txtFecha.setText(evento.getFecha());
        txtidEvento.setText(String.valueOf( evento.getIdEvento()));
    }

}
