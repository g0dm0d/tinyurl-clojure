FROM clojure:temurin-22-lein-2.11.2-alpine as build

WORKDIR /usr/src/app

COPY . .

RUN lein uberjar

FROM eclipse-temurin:22-jre-alpine

ENV DB_DRIVER=postgresql
ENV DB_NAME=postgres
ENV DB_USER=postgres
ENV DB_PASSWORD=12345
ENV DB_HOST=tinyurl-postgres


WORKDIR /app

COPY --from=build /usr/src/app/target/tinyurl.jar .

CMD ["java", "-jar", "tinyurl.jar"]
