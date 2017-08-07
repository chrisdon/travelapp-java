package uk.co.donnellyit.travelappjava.ui.neartrain;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.donnellyit.travelappjava.R;
import uk.co.donnellyit.travelappjava.ui.neartrain.NearStationFragment.OnListFragmentInteractionListener;
import uk.co.donnellyit.travelappjava.ws.StationJson;

/**
 * {@link RecyclerView.Adapter} that can display a {@link StationJson} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyStationJsonRecyclerViewAdapter extends RecyclerView.Adapter<MyStationJsonRecyclerViewAdapter.ViewHolder> {

    private final List<StationJson> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context mContext;

    public MyStationJsonRecyclerViewAdapter(Context context, List<StationJson> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_stationjson, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String distance = mContext.getString(R.string.distance, mValues.get(position).getDistance());
        holder.mStationName.setText(mValues.get(position).getName());
        holder.mStationDistance.setText(distance);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.station_name) TextView mStationName;
        @BindView(R.id.station_distance) TextView mStationDistance;
        public StationJson mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mStationName.getText() + "'";
        }
    }
}
