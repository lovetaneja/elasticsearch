package com.personalcapital.searchplans.service;

import com.personalcapital.searchplans.dto.ElasticSearchResponseDTO;

import java.util.Map;
import java.util.List;

/**
 * @author love_taneja
 * This is a Service Interface for searchPlans API
 */

public interface SearchService {
    List<ElasticSearchResponseDTO> searchPlans(Map<String, String> queryParams);
}
