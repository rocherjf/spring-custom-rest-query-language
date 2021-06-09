# Spring custom rest query language

Exemple d'une API utilisant un DSL custom pour utiliser des critères de recherche directement dans l'API


## Fonctionnement

http://localhost:8080/albums?search=[NOM_DU_CHAMP_ENTITE][OPERATION][VALEUR_DU_CHAMP]

Les opérations disponibles sont
* `:` égalité
* `<` inférieur
* `>` supérieur
* `!` différent

Exemple d'utilisation simple :
* `search=nom:Anomie` -> recherche des albums dont le nom est Anomie
* `search=nom:Ano*` -> recherche des albums dont le nom commence par Ano
* `search=nom:*mie` -> recherche des albums dont le nom finit par mie
* `search=nom:*mie*` -> recherche des albums dont le nom contient mie

Combinaison de critères de recherche :
* `search=nom!*Ano*,nom!*Wel*` -> recherche des albums dont le nom ne contient ni Ano ni Wel
* `search=nom:*Ano*,or nom:*Wel*` -> recherche des albums dont le nom contient Ano ou Wel




## Setup
mvn clean install
