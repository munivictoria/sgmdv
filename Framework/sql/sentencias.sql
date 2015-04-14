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

	update registro_deuda rd set estado = 'PRESCRITA', id_registro_cancelacion = v_id_registro_cancelacion
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

insert into log_scripts_corridos values(117,117,now());