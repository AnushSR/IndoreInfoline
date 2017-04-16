package com.indore.indoreinfoline.model;

/**
 * Created by Harshit on 4/17/2017.
 */

public class DataBean {

    protected String source;
    protected String destination;
    protected int routeno;
    protected String time;

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getRouteno() {
        return routeno;
    }

    public String getTime() {
        return time;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setRouteno(int routeno) {
        this.routeno = routeno;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataBean(String source, String destination, int routeno, String time) {
        this.source = source;
        this.destination = destination;
        this.routeno = routeno;
        this.time = time;
    }
}
