# syntax=docker/dockerfile:1

# Postgres image that bakes in the application schema.
# Scripts in /docker-entrypoint-initdb.d run ONLY on first init (empty data
# volume). base-schema.sql is destructive (DROP TABLE ...), so it must not run
# on every boot — the named data volume preserves data across restarts.
#
# Build context must be the repo root:
#   docker build -f db/Dockerfile -t <image> .
FROM postgres:16-alpine

COPY src/main/resources/static/base-schema.sql /docker-entrypoint-initdb.d/01-base-schema.sql
