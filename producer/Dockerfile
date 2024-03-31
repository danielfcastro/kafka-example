FROM openjdk:22
LABEL authors="daniel castro"

ARG MAVEN_VERSION=3.9.6
ARG USER_HOME_DIR="/root"
ARG SHA=706f01b20dec0305a822ab614d51f32b07ee11d0218175e55450242e49d2156386483b506b3a4e8a03ac8611bae96395fd5eec15f50d3013d5deed6d1ee18224
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
CMD ["mvn"]
# cd /opt/app
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
# Adjusting the module's pom.xml to reference the new parent-pom.xml
RUN sed -i 's/<relativePath>..\/pom.xml<\/relativePath>/<relativePath>.\/parent-pom.xml<\/relativePath>/' /usr/src/myapp/pom.xml
RUN mvn clean install
# Refer to Maven build -> finalName
ARG JAR_FILE=target/producer-1.0-SNAPSHOT.jar

# cp target/spring-boot-web.jar /opt/app/app.jar
RUN cp ${JAR_FILE} /usr/src/myapp/app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]