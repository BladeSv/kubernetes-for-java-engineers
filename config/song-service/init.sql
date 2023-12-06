CREATE TABLE public.song_dao (
	id SERIAL NOT NULL,
	album varchar(255) NULL,
	artist varchar(255) NULL,
	length varchar(255) NULL,
	"name" varchar(255) NULL,
	resource_id varchar(255) NULL,
	"year" varchar(255) NULL,
	CONSTRAINT song_dao_pkey PRIMARY KEY (id)
)