create table task_tag
(
    task_id uuid not null,
    tag_id  uuid not null,
    primary key (task_id, tag_id),
    constraint fk_task_tag_task foreign key (task_id) references task (task_id),
    constraint fk_task_tag_tag foreign key (tag_id) references tag (tag_id)
);