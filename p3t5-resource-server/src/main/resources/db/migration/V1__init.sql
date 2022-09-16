

CREATE TABLE match_status (
	id int8 NOT NULL AUTO_INCREMENT,
	name varchar(20) NULL,
	CONSTRAINT match_status_pkey PRIMARY KEY (id)
);


CREATE TABLE player (
	id int8 NOT NULL AUTO_INCREMENT,
	name varchar(50) NULL,
	email varchar(200) NULL,
	CONSTRAINT player_pkey PRIMARY KEY (id),
	CONSTRAINT email_unique UNIQUE (email)
);


CREATE TABLE `match` (
	id int8 NOT NULL AUTO_INCREMENT,
	player_one_id int8 NULL,
	player_two_id int8 NULL,
	status_id int8 NULL,
	moves text NULL,
	CONSTRAINT match_pkey PRIMARY KEY (id),
	CONSTRAINT match_player_one_id_fkey FOREIGN KEY (player_one_id) REFERENCES player(id),
	CONSTRAINT match_player_two_id_fkey FOREIGN KEY (player_two_id) REFERENCES player(id),
	CONSTRAINT match_status_id_fkey FOREIGN KEY (status_id) REFERENCES match_status(id)
);

insert into match_status(id,name) values(1,'IN_PROGRESS');
insert into match_status(id,name) values(2,'FINISHED');
insert into match_status(id,name) values(0,'READY');
