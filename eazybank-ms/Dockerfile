#Start with a base image containing Java runtime
FROM eclipse-temurin:25-jre-alpine

#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="alex.gri.com"

#Install curl for health checks
RUN apk add --no-cache curl
