package uk.co.donnellyit.travelappjava.ui.common;

/**
 * Created by chrisdonnelly on 03/07/2017.
 */

public interface TAContract {
    interface View {}

    interface Presenter<V extends View> {
        V getView();
        void attachView(V View);
        void detachView();
    }

    interface Component<P extends Presenter> {
        P presenter();
    }
}
