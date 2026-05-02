.PHONY: help build test run clean docker-build docker-up docker-down docker-test

help:
	@echo "Available targets:"
	@echo "  make build           - Build the project"
	@echo "  make test            - Run tests locally"
	@echo "  make test-chrome     - Run tests with Chrome"
	@echo "  make test-firefox    - Run tests with Firefox"
	@echo "  make test-headless   - Run tests in headless mode"
	@echo "  make clean           - Clean build artifacts"
	@echo "  make docker-build    - Build Docker image"
	@echo "  make docker-up       - Start Docker containers"
	@echo "  make docker-down     - Stop Docker containers"
	@echo "  make docker-test     - Run tests in Docker"
	@echo "  make docker-logs     - View Docker logs"
	@echo "  make install         - Install dependencies"

build:
	mvn clean install

test:
	mvn clean test

test-chrome:
	mvn clean test -Dbrowser=chrome

test-firefox:
	mvn clean test -Dbrowser=firefox

test-headless:
	mvn clean test -Dheadless=true

install:
	mvn install

clean:
	mvn clean
	rm -rf screenshots/
	rm -rf target/

docker-build:
	docker build -t selenium-java-docker:latest .

docker-up:
	docker-compose up -d

docker-down:
	docker-compose down

docker-test:
	docker-compose up --build selenium-java

docker-logs:
	docker-compose logs -f selenium-java

docker-clean:
	docker-compose down -v
	docker rmi selenium-java-docker:latest

reports:
	@echo "Test reports generated at: target/surefire-reports/"
	@echo "Cucumber report generated at: target/cucumber-report.html"
