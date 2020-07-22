package com.sli;

import com.sli.service.MaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TestService {

    @Autowired
    private Map<String, MaoService> maoServiceMap;

    @Autowired
    private List<MaoService> maoServiceList;


    public void  sendMap(){
//        this.maoServiceMap.get("cat").say();
        this.maoServiceMap.get("Cat").say();
    }

    public void  sendList(){
        this.maoServiceList.get(0).say();
    }

}
