package com.personalcapital.searchplans;

import com.personalcapital.searchplans.common.SearchConstants;
import com.personalcapital.searchplans.dto.ElasticSearchResponseDTO;
import com.personalcapital.searchplans.exception.ApiException;
import com.personalcapital.searchplans.exception.ErrorCodes;
import com.personalcapital.searchplans.service.SearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author love_taneja
 * JUnits for Search Api
 */
public class SearchApiTest extends AbstractTestClass {

    private static final Logger log = LogManager.getLogger(SearchApiTest.class);

    @Autowired
    SearchService elasticSearchService;

    @Test
    public void testForSearchByPlanName(){
        try{
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put(SearchConstants.QUERY_PARAM_PLAN_NAME, "MECHANICAL SOLUTIONS");
            List<ElasticSearchResponseDTO> list = elasticSearchService.searchPlans(queryParams);
            Assert.assertThat(list.isEmpty(),  is(false));
        }catch(IOException ioException){
            log.error("IOException Occured while executing testForSearchByPlanName test case", ioException);
            Assert.assertFalse(true);
        }catch(ApiException apiException){
            log.error("API Occured while executing testForSearchByPlanName test case", apiException);
            Assert.assertFalse(true);
        }
    }

    @Test
    public void testForSearchByPlanNameEmptyResponse(){
        try{
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put(SearchConstants.QUERY_PARAM_PLAN_NAME, "ZZZZ");
            List<ElasticSearchResponseDTO> list = elasticSearchService.searchPlans(queryParams);
            Assert.assertEquals(0,  list.size());
        }catch(IOException ioException){
            log.error("IOException Occured while executing testForSearchByPlanName test case", ioException);
            Assert.assertFalse(true);
        }catch(ApiException apiException){
            log.error("API Occured while executing testForSearchByPlanName test case", apiException);
            Assert.assertFalse(true);
        }
    }

    @Test
    public void testForSearchBySponsorName(){
        try{
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put(SearchConstants.QUERY_PARAM_SPONSOR_NAME , "SPECIALTY INSURANCE AGENCY");
            List<ElasticSearchResponseDTO> list = elasticSearchService.searchPlans(queryParams);
            Assert.assertThat(list.isEmpty(),  is(false));
        }catch(IOException ioException){
            log.error("IOException Occured while executing testForSearchByPlanName test case", ioException);
            Assert.assertFalse(true);
        }catch(ApiException apiException){
            log.error("API Occured while executing testForSearchByPlanName test case", apiException);
            Assert.assertFalse(true);
        }
    }

    @Test
    public void testForSearchBySponsorNameEmptyResponse(){
        try{
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put(SearchConstants.QUERY_PARAM_SPONSOR_NAME , "AAAAA");
            List<ElasticSearchResponseDTO> list = elasticSearchService.searchPlans(queryParams);
            Assert.assertEquals(0,  list.size());
        }catch(IOException ioException){
            log.error("IOException Occured while executing testForSearchByPlanName test case", ioException);
            Assert.assertFalse(true);
        }catch(ApiException apiException){
            log.error("API Occured while executing testForSearchByPlanName test case", apiException);
            Assert.assertFalse(true);
        }
    }

    @Test
    public void testForSearchBySponsorState(){
        try{
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put(SearchConstants.QUERY_PARAM_SPONSOR_STATE , "AZ");
            List<ElasticSearchResponseDTO> list = elasticSearchService.searchPlans(queryParams);
            Assert.assertThat(list.isEmpty(),  is(false));
        }catch(IOException ioException){
            log.error("IOException Occured while executing testForSearchByPlanName test case", ioException);
            Assert.assertFalse(true);
        }catch(ApiException apiException){
            log.error("API Occured while executing testForSearchByPlanName test case", apiException);
            Assert.assertFalse(true);
        }
    }

    @Test
    public void testForSearchBySponsorStateEmptyResponse(){
        try{
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put(SearchConstants.QUERY_PARAM_SPONSOR_STATE , "BBBBB");
            List<ElasticSearchResponseDTO> list = elasticSearchService.searchPlans(queryParams);
            Assert.assertEquals(0,  list.size());
        }catch(IOException ioException){
            log.error("IOException Occured while executing testForSearchByPlanName test case", ioException);
            Assert.assertFalse(true);
        }catch(ApiException apiException){
            log.error("API Occured while executing testForSearchByPlanName test case", apiException);
            Assert.assertFalse(true);
        }
    }

    @Test
    public void testForNullQueryParameters(){
        try{
            Map<String, String> queryParams = null;
            List<ElasticSearchResponseDTO> list = elasticSearchService.searchPlans(queryParams);
        }catch(IOException ioException){
            log.error("IOException Occured while executing testForSearchByPlanName test case", ioException);
        }catch(ApiException apiException){
            log.error("API Occured while executing testForSearchByPlanName test case", apiException);
            Assert.assertEquals(ErrorCodes.QUERY_PARAMETER_MAP_NULL_ERROR_CODE, apiException.getCode());
        }
    }

    @Test
    public void testForEmptyQueryParameters(){
        try{
            Map<String, String> queryParams = new HashMap<>();
            List<ElasticSearchResponseDTO> list = elasticSearchService.searchPlans(queryParams);
        }catch(IOException ioException){
            log.error("IOException Occured while executing testForSearchByPlanName test case", ioException);
        }catch(ApiException apiException){
            log.error("API Occured while executing testForSearchByPlanName test case", apiException);
            Assert.assertEquals(ErrorCodes.QUERY_PARAMETER_MAP_EMPTY_ERROR_CODE, apiException.getCode());
        }
    }

    @Before
    public void setup(){
        log.info("Inside setup() Method");
    }

    @After
    public void tearDown(){
        log.info("Inside tearDown() Method");
    }

}
