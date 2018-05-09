package id.web.stromzivota.resulthobyactivity;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListHoby extends ListActivity {
    private List<String> hobyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_hoby);

        initListArray();
        initListView();
    }

    private void initListView() {
        final ListView listView;
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_list_hoby, hobyList));
        listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                String hoby;

                hoby = listView.getItemAtPosition(position).toString();
                intent = getIntent();
                intent.putExtra("hoby", hoby);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initListArray() {
        hobyList = new ArrayList<>();
        hobyList.add("Berenang");
        hobyList.add("Lari pagi");
        hobyList.add("GYM");
        hobyList.add("Bulu tangkis");
    }
}
