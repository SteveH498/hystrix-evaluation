package com.sap.hystrix.eval.controller;

import com.sap.hystrix.eval.model.Package;
import com.sap.hystrix.eval.service.PackageGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PackageController {

    @Autowired
    PackageGateway packageGateway;


    @GetMapping(path = "/packages")
    ResponseEntity<List<Package>> getPackages() {
        List<Package> packages = packageGateway.getPackages();
        return new ResponseEntity<List<Package>>(packages, null, HttpStatus.OK);
    }


}
