package com.ashmit.cal.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashmit.cal.R;

import java.util.ArrayList;

public class RecyclerViewStatAct extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<StatModel> arrStat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view_stat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            recyclerView = findViewById(R.id.recyclerView);

            //setting the Layout as linear for the recyclerview
            recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewStatAct.this));

            //setting the data in the arraylist
            getData();

            RecyclerStatAdapter adapter = new RecyclerStatAdapter(RecyclerViewStatAct.this ,arrStat);
            recyclerView.setAdapter(adapter);



            return insets;
        });
    }

    //this method is used for getting the Data from the Mainactivity to the RecyclerViewStatAct (data ->kon jeeta ) and we will get it using the getIntent ;
    public void getData(){
        Intent intent = getIntent();
        ArrayList<Integer> xWin = intent.getIntegerArrayListExtra("xWin");
        ArrayList<Integer> oWin = intent.getIntegerArrayListExtra("oWin");
        ArrayList<Integer> draws = intent.getIntegerArrayListExtra("draws");
        arrStat.clear();

        for (int i = 0 ; i < xWin.size() ; i++){
            arrStat.add(new StatModel((i+1) ,(xWin.get(i)).toString() , (oWin.get(i)).toString() , (draws.get(i)).toString() ));
        }
    }
}