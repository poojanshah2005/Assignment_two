package com.poojanshah.assignment_two.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.poojanshah.assignment_two.Adapters.MusicAdapter;
import com.poojanshah.assignment_two.MainActivity;
import com.poojanshah.assignment_two.R;
import com.poojanshah.assignment_two.model.Music;
import com.poojanshah.assignment_two.model.Result;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * for displaying result back from api
 */
public class Results extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Music music;
    private RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    /**
     * initiating the RecyclerView and the swipe to refresh feature
     * @param view to bind ojects to the view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        mRecyclerView.setAdapter(new MusicAdapter(music,getContext()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ReactiveNetwork.observeInternetConnectivity()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override public void accept(Boolean isConnectedToInternet) {
                                // do something with isConnectedToInternet value
                                if(isConnectedToInternet){
                                    MainActivity.update();
                                } else{
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                            }
                        });
            }
        });
        swipeRefreshLayout.setRefreshing(false);

    }

    /**
     * construtor is reaquired.
     */
    public Results() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Results.
     */
    // TODO: Rename and change types and number of parameters
    public static Results newInstance(String param1, String param2) {
        Results fragment = new Results();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * for getting data from api and loading it up
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
            Bundle b = getArguments();
            music = b.getParcelable("doctor_id");
            for (Result r : music.getResults()) {
                Log.i("MusicLog 63", r.getTrackName());
            }
        }


    /**
     * for inflating view/layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

}
