FROM gradle:8.5-jdk17-jammy
RUN mkdir /code
COPY . /code/
WORKDIR /code
RUN gradle clean build shadowJar
EXPOSE 8124
CMD java -jar build/libs/ExtremeStartupHttp4kServer.jar
