

CREATE TABLE match_status (
	id serial NOT NULL,
	"name" varchar NULL,
	CONSTRAINT match_status_pkey PRIMARY KEY (id)
);


CREATE TABLE player (
	id serial NOT NULL,
	"name" varchar NULL,
	api_key varchar NULL,
	CONSTRAINT player_pkey PRIMARY KEY (id)
);


CREATE TABLE "match" (
	id serial NOT NULL,
	"player_one_id" int4 NULL,
	"player_two_id" int4 NULL,
	status_id int4 NULL,
	match_data json NULL,
	CONSTRAINT match_pkey PRIMARY KEY (id),
	CONSTRAINT match_player_one_id_fkey FOREIGN KEY ("player_one_id") REFERENCES player(id),
	CONSTRAINT match_player_two_id_fkey FOREIGN KEY ("player_two_id") REFERENCES player(id),
	CONSTRAINT match_status_id_fkey FOREIGN KEY (status_id) REFERENCES match_status(id)
);