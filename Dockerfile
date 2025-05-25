ARG GCLOUD_VERSION=latest

FROM google/cloud-sdk:$GCLOUD_VERSION

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        openjdk-17-jdk \
        maven \
        libgbm1 \
        libxkbcommon0 && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

## libgbm1 and libxkbcommon0 are the same as mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install-deps"

COPY . /app
WORKDIR /app

RUN mvn exec:java -e -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install chromium" && \
    mvn clean install -DskipTests  # Resolves dependencies but skips tests

ENTRYPOINT ["mvn"]
CMD ["test"]