# API de Conversion de Devises

## Aperçu
L'API de Conversion de Devises permet de gérer les conversions de devises en temps réel en utilisant des taux de change actuels obtenus via un service externe. Cette API offre les fonctionnalités suivantes :
- Obtenir le taux de change entre deux devises.
- Convertir un montant d'une devise à une autre.
- Obtenir les taux de change pour toutes les devises par rapport à une devise de base.

Elle est développée avec Spring Boot et suit les principes REST.

## Fonctionnalités
- **Conversion de devises** : Conversion de montants entre devises.
- **Récupération des taux de change** : Taux actuels entre devises.
- **Gestion des erreurs** : Réponses claires en cas de paramètres invalides ou de problèmes avec le service externe.

## Prérequis
Pour exécuter cette application, vous devez avoir :
- Java 11 ou supérieur
- Maven ou Gradle pour gérer les dépendances


## Installation
1. Clonez le dépôt GitHub :
   ```bash
   git clone https://github.com/votre-utilisateur/device-conversion-api.git
Accédez au répertoire :
```bash
cd device-conversion-api
```
Compilez et lancez l'application :
```bash
./mvnw spring-boot:run
```
Accédez à l'API à l'adresse suivante : http://localhost:8080/api/v1/exchange

## Endpoints de l'API

1. Obtenir le taux de change
URL : /api/v1/exchange/rate

- **Méthode HTTP : GET**

   Description : Retourne le taux de change actuel entre une devise de base et une devise cible.
   
   Paramètres :
   
   baseCurrency (obligatoire) : Code de la devise de base (ex. : USD).
   targetCurrency (obligatoire) : Code de la devise cible (ex. : EUR).
   Exemple de requête :
   
   GET /api/v1/exchange/rate?baseCurrency=USD&targetCurrency=EUR
   Exemple de réponse :
   
   Copier
   {
     "baseCurrency": "USD",
     "targetCurrency": "EUR",
     "exchangeRate": 0.92
   }
   Codes de réponse :
   
   200 OK : Le taux de change a été retourné avec succès.
   400 Bad Request : Paramètres manquants ou invalides.
  
2. Obtenir les taux de change par rapport à une devise de base
URL : /api/v1/exchange

- **Méthode HTTP : GET**
   Description : Retourne une liste des taux de change de toutes les devises par rapport à une devise donnée. 
   Paramètres : 
   baseCurrency (obligatoire) : Code de la devise de base (ex. : USD).
   Exemple de requête :
  
   GET /api/v1/exchange?baseCurrency=USD
   Exemple de réponse :
   
   {
     "baseCurrency": "USD",
     "rates": {
       "EUR": 0.92,
       "GBP": 0.81,
       "JPY": 134.62
     }
   }
   Codes de réponse :
   
   200 OK : Liste des taux de change retournée avec succès.
   400 Bad Request : Paramètre de devise de base manquant ou invalide.

  
3. Convertir un montant entre deux devises
URL : /api/v1/exchange/rate/amount

- **Méthode HTTP : GET**

   Description : Convertit un montant d'une devise de base vers une devise cible en retournant le montant converti et le taux utilisé.
   
   Paramètres :
   
   baseCurrency (obligatoire) : Code de la devise de base (ex. : USD).
   targetCurrency (obligatoire) : Code de la devise cible (ex. : EUR).
   amount (obligatoire) : Montant à convertir.
   Exemple de requête :
   
   
   GET /api/v1/exchange/rate/amount?baseCurrency=USD&targetCurrency=EUR&amount=100
   Exemple de réponse :
   
   
   {
     "baseCurrency": "USD",
     "targetCurrency": "EUR",
     "amount": 100,
     "exchangeRate": 0.92,
     "convertedAmount": 92.0
   }
   Codes de réponse :
   
   200 OK : Conversion effectuée avec succès.
   400 Bad Request : Paramètres manquants ou invalides.
  
## Gestion des erreurs
L'API retourne des réponses claires en cas de problème :

400 Bad Request : Paramètres requis manquants ou invalides.
500 Internal Server Error : Erreur interne ou problème avec le service externe.
Exemple de réponse en cas d'erreur :

{
  "error": "Invalid baseCurrency or targetCurrency"
}


## Technologies utilisées
Spring Boot : Framework pour développer l'API.
Swagger/OpenAPI : Documentation interactive des endpoints.
JUnit : Framework de tests.
Exchange Rate API : Service externe pour obtenir les taux de change.
Contribution
Les contributions sont les bienvenues ! Pour contribuer :

Forkez ce dépôt.
Créez une nouvelle branche pour vos modifications :
```bash
git checkout -b nom-de-la-fonctionnalite
```
Faites vos changements et poussez-les :
```bash
git add .
git commit -m "Ajout d'une nouvelle fonctionnalité"
git push origin nom-de-la-fonctionnalite
```
Soumettez une Pull Request pour examen.

