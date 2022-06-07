package com.example.a180_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    boolean flag=false;
    int count=0;
    Button start,stop,reset;
    TextView value;
    Handler handle=new Handler(){
        public  void handleMessage(Message m)
        {
            value.setText(String.valueOf(m.what));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.start_button);
        stop=(Button)findViewById(R.id.stop_button);
        reset=(Button)findViewById(R.id.reset_button);
        value=(TextView)findViewById(R.id.count_val);
        stop.setOnClickListener(this);
        start.setOnClickListener(this);
        reset.setOnClickListener(this);
        value.setText("0");

    }
    @Override
    public void onClick(View v) {
        if(v.equals(start))
        {
            flag=true;
            new Counter().start();
        }
        else if(v.equals(stop))
        {
            flag=false;
        }
        else if(v.equals(reset)) {
            count = 0;
        }
    }
    class Counter extends Thread{
        @Override
        public void run()
        {
            while(flag)
            {
                count++;
                handle.sendEmptyMessage(count);
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e)
                {

                }
            }
        }
    }
}
