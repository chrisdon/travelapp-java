package uk.co.donnellyit.travelappjava.ui.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by chrisdonnelly on 03/07/2017.
 */

public abstract class TAFragment<P extends TAContract.Presenter> extends Fragment
        implements TAContract.View {

    protected P presenter;
    protected abstract P createPresenter();

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
