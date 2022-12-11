CREATE SEQUENCE IF NOT EXISTS public.fair_id_seq;
ALTER SEQUENCE public.fair_id_seq
    OWNER TO admin;

CREATE TABLE public.fair
(
    id bigint NOT NULL DEFAULT nextval('fair_id_seq'::regclass),
    long varchar(10),
    lat varchar(10),
    setcens varchar(15),
    areap varchar(13),
    coddist varchar(9),
    distrito varchar(18),
    codsubpref varchar(2),
    subprefe varchar(25),
    regiao5 varchar(6),
    regiao8 varchar(7),
    nome_feira varchar(30),
    registro varchar(6) not null,
    logradouro varchar(34),
    numero varchar(20),
    bairro varchar(20),
    referencia varchar(50),
    CONSTRAINT fair_pkey PRIMARY KEY (id)
);