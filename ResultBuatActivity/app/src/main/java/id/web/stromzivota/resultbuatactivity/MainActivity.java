package id.web.stromzivota.resultbuatactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button buttonHobi;
    private EditText editHobi;
    private Intent intentHobi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        buttonHobi = (Button)findViewById(R.id.buttonHobi);
        editHobi = (EditText)findViewById(R.id.editHobi);
        intentHobi = new Intent(this, ListHobi.class);

        buttonHobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentHobi, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String hobi;
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    hobi = data.getStringExtra("hobi");
                    editHobi.setText(hobi);
                }
                break;
        }
    }
}
