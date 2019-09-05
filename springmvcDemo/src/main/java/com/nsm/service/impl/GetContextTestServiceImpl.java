package com.nsm.service.impl;

import com.nsm.service.CommonAbstract;
import com.nsm.service.GetContextTestService;
import org.springframework.stereotype.Service;

@Service
public class GetContextTestServiceImpl extends CommonAbstract implements GetContextTestService {
    @Override
    public String toString() {
        return "I'm GetContextTestServiceImpl";
    }
}
