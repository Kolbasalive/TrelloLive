create table task
(
    task_id         uuid primary key,
    title           varchar not null,
    description     text,
    creation_date   timestamp not null,
    status          varchar not null,
    priority        varchar,
    deadline        timestamp,
    board_id        uuid not null,
    sprint          varchar,
    tl_id           varchar,
    constraint fk_task_board foreign key (board_id) references board (board_id)
);