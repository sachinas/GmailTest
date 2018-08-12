# Gmail-Selenium-Gradle
This automation test script is developed in Selenium-Webdriver using Java, JUnit, Gradle and Page Object Model (POM). This is based on scenarios performed on Gmail.
Scenarios covered are;
1. Load Chrome browser
2. Open Gmail
3. Enter valid email and password. It will open Inbox.
4. Compose a new email.
5. Add an attachment to that email, enter subject and body and send this email to your own email id (through which you are logged in).
6. After sending email, verify that email is received in your inbox by verifying email id, subject and body.
7. Logout

As a best practice, information (like URLs, username, password, email subject, email body) used in this test script is not hardcoded. It is fetched through properties file.
