package id.web.stromzivota.resultbuatactivity;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListHobi extends ListActivity {
    private List<String> hobyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_hobi);

        initList();
        initListView();
    }

    private void initListView() {
        final ListView listView;
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_list_hobi, hobyList));
        listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                String hobi;

                hobi = listView.getItemAtPosition(position).toString();
                intent = getIntent();
                intent.putExtra("hobi", hobi);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initList() {
        hobyList = new ArrayList<>();
        hobyList.add("Berenang");
        hobyList.add("Bulu tangkis");
        hobyList.add("Jogging");
    }
}
