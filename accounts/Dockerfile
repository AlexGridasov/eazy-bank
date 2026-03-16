#Start with a base image containing Java runtime
FROM eclipse-temurin:25-jdk

# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER alex.gri.com
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="alex.gri.com"

# Add the application's jar to the image
COPY target/accounts-1.0-SNAPSHOT.jar accounts-1.0-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts-1.0-SNAPSHOT.jar"]
