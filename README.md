# microservices-with-spring-sectionwise-code

**Description:** This repository has three maven projects with the names accounts, loans, cards. These three projects will acts as a microservices and these are build by taking EazyBank as an example Bank application. Below are the key steps that are followed while creating these projects.

## Key steps:

 - Go to [https://start.spring.io/](https://start.spring.io/)
 - Fill all the details required to generate a **accounts** Spring Boot project and add dependencies **Spring Web,Spring Boot Actuator,H2 Database, Spring Data JPA , Lombok**. Click GENERATE which will download the **accounts** maven project in a zip format
 - Repeat the above steps for **cards and loans** microservices as well.
 - Extract the downloaded maven projects of accounts, cards, loans and import the same into Eclipse by following the steps mentioned in the course
 - Visit pom.xml of accounts, cards, loans and make sure all the required dependencies are present in it like shown below,
 
 
## accounts, loans, cards\pom.xml

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-autoconfigure</artifactId>
			<version>2.7.1</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>5.3.20</version>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>

- Make sure all the required and associated libraries are downloaded under maven dependencies
- Open the application.properties present inside accounts, loans, cards and make sure to update the properties related to H2 database configuration and port as shown below,

## \accounts\src\main\resources\application.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8085

## \loans\src\main\resources\application.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8095

## \cards\src\main\resources\application.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=9090

- Open the SpringBoot main classes of accounts, loans and cards microservices. We can always identify the main class in a Spring Boot project by looking for the annotation **@SpringBootApplication**. Below are the sample code present inside these SpringBoot main classes.

## \accounts\src\main\java\com\bank\accounts\AccountsApplication.java
``` java
package com.bank.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.bank.accounts.controller") })
@EnableJpaRepositories("com.bank.accounts.repository")
@EntityScan("com.bank.accounts.model")
public class AccountsApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountsApplication.class, args);
	}

}
```
## \accounts\src\main\java\com\bank\loans\AccountsApplication.java
``` java
package com.bank.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.bank.loans.controller") })
@EnableJpaRepositories("com.bank.loans.repository")
@EntityScan("com.bank.loans.model")
public class LoansApplication {

	public static void main(String[] args) {

		SpringApplication.run(LoansApplication.class, args);
	}

}
```
## \accounts\src\main\java\com\bank\cards\AccountsApplication.java
``` java
package com.bank.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScans({ @ComponentScan("com.bank.cards.controller") })
@EnableJpaRepositories("com.bank.cards.repository")
@EntityScan("com.bank.cards.model")
public class BankCardsApplication {

	public static void main(String[] args) {

		SpringApplication.run(BankCardsApplication.class, args);
	}

}
```


