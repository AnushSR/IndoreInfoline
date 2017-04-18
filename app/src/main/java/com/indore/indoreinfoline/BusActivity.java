package com.indore.indoreinfoline;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.indore.indoreinfoline.model.DataBean;
import com.indore.indoreinfoline.model.DatabaseAccess;
import com.indore.indoreinfoline.viewholder.BusAdapter;

import java.util.ArrayList;
import java.util.List;

public class BusActivity extends AppCompatActivity {
//    private ListView listView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bus);
//
//        this.listView = (ListView) findViewById(R.id.listView);
//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
//        databaseAccess.open();
//        List<DataBean> bus = databaseAccess.getBus();
//        Log.d("List", String.valueOf(bus.get(0).getSource()));
//        Log.d("List", String.valueOf(bus.get(1).getDestination()));
//        databaseAccess.close();
//
//        ArrayAdapter<DataBean> adapter = new ArrayAdapter<DataBean>(this, android.R.layout.simple_list_item_1, bus);
//        this.listView.setAdapter(adapter);
//
//    }

    private List<DataBean> busList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BusAdapter busAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_bus);

        final AppCompatSpinner source = (AppCompatSpinner) findViewById(R.id.source);
        final AppCompatSpinner dest = (AppCompatSpinner) findViewById(R.id.dest);

        String[] sourceItems = new String[]{"56 Dukaan", "Bhawarkuan", "High Court"};
        String[] destItems = new String[]{"Anand Bazar", "Bada Ganpati", "Navlakha"};

        ArrayAdapter<String> sourceList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sourceItems);
        ArrayAdapter<String> destList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, destItems);

        sourceList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        source.setAdapter(sourceList);
        dest.setAdapter(destList);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();
                busList = databaseAccess.getBus(source.getSelectedItem().toString(), dest.getSelectedItem().toString());
                Log.d("Database", String.valueOf(busList.get(0).getSource()));
                busAdapter = new BusAdapter(busList);
                recyclerView.setAdapter(busAdapter);
                busAdapter.notifyDataSetChanged();
                databaseAccess.close();
            }
        });

    }
}
