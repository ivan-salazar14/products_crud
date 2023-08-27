FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/products-0.0.1-SNAPSHOT.jar products.jar
EXPOSE 8080
EXPOSE 8081
ENTRYPOINT exec java $JAVA_OPTS -jar products.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar products.jar
