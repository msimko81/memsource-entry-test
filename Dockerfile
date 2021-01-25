# syntax=docker/dockerfile:experimental
FROM gradle:6.7.1-jdk11 as build
WORKDIR /app

COPY --chown=gradle:gradle . /app

RUN echo 'source changed 6'

RUN --mount=type=cache,target=/root/.gradle gradle build --no-daemon --info --stacktrace
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM openjdk:11-jdk
ARG DEPENDENCY=/app/build/dependency
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
COPY --from=build ${DEPENDENCY}/org /app
ENTRYPOINT ["java","-cp","app:app/lib/*","cz.memsource.entrytest.EntryTestApplication"]
