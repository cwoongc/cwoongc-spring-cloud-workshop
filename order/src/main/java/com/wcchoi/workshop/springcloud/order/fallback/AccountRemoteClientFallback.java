package com.wcchoi.workshop.springcloud.order.fallback;

import com.wcchoi.workshop.springcloud.order.client.AccountRemoteClient;
import org.springframework.stereotype.Component;

@Component
public class AccountRemoteClientFallback implements AccountRemoteClient {
//public class AccountRemoteClientFallback {

    public String getOrderAccounts(Long marketAccountNo, Long exchangeAccountNo, Throwable t) {
        System.out.println(String.format(
                "[%s]%s"
                ,System.currentTimeMillis()
                ,t));
        return "FALLBACK!! ACCOUNT SERVICE IS NOT AVAILABLE FOR THE MOMENTS.";
    }

//    @Override
    public String getOrderAccounts(Long marketAccountNo, Long exchangeAccountNo) {
        return "THROWABLE !!!";
    }
}
