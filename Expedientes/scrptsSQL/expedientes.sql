drop table if exists exp_documento cascade;
drop table if exists exp_entidad cascade;
drop table if exists exp_evento cascade;
drop table if exists exp_expediente cascade;
drop table if exists exp_expediente_exp_fase cascade;
drop table if exists exp_fase cascade;
drop table if exists exp_fasecatalogo cascade;
drop table if exists exp_faseprocedimiento cascade;
drop table if exists exp_hito cascade;
drop table if exists exp_hitos cascade;
drop table if exists exp_nodoprocedimiento cascade;
drop table if exists exp_plazo cascade;
drop table if exists exp_procedimiento cascade;
drop table if exists exp_rela_fasecatalogo_tramitecatalogo cascade;
drop table if exists exp_rela_faseprocedimiento_tramiteprocedimiento cascade;
drop table if exists exp_rela_procedimiento_faseprocedimiento cascade;
drop table if exists exp_rela_responsable_area cascade;
drop table if exists exp_rela_responsable_usuario cascade;
drop table if exists exp_responsable cascade;
drop table if exists exp_tramite cascade;
drop table if exists exp_tramite_exp_documento cascade;
drop table if exists exp_tramitecatalogo cascade;
drop table if exists exp_tramiteprocedimiento cascade;

drop sequence if exists gen_id_exp_documento;
drop sequence if exists gen_id_exp_entidad;
drop sequence if exists gen_id_exp_evento;
drop sequence if exists gen_id_exp_expediente;
drop sequence if exists gen_id_exp_fase;
drop sequence if exists gen_id_exp_fasecatalogo;
drop sequence if exists gen_id_exp_hito;
drop sequence if exists gen_id_exp_nodoprocedimiento;
drop sequence if exists gen_id_exp_plazo;
drop sequence if exists gen_id_exp_responsable;
drop sequence if exists gen_id_exp_tramite;
drop sequence if exists gen_id_exp_tramitecatalogo;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.9
-- Dumped by pg_dump version 9.1.9
-- Started on 2014-03-28 14:20:29


--
-- TOC entry 737 (class 1259 OID 230950)
-- Dependencies: 6
-- Name: exp_documento; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_documento (
    id_documento clave NOT NULL,
    activo boolean NOT NULL,
    estado character varying(255),
    fecha timestamp without time zone,
    localizacion character varying(255),
    nroregistro bigint,
    observacion character varying(255),
    id_documentoprocedimiento clave,
    id_tramite clave
);


ALTER TABLE public.exp_documento OWNER TO vipians;

--
-- TOC entry 738 (class 1259 OID 230958)
-- Dependencies: 6
-- Name: exp_documentocatalogo; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_documentocatalogo (
    id_documentocatalogo clave NOT NULL,
    nombre character varying(255) NOT NULL,
    estado character varying(255)
);


ALTER TABLE public.exp_documentocatalogo OWNER TO vipians;

--
-- TOC entry 739 (class 1259 OID 230963)
-- Dependencies: 6
-- Name: exp_documentoprocedimiento; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_documentoprocedimiento (
    id_nodoprocedimeinto clave NOT NULL,
    id_docuemntocatalogo clave
);


ALTER TABLE public.exp_documentoprocedimiento OWNER TO vipians;

--
-- TOC entry 740 (class 1259 OID 230968)
-- Dependencies: 6
-- Name: exp_entidad; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_entidad (
    id_entidad clave NOT NULL,
    activo boolean NOT NULL,
    nombre character varying(255) NOT NULL
);


ALTER TABLE public.exp_entidad OWNER TO vipians;

--
-- TOC entry 741 (class 1259 OID 230973)
-- Dependencies: 6
-- Name: exp_evento; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_evento (
    id_evento clave NOT NULL,
    activo boolean NOT NULL,
    nombre character varying(255) NOT NULL
);


ALTER TABLE public.exp_evento OWNER TO vipians;

--
-- TOC entry 742 (class 1259 OID 230978)
-- Dependencies: 6
-- Name: exp_expediente; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_expediente (
    asunto character varying(255) NOT NULL,
    estado character varying(255),
    fecha_registro timestamp without time zone,
    nroregistro bigint NOT NULL,
    id_nodoexpediente clave NOT NULL,
    id_faseactual clave,
    id_interesado clave
);


ALTER TABLE public.exp_expediente OWNER TO vipians;

--
-- TOC entry 743 (class 1259 OID 230986)
-- Dependencies: 6
-- Name: exp_fase; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_fase (
    activa boolean,
    estado character varying(255),
    id_nodoexpediente clave NOT NULL
);


ALTER TABLE public.exp_fase OWNER TO vipians;

--
-- TOC entry 744 (class 1259 OID 230991)
-- Dependencies: 6
-- Name: exp_fasecatalogo; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_fasecatalogo (
    id_fasecatalogo clave NOT NULL,
    estado character varying(255),
    nombre character varying(255) NOT NULL
);


ALTER TABLE public.exp_fasecatalogo OWNER TO vipians;

--
-- TOC entry 745 (class 1259 OID 230999)
-- Dependencies: 6
-- Name: exp_faseprocedimiento; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_faseprocedimiento (
    id_nodoprocedimeinto clave NOT NULL,
    id_fasecatalogo clave
);


ALTER TABLE public.exp_faseprocedimiento OWNER TO vipians;

--
-- TOC entry 746 (class 1259 OID 231004)
-- Dependencies: 6
-- Name: exp_hito; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_hito (
    id_hito clave NOT NULL,
    descripcion character varying(255) NOT NULL,
    fecha timestamp without time zone,
    nombre character varying(255) NOT NULL,
    id_nodoexpediente clave,
    id_usuario clave
);


ALTER TABLE public.exp_hito OWNER TO vipians;

--
-- TOC entry 747 (class 1259 OID 231012)
-- Dependencies: 6
-- Name: exp_nodoexpediente; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_nodoexpediente (
    id_nodoexpediente clave NOT NULL,
    fecha_fin timestamp without time zone,
    fecha_inicio timestamp without time zone,
    id_nodopadre clave,
    id_nodoprocedimiento clave,
    id_plazo clave
);


ALTER TABLE public.exp_nodoexpediente OWNER TO vipians;

--
-- TOC entry 748 (class 1259 OID 231017)
-- Dependencies: 6
-- Name: exp_nodoprocedimiento; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_nodoprocedimiento (
    id_nodoprocedimeinto clave NOT NULL,
    orden integer,
    id_nodopadre clave,
    id_plazo clave,
    id_responsable clave
);


ALTER TABLE public.exp_nodoprocedimiento OWNER TO vipians;

--
-- TOC entry 749 (class 1259 OID 231022)
-- Dependencies: 6
-- Name: exp_plazo; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_plazo (
    id_plazo clave NOT NULL,
    extension bytea,
    fecha_fin timestamp without time zone,
    fecha_inicio timestamp without time zone,
    id_plazoprocedimiento clave
);


ALTER TABLE public.exp_plazo OWNER TO vipians;

--
-- TOC entry 750 (class 1259 OID 231030)
-- Dependencies: 6
-- Name: exp_plazoprocedimiento; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_plazoprocedimiento (
    id_plazo clave NOT NULL,
    dias integer NOT NULL,
    diascorridos boolean
);


ALTER TABLE public.exp_plazoprocedimiento OWNER TO vipians;

--
-- TOC entry 751 (class 1259 OID 231035)
-- Dependencies: 6
-- Name: exp_procedimiento; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_procedimiento (
    estado character varying(255),
    nombre character varying(255),
    id_nodoprocedimeinto clave NOT NULL
);


ALTER TABLE public.exp_procedimiento OWNER TO vipians;

--
-- TOC entry 752 (class 1259 OID 231043)
-- Dependencies: 6
-- Name: exp_rela_fasecatalogo_tramitecatalogo; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_rela_fasecatalogo_tramitecatalogo (
    id_fasecatalogo clave NOT NULL,
    id_tramitecatalogo clave NOT NULL
);


ALTER TABLE public.exp_rela_fasecatalogo_tramitecatalogo OWNER TO vipians;

--
-- TOC entry 753 (class 1259 OID 231046)
-- Dependencies: 6
-- Name: exp_rela_responsable_area; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_rela_responsable_area (
    id_responsable clave NOT NULL,
    id_area clave NOT NULL
);


ALTER TABLE public.exp_rela_responsable_area OWNER TO vipians;

--
-- TOC entry 754 (class 1259 OID 231049)
-- Dependencies: 6
-- Name: exp_rela_responsable_revision_area; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_rela_responsable_revision_area (
    id_responsable clave NOT NULL,
    id_area clave NOT NULL
);


ALTER TABLE public.exp_rela_responsable_revision_area OWNER TO vipians;

--
-- TOC entry 755 (class 1259 OID 231052)
-- Dependencies: 6
-- Name: exp_rela_responsable_revision_usuario; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_rela_responsable_revision_usuario (
    id_responsable clave NOT NULL,
    id_usuario clave NOT NULL
);


ALTER TABLE public.exp_rela_responsable_revision_usuario OWNER TO vipians;

--
-- TOC entry 756 (class 1259 OID 231055)
-- Dependencies: 6
-- Name: exp_rela_responsable_usuario; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_rela_responsable_usuario (
    id_responsable clave NOT NULL,
    id_usuario clave NOT NULL
);


ALTER TABLE public.exp_rela_responsable_usuario OWNER TO vipians;

--
-- TOC entry 757 (class 1259 OID 231058)
-- Dependencies: 6
-- Name: exp_rela_tramitecatalogo_documentocatalogo; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_rela_tramitecatalogo_documentocatalogo (
    id_tramitecatalogo clave NOT NULL,
    id_documentocatalogo clave NOT NULL
);


ALTER TABLE public.exp_rela_tramitecatalogo_documentocatalogo OWNER TO vipians;

--
-- TOC entry 782 (class 1259 OID 232038)
-- Dependencies: 6
-- Name: exp_responsabilidad; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_responsabilidad (
    tipo character varying(31) NOT NULL,
    id_responsabilidad clave NOT NULL,
    responsabilidad character varying(255),
    id_nodoprocedimiento clave,
    id_area clave,
    id_usuario clave
);


ALTER TABLE public.exp_responsabilidad OWNER TO vipians;

--
-- TOC entry 758 (class 1259 OID 231061)
-- Dependencies: 6
-- Name: exp_responsable; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_responsable (
    id_fase clave NOT NULL,
    nombre character varying(255)
);


ALTER TABLE public.exp_responsable OWNER TO vipians;

--
-- TOC entry 759 (class 1259 OID 231066)
-- Dependencies: 6
-- Name: exp_tramite; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_tramite (
    comentarios character varying(255),
    estado character varying(255),
    id_nodoexpediente clave NOT NULL,
    id_usuarioactivo clave
);


ALTER TABLE public.exp_tramite OWNER TO vipians;

--
-- TOC entry 760 (class 1259 OID 231074)
-- Dependencies: 6
-- Name: exp_tramitecatalogo; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_tramitecatalogo (
    id_tramitecatalogo clave NOT NULL,
    estado character varying(255),
    nombre character varying(255) NOT NULL
);


ALTER TABLE public.exp_tramitecatalogo OWNER TO vipians;

--
-- TOC entry 761 (class 1259 OID 231082)
-- Dependencies: 6
-- Name: exp_tramiteprocedimiento; Type: TABLE; Schema: public; Owner: vipians; Tablespace: 
--

CREATE TABLE exp_tramiteprocedimiento (
    id_nodoprocedimeinto clave NOT NULL,
    id_tramitecatalogo clave
);


ALTER TABLE public.exp_tramiteprocedimiento OWNER TO vipians;

--
-- TOC entry 3871 (class 2606 OID 230957)
-- Dependencies: 737 737 3951
-- Name: exp_documento_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_documento
    ADD CONSTRAINT exp_documento_pkey PRIMARY KEY (id_documento);


--
-- TOC entry 3873 (class 2606 OID 230962)
-- Dependencies: 738 738 3951
-- Name: exp_documentocatalogo_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_documentocatalogo
    ADD CONSTRAINT exp_documentocatalogo_pkey PRIMARY KEY (id_documentocatalogo);


--
-- TOC entry 3875 (class 2606 OID 230967)
-- Dependencies: 739 739 3951
-- Name: exp_documentoprocedimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_documentoprocedimiento
    ADD CONSTRAINT exp_documentoprocedimiento_pkey PRIMARY KEY (id_nodoprocedimeinto);


--
-- TOC entry 3877 (class 2606 OID 230972)
-- Dependencies: 740 740 3951
-- Name: exp_entidad_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_entidad
    ADD CONSTRAINT exp_entidad_pkey PRIMARY KEY (id_entidad);


--
-- TOC entry 3879 (class 2606 OID 230977)
-- Dependencies: 741 741 3951
-- Name: exp_evento_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_evento
    ADD CONSTRAINT exp_evento_pkey PRIMARY KEY (id_evento);


--
-- TOC entry 3881 (class 2606 OID 230985)
-- Dependencies: 742 742 3951
-- Name: exp_expediente_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_expediente
    ADD CONSTRAINT exp_expediente_pkey PRIMARY KEY (id_nodoexpediente);


--
-- TOC entry 3883 (class 2606 OID 230990)
-- Dependencies: 743 743 3951
-- Name: exp_fase_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_fase
    ADD CONSTRAINT exp_fase_pkey PRIMARY KEY (id_nodoexpediente);


--
-- TOC entry 3885 (class 2606 OID 230998)
-- Dependencies: 744 744 3951
-- Name: exp_fasecatalogo_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_fasecatalogo
    ADD CONSTRAINT exp_fasecatalogo_pkey PRIMARY KEY (id_fasecatalogo);


--
-- TOC entry 3887 (class 2606 OID 231003)
-- Dependencies: 745 745 3951
-- Name: exp_faseprocedimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_faseprocedimiento
    ADD CONSTRAINT exp_faseprocedimiento_pkey PRIMARY KEY (id_nodoprocedimeinto);


--
-- TOC entry 3889 (class 2606 OID 231011)
-- Dependencies: 746 746 3951
-- Name: exp_hito_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_hito
    ADD CONSTRAINT exp_hito_pkey PRIMARY KEY (id_hito);


--
-- TOC entry 3891 (class 2606 OID 231016)
-- Dependencies: 747 747 3951
-- Name: exp_nodoexpediente_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_nodoexpediente
    ADD CONSTRAINT exp_nodoexpediente_pkey PRIMARY KEY (id_nodoexpediente);


--
-- TOC entry 3893 (class 2606 OID 231021)
-- Dependencies: 748 748 3951
-- Name: exp_nodoprocedimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_nodoprocedimiento
    ADD CONSTRAINT exp_nodoprocedimiento_pkey PRIMARY KEY (id_nodoprocedimeinto);


--
-- TOC entry 3895 (class 2606 OID 231029)
-- Dependencies: 749 749 3951
-- Name: exp_plazo_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_plazo
    ADD CONSTRAINT exp_plazo_pkey PRIMARY KEY (id_plazo);


--
-- TOC entry 3897 (class 2606 OID 231034)
-- Dependencies: 750 750 3951
-- Name: exp_plazoprocedimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_plazoprocedimiento
    ADD CONSTRAINT exp_plazoprocedimiento_pkey PRIMARY KEY (id_plazo);


--
-- TOC entry 3899 (class 2606 OID 231042)
-- Dependencies: 751 751 3951
-- Name: exp_procedimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_procedimiento
    ADD CONSTRAINT exp_procedimiento_pkey PRIMARY KEY (id_nodoprocedimeinto);


--
-- TOC entry 3909 (class 2606 OID 232042)
-- Dependencies: 782 782 3951
-- Name: exp_responsabilidad_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_responsabilidad
    ADD CONSTRAINT exp_responsabilidad_pkey PRIMARY KEY (id_responsabilidad);


--
-- TOC entry 3901 (class 2606 OID 231065)
-- Dependencies: 758 758 3951
-- Name: exp_responsable_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_responsable
    ADD CONSTRAINT exp_responsable_pkey PRIMARY KEY (id_fase);


--
-- TOC entry 3903 (class 2606 OID 231073)
-- Dependencies: 759 759 3951
-- Name: exp_tramite_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_tramite
    ADD CONSTRAINT exp_tramite_pkey PRIMARY KEY (id_nodoexpediente);


--
-- TOC entry 3905 (class 2606 OID 231081)
-- Dependencies: 760 760 3951
-- Name: exp_tramitecatalogo_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_tramitecatalogo
    ADD CONSTRAINT exp_tramitecatalogo_pkey PRIMARY KEY (id_tramitecatalogo);


--
-- TOC entry 3907 (class 2606 OID 231086)
-- Dependencies: 761 761 3951
-- Name: exp_tramiteprocedimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: vipians; Tablespace: 
--

ALTER TABLE ONLY exp_tramiteprocedimiento
    ADD CONSTRAINT exp_tramiteprocedimiento_pkey PRIMARY KEY (id_nodoprocedimeinto);


--
-- TOC entry 3929 (class 2606 OID 231432)
-- Dependencies: 751 3892 748 3951
-- Name: fk3ae40b32cb0ceea0; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_procedimiento
    ADD CONSTRAINT fk3ae40b32cb0ceea0 FOREIGN KEY (id_nodoprocedimeinto) REFERENCES exp_nodoprocedimiento(id_nodoprocedimeinto);


--
-- TOC entry 3948 (class 2606 OID 232213)
-- Dependencies: 782 758 3900 3951
-- Name: fk47e533c42e628cbe; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_responsabilidad
    ADD CONSTRAINT fk47e533c42e628cbe FOREIGN KEY (id_nodoprocedimiento) REFERENCES exp_responsable(id_fase);


--
-- TOC entry 3946 (class 2606 OID 232203)
-- Dependencies: 782 163 3951
-- Name: fk47e533c437a36454; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_responsabilidad
    ADD CONSTRAINT fk47e533c437a36454 FOREIGN KEY (id_area) REFERENCES area(id_area);


--
-- TOC entry 3949 (class 2606 OID 232218)
-- Dependencies: 782 748 3892 3951
-- Name: fk47e533c4cb437b28; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_responsabilidad
    ADD CONSTRAINT fk47e533c4cb437b28 FOREIGN KEY (id_nodoprocedimiento) REFERENCES exp_nodoprocedimiento(id_nodoprocedimeinto);


--
-- TOC entry 3947 (class 2606 OID 232208)
-- Dependencies: 782 720 3951
-- Name: fk47e533c4f841a0e2; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_responsabilidad
    ADD CONSTRAINT fk47e533c4f841a0e2 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 3931 (class 2606 OID 231442)
-- Dependencies: 3884 744 752 3951
-- Name: fk64b4fb3fc563c26e; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_fasecatalogo_tramitecatalogo
    ADD CONSTRAINT fk64b4fb3fc563c26e FOREIGN KEY (id_fasecatalogo) REFERENCES exp_fasecatalogo(id_fasecatalogo);


--
-- TOC entry 3930 (class 2606 OID 231437)
-- Dependencies: 752 3904 760 3951
-- Name: fk64b4fb3fda6f4fa4; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_fasecatalogo_tramitecatalogo
    ADD CONSTRAINT fk64b4fb3fda6f4fa4 FOREIGN KEY (id_tramitecatalogo) REFERENCES exp_tramitecatalogo(id_tramitecatalogo);


--
-- TOC entry 3941 (class 2606 OID 231492)
-- Dependencies: 3872 738 757 3951
-- Name: fk7c6d237cabfe04ac; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_tramitecatalogo_documentocatalogo
    ADD CONSTRAINT fk7c6d237cabfe04ac FOREIGN KEY (id_documentocatalogo) REFERENCES exp_documentocatalogo(id_documentocatalogo);


--
-- TOC entry 3940 (class 2606 OID 231487)
-- Dependencies: 3904 760 757 3951
-- Name: fk7c6d237cda6f4fa4; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_tramitecatalogo_documentocatalogo
    ADD CONSTRAINT fk7c6d237cda6f4fa4 FOREIGN KEY (id_tramitecatalogo) REFERENCES exp_tramitecatalogo(id_tramitecatalogo);


--
-- TOC entry 3933 (class 2606 OID 231452)
-- Dependencies: 3900 758 753 3951
-- Name: fk85dac403290a6e34; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_responsable_area
    ADD CONSTRAINT fk85dac403290a6e34 FOREIGN KEY (id_responsable) REFERENCES exp_responsable(id_fase);


--
-- TOC entry 3932 (class 2606 OID 231447)
-- Dependencies: 163 753 3951
-- Name: fk85dac40337a36454; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_responsable_area
    ADD CONSTRAINT fk85dac40337a36454 FOREIGN KEY (id_area) REFERENCES area(id_area);


--
-- TOC entry 3914 (class 2606 OID 231357)
-- Dependencies: 743 3882 742 3951
-- Name: fk8620064944470a30; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_expediente
    ADD CONSTRAINT fk8620064944470a30 FOREIGN KEY (id_faseactual) REFERENCES exp_fase(id_nodoexpediente);


--
-- TOC entry 3915 (class 2606 OID 231362)
-- Dependencies: 747 3890 742 3951
-- Name: fk86200649ac5c8e8e; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_expediente
    ADD CONSTRAINT fk86200649ac5c8e8e FOREIGN KEY (id_nodoexpediente) REFERENCES exp_nodoexpediente(id_nodoexpediente);


--
-- TOC entry 3916 (class 2606 OID 231367)
-- Dependencies: 587 742 3951
-- Name: fk86200649ecf004dc; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_expediente
    ADD CONSTRAINT fk86200649ecf004dc FOREIGN KEY (id_interesado) REFERENCES persona(id_persona);


--
-- TOC entry 3912 (class 2606 OID 231347)
-- Dependencies: 738 739 3872 3951
-- Name: fk955cbc627572fb9c; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_documentoprocedimiento
    ADD CONSTRAINT fk955cbc627572fb9c FOREIGN KEY (id_docuemntocatalogo) REFERENCES exp_documentocatalogo(id_documentocatalogo);


--
-- TOC entry 3913 (class 2606 OID 231352)
-- Dependencies: 739 3892 748 3951
-- Name: fk955cbc62cb0ceea0; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_documentoprocedimiento
    ADD CONSTRAINT fk955cbc62cb0ceea0 FOREIGN KEY (id_nodoprocedimeinto) REFERENCES exp_nodoprocedimiento(id_nodoprocedimeinto);


--
-- TOC entry 3918 (class 2606 OID 231377)
-- Dependencies: 744 745 3884 3951
-- Name: fka0bc1105c563c26e; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_faseprocedimiento
    ADD CONSTRAINT fka0bc1105c563c26e FOREIGN KEY (id_fasecatalogo) REFERENCES exp_fasecatalogo(id_fasecatalogo);


--
-- TOC entry 3919 (class 2606 OID 231382)
-- Dependencies: 748 745 3892 3951
-- Name: fka0bc1105cb0ceea0; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_faseprocedimiento
    ADD CONSTRAINT fka0bc1105cb0ceea0 FOREIGN KEY (id_nodoprocedimeinto) REFERENCES exp_nodoprocedimiento(id_nodoprocedimeinto);


--
-- TOC entry 3926 (class 2606 OID 231417)
-- Dependencies: 3900 758 748 3951
-- Name: fkad3c9a6290a6e34; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_nodoprocedimiento
    ADD CONSTRAINT fkad3c9a6290a6e34 FOREIGN KEY (id_responsable) REFERENCES exp_responsable(id_fase);


--
-- TOC entry 3927 (class 2606 OID 231422)
-- Dependencies: 750 748 3896 3951
-- Name: fkad3c9a65c46b934; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_nodoprocedimiento
    ADD CONSTRAINT fkad3c9a65c46b934 FOREIGN KEY (id_plazo) REFERENCES exp_plazoprocedimiento(id_plazo);


--
-- TOC entry 3925 (class 2606 OID 231412)
-- Dependencies: 3892 748 748 3951
-- Name: fkad3c9a6d3e9df1a; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_nodoprocedimiento
    ADD CONSTRAINT fkad3c9a6d3e9df1a FOREIGN KEY (id_nodopadre) REFERENCES exp_nodoprocedimiento(id_nodoprocedimeinto);


--
-- TOC entry 3936 (class 2606 OID 231467)
-- Dependencies: 3900 758 755 3951
-- Name: fkbc7f3d20290a6e34; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_responsable_revision_usuario
    ADD CONSTRAINT fkbc7f3d20290a6e34 FOREIGN KEY (id_responsable) REFERENCES exp_responsable(id_fase);


--
-- TOC entry 3937 (class 2606 OID 231472)
-- Dependencies: 720 755 3951
-- Name: fkbc7f3d20f841a0e2; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_responsable_revision_usuario
    ADD CONSTRAINT fkbc7f3d20f841a0e2 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 3917 (class 2606 OID 231372)
-- Dependencies: 3890 747 743 3951
-- Name: fkbcb4b14fac5c8e8e; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_fase
    ADD CONSTRAINT fkbcb4b14fac5c8e8e FOREIGN KEY (id_nodoexpediente) REFERENCES exp_nodoexpediente(id_nodoexpediente);


--
-- TOC entry 3920 (class 2606 OID 231387)
-- Dependencies: 3890 746 747 3951
-- Name: fkbcb5b83eac5c8e8e; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_hito
    ADD CONSTRAINT fkbcb5b83eac5c8e8e FOREIGN KEY (id_nodoexpediente) REFERENCES exp_nodoexpediente(id_nodoexpediente);


--
-- TOC entry 3921 (class 2606 OID 231392)
-- Dependencies: 720 746 3951
-- Name: fkbcb5b83ef841a0e2; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_hito
    ADD CONSTRAINT fkbcb5b83ef841a0e2 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 3928 (class 2606 OID 231427)
-- Dependencies: 749 3896 750 3951
-- Name: fkda7322f8fb882b9c; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_plazo
    ADD CONSTRAINT fkda7322f8fb882b9c FOREIGN KEY (id_plazoprocedimiento) REFERENCES exp_plazoprocedimiento(id_plazo);


--
-- TOC entry 3935 (class 2606 OID 231462)
-- Dependencies: 754 3900 758 3951
-- Name: fkded13bb290a6e34; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_responsable_revision_area
    ADD CONSTRAINT fkded13bb290a6e34 FOREIGN KEY (id_responsable) REFERENCES exp_responsable(id_fase);


--
-- TOC entry 3934 (class 2606 OID 231457)
-- Dependencies: 163 754 3951
-- Name: fkded13bb37a36454; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_responsable_revision_area
    ADD CONSTRAINT fkded13bb37a36454 FOREIGN KEY (id_area) REFERENCES area(id_area);


--
-- TOC entry 3943 (class 2606 OID 231502)
-- Dependencies: 720 759 3951
-- Name: fke80650ce167a7072; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_tramite
    ADD CONSTRAINT fke80650ce167a7072 FOREIGN KEY (id_usuarioactivo) REFERENCES usuario(id_usuario);


--
-- TOC entry 3942 (class 2606 OID 231497)
-- Dependencies: 747 759 3890 3951
-- Name: fke80650ceac5c8e8e; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_tramite
    ADD CONSTRAINT fke80650ceac5c8e8e FOREIGN KEY (id_nodoexpediente) REFERENCES exp_nodoexpediente(id_nodoexpediente);


--
-- TOC entry 3945 (class 2606 OID 231512)
-- Dependencies: 3892 748 761 3951
-- Name: fkee4bd7e6cb0ceea0; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_tramiteprocedimiento
    ADD CONSTRAINT fkee4bd7e6cb0ceea0 FOREIGN KEY (id_nodoprocedimeinto) REFERENCES exp_nodoprocedimiento(id_nodoprocedimeinto);


--
-- TOC entry 3944 (class 2606 OID 231507)
-- Dependencies: 761 760 3904 3951
-- Name: fkee4bd7e6da6f4fa4; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_tramiteprocedimiento
    ADD CONSTRAINT fkee4bd7e6da6f4fa4 FOREIGN KEY (id_tramitecatalogo) REFERENCES exp_tramitecatalogo(id_tramitecatalogo);


--
-- TOC entry 3938 (class 2606 OID 231477)
-- Dependencies: 758 756 3900 3951
-- Name: fkf19447d8290a6e34; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_responsable_usuario
    ADD CONSTRAINT fkf19447d8290a6e34 FOREIGN KEY (id_responsable) REFERENCES exp_responsable(id_fase);


--
-- TOC entry 3939 (class 2606 OID 231482)
-- Dependencies: 720 756 3951
-- Name: fkf19447d8f841a0e2; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_rela_responsable_usuario
    ADD CONSTRAINT fkf19447d8f841a0e2 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);


--
-- TOC entry 3923 (class 2606 OID 231402)
-- Dependencies: 3894 747 749 3951
-- Name: fkf569fc55548fe7ec; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_nodoexpediente
    ADD CONSTRAINT fkf569fc55548fe7ec FOREIGN KEY (id_plazo) REFERENCES exp_plazo(id_plazo);


--
-- TOC entry 3922 (class 2606 OID 231397)
-- Dependencies: 747 747 3890 3951
-- Name: fkf569fc555bcb166d; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_nodoexpediente
    ADD CONSTRAINT fkf569fc555bcb166d FOREIGN KEY (id_nodopadre) REFERENCES exp_nodoexpediente(id_nodoexpediente);


--
-- TOC entry 3924 (class 2606 OID 231407)
-- Dependencies: 747 3892 748 3951
-- Name: fkf569fc55cb437b28; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_nodoexpediente
    ADD CONSTRAINT fkf569fc55cb437b28 FOREIGN KEY (id_nodoprocedimiento) REFERENCES exp_nodoprocedimiento(id_nodoprocedimeinto);


--
-- TOC entry 3911 (class 2606 OID 231342)
-- Dependencies: 3874 739 737 3951
-- Name: fkfb6fd2d22a5f7e68; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_documento
    ADD CONSTRAINT fkfb6fd2d22a5f7e68 FOREIGN KEY (id_documentoprocedimiento) REFERENCES exp_documentoprocedimiento(id_nodoprocedimeinto);


--
-- TOC entry 3910 (class 2606 OID 231337)
-- Dependencies: 3902 737 759 3951
-- Name: fkfb6fd2d22bd5bc98; Type: FK CONSTRAINT; Schema: public; Owner: vipians
--

ALTER TABLE ONLY exp_documento
    ADD CONSTRAINT fkfb6fd2d22bd5bc98 FOREIGN KEY (id_tramite) REFERENCES exp_tramite(id_nodoexpediente);


-- Completed on 2014-03-28 14:20:30

CREATE SEQUENCE gen_id_exp_documento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE gen_id_exp_documento
  OWNER TO vipians;
  
-- DROP SEQUENCE gen_id_exp_entidad;

CREATE SEQUENCE gen_id_exp_entidad
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE gen_id_exp_entidad
  OWNER TO vipians;

  
 -- Sequence: gen_id_exp_evento

-- DROP SEQUENCE gen_id_exp_evento;

CREATE SEQUENCE gen_id_exp_evento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE gen_id_exp_evento
  OWNER TO vipians;
 
-- Sequence: gen_id_exp_fasecatalogo

-- DROP SEQUENCE gen_id_exp_fasecatalogo;

CREATE SEQUENCE gen_id_exp_fasecatalogo
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 8
  CACHE 1;
ALTER TABLE gen_id_exp_fasecatalogo
  OWNER TO vipians;

  -- Sequence: gen_id_exp_hito

-- DROP SEQUENCE gen_id_exp_hito;

CREATE SEQUENCE gen_id_exp_hito
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;
ALTER TABLE gen_id_exp_hito
  OWNER TO vipians;

  -- Sequence: gen_id_exp_nodoexpediente

-- DROP SEQUENCE gen_id_exp_nodoexpediente;

CREATE SEQUENCE gen_id_exp_nodoexpediente
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 254
  CACHE 1;
ALTER TABLE gen_id_exp_nodoexpediente
  OWNER TO vipians;

  -- Sequence: gen_id_exp_nodoprocedimiento

-- DROP SEQUENCE gen_id_exp_nodoprocedimiento;

CREATE SEQUENCE gen_id_exp_nodoprocedimiento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 192
  CACHE 1;
ALTER TABLE gen_id_exp_nodoprocedimiento
  OWNER TO vipians;

  -- Sequence: gen_id_exp_plazo

-- DROP SEQUENCE gen_id_exp_plazo;

CREATE SEQUENCE gen_id_exp_plazo
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 254
  CACHE 1;
ALTER TABLE gen_id_exp_plazo
  OWNER TO vipians;

  -- Sequence: gen_id_exp_plazoprocedimiento

-- DROP SEQUENCE gen_id_exp_plazoprocedimiento;

CREATE SEQUENCE gen_id_exp_plazoprocedimiento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 4
  CACHE 1;
ALTER TABLE gen_id_exp_plazoprocedimiento
  OWNER TO vipians;

  -- Sequence: gen_id_exp_responsabilidad

-- DROP SEQUENCE gen_id_exp_responsabilidad;

CREATE SEQUENCE gen_id_exp_responsabilidad
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE gen_id_exp_responsabilidad
  OWNER TO vipians;

  -- Sequence: gen_id_exp_responsable

-- DROP SEQUENCE gen_id_exp_responsable;

CREATE SEQUENCE gen_id_exp_responsable
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 10
  CACHE 1;
ALTER TABLE gen_id_exp_responsable
  OWNER TO vipians;

  -- Sequence: gen_id_exp_tramitecatalogo

-- DROP SEQUENCE gen_id_exp_tramitecatalogo;

CREATE SEQUENCE gen_id_exp_tramitecatalogo
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 11
  CACHE 1;
ALTER TABLE gen_id_exp_tramitecatalogo
  OWNER TO vipians;


  -- Sequence: gen_id_documentocatalogo

-- DROP SEQUENCE gen_id_documentocatalogo;

CREATE SEQUENCE gen_id_documentocatalogo
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;
ALTER TABLE gen_id_documentocatalogo
  OWNER TO vipians; 
  
  
--
-- PostgreSQL database dump complete
--

