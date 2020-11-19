FROM gradle:jdk11-slim
ADD --chown=gradle . /memsource
WORKDIR /memsource
CMD ["gradle", "bootRun"]
