package com.example.homeworknetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String testUrl = "http://date.jsontest.com/";
    RequestQueue mRequestQueue;
    TextView timeView, dateView;
    Button button;
    String timeString, dateString;

    private void getTimeAndDate(String url) {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    timeString = response.getString("time");
                    dateString = response.getString("date");
                    setValues(timeString, dateString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    private void setValues(String time, String date) {
        timeView.setText(time);
        dateView.setText(date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTimeAndDate(testUrl);
            }
        });

        timeView = findViewById(R.id.timeTextView);
        dateView = findViewById(R.id.dateTextView);
        mRequestQueue = Volley.newRequestQueue(this);

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.sCreate),Toast.LENGTH_LONG).show();
    }
}