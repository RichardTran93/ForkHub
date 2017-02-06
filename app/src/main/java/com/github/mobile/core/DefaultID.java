package com.github.mobile.core;

/**
 * Created by EpiK on 2/6/2017.
 */

public class DefaultID implements IDBehavior{

    protected Object id;

    @Override
    public Object getID() {
        return id;
    }
}
