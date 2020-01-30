create table oportunidade(
	id serial primary key,
	nome_prospecto varchar(80) not null,
	descricao varchar(200) not null,
	valor numeric(10,2)
);