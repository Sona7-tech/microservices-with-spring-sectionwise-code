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
- Create a new Rest Controller classes like mentioned below for all the three microservices. We can use **@GetMapping** as well instead of **@PostMapping**. But I used **@PostMapping** to make sure any senstive information of Bank customer should not get exposed in the browser URL.

## accounts\src\main\java\com\bank\accounts\controller\AccountsController.java
``` java
package com.bank.accounts.controller;

import com.bank.accounts.model.Account;
import com.bank.accounts.model.Customer;
import com.bank.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {


    @Autowired
    private AccountsRepository accountsRepository;

    @PostMapping (value = "/myAccount")
    public Account getAccountDetails(@RequestBody Customer customer) {

        Account accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }

    }

}
```
## \loans\src\main\java\com\bank\loans\controller\LoansController.java
``` java
package com.bank.loans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loans.model.Customer;
import com.bank.loans.model.Loan;
import com.bank.loans.repository.LoansRepository;

@RestController
public class LoansController {

    @Autowired
    private LoansRepository loansRepository;

    @PostMapping("/myLoans")
    public List<Loan> getLoansDetails(@RequestBody Customer customer) {
        List<Loan> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        if (loans != null) {
            return loans;
        } else {
            return null;
        }

    }

}
```
## \cards\src\main\java\com\eazybytes\cards\controller\CardsController.java
``` java
package com.bank.cards.controller;


import com.bank.cards.model.Card;
import com.bank.cards.model.Customer;
import com.bank.cards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/myCards")
    public List<Card> getCardDetails(@RequestBody Customer customer) {
        List<Card> cards = cardRepository.findByCustomerId(customer.getCustomerId());
        if (cards != null) {
            return cards;
        } else {
            return null;
        }

    }
}
```
- Make sure that all the dependent Entity Java files & JPA Repository files are present inside the three projects like shown below,

## accounts\src\main\java\com\bank\accounts\model\Account.java

``` java
package com.bank.accounts.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Getter @Setter @ToString
public class Account {

    @Column(name="customer_id")
    private int customerId;

    @Column(name="account_number")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_dt")
    private LocalDate createDt;

}
```

## \accounts\src\main\java\com\bank\accounts\model\Customer.java
``` java
package com.bank.accounts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "create_dt")
    private LocalDate createDt;
}
```
## \accounts\src\main\java\com\bank\accounts\repository\AccountsRepository.java
``` java
package com.bank.accounts.repository;

import com.bank.accounts.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Account, Long> {

    Account findByCustomerId(int customerId);

}
```
## \loans\src\main\java\com\bank\loans\model\Loan.java
``` java
package com.bank.loans.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @ToString
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_number")
    private int loanNumber;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name="start_dt")
    private Date startDt;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private int totalLoan;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "outstanding_amount")
    private int outstandingAmount;

    @Column(name = "create_dt")
    private String createDt;

}
```

## \loans\src\main\java\com\bank\loans\repository\LoansRepository.java
``` java
package com.bank.loans.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.loans.model.Loan;

@Repository
public interface LoansRepository extends CrudRepository<Loan, Long> {


    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);

}
```
## \cards\src\main\java\com\bank\cards\model\Card.java
``` java
package com.bank.cards.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private int cardId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private int totalLimit;

    @Column(name = "amount_used")
    private int amountUsed;

    @Column(name = "available_amount")
    private int availableAmount;

    @Column(name = "create_dt")
    private Date createDt;
}
```
## \cards\src\main\java\com\bank\cards\repository\CardsRepository.java
``` java
package com.bank.cards.repository;

import com.bank.cards.model.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findByCustomerId(int customerId);
}
```
- To set up tables, columns, data needed inside the H2 database, create a **data.sql** file in all the microservices under **src\main\resources\\** folder. Below are the sample SQL scripts for each microservice. Please note that these scripts will be executed everytime you start the microservice and the moment you stop/restart your service all your data present inside your H2 database will be lost. So please make sure not to use internal memory H2 database inside production applications.

## \accounts\src\main\resources\data.sql
``` sql
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS accounts;

CREATE TABLE `customer` (
  `customer_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `create_dt` date DEFAULT NULL
);

CREATE TABLE `accounts` (
  `customer_id` int NOT NULL,
   `account_number` int AUTO_INCREMENT  PRIMARY KEY,
  `account_type` varchar(100) NOT NULL,
  `branch_address` varchar(200) NOT NULL,
  `create_dt` date DEFAULT NULL
);

INSERT INTO `customer` (`name`,`email`,`mobile_number`,`create_dt`)
 VALUES ('Eazy Bytes','tutor@eazybytes.com','9876548337',CURDATE());
 
INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`)
 VALUES (1, 186576453, 'Savings', '123 Main Street, New York', CURDATE());
```

## \loans\src\main\resources\data.sql
``` sql
DROP TABLE IF EXISTS loans;

CREATE TABLE `loans` (
  `loan_number` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `start_dt` date NOT NULL,
  `loan_type` varchar(100) NOT NULL,
  `total_loan` int NOT NULL,
  `amount_paid` int NOT NULL,
  `outstanding_amount` int NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`loan_number`)
);

INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
 VALUES ( 1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');
 
INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
 VALUES ( 1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06');
 
INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
 VALUES ( 1, '2021-02-14', 'Home', 50000, 10000, 40000, '2018-02-14');

INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
 VALUES ( 1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');
```
## \cards\src\main\resources\data.sql
``` sql
DROP TABLE IF EXISTS cards;

CREATE TABLE `cards` (
  `card_id` int NOT NULL AUTO_INCREMENT,
  `card_number` varchar(100) NOT NULL,
  `customer_id` int NOT NULL,
  `card_type` varchar(100) NOT NULL,
  `total_limit` int NOT NULL,
  `amount_used` int NOT NULL,
  `available_amount` int NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`card_id`)
);


INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
 VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
 VALUES ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE());
 
INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
 VALUES ('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());
```
- Go to your Spring Boot main classes and start all the three microservices by right click-> Run As -> Java Application. This will start your microservices successfully at port 8085,8095,9090 based on the ports configured inside **application.properties**. Your can confirm the same by looking at the console logs.
- Access the URLs of H2 databases of all the three microservices to make sure tables, columns, data is created inside them successfully. The URLs are [http://localhost:8085/h2- console/](http://localhost:8085/h2-console), [http://localhost:8095/h2-console/](http://localhost:8095/h2-console), [http://localhost:9090h2-console/](http://localhost:9090/h2-console) respectively.
- Invoke the REST APIs http://localhost:8085/myAccount, http://localhost:8095/myLoans, http://localhost:9090/myCards through Postman by passing the below request in JSON format. You should get the response from the corresponding microservices.

```js
{
    "customerId": 1
}
```
