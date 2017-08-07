package uk.co.donnellyit.travelappjava.ui.departures;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

import uk.co.donnellyit.travelappjava.R;
import uk.co.donnellyit.travelappjava.ws.Station;

/**
 * Created by chrisdonnelly on 01/08/2017.
 */

public class StationAdapter extends ArrayAdapter<Station> {
    private Context mContext;
    private ArrayList<Station> mStations;
    private final static String LOG_TAG = StationAdapter.class.getSimpleName();
    private ArrayList<Station> itemsAll;
    private ArrayList<Station> suggestions;

    public StationAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Station> objects) {
        super(context, resource, objects);
        mContext = context;
        mStations = objects;
        itemsAll = (ArrayList<Station>) objects.clone();
        suggestions = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if(view == null) {
            LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflator.inflate(R.layout.item_station, null);
        }

        Station station = mStations.get(position);
        if(station != null) {
            TextView nameTv = (TextView) view.findViewById(R.id.station_name);
            if(nameTv != null) {
                nameTv.setText(station.getName());
            }
            TextView crsTv = (TextView) view.findViewById(R.id.station_crs);
            if(crsTv != null) {
                crsTv.setText(station.getCrs());
            }
        }

        return view;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter = new Filter() {

        @Override
        public String convertResultToString(Object convertValue) {
            Station station = (Station) convertValue;
            if(station != null) {
                return station.getName();
            } else {
                return "";
            }
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                for(Station station : itemsAll) {
                    if(station.getName().contains(constraint.toString())) {
                        suggestions.add(station);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Station> fileredList = (ArrayList<Station>) results.values;
            if(results != null) {
                clear();
                if(results.count > 0) {
                    for(Station station : fileredList) {
                        add(station);
                    }
                    notifyDataSetChanged();
                }
            }
        }
    };
}
