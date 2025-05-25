# ☁️ GCloud Storage CLI Automation Framework

An automation testing framework built in *Java*, using *TestNG* and *Docker*, designed to validate the behavior and output of gcloud storage CLI commands. The framework is extendable, scalable, and supports testing across multiple versions of the Google Cloud SDK (gcloud CLI).

### ✅ Key Features
- Automated testing for gcloud storage commands including sign-url, cp, hash, and mv.
- Tests run independently and in parallel (via TestNG).
- Supports different gcloud versions via Docker build arguments.
- Validates signed URLs for accessibility and phishing safety using Playwright Chromium.
- Framework designed for extensibility (easy to add more commands and suites).

---
## 🔧 Requirements

Before running the tests, ensure you have the following:

1. *Google Cloud Project* with billing enabled.
2. *Google Cloud Service Account* with the following roles:
   - Storage Admin (for creating/deleting buckets and uploading files)
   - Service Account Token Creator (for signing URLs)

3. *Service Account Key JSON file* downloaded locally (e.g., gcloud-key.json).

4. *Pre-created Storage Bucket* (empty) — required for tests.
   - Example bucket name: storage-bucket
   NOTE: we can create more buckets for multiple test to run in parallel.
   in this case i made it per Suite,
   I made a Suite for each main command like "gcloud storage"
   Inside it, we can do groups for specific tests.

---
## 🚀 How to Run

### 1️⃣ Clone the Repo
```
bash
git clone https://github.com/GeorgeTannous98/mend.git \
cd mend
```
#### Add Your Service Account Key
Place your downloaded service account key JSON file in the project root:
/gcloud-key.json or somewhere safe on your machine.
which can be set using -v docker command

#### Environment Variables
```
GCLOUD_STORAGE_BUCKET=<BUCKET_NAME> - NOTE: Defaults to "gcloud-storage-bucket"
GCLOUD_PROJECT_ID=<YOUR-PROJECT-ID>
```
#### Running Docker

Example docker build:
```
docker build . --build-arg GCLOUD_VERSION=523.0.1 -t gcloud-test:523.0.1
```
NOTE: according to Dockerfile we default to "latest" tag
```
docker run \
  -e GCLOUD_STORAGE_BUCKET=storage-bucket \
  -e GCLOUD_PROJECT_ID=projectId \
  -e GOOGLE_APPLICATION_CREDENTIALS=/app/gcloud-key.json
  -v <PATH FROM YOUR MACHINE>/gcloud-key.json:/app/gcloud-key.json \
  gcloud-cli-tests
```
Example
```
docker run --rm -e GCLOUD_STORAGE_BUCKET=mend-bucket -e GCLOUD_PROJECT_ID=avid-garage-460619-q3 -e GOOGLE_APPLICATION_CREDENTIALS=/app/gcloud-key.json -v D:\github\mend\gcloud-key.json:/app/gcloud-key.json gcloud-test:523.0.1
```

Specific Test Suite 

Example
```
docker run --rm -e GCLOUD_STORAGE_BUCKET=mend-bucket -e GCLOUD_PROJECT_ID=avid-garage-460619-q3 -e GOOGLE_APPLICATION_CREDENTIALS=/app/gcloud-key.json -v D:\github\mend\gcloud-key.json:/app/gcloud-key.json gcloud-test:523.0.1 test -DsuiteXmlFile=src/test/resources/suites/gcloud-storage-suite.xml
```
 
Specific Group test 

Example
```
docker run --rm -e GCLOUD_STORAGE_BUCKET=mend-bucket -e GCLOUD_PROJECT_ID=avid-garage-460619-q3 -e GOOGLE_APPLICATION_CREDENTIALS=/app/gcloud-key.json -v D:\github\mend\gcloud-key.json:/app/gcloud-key.json gcloud-test:523.0.1 test -Dgroups=hash
```

---

## Creating new tests
In order to add new commands other than `storage` add a new suite. \
In order to add new sub-commands for a command add a new class for each command. 

**References**

[Google Cloud CLI Docs](https://cloud.google.com/sdk/docs) 

[TestNG Official Docs](https://testng.org/#_testng_documentation) 

[Docker Docs](https://docs.docker.com/reference/dockerfile/) 

[Playwright Java Docs](https://playwright.dev/java/docs/writing-tests) 

[Java Documentation](https://docs.oracle.com/en/java/). 

[Google Cloud Storage Documentation](https://cloud.google.com/sdk/gcloud).

[Apache Maven Official Site](https://maven.apache.org/).
