package hu.ait.android.touristinfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.ait.android.touristinfo.R;
import hu.ait.android.touristinfo.data.Sights;
import io.realm.Realm;

/**
 * Created by oliviakim on 12/5/17.
 */

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder> {

    private Context context;
    private Realm realmSights;

    private List<Sights> sightsList;

    public AgendaAdapter() {
        sightsList = new ArrayList<Sights>();
        for (int i = 0; i < 20; i++) {
            sightsList.add(new Sights("Todo"+i, "16. 10. 2017", false));
        }
    }

    /*
    public AgendaAdapter(Context context, Realm realmSights) {
        //this.cities = cities;
        this.context = context;
        this.realmSights = realmSights;
    }
    */


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_agenda, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sights sightData = sightsList.get(position);

        holder.tvSight.setText("Tester");

    }

    @Override
    public int getItemCount() {
        return sightsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSight;
        private Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            tvSight = itemView.findViewById(R.id.tvSight);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }


    }

    public void swapPlaces(int oldPosition, int newPosition) {

        if (oldPosition < newPosition) {
            for (int i = oldPosition; i < newPosition; i++) {
                Collections.swap(sightsList, i, i + 1);
            }
        } else {
            for (int i = oldPosition; i > newPosition; i--) {
                Collections.swap(sightsList, i, i - 1);
            }
        }
        notifyItemMoved(oldPosition, newPosition);

    }


}
