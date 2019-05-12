FROM gradle:jdk11-slim
ADD --chown=gradle . /lundegaard
WORKDIR /lundegaard
CMD ["gradle", "bootRun"]
