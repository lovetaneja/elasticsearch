package com.personalcapital.searchplans.service.impl;

import org.springframework.stereotype.Service;
import com.personalcapital.searchplans.service.SearchService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author love_taneja
 * This is a Service Class for Elastic Search Service for searchPlans API
 */
@Service("elasticSearchService")
public class ElasticSearchServiceImpl implements SearchService {

    /**
     * @param queryParams
     * @return List of Plans
     */
    public List<String> searchPlans(Map<String, String> queryParams){
        List<String> s = new ArrayList<>();
        s.add("love");
        s.add("taneja");
        return s;
    }

}