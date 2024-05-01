include .env

.PHONY: run

run:
	@DB_DRIVER=$(DB_DRIVER) DB_NAME=$(DB_NAME) DB_USER=$(DB_USER) DB_PASSWORD=$(DB_PASSWORD) APP_AUTH=$(APP_AUTH) \
	lein run -m tinyurl.core