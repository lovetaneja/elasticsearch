package com.personalcapital.searchplans.service;

import com.personalcapital.searchplans.dto.ElasticSearchResponseDTO;
import com.personalcapital.searchplans.exception.ApiException;

import java.io.IOException;
import java.util.Map;
import java.util.List;

/**
 * @author love_taneja
 * This is a Service Interface for searchPlans API
 */

public interface SearchService {
    /**
     * @param queryParams
     * @return List<ElasticSearchResponseDTO>
     * @throws IOException
     * @throws ApiException
     */
    List<ElasticSearchResponseDTO> searchPlans(Map<String, String> queryParams) throws IOException, ApiException;
}