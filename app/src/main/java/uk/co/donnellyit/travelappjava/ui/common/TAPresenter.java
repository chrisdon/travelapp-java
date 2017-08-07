package uk.co.donnellyit.travelappjava.ui.common;

import java.lang.ref.WeakReference;

/**
 * Created by chrisdonnelly on 03/07/2017.
 */

public abstract class TAPresenter<V extends TAContract.View> implements TAContract.Presenter<V> {
    private WeakReference<V> viewRef;

    @Override
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if(viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
