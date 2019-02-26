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
 
 
# STEP 3: Verify Data in Amazon Elastic Search Cluster
   
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
 
# STEP 4: Developed a Microservice which invoke Amazon Elastic Search Service
  
  1. Following Technologies (with their latest versions) have been used to develop a microservice.
  
      i.    Spring Boot: 2.1.3.RELEASE
  
      ii.   Spring: 5.1.5.RELEASE
  
      iii.  Elastic Search Library: 6.6.1
  
      iv.   httpcore: 4.4.11
  
      v.    httpclient: 4.5.7
  
      vi.   Gson: 2.8.5
  
      vii.  Hamcrest: 2.1
  
      viii. log4j2: 2.11.2
      
  
  2. Following are few things which have been taken into consideration while implementing this microservice.
  
      i.    Versioning has been done for the API.
      
      ii.   Excpetion handling has been done in a centralized place using Spring Controller Advice.
      
      iii.  Java 8 features have been used in coding. E.g. lanbda, streams
      
      iv.   Service layer has been written using Interface so that implementation can be changed without modifying most of the code.
      
      v.    Junits have been written for positive and negative scenarios.
      
      vi.   Logs have been written to external log files with rollover features.
      
      vii.  Validations have been done for the input data.
      
      viii. Custom Error Code and Error messages have been implemented for failure scenarios.
  
  
