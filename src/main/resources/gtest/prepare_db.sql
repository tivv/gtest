SET DATABASE TRANSACTION CONTROL MVCC;

create table player(
    username varchar(255) not null primary key,
    balance_version bigint default 0 not null,
    balance numeric not null
);

