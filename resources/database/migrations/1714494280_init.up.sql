CREATE TABLE IF NOT EXISTS urls (
    original_url    TEXT NOT NULL UNIQUE,
    short_url       TEXT NOT NULL UNIQUE,
    PRIMARY KEY (original_url, short_url)
);
