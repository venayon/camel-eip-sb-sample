package com.vrana.ps.camel.api;

public class Response<T> {

    public Response(){
        super();
    }
    public Response(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    private T message;

}
