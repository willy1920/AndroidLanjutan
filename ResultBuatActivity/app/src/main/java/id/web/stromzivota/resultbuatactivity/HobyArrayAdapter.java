package id.web.stromzivota.resultbuatactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HobyArrayAdapter extends ArrayAdapter<Hoby> {
    private Context context;
    private List<Hoby> hobyList;


    public HobyArrayAdapter(Context context, int resource, List<Hoby> hobyList) {
        super(context, resource, hobyList);
        this.context = context;
        this.hobyList = hobyList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Hoby hoby;
        TextView namaHoby;
        LayoutInflater layoutInflater;
        View rowView;

        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = layoutInflater.inflate(R.layout.activity_list_hobi, parent, false);
        hoby = hobyList.get(position);

        namaHoby = (TextView)rowView.findViewById(R.id.textNamaBuah);
        namaHoby.setText(hoby.getNamaHoby());

        return rowView;
    }
}
