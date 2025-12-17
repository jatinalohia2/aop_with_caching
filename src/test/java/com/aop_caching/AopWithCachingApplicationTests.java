package com.aop_caching;


import com.aop_caching.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopWithCachingApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    void testPlaceOrder(){
        orderService.placeOrder(1L);
    }

    @Test
    void testCancelOrder(){
        orderService.cancelOrder(1L);
    }

}
