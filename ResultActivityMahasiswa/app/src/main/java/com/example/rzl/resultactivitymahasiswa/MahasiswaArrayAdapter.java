package com.example.rzl.resultactivitymahasiswa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MahasiswaArrayAdapter extends ArrayAdapter<Mahasiswa> {
    private Context context;
    private List<Mahasiswa> mahasiswaList;

    public MahasiswaArrayAdapter(Context context, int resource, List<Mahasiswa> mahasiswaList) {
        super(context, resource, mahasiswaList);

        this.context = context;
        this.mahasiswaList = mahasiswaList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Mahasiswa mahasiswa;
        TextView nimTextView, namaTextView, jkTextView;
        LayoutInflater layoutInflater;
        View rowView;
        ImageView fotoKecilImageView;

        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = layoutInflater.inflate(R.layout.activity_pilih_mahasiswa, parent, false);
        mahasiswa = mahasiswaList.get(position);

        nimTextView = (TextView)rowView.findViewById(R.id.nimTextView);
        nimTextView.setText(mahasiswa.getNim());

        namaTextView = (TextView)rowView.findViewById(R.id.namaTextView);
        namaTextView.setText(mahasiswa.getNama());

        jkTextView = (TextView)rowView.findViewById(R.id.jkTextView);
        jkTextView.setText(mahasiswa.getJk());

        fotoKecilImageView = (ImageView)rowView.findViewById(R.id.fotoKecilImageView);
        if (mahasiswa.getJk().toLowerCase().equals("laki-laki")){
            fotoKecilImageView.setImageResource(R.mipmap.boy);
        }
        else {
            fotoKecilImageView.setImageResource(R.mipmap.girl);
        }
        return rowView;
    }
}
