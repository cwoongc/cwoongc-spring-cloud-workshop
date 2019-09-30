package com.wcchoi.workshop.springcloud.order.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class AccountRemoteServiceImpl implements AccountRemoteService {

    @Autowired private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getOrderAccountsFallback")
    @Override
    public String getOrderAccounts(Long orderNo) {

        Long[] orderAccountsNo = getOrderAccountsNo(orderNo);

        return restTemplate.getForObject(
                URI.create(String.format("http://localhost:8082/accounts/%d/%d",orderAccountsNo[0],orderAccountsNo[1]))
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
