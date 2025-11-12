create table producto (
	id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	nombre varchar(50) not null,
	precio numeric(10, 2) not null check (precio > 0)
);