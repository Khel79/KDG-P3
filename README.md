# Programmeren 3

In deze repository vind je voorbeeldcode voor het vak Programmeren 3. Elke directory bevat een afzonderlijk IntelliJ IDEA project. Alle projecten zijn voorzien van een [Gradle](https://gradle.org/) wrapper.

## Week 1

- [boot_servlet](boot_servlet) ![Spring Boot][spring_boot]

  Klassieke servlets opgestart via Spring Boot.

- [listeners](listeners) ![Spring Boot][spring_boot]

  Een voorbeeld van een servlet context listener.

- [filters](filters) ![Spring Boot][spring_boot]

  Een voorbeeld van filters.

- [dispatch](dispatch) ![Spring Boot][spring_boot]

  Een RequestDispatcher (forwarding) en een redirecting voorbeeld.

- [thread_safety](thread_safety) ![Spring Boot][spring_boot]

  Enkele servlets die de problematiek rond thread safety illustreren.

## Week 2

- [mvc_account](mvc_account) ![Spring Boot][spring_boot] ![Thymeleaf][thymeleaf]

  Spring MVC met Thymeleaf.

- [di](di) ![Spring][spring]

  Dependency Injection voorbeelden geimplementeerd met Spring (geen Web).

## Week 3

- [cycling](cycling) ![Spring Boot][spring_boot] ![Thymeleaf][thymeleaf]

  Een spring MVC applicatie met DTO's.

- [controllers](controllers) ![Spring Boot][spring_boot]

  Enkele Spring MVC controllers worden gebruikt om de parameters en return types van controller-methods te illustreren. (Opgelet! bevat nog JSP)

## Week 4

- [cycling_hibernate](cycling_hibernate) ![Spring Boot][spring_boot] ![Thymeleaf][thymeleaf] ![Hibernate][hibernate]

  Een spring MVC applicatie met DTO's en achterliggend enkele tabellen in een H2 databank.

- [cycling_hibernate_db_first](cycling_hibernate_db_first) ![Spring Boot][spring_boot] ![Thymeleaf][thymeleaf] ![Hibernate][hibernate]

  Een spring MVC applicatie waarbij de achterliggende databank wordt opgezet m.b.v. SQL scripts.

## Geintegreerd project

- [upvote](upvote) ![Spring Boot][spring_boot] ![Thymeleaf][thymeleaf] ![Hibernate][hibernate]

  Een website waar je vragen kan stellen, beantwoorden en up/downvoten (a la StackOverflow of reddit).

[spring]: images/spring-logo_22_22.png
[spring_boot]: images/spring-boot-logo_24_22.png
[thymeleaf]: images/thymeleaf-logo_22_22.png
[hibernate]: images/hibernate-logo_22_22.png
