# Micro service that invokes AWS elastic search and make it available using API gateway.

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
  
  4. Run logstash to populate data in Amazon Elastic Search Cluster.
     bin/logstash -f logstash-simple.conf
 
 
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
 
