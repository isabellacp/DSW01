connect 'jdbc:derby://localhost:1527/mydb;create=true';

create table USUARIO
(
    ID    INTEGER not null generated always as identity
        constraint USUARIO_PK
        primary key,
    EMAIL VARCHAR(30) not null
        constraint USUARIO_EMAIL_UINDEX
            unique,
    SENHA VARCHAR(30) not null,
    NOME  VARCHAR(30),
    ROLE  VARCHAR(30)
);

create table AGENCIA
(
    ID        INTEGER not null generated always as identity
        constraint TABLE_NAME_PK
            primary key
        constraint TABLE_NAME_USUARIO_ID_FK
            references USUARIO
            on delete cascade,
    DESCRICAO VARCHAR(100),
    CNPJ      VARCHAR(30)
);

create table ARQUIVO
(
    PACOTE_ID INTEGER,
    PATH      VARCHAR(300),
    NAME      VARCHAR(100),
    TIPO      VARCHAR(25),
    ID        INTEGER not null generated always as identity
);

alter table ARQUIVO
    add constraint ARQUIVO_PK
        primary key (ID);



create table CLIENTE
(
ID           INTEGER not null generated always as identity
        constraint CLIENTE_USUARIO_ID_FK
            references USUARIO
            on delete cascade,
    TELEFONE   VARCHAR(30),
    SEXO       VARCHAR(10),
    CPF        VARCHAR(30),
    NASCIMENTO DATE
);

alter table CLIENTE
    add constraint CLIENTE_PK
        primary key (ID);


create table PACOTE
(
    DESTINO    VARCHAR(30),
    PARTIDA    DATE,
    DURACAO    INTEGER,
    VALOR      REAL,
    ID         INTEGER not null generated always as identity
        constraint PACOTE_PK
        primary key,
    AGENCIA_ID INTEGER
        constraint PACOTE_AGENCIA_ID_FK
            references AGENCIA
            on delete cascade
);

create table COMPRA
(
    PACOTE_ID  INTEGER
        constraint COMPRA_PACOTE_ID_FK
            references PACOTE
            on delete cascade,
    DATA       DATE,
    LINK       VARCHAR(20) not null,
    ID         INTEGER not null generated always as identity
        constraint COMPRA_PK
        primary key,
    CLIENTE_ID INTEGER
        constraint COMPRA_CLIENTE_ID_FK
            references CLIENTE
            on delete cascade
);



disconnect;

quit;