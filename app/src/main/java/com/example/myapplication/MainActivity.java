package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Button button1, button2;
    public RelativeLayout relativeLayout;

    private final Handler handler = new Handler();
    private Runnable runnable;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = findViewById(R.id.rlVar1);
        button1 = findViewById(R.id.btVar1);
        button2 = findViewById(R.id.btVar2);

        // Initialize the runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                // set the background on the screen
                relativeLayout.setBackgroundResource(R.color.purple_200);

                // get the system time in milliseconds when the screen background is set
                startTime = System.currentTimeMillis();
            }
        };

        // Set OnClickListener for the start button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // generate a random number from 1-10
                Random random = new Random();
                int num = random.nextInt(10) + 1;  // to ensure it is between 1 and 10

                // call the runnable function after a post delay of num seconds
                handler.postDelayed(runnable, num * 1000);
            }
        });

        // Set OnClickListener for the stop button
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the system time in milliseconds when the stop button is clicked
                long endTime = System.currentTimeMillis();

                // display reflex time in toast message
                if (startTime != 0) {
                    Toast.makeText(getApplicationContext(), "Your reflexes take " + (endTime - startTime) + " ms to work", Toast.LENGTH_LONG).show();

                    // reset the background and start time
                    relativeLayout.setBackgroundResource(0);
                    startTime = 0;
                } else {
                    Toast.makeText(getApplicationContext(), "Please wait for the background to change color before stopping.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
