package id.web.stromzivota.resulthobyactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button buttonHoby;
    private EditText editTextHoby;
    private Intent intentHoby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        buttonHoby = (Button)findViewById(R.id.buttonHoby);
        editTextHoby = (EditText)findViewById(R.id.editHoby);

        intentHoby = new Intent(this, ListHoby.class);

        buttonHoby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentHoby, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String hoby;
        switch(requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    hoby = data.getStringExtra("hoby");
                    editTextHoby.setText(hoby);
                }
        }
    }
}
