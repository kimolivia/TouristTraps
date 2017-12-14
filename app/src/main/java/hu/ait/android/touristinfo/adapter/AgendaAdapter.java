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
import java.util.UUID;

import hu.ait.android.touristinfo.MainActivity;
import hu.ait.android.touristinfo.R;
import hu.ait.android.touristinfo.data.Sights;
import io.realm.Realm;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by oliviakim on 12/5/17.
 */

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder> {

    private Context context;
    private Realm realmSights;
    private List<Sights> sightsList;

    public AgendaAdapter(Context context, Realm realmSights) {
        sightsList = new ArrayList<Sights>();
        this.context = context;
        this.realmSights = realmSights;
    }


    public void addSights(Sights sights){
        realmSights.beginTransaction();

        Sights newSights = realmSights.createObject(Sights.class);
        //Sights newSights = realmSights.createObject(Sights.class, UUID.randomUUID().toString());
        newSights.setName(sights.getName());
        newSights.setRating(sights.getRating());

        realmSights.commitTransaction();
        
        sightsList.add(newSights);
        notifyItemInserted(sightsList.size());
        notifyDataSetChanged();
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_agenda, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Sights sightData = sightsList.get(position);


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeSight(holder.getAdapterPosition());

            }
        });


        holder.tvName.setText(sightData.getName());
        holder.tvRating.setText(sightData.getRating().toString());
//        holder.tvDistance.setText(sightData.getDistance().toString());
    }

    @Override
    public int getItemCount() {
        return sightsList.size();
    }

    public void removeSight(int position) {
        Sights toRemoveSight = sightsList.get(position);

        realmSights.beginTransaction();
//        realmSights.where(Sights.class).equalTo("sightsID",toRemoveSight.getSightsID());
        toRemoveSight.deleteFromRealm();
//        realmSights.deleteAll();
        realmSights.commitTransaction();

        sightsList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvRating;
        private TextView tvDistance;
        private FancyButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvDistance = itemView.findViewById(R.id.tvDistance);
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
