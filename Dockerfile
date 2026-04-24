FROM postgres
ENV POSTGRES_DB="flowers-postgres"
COPY dane.sql /docker-entrypoint-initdb.d/