CREATE SEQUENCE tbl_alumno_seq;

CREATE TABLE public.tbl_alumno
(
    id integer NOT NULL DEFAULT nextval('tbl_alumno_seq'),
    id_curso integer NOT NULL REFERENCES tbl_curso(id) ON DELETE CASCADE,
    rut character varying(20) NOT NULL,
    nombres character varying(300) NOT NULL,
    apellidos character varying(300) NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

comment on table tbl_alumno is 'Tabla destinada a contener el listado de alumnos.';
comment on column tbl_alumno.id is 'ID del registro';
comment on column tbl_alumno.id_curso is 'ID del curso';
comment on column tbl_alumno.rut is 'RUT del alumno';
comment on column tbl_alumno.nombres is 'Nombres del Alumno';
comment on column tbl_alumno.apellidos is 'Apellidos del Alumno';

create index on tbl_alumno(id);

ALTER TABLE public.tbl_alumno
    OWNER to zxrksxbuvmwrjt;

ALTER SEQUENCE tbl_alumno_seq
    OWNED BY tbl_alumno.id;

