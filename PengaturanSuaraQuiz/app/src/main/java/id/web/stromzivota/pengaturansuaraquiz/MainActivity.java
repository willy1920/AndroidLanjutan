package id.web.stromzivota.pengaturansuaraquiz;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CheckBox musikCheckBox;
    private SeekBar musikSeekBar;
    private SeekBar ringerSeekBar;
    private AudioManager audioManager;
    private int suaraMax;
    private int suaraMaxRinger;
    private TextView pesan;
    private TextView pesanRinger;

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
        musikCheckBox = (CheckBox)findViewById(R.id.suarakMusikCheckBox);
        musikSeekBar = (SeekBar)findViewById(R.id.seekBarMusik);
        ringerSeekBar = (SeekBar)findViewById(R.id.seekBarRinger);
        pesan = (TextView)findViewById(R.id.pesan);
        pesanRinger = (TextView)findViewById(R.id.pesanRinger);
        initCheckBox();
        initSeekBar();
    }

    private void initSeekBar() {
        int suaraMusik;
        int suaraRinger;

        suaraMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        musikSeekBar.setMax(suaraMax);

        suaraMusik = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        musikSeekBar.setProgress(suaraMusik);

        suaraMaxRinger = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        ringerSeekBar.setMax(suaraMaxRinger);

        suaraRinger = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        ringerSeekBar.setProgress(suaraRinger);

        ringerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_RING, progress, AudioManager.FLAG_SHOW_UI);
                pesanRinger.setText("Suara Ringtone saat ini " + progress + " dari " + suaraMaxRinger);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        musikSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI);
                pesan.setText("Suara saat ini adalah " + progress + " dari " + suaraMax);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initCheckBox() {
        boolean suaraAktif;
        suaraAktif = true;
        if (audioManager.getRingerMode() != AudioManager.RINGER_MODE_NORMAL){
            suaraAktif = false;
        }

        musikCheckBox.setChecked(suaraAktif);

        musikCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
