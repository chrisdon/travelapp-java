package uk.co.donnellyit.travelappjava.ui.departures;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.donnellyit.travelappjava.R;
import uk.co.donnellyit.travelappjava.ui.common.TAFragment;
import uk.co.donnellyit.travelappjava.util.Injection;
import uk.co.donnellyit.travelappjava.ws.Service;
import uk.co.donnellyit.travelappjava.ws.Station;
import uk.co.donnellyit.travelappjava.ws.TrainLiveResponse;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DeparturesFragment extends TAFragment<DeparturesContract.Presenter> implements DeparturesContract.View, AdapterView.OnItemClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_CRS = "crs";
    private final static String LOGTAG = DeparturesFragment.class.getSimpleName();
    // TODO: Customize parameters
    private OnListFragmentInteractionListener mListener;
    private List<Station> mStations;
    private List<Service> mServices;
    private int mStationIndex;
    private MyDeparturesRecyclerViewAdapter mListAdapter;
    private String mCrs;
    @BindView(R.id.root)
    LinearLayout mRootView;
    @BindView(R.id.stationsACTV)
    AutoCompleteTextView mStationsACTV;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.serviceList)
    RecyclerView mRecyclerView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DeparturesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DeparturesFragment newInstance(String stationCode) {
        DeparturesFragment fragment = new DeparturesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CRS, stationCode);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressWarnings("unused")
    public static DeparturesFragment newInstance() {
        DeparturesFragment fragment = new DeparturesFragment();
        //Bundle args = new Bundle();
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected DeparturesContract.Presenter createPresenter() {
        return Injection.injectDeparturesPresenter(getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mCrs = getArguments().getString(ARG_CRS);
        }

        setRetainInstance(true);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_departures_list, container, false);
        ButterKnife.bind(this, view);
        // Set the adapter
        Context context = view.getContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.requestStations();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onItemPressed(mStations.get(mStationIndex).getCrs());
            }
        });

        if(mCrs != null) {
            onItemPressed(mCrs);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void displayLiveDepartures(TrainLiveResponse response) {
        getActivity().setTitle(response.getStation_name());
        mListAdapter = new MyDeparturesRecyclerViewAdapter(response.getDepartures().getAll(), mListener);
        mRecyclerView.setAdapter(mListAdapter);

        hideKeyboard();
    }

    @Override
    public void displayStations(List<Station> stations) {
        mStations = stations;
        StationAdapter stationAdapter = new StationAdapter(getContext(), R.layout.item_station, new ArrayList<>(stations));
        mStationsACTV.setAdapter(stationAdapter);
        mStationsACTV.setOnItemClickListener(this);


    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public void showProgress() {
        if(!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Station tappedStation = (Station)parent.getAdapter().getItem(position);

        Station stationInList = getStation(tappedStation);
        mStationIndex = mStations.indexOf(stationInList);
        presenter.requestDepartures(stationInList.getCrs());
    }

    private void onItemPressed(String crs) {
        presenter.requestDepartures(crs);
    }

    private Station getStation(Station tappedStation) {
        for(Station station : mStations) {
            if(station.getCrs().equals(tappedStation.getCrs())) {
                return station;
            }
        }

        return null;
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Service item);
    }
}
