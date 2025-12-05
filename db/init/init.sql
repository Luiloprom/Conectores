alter session set container = instituto;

grant connect,resource to usuario;

-- crear las tablas en ese esquema
create table usuario.profesor (
   cod_prof number(2) primary key,
   nombre   varchar2(30) not null,
   apellido varchar2(30) not null
);

create table usuario.alumno (
   cod_alumn number(2) primary key,
   nombre    varchar2(30) not null,
   apellido  varchar2(30),
   edad      number(2),
   cod_tutor number(2),
   constraint fk_alumno_profesor foreign key ( cod_tutor )
      references usuario.profesor ( cod_prof )
);