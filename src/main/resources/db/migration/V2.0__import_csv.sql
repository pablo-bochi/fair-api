COPY public.fair(id,long,lat,setcens,areap,coddist,distrito,codsubpref,subpref,regiao5,regiao8,nome_feira,registro,logradouro,numero,bairro,referencia)
FROM 'file:src/main/resources/db/data/DEINFO_AB_FEIRASLIVRES_2014.csv'
DELIMITER ','
CSV HEADER;