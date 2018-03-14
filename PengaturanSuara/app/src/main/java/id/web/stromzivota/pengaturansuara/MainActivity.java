package id.web.stromzivota.pengaturansuara;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private AudioManager audioManager;
    private CheckBox suaraCheckBox;
    private SeekBar suaraSeekBar;
    private int suaraMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canWrite();
        init();
    }

    public void canWrite() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean writeSetting = Settings.System.canWrite(this);
            if(!writeSetting){
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                startActivity(intent);
            }
        }
    }

    private void init() {
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

        initSuaraCheckBox();
        initSuaraSeekBar();
    }

    private void initSuaraSeekBar() {
        final int suaraMusik;
        suaraSeekBar = (SeekBar)findViewById(R.id.suaraSeekBar);

        suaraMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        suaraSeekBar.setMax(suaraMax);

        suaraMusik = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        suaraSeekBar.setProgress(suaraMusik);

        suaraSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initSuaraCheckBox() {
        boolean suaraAktif;
        suaraCheckBox = (CheckBox)findViewById(R.id.suaraCheckBox);
        suaraAktif = true;

        if (audioManager.getRingerMode() != AudioManager.RINGER_MODE_NORMAL){
            suaraAktif = false;
        }

        suaraCheckBox.setChecked(suaraAktif);

        suaraCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }

                else {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                }
            }
        });
    }
}
