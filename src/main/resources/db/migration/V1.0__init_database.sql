CREATE TYPE level AS ENUM ('ERROR', 'WARNING', 'INFO');

CREATE TABLE event (
	id bigserial PRIMARY KEY,
	event_level level NOT NULL,
	description VARCHAR(500),
	log VARCHAR(255) UNIQUE NOT NULL,
	system VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
	quantity integer
);
