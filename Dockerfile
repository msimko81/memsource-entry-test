FROM gradle:jdk11
ADD --chown=gradle . /memsource
WORKDIR /memsource
CMD ["gradle", "bootRun"]
