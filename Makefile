include .env

.PHONY: run

run:
	@DB_DRIVER=$(DB_DRIVER) DB_NAME=$(DB_NAME) DB_USER=$(DB_USER) DB_PASSWORD=$(DB_PASSWORD) \
	lein run -m tinyurl.core