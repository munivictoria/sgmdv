alter table detalle_ticket_caja add id_registro_deuda clave;

--Ponerle un numero hasta aca.

alter table detalle_ticket_caja add registro_deuda_reatachado boolean not null default false;