package hu.ait.android.touristinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.touristinfo.adapter.AgendaAdapter;
import hu.ait.android.touristinfo.touch.AgendaTouchHelperCallback;
import io.realm.Realm;

/**
 * Created by oliviakim on 12/3/17.
 */

public class MyAgenda extends AppCompatActivity {

    private AgendaAdapter agendaAdapter;
    RecyclerView recyclerViewSight;
    List<Integer> agendaResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_layout);

        //((MainApplication) getApplication()).openRealm();

        //setUpRealmItems();

        setUpRecyclerView();
        setUpTouchHelper();

    }

    /*
    private void setUpRealmItems() {
        agendaResult = new ArrayList<>();

    }
    */

    private void setUpTouchHelper() {
       AgendaTouchHelperCallback touchHelperCallback = new AgendaTouchHelperCallback(
                agendaAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(recyclerViewSight);
    }

    private void setUpRecyclerView() {

        RecyclerView recyclerViewSights = (RecyclerView) findViewById(R.id.recyclerViewSight);
        agendaAdapter = new AgendaAdapter();

        recyclerViewSights.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSights.setHasFixedSize(true);
        //agendaAdapter = new AgendaAdapter(this, ((MainApplication)getApplication()).getRealmSights() );
        // agendaResult, add as first parameter
        recyclerViewSights.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewSights.setAdapter(agendaAdapter);
    }

    public Realm getRealm() {
        return ((MainApplication)getApplication()).getRealmSights();
    }

    /*
    public void deleteCity(City city) {
        getRealm().beginTransaction();
        city.deleteFromRealm();
        getRealm().commitTransaction();
    }
    */

    /*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MainApplication)getApplication()).closeRealm();
    }
    */


}
