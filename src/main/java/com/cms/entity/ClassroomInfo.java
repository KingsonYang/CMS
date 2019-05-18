package com.cms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table(name = "classroom_info")
public class ClassroomInfo implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = CourseInfo.UserUpdateChecks.class)
    private Integer id;

    private Integer floorNo;
    private Integer roomNo;
    private Integer seat;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public ClassroomInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public ClassroomInfo setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
        return this;

    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public ClassroomInfo setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
        return this;

    }

    public Integer getSeat() {
        return seat;
    }

    public ClassroomInfo setSeat(Integer seat) {
        this.seat = seat;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", floorNo=").append(floorNo);
        sb.append(", roomNo=").append(roomNo);
        sb.append(", seat=").append(seat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}