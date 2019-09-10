package com.nsm.activemqtest;

import org.springframework.stereotype.Service;


public interface ProduceService {

    void produceMessage(String msg);
}
