#!/bin/sh
echo "Esperando a que PostgreSQL esté disponible..."

until pg_isready -h postgres -p 5432 -U postgres; do
  echo "Esperando a PostgreSQL en postgres:5432..."
  sleep 2
done

echo "PostgreSQL está disponible, iniciando app..."
exec "$@"
