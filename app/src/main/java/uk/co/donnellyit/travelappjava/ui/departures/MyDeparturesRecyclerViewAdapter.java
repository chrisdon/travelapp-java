package uk.co.donnellyit.travelappjava.ui.departures;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.donnellyit.travelappjava.R;
import uk.co.donnellyit.travelappjava.ui.departures.DeparturesFragment.OnListFragmentInteractionListener;
import uk.co.donnellyit.travelappjava.ui.departures.dummy.DummyContent.DummyItem;
import uk.co.donnellyit.travelappjava.ws.Service;
import uk.co.donnellyit.travelappjava.ws.Station;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyDeparturesRecyclerViewAdapter extends RecyclerView.Adapter<MyDeparturesRecyclerViewAdapter.ViewHolder> {

    private final List<Service> mValues;
    private final OnListFragmentInteractionListener mListener;
    private int mExpandedPosition = 0;

    public MyDeparturesRecyclerViewAdapter(List<Service> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final boolean isExpanded = mExpandedPosition == position;
        holder.mExpandedLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.mItem = mValues.get(position);
        holder.mDestTv.setText(mValues.get(position).getDestination_name());
        holder.mAimedTv.setText(mValues.get(position).getAimed_departure_time());
        holder.mExpectedTv.setText(mValues.get(position).getExpected_departure_time());
        holder.mPlatformTv.setText(mValues.get(position).getPlatform());
        holder.mOperatorTv.setText(mValues.get(position).getOperator_name());
        holder.mOriginTv.setText(mValues.get(position).getOrigin_name());
        holder.mStatusTv.setText(mValues.get(position).getStatus());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        @BindView(R.id.service_expanded)
        TableLayout mExpandedLayout;
        @BindView(R.id.service_dest) TextView mDestTv;
        @BindView(R.id.service_aimed) TextView mAimedTv;
        @BindView(R.id.service_expected) TextView mExpectedTv;
        @BindView(R.id.service_platform) TextView mPlatformTv;
        @BindView(R.id.service_operator) TextView mOperatorTv;
        @BindView(R.id.service_origin) TextView mOriginTv;
        @BindView(R.id.service_status) TextView mStatusTv;

        Service mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDestTv.getText() +" "+mExpectedTv.getText()+ "'";
        }
    }
}
