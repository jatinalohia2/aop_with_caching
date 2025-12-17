package com.aop_caching.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrderService {

    public String placeOrder(Long orderId){

        try {
            log.info("Order Placed");
        } catch (Exception e) {
            log.info("something went wrong {}",e.getLocalizedMessage());
        }

        return "Order Successfully placed";
    }

    @Transactional
    public String cancelOrder(Long orderId){

        try {
            log.info("Order Cancelled");
        } catch (Exception e) {
            log.info("something went wrong {}",e.getLocalizedMessage());
        }

        return "Order Successfully cancelled";
    }


}
