package com.example.onlinemarket.data.repository;


import com.example.onlinemarket.util.enums.ConnectionState;

public class RepositoryState<T> {

    private ConnectionState mConnectionState;
    private T mData;
    private Exception mException;

    public RepositoryState(ConnectionState connectionState, T data, Exception exception) {
        mConnectionState = connectionState;
        mData = data;
        mException = exception;
    }


}