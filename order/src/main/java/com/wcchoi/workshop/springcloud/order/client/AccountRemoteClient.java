package com.wcchoi.workshop.springcloud.order.client;


import com.wcchoi.workshop.springcloud.order.fallback.AccountRemoteClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "account", url = "http://localhost:8082/")
@FeignClient(name = "account", fallback = AccountRemoteClientFallback.class)
public interface AccountRemoteClient {

    @RequestMapping(path = "/accounts/{marketAccountNo}/{exchangeAccountNo}")
    String getOrderAccounts(@PathVariable("marketAccountNo") Long marketAccountNo
            , @PathVariable("exchangeAccountNo") Long exchangeAccountNo);

}
