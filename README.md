
# [The Road Watch](https://vegavaktin.herokuapp.com/) - _Vegavaktin_

#### Software project in **Software Development I (HBV501)** at University of Iceland.
This project is a web application that uses **JVM** platform and **Spring Framework** for server-side scripting.  **PostgresSQL** is used to store data along with, **JavaServer Pages** (JSP), a high performance template engine to render the view.

<br> 

## Authors

\- [Bjarki Viðar Kristjánsson](https://github.com/bjarkivk/) <br>
\- [Hinrik Snær Guðmundsson](https://github.com/hinriksnaer/) <br>
\- [Huy Van Nguyen](https://github.com/serpentisx/) <br>
\- [Valentin Oliver Loftsson](https://github.com/valentinoli/) <br>

<br>

## Running locally 

### ![#f03c15](https://placehold.it/15/f03c15/000000?text=+) Installing PostgresSQL
In order to run the project locally you have to have PostgresSQL server running on your computer (this is because we need to create a connection to the database used in this project). If you haven't installed PosgresSQL you can do download it at

```sh
https://www.postgresql.org/download/
```
Once downloaded follow the instructions to setup PostgresSQL and create an account. After setup, add PostgresSQL (psql) to your PATH Environment Variables.

<hr> <br>

### ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) Installing Maven
This project uses, Maven, a build automation tool for Java project management. <br>
If you haven't installed Maven you can do download it at

```sh
http://maven.apache.org/download.cgi
```
Set maven (mvn) to your PATH Environment Variables.

<hr> <br>

### ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) Run from command line

To run the project locally, first clone this repository...
```sh
$ git clone https://github.com/serpentisx/road-watch.git
```
... compile and install the dependencies 

```sh
$ mvn clean install
[Bunch of output]
```
... run the project
```sh
$ java -jar target/Vegavaktin-0.1.jar
```
<hr> <br>

### ![#b74dbf](https://placehold.it/15/b74dbf/000000?text=+) Run with IDE
\- Download this repository as .zip <br>
\- Either extract or leave it zipped (extract if you want to make changes) <br>
\- Choose an IDE of your choice, _Eclipse, Netbeans, IntelliJ..._ <br>
\- Open the IDE and import the archive (.zip) or open a new project (extracted) <br>

