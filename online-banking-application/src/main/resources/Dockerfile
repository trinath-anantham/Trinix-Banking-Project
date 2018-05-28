# Docker image that contains a Spring Boot application.
#
FROM anapsix/alpine-java:8_jdk_unlimited

# Absolute path to the JAR file to be launched when a Docker container is started.
ENV JAR_PATH=/application/lib/springboot-webapp.jar

# Create directory to hold the application and all its contents in the Docker image.
RUN mkdir /application
# Copy all the static contents to be included in the Docker image.
COPY ./application/ /application/
# Make all scripts in the bin directory executable. Includes start-script.
RUN chmod +x /application/bin/*.sh

# Web port.
EXPOSE 2018

CMD [ "/application/bin/start-app.sh" ]
