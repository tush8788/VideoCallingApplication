package com.example.videocallingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.videocallingapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Dashboard_Activity extends AppCompatActivity {

    EditText code;
    Button joinBtn,shareBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        code=findViewById(R.id.codeBox);
        joinBtn=findViewById(R.id.joinBtn);
        shareBtn=findViewById(R.id.shareBtn);



        URL serverURL;

        try {
            serverURL=new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder().setServerURL(serverURL)
                    .setWelcomePageEnabled(false).build();

            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        }
         catch (MalformedURLException e) {
            e.printStackTrace();
        }



        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options=new JitsiMeetConferenceOptions.Builder()
                        .setRoom(code.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(Dashboard_Activity.this,options);
            }
        });


        //share button code under checking
        shareBtn.setOnClickListener(v ->{
            Intent share=new Intent(Intent.ACTION_SEND);
            share.setType("Text/plane");
            share.putExtra(Intent.EXTRA_TEXT,code.getText());    // ye aak bar check kar lena
            getApplicationContext().startActivity(share);
        });
    }
}