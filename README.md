# 🏅 Olympic Management System

## 📌 Présentation

Olympic Management System est une application développée avec **Spring Boot 3** permettant de gérer les Jeux Olympiques.

Le projet permet de gérer :

- Les disciplines sportives
- Les athlètes
- Les épreuves
- Les résultats
- L'attribution automatique des médailles
- Le tableau des médailles
- Un tableau de bord statistique
- Un service SOAP pour consulter le tableau des médailles

---

# 🛠 Technologies utilisées

- Java 21
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Spring Web Services (SOAP)
- SpringDoc OpenAPI (Swagger)
- Maven
- MySQL
- JAXB
- IntelliJ IDEA

---

# 📁 Structure du projet

```
src
 ├── controller
 ├── dto
 ├── entity
 ├── enums
 ├── exception
 ├── mapper
 ├── repository
 ├── service
 ├── soap
 ├── config
 └── resources
```

---

# ⚙ Configuration

Créer une base de données MySQL :

```sql
CREATE DATABASE olympic_management;
```

Configurer le fichier :

```
src/main/resources/application.properties
```

Exemple :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/olympic_management
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update

server.port=8081
```

---

# ▶ Exécution du projet

Compiler :

```bash
mvn clean compile
```

Lancer :

```bash
mvn spring-boot:run
```

ou

```bash
java -jar target/olympic-management-system-0.0.1-SNAPSHOT.jar
```

---

# 📚 Documentation Swagger

Une fois l'application démarrée :

```
http://localhost:8081/swagger-ui/index.html
```

---

# 🌐 API REST

## Discipline

- GET /api/disciplines
- GET /api/disciplines/{id}
- POST /api/disciplines
- PUT /api/disciplines/{id}
- DELETE /api/disciplines/{id}

---

## Athlète

- GET /api/athletes
- GET /api/athletes/{id}
- POST /api/athletes
- PUT /api/athletes/{id}
- DELETE /api/athletes/{id}

---

## Épreuve

- GET /api/epreuves
- GET /api/epreuves/{id}
- POST /api/epreuves
- PUT /api/epreuves/{id}
- DELETE /api/epreuves/{id}

---

## Résultat

- GET /api/resultats
- GET /api/resultats/{id}
- POST /api/resultats
- PUT /api/resultats/{id}
- DELETE /api/resultats/{id}

---

## Tableau des médailles

```
GET /api/tableau-medailles
```

Retourne le classement officiel des pays.

---

## Tableau de bord

```
GET /api/dashboard/summary
```

Retourne :

- nombre total d'athlètes
- nombre de pays
- statistiques des médailles
- classement des pays
- nombre de médaillés par pays

Autres endpoints :

```
GET /api/dashboard/athletes/count
GET /api/dashboard/countries/count
GET /api/dashboard/medals/count
GET /api/dashboard/countries/ranking
GET /api/dashboard/medalists-by-country
```

---

# 🧮 Attribution automatique des médailles

Les médailles sont attribuées automatiquement selon le classement :

| Classement | Médaille |
|------------|----------|
| 1 | 🥇 Or |
| 2 | 🥈 Argent |
| 3 | 🥉 Bronze |
| ≥ 4 | Aucune |

---

# 🏆 Classement des pays

Le classement est calculé avec le barème suivant :

| Médaille | Points |
|----------|---------|
| Or | 7 |
| Argent | 4 |
| Bronze | 1 |

En cas d'égalité :

1. nombre d'Or
2. nombre d'Argent
3. nombre de Bronze

---

# 🔗 Service SOAP

## WSDL

```
http://localhost:8081/ws/olympics.wsdl
```

## Endpoint SOAP

```
POST http://localhost:8081/ws/
```

Opération disponible :

```
getTableauMedailles
```

---

# 🧪 Tests

## REST

Tester avec :

- Swagger
- Postman

---

## SOAP

Tester avec :

- Postman
- SoapUI

Exemple de requête :

```xml
<?xml version="1.0" encoding="UTF-8"?>

<soapenv:Envelope
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:oly="http://olympics.com/management/soap">

   <soapenv:Header/>

   <soapenv:Body>
      <oly:getTableauMedaillesRequest/>
   </soapenv:Body>

</soapenv:Envelope>
```

---

# ✅ Fonctionnalités réalisées

- Gestion des disciplines
- Gestion des athlètes
- Gestion des épreuves
- Gestion des résultats
- Attribution automatique des médailles
- Tableau des médailles REST
- Tableau des médailles SOAP
- Tableau de bord statistique
- Documentation Swagger
- Gestion des exceptions
- Validation des données

---

# 👨‍💻 Auteur

Projet réalisé dans le cadre du module :

- Développement Java Spring Boot

Université / École : ....................................

Année académique : 2025–2026

Étudiant : ....................................