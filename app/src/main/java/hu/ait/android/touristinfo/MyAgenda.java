package hu.ait.android.touristinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.touristinfo.adapter.AgendaAdapter;
import hu.ait.android.touristinfo.data.Sights;
import hu.ait.android.touristinfo.touch.AgendaTouchHelperCallback;
import io.realm.Realm;

/**
 * Created by oliviakim on 12/3/17.
 */

public class MyAgenda extends AppCompatActivity {

    private AgendaAdapter agendaAdapter;
    RecyclerView recyclerViewSight;
    private ArrayList<Sights> newSightsInAgenda;
    private ArrayList<Sights> savedSightsInAgenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_layout);

        ((MainApplication) getApplication()).openRealm();

        //setUpRealmItems();
        Intent intent = getIntent();
        newSightsInAgenda = intent.getParcelableArrayListExtra("list");
        savedSightsInAgenda = new ArrayList<>();
        setUpRecyclerView();
        setUpTouchHelper();
        setupToolbar();
        addSightsToAgenda();
    }

    private void addSightsToAgenda() {
        for (Sights sights: newSightsInAgenda){
            if (!savedSightsInAgenda.contains(sights)){
                savedSightsInAgenda.add(sights);
                agendaAdapter.addSights(sights);
            }
        }
    }


    private void setUpTouchHelper() {
        AgendaTouchHelperCallback touchHelperCallback = new AgendaTouchHelperCallback(
                agendaAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(recyclerViewSight);
    }

    private void setUpRecyclerView() {

        RecyclerView recyclerViewSights = (RecyclerView) findViewById(R.id.recyclerViewSight);
        agendaAdapter = new AgendaAdapter(this, getRealm());

        recyclerViewSights.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSights.setHasFixedSize(true);
        //agendaAdapter = new AgendaAdapter(this, ((MainApplication)getApplication()).getRealmSights() );
        // agendaResult, add as first parameter
        recyclerViewSights.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewSights.setAdapter(agendaAdapter);
    }

    private void setupToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public Realm getRealm() {
        return ((MainApplication)getApplication()).getRealmSights();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MainApplication)getApplication()).closeRealm();

    }
}