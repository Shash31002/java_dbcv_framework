# 🗄️ SQL / DB Validation Framework

A lightweight **Java + JDBC + MySQL** framework for validating backend database state directly — built to catch data-integrity issues that UI or API tests alone can't see.

![Java](https://img.shields.io/badge/Java-21-orange)
![TestNG](https://img.shields.io/badge/TestNG-7.10-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Jenkins](https://img.shields.io/badge/CI-Jenkins-red)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)

---

## 📌 Overview

This framework connects directly to a MySQL database and runs validation queries as automated tests — checking things like row counts, null constraints, referential integrity, and data correctness after operations that an API or UI would normally trigger.

It's designed as a **reusable module**: the `db` package (connection handling + query execution) is built to plug directly into a larger API + UI + SQL integration framework, where the same DB layer verifies backend state after both API calls and browser-driven UI actions.

---

## 🛠️ Tech Stack

| Layer | Tool |
|---|---|
| Language | Java 21 |
| Test Runner | TestNG |
| Database | MySQL 8.0 |
| DB Connectivity | JDBC (`mysql-connector-j`) |
| Build Tool | Maven |
| Logging | Log4j2 |
| Reporting | ExtentReports (HTML) |
| CI/CD | Jenkins (GitHub webhook triggered) |

---

## 📁 Project Structure

```
DB-Validation-Framework/
├── pom.xml
├── Jenkinsfile
├── sql/
│   ├── schema.sql            # table definitions
│   └── seed_data.sql         # sample data for validation
├── src/
│   ├── main/java/
│   │   ├── config/            # reads db.properties
│   │   ├── db/                 # connection manager + query executor
│   │   └── utils/              # ResultSet → List<Map> conversion
│   └── test/
│       ├── java/
│       │   ├── base/           # shared setup/teardown
│       │   ├── listeners/      # ExtentReports TestNG listener
│       │   └── tests/          # validation test classes
│       └── resources/
│           ├── db.properties
│           └── testng.xml
└── README.md
```

---

## ✅ What It Validates

- Row-level data integrity (e.g. no null emails, no orphaned foreign keys)
- Expected record counts after seed/setup
- Referential integrity between related tables (`users` ↔ `orders`)
- Easily extendable to validate data written by an API or UI test run

---

## 🚀 Getting Started

### 1. Set up the database
```sql
USE testdb;
SOURCE sql/schema.sql;
SOURCE sql/seed_data.sql;
```

### 2. Configure your connection
Edit `src/test/resources/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/testdb
db.username=root
db.password=yourpassword
db.driver=com.mysql.cj.jdbc.Driver
```

### 3. Run the tests
```bash
mvn clean test
```

### 4. View the reports
- **TestNG/Surefire results:** `target/surefire-reports/`
- **ExtentReports HTML:** `test-output/ExtentReport/index.html`
- **Jenkins:** Test Result + Extent Report tabs on each build

---

## 🔄 CI/CD

This project is wired into a **Jenkins pipeline** that triggers automatically on every push to `main` via a GitHub webhook:

```
GitHub Push → Jenkins Webhook → Checkout → mvn test → Surefire + ExtentReports → Jenkins UI
```

See [`Jenkinsfile`](./Jenkinsfile) for the full pipeline definition.

---

## 🧭 Roadmap

- [ ] Integrate with a companion API test layer (RestAssured) for full API → DB validation
- [ ] Integrate with Selenium UI tests for UI → DB validation
- [ ] Add data-driven query validation via external JSON/Excel config
- [ ] Parameterize environment (dev/staging) via Jenkins build parameters

---

## 👤 Author

**Shashank Joshi**
Software Test Engineer | Building automation frameworks across UI, API, and DB layers
[LinkedIn](https://www.linkedin.com/in/shashank-s-joshi) 
