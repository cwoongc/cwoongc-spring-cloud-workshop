package com.wcchoi.workshop.springcloud.order.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class AccountRemoteServiceImpl implements AccountRemoteService {

    @Autowired private RestTemplate restTemplate;

//    private final String orderAccountsUrl = "http://localhost:8082/accounts/%d/%d";
    private final String TARGET_SERVICE_NAME = "account";
    private final String orderAccountsUrl = "http://" + TARGET_SERVICE_NAME + "/accounts/%d/%d";


    /**
     * https://github.com/Netflix/Hystrix/wiki/Configuration
     * @param marketAccountNo
     * @param exchangeAccountNo
     * @return
     */
    @HystrixCommand(fallbackMethod = "getOrderAccountsFallback",
            // Declare circuit & circuit id. (default: method name)
            commandKey = "getOrderAccounts",

            //Assign (or declare & assign) thread pool to this DependencyCommand by thread pool name (default: class name)
            groupKey = "getOrderAccountsThreads",

            //Configuration for execution.isolation, fallback, circuitBreaker, metrics
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
            },

            threadPoolProperties = {
//                @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "true"),
//                @HystrixProperty(name = "maximumSize", value = "20"),
                @HystrixProperty(name = "coreSize", value = "10"),
//                @HystrixProperty(name = "keepAliveTimeInMinutes", value = "2"),

                @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                @HystrixProperty(name = "maxQueueSize", value = "101"),

                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10")
            }
    )
    @Override
    public String getOrderAccounts(Long marketAccountNo, Long exchangeAccountNo) {

        return restTemplate.getForObject(
                URI.create(String.format(orderAccountsUrl, marketAccountNo, exchangeAccountNo))
                , String.class
        );
    }


    public String getOrderAccountsFallback(Long marketAccountNo, Long exchangeAccountNo, Throwable t) {
        System.out.println(String.format(
                "[%s]%s"
                ,System.currentTimeMillis()
                ,t));
        return "ACCOUNT SERVICE IS NOT AVAILABLE FOR THE MOMENTS.";
    }
}
