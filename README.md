# Pràctica 3: MOVIES

Has de crear una aplicació per gestionar una base de dades de pel·lícules.

## Adaptació de la BD proporcionada a Hibernate
- Utilitzar Hibernate per adaptar la base de dades proporcionada.

## Funcionalitats a implementar:
- Cerca de pel·lícules:
    - Per actor
    - Per "personatges" que hi apareixen
    - Per títol
    - Per gènere
    - Per director

- CRUD de les següents entitats:
    - country
    - language
    - language_role
    - genre
    - keyword
    - movie_company
    - production_company
    - gender
    - person
    - department

- Crear, modificar i esborrar pel·lícules:
    - Creació de noves pel·lícules
    - Modificació de pel·lícules existents (canvis d'actors, etc.)
    - Eliminació de pel·lícules
    - Introduir actor dins pel·lícula, amb el seu "paper"
    - Gestió de: gèneres, keywords, llenguatges i personal ("crew")

## Autenticació
Les operacions de consulta no requereixen autenticació de l'usuari. No obstant això, per totes les operacions que impliquen canvis a les dades, l'usuari ha de ser autenticat prèviament amb l'aplicació.

## Avaluació
La pràctica ha de ser lliurada en un arxiu comprimit amb el projecte preparat per ésser importat amb INTELLIJ IDEA. L'arxiu ha de contenir:
- Fitxer `pom.xml`
- Carpeta `src/`

A més, s'ha d'adjuntar un document PDF que expliqui el funcionament de l'aplicació i contingui una descripció de la seva arquitectura, incloent classes, patrons, dependències, etc.

S'ha de fer servir el framework Spring, com s'ha explicat a classe. La capa de persistència ha d'estar implementada amb Hibernate.

Es recomana l'ús de plugins com datatables o equivalents per millorar la funcionalitat de la interfície d'usuari, tal com s'ha mostrat a classe.

Es valorarà especialment l'atenció prestada a l'aspecte estètic del resultat final.