package com.mohammad.demo.model.todos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties("_objectID")
public class Todo {
    @JsonProperty("id")
    private int id;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("status")
    private boolean status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", desc:'" + desc + '\'' +
                ", status:" + status +
                '}';
    }
}
