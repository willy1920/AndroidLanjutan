package id.web.stromzivota.pengaturancahaya;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SeekBar cahayaSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCahayaSeekBar();
        tampilPosisiCahay();
        buatEventCahayaSeekBar();
    }

    private void buatEventCahayaSeekBar() {
        cahayaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void tampilPosisiCahay() {
        int itensitasCahaya;
        itensitasCahaya = 0;
        try {
            itensitasCahaya = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        }
        catch (Settings.SettingNotFoundException safe){
            Toast.makeText(getApplicationContext(),safe.getMessage(), Toast.LENGTH_LONG).show();
        }
        cahayaSeekBar.setProgress(itensitasCahaya);
    }

    private void initCahayaSeekBar() {
        cahayaSeekBar = (SeekBar)findViewById(R.id.cahayaSeekBar);
        cahayaSeekBar.setMax(255);
    }
}
