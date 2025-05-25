# ☁️ GCloud Storage CLI Automation Framework

An automation testing framework built in **Java**, using **TestNG** and **Docker**, designed to validate the behavior and output of `gcloud storage` CLI commands. The framework is extendable, scalable, and supports testing across multiple versions of the Google Cloud SDK (gcloud CLI).

### ✅ Key Features
- Automated testing for `gcloud storage` commands including `sign-url`, `upload`, `hash`, and `rename`.
- Tests run independently and in parallel (via TestNG).
- Supports multiple gcloud versions via Docker build arguments.
- Validates signed URLs for accessibility and phishing safety using Playwright Chromium.
- Framework designed for extensibility (easy to add more commands and suites).

---
## 🔧 Requirements

Before running the tests, ensure you have the following:

1. **Google Cloud Project** with billing enabled.
2. **Google Cloud Service Account** with the following roles:
   - Storage Admin *(for creating/deleting buckets and uploading files)*
   - Service Account Token Creator *(for signing URLs)*

3. **Service Account Key JSON file** downloaded locally (e.g., `gcloud-key.json`).

4. **Pre-created Storage Bucket** (empty) — required for tests.
   - Example bucket name: `mend-bucket`

---
## 🚀 How to Run

### 1️⃣ Clone the Repo
bash
git clone https://github.com/GeorgeTannous98/mend.git \
cd mend

Add Your Service Account Key
Place your downloaded service account key JSON file in the project root:
/gcloud-key.json

pass thsoe using -e in docker as environment variables.
GCLOUD_BUCKET_NAME=mend-bucket
GCLOUD_PROJECT_ID=your-project-id

docker build -t gcloud-cli-tests .

docker run \
  -e GCLOUD_BUCKET_NAME=my-test-bucket \
  -e GCLOUD_PROJECT_ID=your-project-id \
  -v $PWD/gcloud-key.json:/app/gcloud-key.json \
  gcloud-cli-tests

can run a specific test suite
docker run gcloud-cli-tests mvn test -DsuiteXmlFile=src/test/resources/suites/gcloud-storage-suite.xml

or run a test group \
docker run -e TEST_GROUP=upload gcloud-cli-tests mvn test -Dgroups=upload

---

Add test groups for finer control
--

:shield: Security & Phishing Check
For sign-url tests, the framework uses Playwright Chromium to validate that the signed URL is accessible and not flagged as a phishing attempt.

--

:books: References \
Google Cloud CLI Docs

TestNG Official Docs

Docker Docs

Playwright Java Docs
