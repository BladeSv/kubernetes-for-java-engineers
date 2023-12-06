CREATE TABLE public.mp3resource (
    id SERIAL NOT NULL,
    file_name varchar(255) NULL,
    mp3record oid NULL,
    CONSTRAINT mp3resource_pkey PRIMARY KEY (id)
);