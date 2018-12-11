CREATE SEQUENCE tbl_alumno_seq;

CREATE TABLE public.tbl_alumno
(
    id integer NOT NULL DEFAULT nextval('tbl_alumno_seq'),
    id_curso integer NOT NULL REFERENCES tbl_curso(id) ON DELETE CASCADE,
    rut character varying(20) NOT NULL,
    nombres character varying(300) NOT NULL,
    apellidos character varying(300) NOT NULL,
    fono character varying(100) NOT NULL,
    correo character varying(300) NOT NULL,
    fecnac TIMESTAMP NOT NULL,
    sexo BOOLEAN NOT NULL,
    crtd_fecha TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    lupd_fecha TIMESTAMP,
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
comment on column tbl_alumno.fono is 'Fono de contacto del Alumno';
comment on column tbl_alumno.correo is 'Correo de contacto del Alumno';
comment on column tbl_alumno.fecnac is 'Fecha de necimiento del Alumno';
comment on column tbl_alumno.sexo is 'Sexo del Alumno, TRUE: Masculino - FALSE: Femenino';
comment on column tbl_alumno.crtd_fecha is 'Fecha de creacion del registro';
comment on column tbl_alumno.lupd_fecha is 'Ultima fecha de actualizacion del registro';

create index on tbl_alumno(id);

ALTER TABLE public.tbl_alumno
    OWNER to zxrksxbuvmwrjt;

ALTER SEQUENCE tbl_alumno_seq
    OWNED BY tbl_alumno.id;

