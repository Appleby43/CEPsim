package com.alexblakeappleby.cepsim.model.env;

/**
 * Abstract superclass for all elements that can change with time.
 * May be either simulation elements or UI elements
 */
public abstract class ProgressableElement {
    private ProgressEvent progressEvent;

    public final void setProgressEvent(ProgressEvent progressEvent){
        this.progressEvent = progressEvent;
    }

    public final void progress(){
        if(progressEvent != null) progressEvent.onProgress();
        internalProgress();
    }

    protected abstract void internalProgress();
}
