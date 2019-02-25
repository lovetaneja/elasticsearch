package com.personalcapital.searchplans.service;

import java.util.Map;
import java.util.List;

/**
 * @author love_taneja
 * This is a Service Interface for searchPlans API
 */

public interface SearchService {
    List<String> searchPlans(Map<String, String> queryParams);
}
