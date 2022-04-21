package com.barmej.wecare.ui;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.barmej.wecare.R;
import com.barmej.wecare.data.Limit;
import com.barmej.wecare.utiles.Utiles;
import com.barmej.wecare.viewModels.MainViewModel;

import java.util.Date;
import java.util.List;

public class DailyData extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private ArrayAdapter<String> arrayAdapter;
    private Spinner spinner;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_data);

        textView = findViewById(R.id.dailyUsage);
        spinner = (Spinner) findViewById(R.id.spinner);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getLimits().observe(this, new Observer<List<Limit>>() {
            @Override
            public void onChanged(List<Limit> limits) {
                if (limits.size() > 0){
                    for (Limit limit : limits){
                        arrayAdapter.add(limit.getData());
                    }
                    spinner.setAdapter(arrayAdapter);
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mainViewModel.getLimit((String) adapterView.getSelectedItem()).observe(DailyData.this, new Observer<Limit>() {
                    @Override
                    public void onChanged(Limit limit) {
                        if (limit != null) {
                            textView.setText(String.valueOf(limit.getCounter()));
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mainViewModel.getLimit((String) adapterView.getItemAtPosition(adapterView.getCount()-1)).observe(DailyData.this, new Observer<Limit>() {
                    @Override
                    public void onChanged(Limit limit) {
                        textView.setText(String.valueOf(limit.getCounter()));
                    }
                });
            }
        });
    }
}
