package com.example.martinsaad.hackidc;

import java.util.List;

/**
 * Created by martinsaad on 06/05/2016.
 */
public class Request {
    private String method;
    private List<String> params;
    private String body;

    Request(String method, List<String> params, String body){
        this.method = method;
        this.params = params;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
