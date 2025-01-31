# Spotify RestAssured TestNG Framework

## Overview
This project is a **REST API Automation Framework** for Spotify, built using **RestAssured**, **TestNG**, and **Maven**. It automates various API operations such as creating playlists, adding tracks, retrieving user details, and validating responses using assertions.

## Technologies Used
- **Java 22.0.1 (2024-04-16)**
- **RestAssured** (API Testing)
- **TestNG** (Test Execution & Assertions)
- **Maven** (Dependency Management & Build Tool)
- **Allure Reports** (Test Reporting)
- **JSON & Jackson Libraries** (Payload Handling)
- **Jenkins** (CI/CD Integration with GitHub Hook Trigger and Ngrok option)

## Features
- **Automated API Testing**: Covers Spotify API operations like authentication, playlist creation, track management, and user profile retrieval.
- **Data-Driven Testing**: Parameterized test execution using external data sources.
- **Logging & Reporting**: Integrated with **Allure Reports** for detailed test execution reports.
- **Reusable Utility Methods**: Common API request methods encapsulated for better maintainability.
- **Maven Integration**: Supports execution via `mvn test`.
- **Jenkins Integration**: Supports CI/CD execution with GitHub hook trigger and Ngrok configuration.

## Project Structure
```
RestAssuredFramework/
│-- .idea/                      # IDE configuration files
│-- allure-report/              # Allure test execution reports
│   ├── data/
│   ├── export/
│   ├── history/
│   ├── plugin/
│   │   ├── behaviors/
│   │   ├── packages/
│   │   ├── screen-diff/
│   ├── widgets/
│-- src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── org/
│   │   │   │   ├── example/   # Main framework components
│   ├── test/
│   │   ├── java/               # Test cases implementation
│-- pom.xml                     # Maven dependencies
│-- README.md                    # Project documentation
```

## Setup & Execution
### Prerequisites
1. Install **Java 22.0.1** and set up `JAVA_HOME`.
2. Install **Maven** (`mvn -version` to verify).
3. Clone the repository:
   ```sh
   git clone https://github.com/RAMEEE/SpotifyRestAssuredTestNGFramework.git
   cd SpotifyRestAssuredTestNGFramework
   ```
4. Update the **config.properties** file with your Spotify API credentials (Client ID, Client Secret, and Access Token).
5. Install dependencies:
   ```sh
   mvn clean install
   ```

### Running Tests
- Run tests using Maven:
  ```sh
  mvn test
  ```
- Run tests using TestNG XML suite:
  ```sh
  mvn test -DsuiteXmlFile=src/test/resources/testng.xml
  ```
- View reports in `allure-report/index.html`.

### Running Tests with Allure Reports
1. Execute tests:
   ```sh
   mvn clean test
   ```
2. Generate Allure report:
   ```sh
   mvn allure:serve
   ```

### Jenkins Integration
- Configured with **GitHub hook trigger for SCM polling**.
- Supports **Ngrok option** for remote accessibility.
- Automated test execution and reporting through Jenkins pipeline.

## Future Enhancements
- Implement **OAuth2 Token Refresh Mechanism**.
- Add **Parallel Execution** support.
- Enhance **Error Handling** and **Logging**.
- Extend **Data-Driven Testing** with multiple data sources.

## Contribution
Feel free to fork, contribute, and raise pull requests for enhancements or bug fixes.

## Author
Developed by **Ramkumar A.** 🚀

