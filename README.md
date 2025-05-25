## Objective 
This project provides a modular and scalable test framework with readable code for validating Google Cloud Storage CLI commands.
It uses Java, TestNG, and Maven to automate and verify command-line operations across different environments. 
*** The 4 CLI gcloud storage commands I tested are: **hash**, **cp**, **sign-url** and **mv**.

## Requirements 
1. Java 11 or higher
2. Maven 3.6+
3. Google Cloud SDK installed and authenticated (gcloud auth login)
4. GCP account and a created empty bucket (called mend-bucket)
5. Creating an impersonated service account and giving it Storage Object Viewer role.

## How to Run
**1. Clone the project** 
  1. git clone https://github.com/GeorgeTannous98/mend.git
  2. cd mend
     
**2. Configure Google Cloud**
  1. gcloud auth login.
  2. gcloud config set project [YOUR_PROJECT_ID] 
      
**3. Build the project and run the tests using Maven**   
  1. mvn clean install -DskipTests
  2. mvn test -DsuiteXmlFile="src/test/resources/suites/gcloud-storage-suite.xml"
