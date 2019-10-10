package com.wcchoi.workshop.springcloud.order.service;

public interface AccountRemoteService {
    String getOrderAccounts(Long marketAccountNo, Long exchangeAccountNo);
}
