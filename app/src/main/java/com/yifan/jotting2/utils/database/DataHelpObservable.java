package com.yifan.jotting2.utils.database;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by yifan on 2016/7/20.
 */
public interface DataHelpObservable {

    void regesiterDataObserver(Observer o);

    void unregesiterDataObservers();

    void unregesiterDataObserver(Observer o);

    void notifyDataChanged();

    void notifyDataChanged(Object arg);
}
