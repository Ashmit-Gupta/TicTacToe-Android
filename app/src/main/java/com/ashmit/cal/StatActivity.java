package com.ashmit.cal;

import static java.lang.Integer.sum;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class StatActivity extends AppCompatActivity {

    ListView lstView;


    ArrayList<String> arrStats = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            arrStats.clear();
            lstView = findViewById(R.id.lstView);


            //receiving the Stats from the main Activity !
            Intent intent = getIntent();

            ArrayList<Integer>xWinsList = intent.getIntegerArrayListExtra("xWinsList");

            ArrayList<Integer> oWinsList = intent.getIntegerArrayListExtra("oWinsList");

            ArrayList<Integer> drawsList = intent.getIntegerArrayListExtra("drawsList");

            //adding the data to the ArrayList arrstats

            for (int i = 0 ; i < xWinsList.size() ; i++){
                arrStats.add((i+1)+". X win : " + xWinsList.get(i) + " , O's win : " + oWinsList.get(i) + " , Draws : " + drawsList.get(i));

            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(StatActivity.this , R.layout.list_item, arrStats );

            lstView.setAdapter(adapter);


            return insets;
        });
    }
}