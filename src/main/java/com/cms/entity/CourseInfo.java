package com.cms.entity;

import java.io.Serializable;

public class CourseInfo implements Serializable {

    private Integer id;
    private String name;
    private String dec;
    private Double credit;
    private Integer character;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec == null ? null : dec.trim();
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Integer getCharacter() {
        return character;
    }

    public void setCharacter(Integer character) {
        this.character = character;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", dec=").append(dec);
        sb.append(", credit=").append(credit);
        sb.append(", character=").append(character);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}