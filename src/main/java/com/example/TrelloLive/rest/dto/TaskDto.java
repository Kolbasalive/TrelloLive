package com.example.TrelloLive.rest.dto;

import com.example.TrelloLive.dao.model.Status;

import java.util.Date;

public class TaskDto {

    private String title;

    private String description;

    private Date creationDate;

    private Status status;

    private String priority;

    private Date deadline;

    private TagDto tagDto;
}
