package id.web.stromzivota.pengaturancahayaquiz;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        canWrite();
        initSeekBar();
        tampilSeekBar();
        eventSeekBar();
    }

    private void canWrite() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean writeSetting = Settings.System.canWrite(this);
            if(!writeSetting){
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                startActivity(intent);
            }
        }
    }

    private void tampilSeekBar() {
        int itensitasCahaya;
        itensitasCahaya = 0;
        try{
            itensitasCahaya = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        }
        catch (Settings.SettingNotFoundException safe){
            Toast.makeText(getApplicationContext(), safe.getMessage(), Toast.LENGTH_LONG).show();
        }
        seekBar.setProgress(itensitasCahaya);
    }

    private void eventSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, progress);
                cekTerangGelap(progress);
                gantiKeterangan(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void gantiKeterangan(int progress){
        TextView keterangan = (TextView)findViewById(R.id.keterangan);
        keterangan.setText("Intensitas cahaya saat ini adalah " + progress);
    }

    private void cekTerangGelap(int progress){
        TextView gelap = (TextView)findViewById(R.id.gelap);
        TextView terang = (TextView)findViewById(R.id.terang);

        if (progress > 0 && progress < 128){
        }
        else if (progress >= 128 && progress <= 255){
        }
        else {
            Toast.makeText(getApplicationContext(), "Unknown int", Toast.LENGTH_SHORT).show();
        }
    }
    private void initSeekBar() {
        seekBar = (SeekBar)findViewById(R.id.itensitasCahaya);
        seekBar.setMax(255);
    }
}
