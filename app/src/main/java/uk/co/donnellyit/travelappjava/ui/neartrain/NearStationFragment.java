package uk.co.donnellyit.travelappjava.ui.neartrain;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import uk.co.donnellyit.travelappjava.R;
import uk.co.donnellyit.travelappjava.ui.common.TAFragment;
import uk.co.donnellyit.travelappjava.util.Injection;
import uk.co.donnellyit.travelappjava.ws.StationJson;
import uk.co.donnellyit.travelappjava.ws.TrainNearResponse;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NearStationFragment extends TAFragment<NearTrainPresenter> implements NearTrainContract.View {

    private final static String LOGTAG = NearStationFragment.class.getSimpleName();
    private OnListFragmentInteractionListener mListener;
    @BindView(R.id.list) RecyclerView mRecyclerView;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private int mPageNumber = 1;
    private MyStationJsonRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NearStationFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NearStationFragment getInstance() {
        NearStationFragment fragment = new NearStationFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NearTrainPresenter createPresenter() {
        return Injection.injectNearTrainPresenter(getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_refresh:

                fetchNearestStationsWithPermissionsRequest(mPageNumber);
                return true;

            default:
                break;
        }

        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stationjson_list, container, false);
        //ButterKnife.setDebug(true);
        ButterKnife.bind(this, view);

        //Context context = view.getContext();




        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchNearestStationsWithPermissionsRequest(mPageNumber);
            }
        });


        RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    Log.d(LOGTAG, "Load page "+(mPageNumber+1));
                }
            }
        };
        mRecyclerView.addOnScrollListener(scrollListener);

        getActivity().setTitle(getString(R.string.nearest_station));

        fetchNearestStationsWithPermissionsRequest(mPageNumber);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void displayNearestStations(TrainNearResponse response) {
        Log.d(LOGTAG, "no. nearest stations: "+response.getStations().size() + "/"+ response.getTotal());
        mAdapter = new MyStationJsonRecyclerViewAdapter(getContext(), response.getStations(), mListener);
        mRecyclerView.setAdapter(mAdapter);
        mPageNumber = Integer.parseInt(response.getPage());
    }

    @Override
    public void onError(String msg) {

    }

    private void fetchNearestStationsWithPermissionsRequest(final int pageNumber) {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(LOGTAG, e.getMessage(), e);
                    }

                    @Override
                    public void onNext(Boolean granted) {
                        if(granted) {
                            presenter.getNearStations(pageNumber);
                        } else {
                            Log.d(LOGTAG, "ACCESS_FINE_LOCATION permission denied");
                        }
                    }
                });
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
        void onListFragmentInteraction(StationJson station);
    }
}
