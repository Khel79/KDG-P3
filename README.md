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

## Week 2

- [thread_safety](thread_safety) ![Spring Boot][spring_boot]

  Enkele kleine servlets die de problematiek rond thread safety illustreren.

- [session](session) ![Spring Boot][spring_boot]

  Een voorbeeld van het aanmaken en raadplegen van een sessie.

- [blog_jsp](blog_jsp)

  Het [dispatch](dispatch) voorbeeld omgezet naar JSP anno 1999-2000.

- [blog_jstl](blog_jstl) ![Spring Boot][spring_boot]

  Het [dispatch](dispatch) voorbeeld omgezet naar een eenvoudige JSP/JSTL pagina.

## Week 3

- [di](di) ![Spring][spring]

  Dependency Injection voorbeelden geimplementeerd met Spring (geen Web).

- [spring_mvc_account](spring_mvc_account) ![Spring Boot][spring_boot]

  Een heel eenvoudig Spring MVC voorbeeld waarvan de view kant is gebouwd met JSP.

- **Extra:** [smvc_thymeleaf_account](smvc_thymeleaf_account) ![Spring Boot][spring_boot] ![Thymeleaf][thymeleaf]

  Spring MVC met Thymeleaf.

## Week 4

- [controllers](controllers) ![Spring Boot][spring_boot]

  Drie Spring MVC controllers worden gebruikt om de parameters en return types van controller-methods te illustreren.

- [cycling](cycling) ![Spring Boot][spring_boot] ![Hibernate][hibernate]

  Een spring MVC applicatie met achterliggend twee tabellen in een H2 databank.

## Week 5

- [form](form) ![Spring Boot][spring_boot] ![Hibernate][hibernate]

  Spring form met validatie. i18n wordt toegepast op het formulier (JSP/JSTL) en de validatie-berichten. Gebruikt Hibernate om form-validatie te doen.

- [cycling_orika](cycling_orika) ![Spring Boot][spring_boot] ![Hibernate][hibernate]

  De cycling applicatie van week 4, uitgebreid met Orika bean mapping (en enkele extra tabellen). Eveneens omgezet naar Spring Boot.

## Geintegreerd project

- [upvote](upvote) ![Spring Boot][spring_boot] ![Thymeleaf][thymeleaf] ![Hibernate][hibernate]

  Een website waar je vragen kan stellen, beantwoorden en up/downvoten (a la StackOverflow of reddit).

- [repaircafe](repaircafe) ![Spring][spring] ![Hibernate][hibernate]

  Het Repair Cafe project dat beschreven wordt op de [website van programmeren 3](https://programmeren3-repaircafe.rhcloud.com/).

## Roadmaps

- [webapp](webapp)

  Demo-project met allerhande kleine Java EE klassen (geen Spring). Naar deze demo's wordt gerefereerd als "WebApps103" in de roadmap [Java Web Applicatie RM](https://programmeren3-repaircafe.rhcloud.com/road-maps/jwa-rm/).

- [hardware_shop](hardware_shop) ![Spring][spring] ![Hibernate][hibernate]

  Het start-project voor het maken van de [Hardware Shop](https://programmeren3-repaircafe.rhcloud.com/road-maps/hardware-shop-roadmap-1/).

[spring]: images/spring-logo_22_22.png
[spring_boot]: images/spring-boot-logo_24_22.png
[thymeleaf]: images/thymeleaf-logo_22_22.png
[hibernate]: images/hibernate-logo_22_22.png
