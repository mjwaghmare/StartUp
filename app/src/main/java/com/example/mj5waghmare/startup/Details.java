package com.example.mj5waghmare.startup;

public class Details {
    String userid;
    String cname;
    String cserv;
    String cloc;

    public Details(){

    }

    public Details(String userid,String cname, String cserv, String cloc) {
        this.userid = userid;
        this.cname = cname;
        this.cserv = cserv;
        this.cloc = cloc;
    }

    public String userid() {
        return userid;
    }

    public String getCname() {
        return cname;
    }

    public String getCserv() {
        return cserv;
    }

    public String getCloc() {
        return cloc;
    }
}
