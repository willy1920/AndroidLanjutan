package com.example.rzl.resultactivitymahasiswa;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class PilihMahasiswaActivity extends ListActivity {
    private List<Mahasiswa> mahasiswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pilih_mahasiswa);

        getMahasiswaJson();
    }

    private void getMahasiswaJson() {
        AsyncHttpClient asyncHttpClient;
        String url;

        url = "http://stromzivota.web.id/tampilMahasiswa.php";

        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                initMahasiswaList(responseBody);

                setListAdapter(new MahasiswaArrayAdapter(getApplicationContext(), R.layout.activity_pilih_mahasiswa, mahasiswaList));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initMahasiswaList(byte[] resposeBody) {
        Gson gson;

        gson = new Gson();

        try{
            mahasiswaList = gson.fromJson(new String(resposeBody), new TypeToken<List<Mahasiswa>>(){}.getType());
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
