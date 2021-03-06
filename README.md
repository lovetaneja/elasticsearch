# Micro Service with AWS Elastic Search and API Gateway

# STEP 1 - Set up Elastic Search Domain

  1. Created a new domain in AWS Elastic Search Service with following configurations:
  
      Availability zones: 1 
      
      Instance type: t2.small.elasticsearch
      
      Number of instances: 1
    
      Storage type: EBS
      
      EBS volume type: General Purpose (SSD)
      
      EBS volume size: 10 GB
   
   
# STEP 2 - Populate Data in Amazon Elastic Search Cluster

  1. Installed logstash.
 
  2. Installed logstash-output-amazon_es plugin. This is a connector for logstash and Amazon Elastic Search Cluster.
     
     Command to install plugin: sudo bin/logstash-plugin install logstash-output-amazon_es 
 
  3. Configured logstash config file to take input from CSV file and output it to Amazon Elastic Search Cluster. Please see checked in file (logstash-simple.conf).
  
  4. Run logstash to populate data in Amazon Elastic Search Cluster. It took 15-20 mins approx. to upload 219650 records.
  
     Command to run logstash: bin/logstash -f logstash-simple.conf
 
 
# STEP 3 - Verify Data in Amazon Elastic Search Cluster
   
   1. Go to Indices tab in Amazon Elastic Search Service and see number of indices created.
   
      Count: 219650
      
      Size in bytes: 511.79 MB
      
   2. Check data in Kibana UI by configuring indices.
   
      URL for Kibana: https://search-myelasticsearchcluster-3p7fvdqvbhbj4tpraoqpd36db4.us-east-1.es.amazonaws.com/_plugin/kibana/app/kibana#/discover?_g=()
   
   3. Use Rest APIs of Elastic Search to verify data.
   
      URL: https://search-myelasticsearchcluster-3p7fvdqvbhbj4tpraoqpd36db4.us-east-1.es.amazonaws.com/plans/_search
      
      Request Type: POST
      
      Header: Content-Type: application/json
      
      Sample Body 1:
      {
        "query": {
          "multi_match": {
            "query": "CO",
            "fields": ["SPONS_DFE_MAIL_US_STATE"]
          }
        }
      }
      
      Sample Body 2:
      {
        "query": {
          "multi_match": {
            "query": "401(K)",
            "fields": ["PLAN_NAME"]
          }
        }
      }
      
      Sample Body 3:
      {
        "query": {
          "multi_match": {
            "query": "MECHANICAL SOLUTIONS",
            "fields": ["SPONSOR_DFE_NAME"]
          }
        }
      }
 
# STEP 4 - Developed a Microservice which invoke Amazon Elastic Search Service
  
  1. Following libraries (with their latest versions) have been used to develop a microservice.
  
      i.    Spring Boot: 2.1.3.RELEASE
  
      ii.   Spring: 5.1.5.RELEASE
  
      iii.  Elastic Search Library: 6.6.1
  
      iv.   httpcore: 4.4.11
  
      v.    httpclient: 4.5.7
  
      vi.   Gson: 2.8.5
  
      vii.  Hamcrest: 2.1
  
      viii. log4j2: 2.11.2
      
      ix.   jacoco: 0.8.3 
      
  
  2. Following are few points which have been taken into consideration while implementing this microservice.
  
      i.    Versioning has been done for the API.
      
      ii.   Exception handling has been done in a centralized place using Spring Controller Advice.
      
      iii.  Java 8 features have been used in coding. E.g. lambda, streams etc.
      
      iv.   Service layer is loosely coupled so that implementation can be changed without modifying other components.
      
      v.    Junits have been written for positive and negative scenarios.
      
      vi.   Logs have been written to external log files with rollover features.
      
      vii.  Validations have been done for the input data.
      
      viii. Custom Error Code and Error messages have been implemented for failure scenarios.
      
      ix.   jacoco has been used for code coverage.
      
      
  3. Sample URLs
  
     Search by Plan Name: http://localhost:8080/v1/plans?query=planName:MECHANICAL%20SOLUTIONS

     Search by Sponsor Name: http://localhost:8080/v1/plans?query=sponsorName:SPECIALTY%20INSURANCE%20AGENCY

     Search by Sponsor State: http://localhost:8080/v1/plans?query=sponsorState:CA
     
     
  4. Sample Success Response:
  
     [
      {
        "sponsDefLocForeignAddress1": null,
        "typeWelfareBnftCode": null,
        "numSchAAttachedCnt": "0",
        "preparerFirmName": null,
        "preparerForeignProvState": null,
        "sponsDefMailUsZip": "99511",
        "lastRptPlanNum": null,
        "adminForeignCity": null,
        "sponsSignedName": null,
        "adminForeignAddress2": null,
        "schRAttachedInd": "0",
        "preparerForeignPostalCd": null,
        "sponsDefMailUsCity": "ANCHORAGE",
        "sponsDefPn": "001",
        "sepPartcpPartlVstdCnt": "0",
        "schSbAttachedInd": "0",
        "adminNameSameSponInd": "1",
        "sponsDefLocForeignAddress2": null,
        "typeDefPlanEntityCd": null,
        "sponsSignedDate": null,
        "sponsDefMailForignCntry": null,
        "adminUsState": null,
        "adminForeignPostalCd": null,
        "defSignedName": null,
        "dfvcProgramInd": "0",
        "sponsDefEin": "260391445",
        "lastRptPlanName": null,
        "adminManualSignedDate": null,
        "host": "M223318ASHTDD",
        "sponsDefMailForeignAddr1": null,
        "extSpecialText": null,
        "fundingTrustInd": "1",
        "finalFilingInd": "0",
        "partcpAccountBalCnt": "5",
        "fundingGenAssetInd": "0",
        "sponsDefMailForeignCity": null,
        "getSponsDefLocForeignAddress2": null,
        "preparerUsAddress1": null,
        "sponsDefLocUsZip": "99507",
        "preparerUSAddress2": null,
        "fundingSec412Ind": "0",
        "lastRptSponsName": null,
        "benefitSec412Ind": "0",
        "formPlanYearBeginDate": "2017-01-01",
        "adminPhoneNumForeign": null,
        "preparerName": null,
        "adminUsCity": null,
        "m1ReceiptConfirmationCode": null,
        "preparerForeignAddress1": null,
        "benefitTrustInd": "1",
        "dateReceived": "2018-02-21",
        "version": "1",
        "sponsDefDbaName": null,
        "sponsDefPhoneNum": "9073349322",
        "adminSignedDate": "2018-02-21T16:11:13-0600",
        "benefRcvgBnftCnt": "0",
        "rtdSepPartcpRcvgCnt": "0",
        "shortPlanYrInd": "0",
        "defSignedDate": null,
        "fundingInsuranceInd": "0",
        "schDAttachedInd": "0",
        "adminPhoneNum": null,
        "adminUSAddress2": null,
        "sponsDefLocUsAddress2": null,
        "adminUsZip": null,
        "filingStatus": "FILING_RECEIVED",
        "validSponsorSignature": null,
        "subjM1FilingReqInd": null,
        "sponsManualSignedDate": null,
        "totPartcpBoyCnt": "5",
        "preparerUsCity": null,
        "adminManualSignedName": null,
        "sponsDefPhoneNumForeign": null,
        "benefitInsuranceInd": "0",
        "sponsDefLocForgnPostalCd": null,
        "timestamp": "2019-02-23T03:45:38.974Z",
        "schGAttachedInd": "0",
        "adminAddressSameSponInd": null,
        "sponsDefMailForgnProvSt": null,
        "lastRptSponsEin": null,
        "adminSignedName": "ERNEST BELANGER III",
        "planDefDate": "2008-01-01",
        "adminForeignCntry": null,
        "sponsDefLocUSCity": "ANCHORAGE",
        "contribEmplrsCnt": null,
        "schHAttachedInd": "0",
        "adminForeignAddress1": null,
        "preparerPhoneNum": null,
        "adminUsAddress1": null,
        "planName": "MECHANICAL SOLUTIONS INC 401(K) PLAN",
        "collectiveBargainInd": "0",
        "extAutomaticInd": "0",
        "sponsDefMailUsAddress1": "PO BOX 111648",
        "sponsDefLocUsAddress1": "2261 CINNABAR LOOP #B",
        "defManualSignedDate": null,
        "formTaxPrd": "2017-12-31",
        "sponsDefLocForeignCntry": null,
        "sponsDefMailUsState": "AK",
        "rtdSepPartchFunCnt": "1",
        "complaineM1FilingReqInd": null,
        "preparerForeignAddress2": null,
        "sponsManualSignedName": null,
        "subtlActRtdSepCnt": "5",
        "schAAttachedInd": "0",
        "sponsDefCareOfName": null,
        "ackId": "20180221161428P040094915783001",
        "schIAttachedInd": "1",
        "preparerPhoneNumForeign": null,
        "validDefSignature": null,
        "schCAttachedInd": "0",
        "preparerUsState": null,
        "typePlanEntiyCd": "2",
        "sponsDefLocForgnProvSt": null,
        "schMBAttachedInd": "0",
        "adminForeignProvState": null,
        "validAdminSignature": "Filed with authorized/valid electronic signature",
        "sponsorDefName": "MECHANICAL SOLUTIONS, INC.",
        "sponsDefMailUsAddress2": null,
        "totActPartcpBoyCnt": "5",
        "f5558ApplicationFiledInd": "0",
        "extSpecialInd": "0",
        "amendedInd": "0",
        "adminCareOfName": null,
        "initialFilingInd": "0",
        "typePensionBnftCode": "2A2E2F2G2J2K2R3D",
        "preparerForeignCity": null,
        "sponsDefLocUSState": "AK",
        "preparerForeignCntry": null,
        "benefitGenAssetInd": "0",
        "preparerUsZip": null,
        "path": "/Users/love_taneja/Desktop/PersonalCap/Coding-Round/data/F_5500_2017_Latest/f_5500_2017_latest.csv",
        "defManualSignedName": null,
        "sponsDefMailForgnPostalCd": null,
        "totActivePartcpCnt": "4",
        "message": "\"20180221161428P040094915783001\",\"2017-01-01\",\"2017-12-31\",\"2\",,\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",,\"MECHANICAL SOLUTIONS INC 401(K) PLAN\",\"001\",\"2008-01-01\",\"MECHANICAL SOLUTIONS, INC.\",,,\"PO BOX 111648\",,\"ANCHORAGE\",\"AK\",\"99511\",,,,,,,\"2261 CINNABAR LOOP #B\",,\"ANCHORAGE\",\"AK\",\"99507\",,,,,,,\"260391445\",\"9073349322\",\"423700\",,,,,,,,,,,,,,,,,,,\"2018-02-21T16:11:13-0600\",\"ERNEST BELANGER III\",,,,,5,4,0,1,5,0,5,5,0,,\"2A2E2F2G2J2K2R3D\",,\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"0\",\"0\",\"FILING_RECEIVED\",\"2018-02-21\",\"Filed with authorized/valid electronic signature\",,,,,\"1\",,,,,,,,,,,,,,,,,5,,,,,,,,,,\r",
        "totActRtdSepBenefCnt": "5",
        "sponsdefLocForeignCity": null,
        "businessCode": "423700",
        "adminEin": null,
        "adminName": null
    }
  ]
      
      
 5. Sample Error Response  
 
    {
      "url": "http://localhost:8080/v1/plans",
      "errorCode": 1002,
      "errorMessage": "Error Response from Search API - Query Parameter Pattern is Not Valid"
    }
    
 6. Following will be Http Status for different scenarios. These have been configured in GlobalExceptionHandler class.
 
    (200, "OK"): This will be Http Status for Success Scenarios.
    
    (400, "Bad Request"): This will be Http Status if incoming request/query parameters are not correct.
    
    (503, "Service Unavailable"): This will be Http Status if service is not able to connect to Amazon Elastic Search.
    
    (500, "Internal Server Error"): This will be Http Status if any generic exception is there.
  
  
# STEP 5 - Created Deployment Shell Script, Externalized Properties files and Deployed on Amazon EC2 Private Instance. 
  
  1. Wrote a shell script which will run executable jar file to start the spring boot application.
  
  2. application.properties and log4j2.xml files have been externalized as values in these files will be environment specific.
  
  3. Created an EC2 instance in a private subnet using CloudFormation template along with other supporting components (Nat Gateway, Elastic IP, Bastion Host, Elastic Load Balacer, Security Groups, Routing Tables, Routes, Internet Gateway etc.)
  
  4. Deployed application on private EC2 instance.
  
  5. Tested application with following URLs.
  
      http://myloadbalancer-1404011250.us-east-1.elb.amazonaws.com/v1/plans?query=planName:MECHANICAL%20SOLUTIONS


      http://myloadbalancer-1404011250.us-east-1.elb.amazonaws.com/v1/plans?query=sponsorName:SPECIALTY%20INSURANCE%20AGENCY


      http://myloadbalancer-1404011250.us-east-1.elb.amazonaws.com/v1/plans?query=sponsorState:CA
      
 
 # STEP 6 - Set Up API Gateway
 
  1. Wrote a Cloudformation Template to create ApiGateway, ApiGateway Method, ApiGateway Deployment and Stage.
  
  2. Tested it with following URLs:
  
     https://a21s0in0y6.execute-api.us-east-1.amazonaws.com/v1?query=planName:MECHANICAL%20SOLUTIONS
     
     https://a21s0in0y6.execute-api.us-east-1.amazonaws.com/v1?query=sponsorName:SPECIALTY%20INSURANCE%20AGENCY
     
     https://a21s0in0y6.execute-api.us-east-1.amazonaws.com/v1?query=sponsorState:CA
     
  3. Added a Lambda Authorizer in API Gateway and attached it to GET method in resource. A lambda funtion has been written to autorize the request based upon the authorizationToken in the header. Following is the sample input to Lambda Authorizer function.
    {
      "type":"TOKEN",
      "authorizationToken":"allow",
      "methodArn":"arn:aws:execute-api:us-east-1:123456789012:ymy8tbxw7b/*/GET/"
    }
    
  4. If we try to access URLs in step #2 above, we will get 401 Unauthorized http status. We need to add header for authorizationToken in request to access the API.
  
  5. As of now, lambda funtion is having logic for few hardcoded values for authorizationToken. It needs to be enhanced to actually validate the token against some provider (e.g. google token service). Client who is using this API needs to generate token against the same provider.
  
  6. Following are sample http response status returned from lambda function.
  
      200 OK: If authorization token has a 'allow' value
  
      403 Forbidden: If authorization token has a 'deny' value
      
      401 Unauthorized: If authorization token has a 'unauthorized' value or if it is not provided
      
      500 Internal Server: If authorization token has a value 'fail' or anything else
     

# NEXT STEPS

  Following are the next steps which I want to implement further.
  
  1. Make Elastic Search Domain more secure by choosing 'VPC Access' instead of 'Public Access'. Configure private VPC, Security Groups, IAM Role and Domain Access Policy. Write a Cloudformation template.
  
  2. Write more JUnits to increase the code coverage.
  
  3. Implement PMD and Checstyle plugins to improve the code quality.
  
  4. Set up Jenkins for Continuous Integration (CI) and Continuous Delivery/Continuous Deployment (CD). We can use Jenkins Pipeline plugin.
  
  5. Containerization: We can use docker.
  
  6. Write shut down script for graceful shutdown of Spring Boot application. 
  
  7. Pagination and Sorting of API's output.
  
  8. Support for search by combination of query parameters.
