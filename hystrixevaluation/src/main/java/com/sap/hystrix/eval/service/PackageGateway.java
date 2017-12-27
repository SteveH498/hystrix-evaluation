package com.sap.hystrix.eval.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sap.hystrix.eval.controller.PackageController;
import com.sap.hystrix.eval.model.Package;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PackageGateway {

    private int requestCounter = 0;

    private static final Logger logger = LoggerFactory.getLogger(PackageController.class);

    private RedisConnectionFactory redisConnectionFactory;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public PackageGateway(RedisConnectionFactory redisConnectionFactory, RedisTemplate redisTemplate) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.redisTemplate = redisTemplate;
    }


    @HystrixCommand(groupKey = "IoT-AE", commandKey = "getPackages", fallbackMethod = "getPackagesFallback")
    public List<Package> getPackages() {
        if (requestCounter < 2) {
            requestCounter++;

            logger.info("Successful call");

            Package p = new Package("test.sandbox.zzvjr.bigdata.test", "1.0.0", "public", "/Configuration('test.sandbox.zzvjr.bigdata.test')");

            List<Package> packages = Arrays.asList(p, p);

            // Save last response
            redisTemplate.opsForValue().set("packages", packages);

            return packages;
        } else {

            logger.info("Exception");

            throw new RuntimeException();
        }
    }


    private List<Package> getPackagesFallback() {
        logger.info("Fallback executed");

        // Retrieve last response
        return (List<Package>) redisTemplate.opsForValue().get("packages");
    }


}
