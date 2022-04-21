package com.barmej.wecare.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.barmej.wecare.R;
import com.barmej.wecare.data.Limit;
import com.barmej.wecare.data.Time.TimerIntentService;
import com.barmej.wecare.data.Time.TimerUtils;
import com.barmej.wecare.utiles.NotificationUtils;
import com.barmej.wecare.utiles.Utiles;
import com.barmej.wecare.viewModels.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private TextView dailyUsed;
    private TextView weeklyaverge;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationUtils.createWeatherStatusNotificationChannel(this);
        ContextCompat.startForegroundService(this, new Intent(this, TimerIntentService.class));
        TimerUtils.startScreenMonitor(this);

        dailyUsed = findViewById(R.id.number);
        weeklyaverge = findViewById(R.id.weekly);
        button = findViewById(R.id.btn);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getLimits().observe(this, new Observer<List<Limit>>() {
            @Override
            public void onChanged(List<Limit> limits) {
                int sum = 0;
                if (limits.size() > 0){
                    if(limits.get(limits.size()-1).getData().contains(Utiles.toString(System.currentTimeMillis()))){
                        dailyUsed.setText(String.valueOf(limits.get(limits.size()-1).getCounter()));
                        Log.d("Counter", String.valueOf(limits.get(limits.size()-1).getCounter()));
                        for (int i = 0; i < 7; i++) {
                            if (i >= limits.size())
                                break;
                            Limit limit = limits.get(i);
                            sum = sum + limit.getCounter();
                        }
                        sum = sum / 7;
                        weeklyaverge.setText(String.valueOf(sum));
                    }else {
                        mainViewModel.addLimit(new Limit(Utiles.toString(System.currentTimeMillis())));
                        dailyUsed.setText("2");
                        for (int i = 0; i < 7; i++) {
                            if (i >= limits.size())
                                break;
                            Limit limit = limits.get(i);
                            sum = sum + limit.getCounter();
                        }
                        sum = sum / 7;
                        weeklyaverge.setText(String.valueOf(sum));
                    }

                }else{
                    mainViewModel.addLimit(new Limit(Utiles.toString(System.currentTimeMillis())));
                    dailyUsed.setText(String.valueOf(0));
                    weeklyaverge.setText(String.valueOf(0));
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DailyData.class);
                startActivity(intent);
            }
        });

    }//end of onCreate



}
