/*
 * Copyright (c) 52apps 2014. All rights reserved.
 */

package com.r0adkll.kiosk.session;

/**
 * Generic Async callback handler for making api requests, or
 * it can be adapted to any async task that requires a callback
 *
 * Created by drew.heavner
 */
public interface APIHandler<T> {

    /**
     * Success responder that reports back success and the generic object
     * specified when you create the handler
     *
     * @param data  the data response
     */
    public void onSuccess(T data);

    /**
     * Called when the task hits an error, and needs to report back so.
     *
     * @param err       the throwable error, or null
     * @param code      the request status code
     * @param message   the error message
     */
    public void onFailure(Throwable err, int code, String message);

}
