package com.sap.hystrix.eval.model;

import java.io.Serializable;

public class Package implements Serializable {

    private String id; //id: "test.sandbox.zzvjr.bigdata.siemens",
    private String version; //version: "1.0.0",
    private String scope; // scope: "public",
    private String services; // services: "/Configuration('test.sandbox.zzvjr.bigdata.siemens')"

    public Package(String id, String version, String scope, String services) {
        this.id = id;
        this.version = version;
        this.scope = scope;
        this.services = services;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }
}
