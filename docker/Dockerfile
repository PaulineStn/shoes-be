# Utiliser l'image officielle PostgreSQL
FROM postgres:latest

# Définir les variables d'environnement pour PostgreSQL
ENV POSTGRES_USER=myuser
ENV POSTGRES_PASSWORD=mypassword
ENV POSTGRES_DB=shoesdb

# Copier le script SQL d'initialisation
COPY init.sql /docker-entrypoint-initdb.d/

# Exposer le port par défaut de PostgreSQL
EXPOSE 5432

