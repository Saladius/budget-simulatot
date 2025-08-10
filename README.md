# Budget Simulator (Spring Boot + PostgreSQL)

Endpoints:
- `GET /api/profile` – récupérer le profil
- `PUT /api/profile` – mettre à jour le profil (monthlyIncome, goalAmount, goalMonths, groceries)
- `GET /api/fixed-expenses` – lister les charges fixes
- `POST /api/fixed-expenses` – créer une charge fixe `{name, period: MONTHLY|YEARLY, amount}`
- `DELETE /api/fixed-expenses/{id}` – supprimer
- `GET /api/plan?income=&goalAmount=&goalMonths=&groceries=` – calculer la répartition
- Swagger UI : `/api/swagger`

## Lancer en local
```bash
mvn spring-boot:run
```
Puis ouvrir http://localhost:8080

## Config PostgreSQL (Railway/Render)
Exporter ces variables d’environnement (ex: Railway) :
```
SPRING_DATASOURCE_URL=jdbc:postgresql://<PGHOST>:<PGPORT>/<PGDATABASE>
SPRING_DATASOURCE_USERNAME=<PGUSER>
SPRING_DATASOURCE_PASSWORD=<PGPASSWORD>
```

## Build & Docker
```bash
mvn -DskipTests package
java -jar target/budget-simulator-0.0.1-SNAPSHOT.jar
# ou image via buildpacks :
mvn spring-boot:build-image
```

