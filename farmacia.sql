/*==============================================================*/
/* DBMS name:      SAP SQL Anywhere 16                          */
/* Created on:     30/09/2019 16:51:03                          */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_DETALLED_TIENE_VENTA') then
    alter table DETALLEDEVENTA
       delete foreign key FK_DETALLED_TIENE_VENTA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HISTORIA_CONSULTAR_VENTA') then
    alter table HISTORIAL
       delete foreign key FK_HISTORIA_CONSULTAR_VENTA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HISTORIA_GUARDA_CLIENTE') then
    alter table HISTORIAL
       delete foreign key FK_HISTORIA_GUARDA_CLIENTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HISTORIA_RELATIONS_CAJERO') then
    alter table HISTORIAL
       delete foreign key FK_HISTORIA_RELATIONS_CAJERO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HISTORIA_RESGUARDA_PRODUCTO') then
    alter table HISTORIAL
       delete foreign key FK_HISTORIA_RESGUARDA_PRODUCTO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_INVENTAR_HAY_PRODUCTO') then
    alter table INVENTARIO
       delete foreign key FK_INVENTAR_HAY_PRODUCTO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PRODUCTO_CONSTA_DETALLED') then
    alter table PRODUCTO
       delete foreign key FK_PRODUCTO_CONSTA_DETALLED
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_VENTA_COMPRA_CLIENTE') then
    alter table VENTA
       delete foreign key FK_VENTA_COMPRA_CLIENTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_VENTA_REALIZA_CAJERO') then
    alter table VENTA
       delete foreign key FK_VENTA_REALIZA_CAJERO
end if;

drop index if exists CAJERO.CAJERO_PK;

drop table if exists CAJERO;

drop index if exists CLIENTE.CLIENTE_PK;

drop table if exists CLIENTE;

drop index if exists DETALLEDEVENTA.TIENE_FK;

drop index if exists DETALLEDEVENTA.DETALLEDEVENTA_PK;

drop table if exists DETALLEDEVENTA;

drop index if exists HISTORIAL.GUARDA_FK;

drop index if exists HISTORIAL.RELATIONSHIP_8_FK;

drop index if exists HISTORIAL.CONSULTAR_FK;

drop index if exists HISTORIAL.RESGUARDA_FK;

drop index if exists HISTORIAL.HISTORIAL_PK;

drop table if exists HISTORIAL;

drop index if exists INVENTARIO.HAY_FK;

drop table if exists INVENTARIO;

drop index if exists PRODUCTO.CONSTA_FK;

drop index if exists PRODUCTO.PRODUCTO_PK;

drop table if exists PRODUCTO;

drop index if exists VENTA.COMPRA_FK;

drop index if exists VENTA.REALIZA_FK;

drop index if exists VENTA.VENTA_PK;

drop table if exists VENTA;

/*==============================================================*/
/* Table: CAJERO                                                */
/*==============================================================*/
create table CAJERO 
(
   CODCAJERO            varchar(10)                    not null,
   NOMCAJERO            varchar(20)                    null,
   SUELDOCAJERO         varchar(10)                    null,
   DIRCAJERO            varchar(20)                    null,
   TELFCAJERO           integer                        null,
   constraint PK_CAJERO primary key clustered (CODCAJERO)
);

/*==============================================================*/
/* Index: CAJERO_PK                                             */
/*==============================================================*/
create unique clustered index CAJERO_PK on CAJERO (
CODCAJERO ASC
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE 
(
   CODCLIENTE           varchar(10)                    not null,
   NOMCLINTE            varchar(20)                    null,
   NITCLIENTE           integer                        null,
   constraint PK_CLIENTE primary key clustered (CODCLIENTE)
);

/*==============================================================*/
/* Index: CLIENTE_PK                                            */
/*==============================================================*/
create unique clustered index CLIENTE_PK on CLIENTE (
CODCLIENTE ASC
);

/*==============================================================*/
/* Table: DETALLEDEVENTA                                        */
/*==============================================================*/
create table DETALLEDEVENTA 
(
   CODDETALLEDEVENA     integer                        not null,
   CODVENTA             varchar(10)                    null,
   CANTIDADVENDIDA      integer                        null,
   SUBTOTAL             decimal                        null,
   constraint PK_DETALLEDEVENTA primary key clustered (CODDETALLEDEVENA)
);

/*==============================================================*/
/* Index: DETALLEDEVENTA_PK                                     */
/*==============================================================*/
create unique clustered index DETALLEDEVENTA_PK on DETALLEDEVENTA (
CODDETALLEDEVENA ASC
);

/*==============================================================*/
/* Index: TIENE_FK                                              */
/*==============================================================*/
create index TIENE_FK on DETALLEDEVENTA (
CODVENTA ASC
);

/*==============================================================*/
/* Table: HISTORIAL                                             */
/*==============================================================*/
create table HISTORIAL 
(
   CODHISTORIAL         integer                        not null,
   CODPRODUCTO          varchar(10)                    null,
   CODVENTA             varchar(10)                    null,
   CODCAJERO            varchar(10)                    null,
   CODCLIENTE           varchar(10)                    null,
   DESCRIPCION          varchar(20)                    null,
   FECHA                timestamp                      null,
   constraint PK_HISTORIAL primary key clustered (CODHISTORIAL)
);

/*==============================================================*/
/* Index: HISTORIAL_PK                                          */
/*==============================================================*/
create unique clustered index HISTORIAL_PK on HISTORIAL (
CODHISTORIAL ASC
);

/*==============================================================*/
/* Index: RESGUARDA_FK                                          */
/*==============================================================*/
create index RESGUARDA_FK on HISTORIAL (
CODPRODUCTO ASC
);

/*==============================================================*/
/* Index: CONSULTAR_FK                                          */
/*==============================================================*/
create index CONSULTAR_FK on HISTORIAL (
CODVENTA ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_8_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_8_FK on HISTORIAL (
CODCAJERO ASC
);

/*==============================================================*/
/* Index: GUARDA_FK                                             */
/*==============================================================*/
create index GUARDA_FK on HISTORIAL (
CODCLIENTE ASC
);

/*==============================================================*/
/* Table: INVENTARIO                                            */
/*==============================================================*/
create table INVENTARIO 
(
   CODPRODUCTO          varchar(10)                    null,
   EXISTENCIA           integer                        null
);

/*==============================================================*/
/* Index: HAY_FK                                                */
/*==============================================================*/
create index HAY_FK on INVENTARIO (
CODPRODUCTO ASC
);

/*==============================================================*/
/* Table: PRODUCTO                                              */
/*==============================================================*/
create table PRODUCTO 
(
   CODPRODUCTO          varchar(10)                    not null,
   CODDETALLEDEVENA     integer                        null,
   NOMPRODUCTO          varchar(20)                    null,
   PRECIOPRODUCTO       decimal                        null,
   PRECIOVENTA          decimal                        null,
   constraint PK_PRODUCTO primary key clustered (CODPRODUCTO)
);

/*==============================================================*/
/* Index: PRODUCTO_PK                                           */
/*==============================================================*/
create unique clustered index PRODUCTO_PK on PRODUCTO (
CODPRODUCTO ASC
);

/*==============================================================*/
/* Index: CONSTA_FK                                             */
/*==============================================================*/
create index CONSTA_FK on PRODUCTO (
CODDETALLEDEVENA ASC
);

/*==============================================================*/
/* Table: VENTA                                                 */
/*==============================================================*/
create table VENTA 
(
   CODVENTA             varchar(10)                    not null,
   CODCAJERO            varchar(10)                    null,
   CODCLIENTE           varchar(10)                    null,
   TOTAL                decimal                        null,
   constraint PK_VENTA primary key clustered (CODVENTA)
);

/*==============================================================*/
/* Index: VENTA_PK                                              */
/*==============================================================*/
create unique clustered index VENTA_PK on VENTA (
CODVENTA ASC
);

/*==============================================================*/
/* Index: REALIZA_FK                                            */
/*==============================================================*/
create index REALIZA_FK on VENTA (
CODCAJERO ASC
);

/*==============================================================*/
/* Index: COMPRA_FK                                             */
/*==============================================================*/
create index COMPRA_FK on VENTA (
CODCLIENTE ASC
);

alter table DETALLEDEVENTA
   add constraint FK_DETALLED_TIENE_VENTA foreign key (CODVENTA)
      references VENTA (CODVENTA)
      on update restrict
      on delete restrict;

alter table HISTORIAL
   add constraint FK_HISTORIA_CONSULTAR_VENTA foreign key (CODVENTA)
      references VENTA (CODVENTA)
      on update restrict
      on delete restrict;

alter table HISTORIAL
   add constraint FK_HISTORIA_GUARDA_CLIENTE foreign key (CODCLIENTE)
      references CLIENTE (CODCLIENTE)
      on update restrict
      on delete restrict;

alter table HISTORIAL
   add constraint FK_HISTORIA_RELATIONS_CAJERO foreign key (CODCAJERO)
      references CAJERO (CODCAJERO)
      on update restrict
      on delete restrict;

alter table HISTORIAL
   add constraint FK_HISTORIA_RESGUARDA_PRODUCTO foreign key (CODPRODUCTO)
      references PRODUCTO (CODPRODUCTO)
      on update restrict
      on delete restrict;

alter table INVENTARIO
   add constraint FK_INVENTAR_HAY_PRODUCTO foreign key (CODPRODUCTO)
      references PRODUCTO (CODPRODUCTO)
      on update restrict
      on delete restrict;

alter table PRODUCTO
   add constraint FK_PRODUCTO_CONSTA_DETALLED foreign key (CODDETALLEDEVENA)
      references DETALLEDEVENTA (CODDETALLEDEVENA)
      on update restrict
      on delete restrict;

alter table VENTA
   add constraint FK_VENTA_COMPRA_CLIENTE foreign key (CODCLIENTE)
      references CLIENTE (CODCLIENTE)
      on update restrict
      on delete restrict;

alter table VENTA
   add constraint FK_VENTA_REALIZA_CAJERO foreign key (CODCAJERO)
      references CAJERO (CODCAJERO)
      on update restrict
      on delete restrict;

