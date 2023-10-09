package com.example.volleyinsert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String URLInsert = "https://websensordata.000webhostapp.com/android/login.php";
    //public static final String URLInsert = "http://192.168.1.5:8080/kampus/android/login.php";

    EditText tnim, tnama;
    Button binput;

    public class AppVar{
        public static final String LOGIN_SUCCESS = "berhasil";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tnim = (EditText) findViewById(R.id.inNim);
        tnama = (EditText) findViewById(R.id.inNama);
        binput = (Button) findViewById(R.id.btInput);

        binput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                inputData();
            }
        });
    }

    void inputData(){
        String nim = tnim.getText().toString();
        String nama = tnama.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLInsert,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.contains(AppVar.LOGIN_SUCCESS))
                        {
                            Intent AwalActivity = new Intent(MainActivity.this, AwalActivity.class);
                            startActivity(AwalActivity);
                        }

                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error Tidak Dapat Diproses", Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("nim", nim);
                params.put("nama", nama);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
