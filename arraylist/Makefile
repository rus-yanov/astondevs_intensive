.DEFAULT_GOAL := run-dist

run-dist:
	./build/install/app/bin/app

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean installDist

run:
	./gradlew run

test:
	./gradlew test

lint:
	./gradlew checkstyleMain checkstyleTest

.PHONY: build
