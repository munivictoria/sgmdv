alter table detalle_ticket_caja add id_registro_deuda clave;

insert into log_scripts_corridos values(100,100,now());

alter table detalle_ticket_caja add registro_deuda_reatachado boolean not null default false;

--Ponerle numero
insert into log_scripts_corridos values(101,101,now());

DROP FUNCTION IF EXISTS array_sort(anyarray);
CREATE FUNCTION array_sort(array_vals_to_sort anyarray)
	RETURNS TABLE (
		sorted_array anyarray
	)
	AS $BODY$
		BEGIN
			RETURN QUERY SELECT
				ARRAY_AGG(val) AS sorted_array
			FROM
				(
					SELECT
						UNNEST(array_vals_to_sort) AS val
					ORDER BY
						val
				) AS sorted_vals
			;
		END;
	$BODY$
LANGUAGE plpgsql;

--Ponerle un numero.
insert into log_scripts_corridos values(102,102,now());

alter table liquidacion_tasa add fecha_notificacion date;
alter table liquidacion_tasa add fecha_apremio date;

--Ponerle un numero
insert into log_scripts_corridos values(103,103,now());

create table proceso_db(
id_proceso_db clave not null primary key,
nombre varchar(100) not null,
nombre_proceso varchar(100) not null,
descripcion varchar(1000));

alter table proceso_db owner to vipians;

create sequence gen_id_proceso_db;

alter sequence gen_id_proceso_db owner to vipians;

--Ponerle un numero.
insert into log_scripts_corridos values(104,104,now());

-- Function: select p_estado_cuenta();

-- DROP FUNCTION p_estado_cuenta();

CREATE OR REPLACE FUNCTION p_estado_cuenta()
  RETURNS TABLE(id_persona clave, id_parcela clave, tipo_obligacion character varying, id_registro_deuda clave) AS
$BODY$

DECLARE
tipo_ob Varchar(50);
cont Int4;
BEGIN

 return query SELECT ob.id_persona, doc.id_parcela,
        case when doc.tipo_doc_hab_especializado = 'OYSP' then 'OSP'
       when doc.tipo_doc_hab_especializado = 'PLAN_FINANCIACION_OBRA' then 'PO'
       else doc.tipo_doc_hab_especializado end,
        MIN(rd.id_registro_deuda)::clave
 FROM doc_hab_especializado AS doc
 INNER JOIN obligacion AS ob ON ob.id_obligacion = doc.id_obligacion
 INNER JOIN doc_generador_deuda AS doc_gen ON doc_gen.id_obligacion = ob.id_obligacion
 INNER JOIN registro_deuda AS rd ON rd.id_doc_generador_deuda = doc_gen.id_doc_generador_deuda
 INNER JOIN RELA_VENC_LIQ_T AS RELA ON RELA.ID_REGISTRO_DEUDA = RD.ID_REGISTRO_DEUDA
 INNER JOIN vencimiento AS ven ON ven.ID_VENCIMIENTO = RELA.ID_VENCIMIENTO
 WHERE rd.id_registro_cancelacion IS NULL
 --AND doc.tipo_doc_hab_especializado in ('TGI', 'OSP')
 GROUP BY ob.id_persona, doc.id_parcela,
          doc.tipo_doc_hab_especializado
  --        ,rd.id_registro_deuda
 HAVING MAX(ven.fecha) < CURRENT_DATE
 --ORDER BY rd.id_registro_deuda
 ;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION p_estado_cuenta()
  OWNER TO vipians;

  
--Ponerle un numero
insert into log_scripts_corridos values(105,105,now());

  CREATE OR REPLACE FUNCTION p_sumar_distintos(arreglo_id numeric[], arreglo_valor numeric[])
  RETURNS numeric AS
$BODY$
DECLARE
sumador numeric;arreglo_validador numeric[];
BEGIN
/*
Suma los valores de arreglo_valor sin repetir segun el arreglo arreglo_id.
*/
sumador = 0;
for i in 1..array_length(arreglo_id, 1) loop
	if (arreglo_validador @> array[arreglo_id[i]]) then begin
	end;
	else begin
		sumador = sumador + arreglo_valor[i];
		arreglo_validador = arreglo_validador || arreglo_id[i];
	end;
	end if;
end loop;

return sumador;
END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

alter function p_sumar_distintos(numeric[], numeric[]) owner to vipians;

--ponerle un numero
insert into log_scripts_corridos values(106,106,now());

-- Function: select actualizar_deudas_prescritas(2008);

-- DROP FUNCTION actualizar_deudas_prescritas(integer);

CREATE OR REPLACE FUNCTION actualizar_deudas_prescritas(p_anio integer)
  RETURNS character varying AS
$BODY$
declare
v_cantidad int;v_id_registro_cancelacion clave;
BEGIN
drop table if exists temp_table_prescripciones;
create temp table temp_table_prescripciones as(
select rd.id_registro_deuda from 
liquidacion_tasa liq 
join registro_deuda rd on rd.id_registro_deuda = liq.id_registro_deuda
join cuota_liquidacion c on c.id_cuota_liquidacion = liq.id_cuota_liquidacion
join periodo per on per.id_periodo = c.id_periodo
join calendario cal on cal.id_calendario = per.id_calendario
where cal.anio = p_anio 
and rd.id_registro_cancelacion is null
and (liq.fecha_notificacion is null or (liq.fecha_notificacion + interval '5 years') < now()));

select count(*) from temp_table_prescripciones into v_cantidad;

if (v_cantidad > 0) then begin
	insert into registro_cancelacion values 
		(nextval('gen_id_registro_cancelacion'), now())
	returning id_registro_cancelacion into v_id_registro_cancelacion;
	insert into registro_cancelacion_manual values (v_id_registro_cancelacion, 1, 'Cancelacion por deuda prescripta');

	update registro_deuda rd set estado = 'PRESCRIPTA', id_registro_cancelacion = v_id_registro_cancelacion
	from temp_table_prescripciones tempo where rd.id_registro_deuda = tempo.id_registro_deuda;

	return 'Finalizado, '||v_cantidad||' liquidaciones actualizadas.';
end;
end if;
return 'Finalizado, no se encontraron liquidaciones para actualizar.';
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION actualizar_deudas_prescritas(integer)
  OWNER TO vipians;
  
  --Ponerle un numero
  insert into log_scripts_corridos values(107,107,now());
  
  create table configuracion_accesos_directos(
id_configuracion_accesos_directos clave not null primary key,
id_usuario clave not null,
constraint fk_conf_accdir_usuario foreign key (id_usuario) references usuario (id_usuario) on delete cascade on update cascade);

alter table configuracion_accesos_directos owner to vipians;

create sequence gen_id_conf_accesos_directos;

alter SEQUENCE gen_id_conf_accesos_directos owner to vipians;

create table acceso_directo(
id_acceso_directo clave not null primary key,
id_recurso recurso not null,
id_configuracion clave not null,
constraint fk_acceso_config foreign key (id_configuracion) references configuracion_accesos_directos (id_configuracion_accesos_directos) on delete cascade on update cascade);

alter table acceso_directo owner to vipians;

create sequence gen_id_acceso_directo;

alter sequence gen_id_acceso_directo owner to vipians;

--Ponerle un numero
insert into log_scripts_corridos values(108,108,now());



-- Function: insertar_modificador_liquidacion(importe, clave, character varying, clave)

-- DROP FUNCTION insertar_modificador_liquidacion(importe, clave, character varying, clave);

CREATE OR REPLACE FUNCTION insertar_modificador_liquidacion(v_valor importe, v_id_registro_deuda clave, v_nombre character varying, v_id_tipo_modificador clave)
  RETURNS clave AS
$BODY$
declare
v_id_modificador_liquidacion clave;
BEGIN
	select id_modificador_liquidacion from modificador_liquidacion where valor = v_valor and
	id_tipo_modificador = v_id_tipo_modificador into v_id_modificador_liquidacion;
	if (v_id_modificador_liquidacion is null) then begin
		select nextval('gen_id_modificador_liquidacion') into v_id_modificador_liquidacion;
		insert into modificador_liquidacion (id_modificador_liquidacion, nombre, valor, id_tipo_modificador, tipo_modificador)
		values( v_id_modificador_liquidacion, v_nombre, v_valor, v_id_tipo_modificador, 'F');
	end;
	end if;
	insert into rela_modif_liq_liq_t(id_modificador_liquidacion, id_registro_deuda)
		values (v_id_modificador_liquidacion, v_id_registro_deuda);
	return v_id_modificador_liquidacion;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION insertar_modificador_liquidacion(importe, clave, character varying, clave)
  OWNER TO vipians;

insert into log_scripts_corridos values(109,109,now());

delete from tipo_parametro where nombre_variable = 'VALOR_MULTA_POR_OMISION';

insert into log_scripts_corridos values(110,110,now());

alter table domicilio add domicilio_armado varchar(300);

insert into log_scripts_corridos values(111,111,now());

insert into tipo_obligacion values (nextval('gen_id_tipo_obligacion'), 'ARRENDAMIENTO');

insert into log_scripts_corridos values(112,112,now());

delete from plantilla_obligacion where id_tipo_obligacion in 
(select id_tipo_obligacion from tipo_obligacion where nombre = 'Arrendamiento');
delete from plantilla_doc_tasa_menor;
delete from tipo_obligacion where nombre = 'Arrendamiento';

insert into log_scripts_corridos values(113,113,now());

--Completa la columna domicilio_armado a partir de las calles.
update domicilio d set domicilio_armado = 
case when domi.nombre1 is not null then domi.nombre1 else domi.calle end||' '||
case when domi.numero is not null and trim(domi.numero) <> '' then domi.numero 
when domi.nombre2 is not null and domi.nombre3 is null 
	then case when substring(lower(domi.nombre2) from 1 for 1) = 'i' then 'e ' else 'y ' end ||domi.nombre2
when domi.nombre2 is not null and domi.nombre3 is not null 
	then 'entre ' || domi.nombre2 || 
	case when substring(lower(domi.nombre3) from 1 for 1) = 'i' then ' e ' else ' y ' end || domi.nombre3
else ''
end from (select d.id_domicilio, d.numero, d.calle as calle, calle1.nombre as nombre1, calle2.nombre as nombre2, calle3.nombre as nombre3
from domicilio d
left join calle calle1 on d.id_calle = calle1.id_calle
left join calle calle2 on d.id_calle_comienza = calle2.id_calle
left join calle calle3 on d.id_calle_finaliza = calle3.id_calle)
as domi where domi.id_domicilio = d.id_domicilio;

insert into log_scripts_corridos values(114,114,now());

create table plantilla_plan_de_pago (
id_plantilla_plan_de_pago clave not null primary key,
nombre varchar(255),
monto_condonacion_importe importe,
monto_condonacion_interes importe,
condonacion_importe_porcentual boolean,
condonacion_interes_porcentual boolean,
cantidad_cuotas integer,
tasa_nominal_anual importe,
interes_punitorio importe,
dia_vencimiento integer,
cantidad_dias_cese integer,
cantidad_cuotas_cese integer);

alter table plantilla_plan_de_pago owner to vipians;

create sequence gen_id_plantilla_plan_pago;

alter SEQUENCE gen_id_plantilla_plan_pago owner to vipians;

insert into log_scripts_corridos values(115,115,now());

alter table cuota_refinanciac drop id_periodo;

insert into log_scripts_corridos values(116,116,now());

alter table plantilla_plan_de_pago rename column monto_condonacion_interes to monto_condonacion_intereses;

alter table documento_refinanciacion add id_plantilla clave;
alter table documento_refinanciacion add constraint fK_doc_ref_plantilla foreign key (id_plantilla) 
references plantilla_plan_de_pago(id_plantilla_plan_de_pago) on update cascade on delete restrict;

alter table parametro_asociacion add id_plantilla clave;
alter table parametro_asociacion add constraint fK_par_asociacion_plantilla foreign key (id_plantilla) 
references plantilla_plan_de_pago(id_plantilla_plan_de_pago) on update cascade on delete restrict;

-- Function: p_estado_cuenta()

-- DROP FUNCTION p_estado_cuenta();

CREATE OR REPLACE FUNCTION p_estado_cuenta()
  RETURNS TABLE(id_persona clave, id_parcela clave, tipo_obligacion character varying, id_registro_deuda clave) AS
$BODY$

DECLARE
tipo_ob Varchar(50);
cont Int4;
BEGIN

 return query SELECT ob.id_persona, doc.id_parcela,
        case when doc.tipo_doc_hab_especializado = 'OYSP' then 'OSP'
       when doc.tipo_doc_hab_especializado = 'PLAN_FINANCIACION_OBRA' then 'PO'
       else doc.tipo_doc_hab_especializado end,
        MIN(rd.id_registro_deuda)::clave
 FROM doc_hab_especializado AS doc
 INNER JOIN obligacion AS ob ON ob.id_obligacion = doc.id_obligacion
 INNER JOIN doc_generador_deuda AS doc_gen ON doc_gen.id_obligacion = ob.id_obligacion
 INNER JOIN registro_deuda AS rd ON rd.id_doc_generador_deuda = doc_gen.id_doc_generador_deuda
 INNER JOIN liquidacion_tasa as liq on liq.id_registro_deuda = rd.id_registro_deuda
 INNER JOIN cuota_liquidacion cuota on cuota.id_cuota_liquidacion = liq.id_cuota_liquidacion
 INNER JOIN RELA_CUOTA_FECHA_VENCIMIENTO rela on rela.id_cuota_liquidacion = cuota.id_cuota_liquidacion
 WHERE rd.id_registro_cancelacion IS NULL 
 GROUP BY ob.id_persona, doc.id_parcela,
          doc.tipo_doc_hab_especializado
 HAVING MIN(FECHA_VENCIMIENTO) < CURRENT_DATE;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION p_estado_cuenta()
  OWNER TO vipians;

  -- Function: p_estado_cuenta_parcela(numeric)

-- DROP FUNCTION p_estado_cuenta_parcela(numeric);

CREATE OR REPLACE FUNCTION p_estado_cuenta_parcela(IN p_id_parcela numeric)
  RETURNS TABLE(id_persona clave, id_parcela clave, tipo_obligacion character varying, id_registro_deuda clave) AS
$BODY$
DECLARE
tipo_ob Varchar(50);
cont Int4;
BEGIN
 cont := 0;
 return query SELECT pers.id_persona, par.id_parcela,
        case when doc.tipo_doc_hab_especializado = 'OYSP' then 'OSP'
       when doc.tipo_doc_hab_especializado = 'PLAN_FINANCIACION_OBRA' then 'PO'
       else doc.tipo_doc_hab_especializado end,
        rd.id_registro_deuda
 FROM doc_hab_especializado AS doc
 INNER JOIN obligacion AS ob ON ob.id_obligacion = doc.id_obligacion
 INNER JOIN parcela AS par ON par.id_parcela = doc.id_parcela
 INNER JOIN titulo_propiedad AS tp ON tp.id_titulo_propiedad = par.id_titulo_propiedad
 INNER JOIN registro_propietario AS rp ON rp.id_titulo_propiedad = tp.id_titulo_propiedad
 inner join persona AS pers on pers.id_persona = rp.id_persona
 INNER JOIN doc_generador_deuda AS doc_gen ON doc_gen.id_obligacion = ob.id_obligacion
 INNER JOIN registro_deuda AS rd ON rd.id_doc_generador_deuda = doc_gen.id_doc_generador_deuda
 INNER JOIN liquidacion_tasa as liq on liq.id_registro_deuda = rd.id_registro_deuda
 INNER JOIN cuota_liquidacion cuota on cuota.id_cuota_liquidacion = liq.id_cuota_liquidacion
 INNER JOIN RELA_CUOTA_FECHA_VENCIMIENTO rela on rela.id_cuota_liquidacion = cuota.id_cuota_liquidacion
 WHERE rd.id_registro_cancelacion IS NULL AND
       PAR.id_parcela = p_id_parcela
 GROUP BY pers.id_persona, par.id_parcela,
          doc.tipo_doc_hab_especializado,
          rd.id_registro_deuda
 HAVING MIN(FECHA_VENCIMIENTO) < CURRENT_DATE
ORDER BY rd.id_registro_deuda;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION p_estado_cuenta_parcela(numeric)
  OWNER TO vipians;

  -- Function: p_estado_cuenta_persona(numeric)

-- DROP FUNCTION p_estado_cuenta_persona(numeric);

CREATE OR REPLACE FUNCTION p_estado_cuenta_persona(IN p_id_persona numeric)
  RETURNS TABLE(id_persona clave, id_parcela clave, tipo_obligacion character varying, id_registro_deuda clave) AS
$BODY$
DECLARE
tipo_ob Varchar(50);
cont Int4;
BEGIN

 return query SELECT ob.id_persona, doc.id_parcela,
        case when doc.tipo_doc_hab_especializado = 'OYSP' then 'OSP'
       when doc.tipo_doc_hab_especializado = 'PLAN_FINANCIACION_OBRA' then 'PO'
       else doc.tipo_doc_hab_especializado end,
        rd.id_registro_deuda
 FROM doc_hab_especializado AS doc
 INNER JOIN obligacion AS ob ON ob.id_obligacion = doc.id_obligacion
 INNER JOIN doc_generador_deuda AS doc_gen ON doc_gen.id_obligacion = ob.id_obligacion
 INNER JOIN registro_deuda AS rd ON rd.id_doc_generador_deuda = doc_gen.id_doc_generador_deuda
 INNER JOIN liquidacion_tasa as liq on liq.id_registro_deuda = rd.id_registro_deuda
 INNER JOIN cuota_liquidacion cuota on cuota.id_cuota_liquidacion = liq.id_cuota_liquidacion
 INNER JOIN RELA_CUOTA_FECHA_VENCIMIENTO rela on rela.id_cuota_liquidacion = cuota.id_cuota_liquidacion
 WHERE rd.id_registro_cancelacion IS NULL AND
       ob.id_persona = p_id_persona
 GROUP BY ob.id_persona, doc.id_parcela,
          doc.tipo_doc_hab_especializado,
          rd.id_registro_deuda
 HAVING MAX(FECHA_VENCIMIENTO) < CURRENT_DATE
 ORDER BY rd.id_registro_deuda;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION p_estado_cuenta_persona(numeric)
  OWNER TO vipians;

insert into log_scripts_corridos values(117,117,now());

﻿-- Function: select corregir_liquidaciones_diario_manual()

-- DROP FUNCTION corregir_liquidaciones_diario_manual();

CREATE OR REPLACE FUNCTION corregir_liquidaciones_diario_manual()
  RETURNS void AS
$BODY$
BEGIN
/*
Esto no es un procedure como tal, pues contiene DDL's por lo que debe ejecutarse manualmente.
Usar este cuando el diario normal demora demasiado por la cantidad de registros a borrar.
*/

-- Vencimientos:
drop table if exists aux_vencimientos;
create temp table aux_vencimientos as (
select min(id_vencimiento) as id_vencimiento, fecha, nombre, valor from vencimiento
group by fecha,nombre,numero, valor);

update rela_venc_liq_t set id_vencimiento = aux.id_vencimiento
from vencimiento venc, aux_vencimientos aux
where rela_venc_liq_t.id_vencimiento = venc.id_vencimiento
and venc.nombre = aux.nombre and venc.fecha = aux.fecha and venc.valor = aux.valor
and venc.id_vencimiento <> aux.id_vencimiento;

--dropeamos la constraint fk de la tabla rela registro_deuda vencimientos.
alter table rela_venc_liq_t drop constraint if exists fk_rela_venc_liq_t_id_vencimiento;

alter table vencimiento drop constraint if exists pk_vencimiento;

CREATE TABLE vencimiento_2
(
  id_vencimiento clave NOT NULL,
  fecha date NOT NULL,
  valor importe NOT NULL,
  nombre nombre,
  numero integer,
  CONSTRAINT pk_vencimiento PRIMARY KEY (id_vencimiento)
);

insert into vencimiento_2 (select * from vencimiento where id_vencimiento in (select id_vencimiento from rela_venc_liq_t group by id_vencimiento));
drop table vencimiento;
alter table vencimiento_2 rename to vencimiento;

--Volvemos a crear la constraint fk de la tabla rela registro_deuda vencimientos.
alter table rela_venc_liq_t add CONSTRAINT fk_rela_venc_liq_t_id_vencimiento FOREIGN KEY (id_vencimiento)
      REFERENCES vencimiento (id_vencimiento) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

--Modificadores
drop table if exists aux_modificadores;

create temp table aux_modificadores as (
select min(id_modificador_liquidacion) as id_modificador_liquidacion, nombre, valor, id_tipo_modificador, tipo_modificador
from modificador_liquidacion where tipo_modificador = 'F'
group by id_tipo_modificador, nombre, valor, tipo_modificador);

update rela_modif_liq_liq_t set id_modificador_liquidacion = aux.id_modificador_liquidacion
from modificador_liquidacion mod, aux_modificadores aux
where rela_modif_liq_liq_t.id_modificador_liquidacion = mod.id_modificador_liquidacion
and mod.nombre = aux.nombre and mod.valor = aux.valor and mod.id_tipo_modificador = aux.id_tipo_modificador
and mod.id_modificador_liquidacion <> aux.id_modificador_liquidacion;

--Dropeamos constraint que referencian a la tabla modificador_liquidacion
alter table rela_modif_liq_liq_t drop CONSTRAINT fk_rela_modif;

--Dropeamos la constraint primaria de modificador_liquidacion
alter table modificador_liquidacion drop constraint pk_modificador_liquidacion;

--Creamos modificador_liquidacion_2
CREATE TABLE modificador_liquidacion_2
(
  id_modificador_liquidacion clave NOT NULL,
  nombre nombre,
  valor importe NOT NULL,
  sobre_saldo_neto boolean,
  id_tipo_modificador clave,
  tipo_modificador character(1) NOT NULL,
  enum_tipo_modificador estado,
  CONSTRAINT pk_modificador_liquidacion PRIMARY KEY (id_modificador_liquidacion ),
  CONSTRAINT fk_tipo_mod_mod_liq FOREIGN KEY (id_tipo_modificador)
      REFERENCES tipo_modificador (id_tipo_modificador) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE NO ACTION
);

--La llanamos.
insert into modificador_liquidacion_2 
(id_modificador_liquidacion, nombre, valor, sobre_saldo_neto, id_tipo_modificador, tipo_modificador, enum_tipo_modificador) 
(select id_modificador_liquidacion, nombre, valor, sobre_saldo_neto, id_tipo_modificador, tipo_modificador, enum_tipo_modificador from modificador_liquidacion 
where id_modificador_liquidacion 
in (select id_modificador_liquidacion from rela_modif_liq_liq_t group by id_modificador_liquidacion));

--Borramos modificador_liquidacion
drop table modificador_liquidacion; 

--Renombramos modificador_liquidacion_2
alter table modificador_liquidacion_2 rename to modificador_liquidacion;

--Volvemos a crear la constraint que referencia a modificador_liquidacion
alter table rela_modif_liq_liq_t add
CONSTRAINT fk_rela_modif FOREIGN KEY (id_modificador_liquidacion)
      REFERENCES modificador_liquidacion (id_modificador_liquidacion) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

-- Parametros Valuados.
drop table if exists aux_parametros_double;
create temp table aux_parametros_double as (
select min(id_parametro_valuado) as id_parametro_valuado, nombre_parametro, valor_parametro_double
from parametro_valuado
where tipo = 'DOUBLE'
group by nombre_parametro, valor_parametro_double);

update rela_par_val_liq_t set id_parametro_valuado = aux.id_parametro_valuado
from parametro_valuado par, aux_parametros_double aux
where rela_par_val_liq_t.id_parametro_valuado = par.id_parametro_valuado
and par.nombre_parametro = aux.nombre_parametro 
and par.valor_parametro_double = aux.valor_parametro_double
and par.id_parametro_valuado <> aux.id_parametro_valuado;

drop table if exists aux_parametros_string;
create temp table aux_parametros_string as (
select min(id_parametro_valuado) as id_parametro_valuado, nombre_parametro, valor_parametro_string
from parametro_valuado
where tipo = 'STRING'
group by nombre_parametro, valor_parametro_string);

update rela_par_val_liq_t set id_parametro_valuado = aux.id_parametro_valuado
from parametro_valuado par, aux_parametros_string aux
where rela_par_val_liq_t.id_parametro_valuado = par.id_parametro_valuado
and par.nombre_parametro = aux.nombre_parametro 
and par.valor_parametro_string = aux.valor_parametro_string
and par.id_parametro_valuado <> aux.id_parametro_valuado;

--Dropeamos constraint que referencian a la tabla parametro_valuado
alter table rela_par_val_liq_t drop
CONSTRAINT fk_rela_par_par_v;
--Dropeamos la constraint primaria de parametro_valuado
alter table parametro_valuado drop constraint pk_parametro_valuado;

--Creamos parametro_valuado_2
CREATE TABLE parametro_valuado_2
(
  id_parametro_valuado clave NOT NULL,
  valor_parametro_double importe,
  nombre_parametro nombre,
  valor_parametro_string character varying(200),
  tipo character varying(10),
  CONSTRAINT pk_parametro_valuado PRIMARY KEY (id_parametro_valuado )
);

--La llanamos.
insert into parametro_valuado_2 (select * from parametro_valuado where id_parametro_valuado 
in (select id_parametro_valuado from rela_par_val_liq_t group by id_parametro_valuado));

--Borramos parametro_valuado;
drop table parametro_valuado;

--Renombramos modificador_liquidacion_2
alter table parametro_valuado_2 rename to parametro_valuado;

--Volvemos a crear la constraint que referencia a modificador_liquidacion
alter table rela_par_val_liq_t add
CONSTRAINT fk_rela_par_par_v FOREIGN KEY (id_parametro_valuado)
      REFERENCES parametro_valuado (id_parametro_valuado) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE NO ACTION;

END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION corregir_liquidaciones_diario_manual()
  OWNER TO vipians;
  
  insert into log_scripts_corridos values(118,118,now());

drop function if exists p_estado_cuenta_parcela(numeric);

CREATE OR REPLACE FUNCTION p_estado_cuenta_parcela(IN p_id_parcela numeric, IN p_ids_excluidos numeric[] default ARRAY[-50])
  RETURNS TABLE(id_persona clave, id_parcela clave, tipo_obligacion character varying, id_registro_deuda clave) AS
$BODY$
DECLARE
tipo_ob Varchar(50);
cont Int4;
BEGIN
 cont := 0;
 return query SELECT pers.id_persona, par.id_parcela,
        case when doc.tipo_doc_hab_especializado = 'OYSP' then 'OSP'
       when doc.tipo_doc_hab_especializado = 'PLAN_FINANCIACION_OBRA' then 'PO'
       else doc.tipo_doc_hab_especializado end,
        rd.id_registro_deuda
 FROM doc_hab_especializado AS doc
 INNER JOIN obligacion AS ob ON ob.id_obligacion = doc.id_obligacion
 INNER JOIN parcela AS par ON par.id_parcela = doc.id_parcela
 INNER JOIN titulo_propiedad AS tp ON tp.id_titulo_propiedad = par.id_titulo_propiedad
 INNER JOIN registro_propietario AS rp ON rp.id_titulo_propiedad = tp.id_titulo_propiedad
 inner join persona AS pers on pers.id_persona = rp.id_persona
 INNER JOIN doc_generador_deuda AS doc_gen ON doc_gen.id_obligacion = ob.id_obligacion
 INNER JOIN registro_deuda AS rd ON rd.id_doc_generador_deuda = doc_gen.id_doc_generador_deuda
 INNER JOIN liquidacion_tasa as liq on liq.id_registro_deuda = rd.id_registro_deuda
 INNER JOIN cuota_liquidacion cuota on cuota.id_cuota_liquidacion = liq.id_cuota_liquidacion
 INNER JOIN RELA_CUOTA_FECHA_VENCIMIENTO rela on rela.id_cuota_liquidacion = cuota.id_cuota_liquidacion
 WHERE rd.id_registro_cancelacion IS NULL 
	AND PAR.id_parcela = p_id_parcela
       and not rd.id_registro_deuda = ANY (p_ids_excluidos)
 GROUP BY pers.id_persona, par.id_parcela,
          doc.tipo_doc_hab_especializado,
          rd.id_registro_deuda
 HAVING MIN(FECHA_VENCIMIENTO) < CURRENT_DATE
ORDER BY rd.id_registro_deuda;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION p_estado_cuenta_parcela(numeric, numeric[])
  OWNER TO vipians;
  
   DROP FUNCTION if exists p_estado_cuenta_persona(numeric);

CREATE OR REPLACE FUNCTION p_estado_cuenta_persona(IN p_id_persona numeric, IN p_ids_excluidos numeric[] default ARRAY[-50])
  RETURNS TABLE(id_persona clave, id_parcela clave, tipo_obligacion character varying, id_registro_deuda clave) AS
$BODY$
DECLARE
tipo_ob Varchar(50);
cont Int4;
BEGIN
 return query SELECT ob.id_persona, doc.id_parcela,
        case when doc.tipo_doc_hab_especializado = 'OYSP' then 'OSP'
       when doc.tipo_doc_hab_especializado = 'PLAN_FINANCIACION_OBRA' then 'PO'
       else doc.tipo_doc_hab_especializado end,
        rd.id_registro_deuda
 FROM doc_hab_especializado AS doc
 INNER JOIN obligacion AS ob ON ob.id_obligacion = doc.id_obligacion
 INNER JOIN doc_generador_deuda AS doc_gen ON doc_gen.id_obligacion = ob.id_obligacion
 INNER JOIN registro_deuda AS rd ON rd.id_doc_generador_deuda = doc_gen.id_doc_generador_deuda
 INNER JOIN liquidacion_tasa as liq on liq.id_registro_deuda = rd.id_registro_deuda
 INNER JOIN cuota_liquidacion cuota on cuota.id_cuota_liquidacion = liq.id_cuota_liquidacion
 INNER JOIN RELA_CUOTA_FECHA_VENCIMIENTO rela on rela.id_cuota_liquidacion = cuota.id_cuota_liquidacion
 WHERE rd.id_registro_cancelacion IS NULL AND
       ob.id_persona = p_id_persona
       and not rd.id_registro_deuda = ANY (p_ids_excluidos)
 GROUP BY ob.id_persona, doc.id_parcela,
          doc.tipo_doc_hab_especializado,
          rd.id_registro_deuda
 HAVING MIN(FECHA_VENCIMIENTO) < CURRENT_DATE
 ORDER BY rd.id_registro_deuda;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION p_estado_cuenta_persona(numeric, numeric[])
  OWNER TO vipians;
  
insert into log_scripts_corridos values(119,119,now());
  
alter table ingreso_vario rename column id_ingreso_vario to id_registro_deuda;
alter table ingreso_vario drop id_registro_cancelacion;
alter table ingreso_vario drop id_persona;

  insert into log_scripts_corridos values(120,120,now());
  
  -- ACTUALIZA LOSREGISTROS DE TITULO DE PROPIEDAD A LOS NNUEVOS VALORES INDICADOS POR CLAUDIO RIOS
update titulo_propiedad  
set
	tipo_transaccion_catastral  = 'PROPIEDAD_HORIZONTAL'
Where
	tipo_transaccion_catastral  = 'SEGREGACION';
	
 insert into log_scripts_corridos values(121,121,now());
 
 alter table cuota_refinanciac add column recargo importe;
 
 insert into log_scripts_corridos values(122,122,now());
 
 alter table plantilla_plan_de_pago add tipo_calculo_interes varchar(20) not null default 'DIRECTO';
 
 insert into log_scripts_corridos values(123,123,now());
 
 create table tasa_nominal_anual(
id_tasa_nominal_anual clave not null primary key,
cuotas_hasta integer not null,
interes numeric(10,2) not null,
id_plantilla_plan_de_pago clave not null,
constraint fk_tan_id_plantilla foreign key (id_plantilla_plan_de_pago) 
references plantilla_plan_de_pago (id_plantilla_plan_de_pago) on delete restrict on update cascade
);

alter table tasa_nominal_anual owner to vipians;

create sequence gen_id_tasa_nominal_anual;

alter sequence gen_id_tasa_nominal_anual owner to vipians;

 insert into log_scripts_corridos values(124,124,now());

CREATE TABLE reporte
(
  id_reporte clave NOT NULL,
  id_recurso recurso,
  seleccionaEntidad character varying(10),
  nombre character varying(50),
  nombre_archivo_jasper character varying(50),
  estado estado,
  tipo character varying(20),
  CONSTRAINT reporte_pkey PRIMARY KEY (id_reporte)
);
ALTER TABLE reporte OWNER TO vipians;

CREATE TABLE parametro_reporte
(
  id_parametro_reporte clave NOT NULL,
  nombre character varying(50),
  id_reporte clave,
  requerido boolean DEFAULT false,
  tipo character varying(30),
  id_recurso recurso,
  CONSTRAINT pk_parametro_reporte PRIMARY KEY (id_parametro_reporte),
  CONSTRAINT fk_parametro_reporte_reporte FOREIGN KEY (id_reporte)
      REFERENCES reporte (id_reporte) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE parametro_reporte OWNER TO vipians;

CREATE TABLE rela_reporte_usuario
(
  id_reporte clave NOT NULL,
  id_usuario clave NOT NULL,
  CONSTRAINT fk_reporte_rela_reporte_usuario FOREIGN KEY (id_reporte)
      REFERENCES reporte (id_reporte) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_usuario_rela_reporte_usuario FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rela_reporte_usuario OWNER TO vipians;

CREATE TABLE opcion_parametro_reporte
(
  id_opcion_rp clave NOT NULL,
  nombre nombre NOT NULL,
  id_parametro_reporte clave NOT NULL,
  CONSTRAINT opcion_parametro_reporte_pkey PRIMARY KEY (id_opcion_rp),
  CONSTRAINT fk_opcion_param_reporte FOREIGN KEY (id_parametro_reporte)
      REFERENCES parametro_reporte (id_parametro_reporte) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
);
ALTER TABLE opcion_parametro_reporte OWNER TO vipians;

CREATE SEQUENCE gen_id_reporte;
ALTER TABLE gen_id_reporte OWNER TO vipians;

CREATE SEQUENCE gen_id_parametro_reporte;
ALTER TABLE gen_id_parametro_reporte OWNER TO vipians;

CREATE SEQUENCE gen_id_opcion_rp;
ALTER TABLE gen_id_opcion_rp OWNER TO vipians;

alter table parametro_reporte add column nombre_atributo char varying(50);
alter table parametro_reporte add column orden clave;

insert into log_scripts_corridos values(125,125,now());

alter table exp_documentocatalogo add column id_reporte clave;
alter table exp_documentocatalogo add constraint fk_exp_doccat_id_reporte FOREIGN KEY(id_reporte) REFERENCES reporte (id_reporte) on delete restrict on update cascade;
alter table exp_documentocatalogo add column tipo character varying(255);
update exp_documentocatalogo set tipo = 'ENTRADA';

alter table exp_documento add column documento_adjunto bytea;

CREATE TABLE exp_parametro_valuado_reporte
(
  id_parametro_valuado_rp clave NOT NULL,
  id_documento clave NOT NULL,
  nombre nombre NOT NULL,
  valor_cadena character varying(200),
  valor_entero clave,
  valor_decimal numeric(15,2),
  valor_fecha date,
  valor_booleano boolean,
  CONSTRAINT exp_parametro_valuado_reporte_pkey PRIMARY KEY (id_parametro_valuado_rp),
  CONSTRAINT fk_exp_param_rp_documento FOREIGN KEY (id_documento)
      REFERENCES exp_documento (id_documento) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
);
ALTER TABLE exp_parametro_valuado_reporte OWNER TO vipians;

CREATE SEQUENCE gen_id_parametro_valuado_rp;
ALTER TABLE gen_id_parametro_valuado_rp OWNER TO vipians;

create table numerador (
id_numerador clave not null primary key,
nombre char varying not null,
contador clave not null,
resetea_con_anio boolean DEFAULT true,
estado estado
);
alter table numerador owner to vipians;

create sequence gen_id_numerador;
alter sequence gen_id_numerador owner to vipians;

create table linea_numerador (
id_linea_numerador clave not null primary key,
id_numerador clave not null,
anio clave not null,
contador clave not null,
CONSTRAINT fk_linea_numerador_numerador FOREIGN KEY (id_numerador)
      REFERENCES numerador (id_numerador) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
);
alter table linea_numerador owner to vipians;

create sequence gen_id_linea_numerador;
alter sequence gen_id_linea_numerador owner to vipians;

alter table exp_procedimiento add column id_numerador clave;
alter table exp_procedimiento add constraint fk_exp_procedimiento_numerador foreign key (id_numerador) references numerador(id_numerador) on delete restrict on update cascade;

alter table exp_nodoprocedimiento  add column estado varchar(25);
update exp_nodoprocedimiento  set estado = 'ACTIVO';

alter table exp_documento drop column documento_adjunto;
alter table exp_parametro_valuado_reporte drop column id_documento;

CREATE TABLE exp_version_ejecucion_reporte
(
  id_version_ejecucion_rp clave NOT NULL,
  id_documento clave NOT NULL,
  activo boolean,
  fecha_ejecucion timestamp without time zone,
  documento_adjunto bytea,
  CONSTRAINT exp_version_ejecucion_reporte_pkey PRIMARY KEY (id_version_ejecucion_rp),
  CONSTRAINT fk_exp_ver_ejec_rp_documento FOREIGN KEY (id_documento)
      REFERENCES exp_documento (id_documento) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
);
ALTER TABLE exp_version_ejecucion_reporte OWNER TO vipians;

CREATE SEQUENCE gen_id_version_ejecucion_rp;
ALTER TABLE gen_id_version_ejecucion_rp OWNER TO vipians;

delete from exp_documento;
delete from exp_parametro_valuado_reporte;

alter table exp_parametro_valuado_reporte add column id_version_ejecucion_rp clave not null;
alter table exp_parametro_valuado_reporte add constraint fk_exp_param_val_rp_version_reporte foreign key (id_version_ejecucion_rp) references exp_version_ejecucion_reporte(id_version_ejecucion_rp) on delete restrict on update cascade;

alter table exp_parametro_valuado_reporte add column valor_seleccion bytea;

alter table tasa_nominal_anual add interes_condonado porcentaje;

alter table plantilla_plan_de_pago add cantidad_propiedades_maxima integer;

create table RELA_REG_CANCE_REGISTRO_CONDONADO (
	ID_REGISTRO_CANCELACION clave not null,
	ID_REGISTRO_DEUDA clave not null
); 

alter table RELA_REG_CANCE_REGISTRO_CONDONADO owner to vipians;

alter table plantilla_plan_de_pago add condona_deuda_antigua boolean not null default false;

alter table plantilla_plan_de_pago add anios_aplicacion varchar(200);

alter table plantilla_plan_de_pago add fecha_vencimiento_primer_cuota date;

insert into log_scripts_corridos values(126,126,now());

create table condicion_condonacion_periodo (
id_condicion_condonacion_periodo clave not null primary key,
id_plantilla clave not null,
anio integer,
porcentaje porcentaje
);

alter table condicion_condonacion_periodo owner to vipians;

create sequence gen_id_condicion_condonacion_periodo;

alter sequence gen_id_condicion_condonacion_periodo owner to vipians;

alter table condicion_condonacion_periodo add condicion varchar(30);

create table condonacion_registro_deuda(
id_condonacion_registro_deuda clave not null primary key,
ID_REG_CANCELACION_POR_REF clave not null,
id_registro_deuda clave not null,
porcentaje porcentaje);

alter table condonacion_registro_deuda owner to vipians;

create sequence gen_id_condonacion_registro_deuda;

alter sequence gen_id_condonacion_registro_deuda owner to vipians;

insert into log_scripts_corridos values(127,127,now());

alter table tasa_nominal_anual alter column interes type numeric(10,8);
alter table cuota_refinanciac add fecha_vencimiento_original date;
update cuota_refinanciac set fecha_vencimiento_original = fecha_vencimiento;

insert into log_scripts_corridos values(128,128,now());

alter table ingreso_vario add fecha_vencimiento date;
alter table ingreso_vario add fecha_vencimiento_original date;
alter table concepto_ingreso_vario add id_tipo_tasa clave;

update ingreso_vario set fecha_vencimiento = fecha_emision;
update ingreso_vario set fecha_vencimiento_original = fecha_emision;

alter table ingreso_vario add interes importe default 0;

insert into log_scripts_corridos values(129,129,now());

-- Function: eliminar_liquidaciones(text)

-- DROP FUNCTION eliminar_liquidaciones(text);

CREATE OR REPLACE FUNCTION eliminar_liquidaciones(nombre_tabla_temp text)
  RETURNS text AS
$BODY$
declare
sql text;
BEGIN

sql = 'delete from rela_modif_liq_liq_t rela using '||nombre_tabla_temp||' deu
	where rela.id_registro_deuda = deu.id_registro_deuda;';
RAISE NOTICE '%', sql;
execute sql;

sql = 'delete from rela_par_val_liq_t rela using '||nombre_tabla_temp||' deu
	where rela.id_registro_deuda = deu.id_registro_deuda;';
RAISE NOTICE '%', sql;
execute sql;

sql = 'delete from rela_venc_liq_t rela using '||nombre_tabla_temp||' deu
	where rela.id_registro_deuda = deu.id_registro_deuda;';
RAISE NOTICE '%', sql;
execute sql;

sql = 'delete from liquidacion_tasa rela using '||nombre_tabla_temp||' deu
	where rela.id_registro_deuda = deu.id_registro_deuda;';
RAISE NOTICE '%', sql;
execute sql;

sql = 'delete from rela_liq_t_alic_liq rela using '||nombre_tabla_temp||' deu
	where rela.id_registro_deuda = deu.id_registro_deuda;';
RAISE NOTICE '%', sql;
execute sql;

sql = 'delete from registro_deuda rela using '||nombre_tabla_temp||' deu
	where rela.id_registro_deuda = deu.id_registro_deuda;';
RAISE NOTICE '%', sql;
execute sql;

sql = 'delete from rela_liq_t_alic_liq rela using '||nombre_tabla_temp||' deu
	where rela.id_registro_deuda = deu.id_registro_deuda;';
RAISE NOTICE '%', sql;
execute sql;

return 'Exito';
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION eliminar_liquidaciones(text)
  OWNER TO vipians;
COMMENT ON FUNCTION eliminar_liquidaciones(text) IS 'Elimina las liquidaciones y todo lo relacionado a ellas, joineando con el nombre de la tabla 
pasada por parametro. Dicha tabla debe tener la columna ID_REGISTRO_DEUDA';

alter table difunto alter column id_persona drop not null;

alter table difunto add difunto varchar(255);

alter table ingreso_vario alter column observaciones type varchar(500);