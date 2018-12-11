CREATE SEQUENCE tbl_curso_seq;

CREATE TABLE public.tbl_curso
(
    id integer NOT NULL DEFAULT nextval('tbl_curso_seq'),
    nombre character varying(300) NOT NULL,
    profesor character varying(300) NOT NULL,
    descripcion character varying(1000),
    nivel int NOT NULL,
    basica BOOLEAN NOT NULL DEFAULT TRUE,
    diurno BOOLEAN NOT NULL DEFAULT TRUE,
    crtd_fecha TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    lupd_fecha TIMESTAMP,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

comment on table tbl_curso is 'Tabla destinada a contener el listado de cursos.';
comment on column tbl_curso.id is 'ID del registro';
comment on column tbl_curso.nombre is 'Nombre/Codigo del curso';
comment on column tbl_curso.profesor is 'Nombre del profesor jefe del curso';
comment on column tbl_curso.descripcion is 'Descripcion del curso';
comment on column tbl_curso.nivel is 'Nivel del curso';
comment on column tbl_curso.basica is 'Determina si es un curso de basica o media';
comment on column tbl_curso.diurno is 'Determina si el curso es diurno o nocturno';
comment on column tbl_curso.crtd_fecha is 'Fecha de creacion del registro';
comment on column tbl_curso.lupd_fecha is 'Ultima fecha de actualizacion del registro';

create index on tbl_curso(id);

ALTER TABLE public.tbl_curso
    OWNER to zxrksxbuvmwrjt;

ALTER SEQUENCE tbl_curso_seq
    OWNED BY tbl_curso.id;
