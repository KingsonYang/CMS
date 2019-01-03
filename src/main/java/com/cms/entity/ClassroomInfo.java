package com.cms.entity;

import java.io.Serializable;

public class ClassroomInfo implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column classroom_info.ID
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column classroom_info.Floor_No
     *
     * @mbg.generated
     */
    private Integer floorNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column classroom_info.Room_No
     *
     * @mbg.generated
     */
    private Integer roomNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table classroom_info
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column classroom_info.ID
     *
     * @return the value of classroom_info.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column classroom_info.ID
     *
     * @param id the value for classroom_info.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column classroom_info.Floor_No
     *
     * @return the value of classroom_info.Floor_No
     *
     * @mbg.generated
     */
    public Integer getFloorNo() {
        return floorNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column classroom_info.Floor_No
     *
     * @param floorNo the value for classroom_info.Floor_No
     *
     * @mbg.generated
     */
    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column classroom_info.Room_No
     *
     * @return the value of classroom_info.Room_No
     *
     * @mbg.generated
     */
    public Integer getRoomNo() {
        return roomNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column classroom_info.Room_No
     *
     * @param roomNo the value for classroom_info.Room_No
     *
     * @mbg.generated
     */
    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classroom_info
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", floorNo=").append(floorNo);
        sb.append(", roomNo=").append(roomNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}