FROM mysql
ENV MYSQL_DATABASE="flowers-mysql"
COPY dane.sql /docker-entrypoint-initdb.d/