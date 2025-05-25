ARG GCLOUD_VERSION=latest

FROM google/cloud-sdk:$GCLOUD_VERSION

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

COPY . /app
WORKDIR /app

RUN mvn exec:java -e -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install chromium"

CMD ["mvn", "test"]