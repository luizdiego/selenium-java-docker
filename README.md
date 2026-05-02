# selenium-java-docker

A comprehensive testing repository for experimenting with Docker containers and Selenium WebDriver automation using Java. This project demonstrates best practices for containerized test automation with support for multiple browsers and test frameworks.

## 📋 Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Project Structure](#project-structure)
- [Running Tests](#running-tests)
- [Docker Usage](#docker-usage)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Overview

This repository showcases:
- **Selenium WebDriver** integration with Java for browser automation
- **Docker containers** for isolated test environments
- **Maven** for dependency and build management
- **Multiple browser drivers** (Chrome, Firefox, etc.)
- **Test frameworks** (JUnit 4/5)
- **Test reporting** and artifacts collection
- **CI/CD integration** ready

## Prerequisites

- **JDK 21+** (Java Development Kit)
- **Maven 3.6+**
- **Docker** and **Docker Compose**
- **Git**

## Quick Start

### Clone the Repository

```bash
git clone https://github.com/luizdiego/selenium-java-docker.git
cd selenium-java-docker
```

### Build Locally

```bash
mvn clean install
```

### Run Tests

```bash
# Using Maven directly
mvn clean test

# Using Docker Compose
docker-compose up --build selenium-java
```

## Project Structure

```
selenium-java-docker/
├── .github/
│   └── pull_request_template.md
├── src/
│   ├── main/
│   │   └── java/          # Production code
│   └── test/
│       └── java/          # Test code
├── docker/                # Docker configuration
├── Dockerfile
├── docker-compose.yml
├── pom.xml                # Maven configuration
├── README.md
└── .gitignore
```

## Running Tests

### Locally with Maven

```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=YourTestClass

# Run with specific browser (if configured)
mvn test -Dbrowser=chrome
```

### With Docker Compose

```bash
# Build and run tests
docker-compose up --build

# Run tests in background
docker-compose up -d

# View logs
docker-compose logs -f selenium-java

# Stop services
docker-compose down
```

## Docker Usage

### Build Docker Image

```bash
docker build -t selenium-java-docker:latest .
```

### Run Container

```bash
docker run -it selenium-java-docker:latest mvn clean test
```

### Docker Compose Services

```bash
# Start all services
docker-compose up

# Start specific service
docker-compose up selenium-java

# Clean up
docker-compose down -v
```

## Configuration

### Maven Properties

Update `pom.xml` for:
- Java version
- Selenium version
- Browser driver versions
- Test framework versions

### Environment Variables

Set in `.env` or `docker-compose.yml`:
- `BROWSER`: Target browser (chrome, firefox)
- `HEADLESS`: Run in headless mode (true/false)
- `TEST_TIMEOUT`: Test timeout in seconds

### Browser Drivers

Drivers are managed automatically via Maven dependencies:
- Chrome: WebDriverManager
- Firefox: WebDriverManager
- Edge: WebDriverManager

## Contributing

We welcome contributions! Please follow these guidelines:

1. **Create a feature branch**: `git checkout -b feature/your-feature`
2. **Make your changes** and test locally
3. **Follow code style**: Ensure consistency with existing code
4. **Add tests**: Include test coverage for new features
5. **Submit a Pull Request**: Use our [pull request template](.github/pull_request_template.md)

### Pull Request Process

- Reference related issues
- Provide clear description of changes
- Ensure all tests pass locally
- Verify Docker build succeeds
- Follow the PR template checklist

### Testing Requirements

Before submitting a PR:

```bash
# Run all tests
mvn clean test

# Verify Docker build
docker build -t selenium-java-docker:latest .

# Test with Docker Compose
docker-compose up --build
```

## Best Practices

- Use Page Object Model (POM) for test organization
- Implement explicit waits instead of Thread.sleep()
- Use descriptive test method names
- Keep tests independent and idempotent
- Manage test data properly
- Document complex test scenarios

## Troubleshooting

### Tests fail locally but pass in Docker

Check Docker image Java and Maven versions match your local environment.

### Port conflicts with Docker Compose

Modify port mappings in `docker-compose.yml` if needed.

### WebDriver not found

Ensure Maven dependencies are installed: `mvn clean install`

## License

This project is licensed under the MIT License - see LICENSE file for details.
