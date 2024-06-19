create table task_assignees
(
    task_id uuid not null,
    user_id uuid not null,
    primary key (task_id, user_id),
    constraint fk_task_assignees_task foreign key (task_id) references task (task_id),
    constraint fk_task_assignees_users foreign key (user_id) references users (user_id)
);