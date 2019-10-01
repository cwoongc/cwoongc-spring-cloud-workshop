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
    private final String orderAccountsUrl = "http://account/accounts/%d/%d";


    /**
     * https://github.com/Netflix/Hystrix/wiki/Configuration
     * @param orderNo
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
    public String getOrderAccounts(Long orderNo) {

        Long[] orderAccountsNo = getOrderAccountsNo(orderNo);

        return restTemplate.getForObject(
                URI.create(String.format(orderAccountsUrl,orderAccountsNo[0],orderAccountsNo[1]))
                , String.class
        );
    }

    private Long[] getOrderAccountsNo(Long orderNo) {

        Long[] accountsNo = {12345L, 67890L};

        return accountsNo;
    }

    public String getOrderAccountsFallback(Long orderNo, Throwable t) {
        System.out.println(String.format(
                "[%s]%s"
                ,System.currentTimeMillis()
                ,t));
        return "ACCOUNT SERVICE IS NOT AVAILABLE FOR THE MOMENTS.";
    }
}
