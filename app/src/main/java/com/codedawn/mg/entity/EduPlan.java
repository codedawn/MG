package com.codedawn.mg.entity;

/**
 * @author codedawn
 * @date 2021-01-17 10:45
 */
public class EduPlan {

    private String planName; //计划名字
    private String period; //课时
    private String semester;//学期
    private String year;//学年
    private String holiday;//假期
    private String beginTime;//开始时间
    private String endTime;//结束时间
    private String teacherName;//老师名字
    private String teacherIcon;//老师头像

    public String getTeacherName() {
        return teacherName;
    }

    public EduPlan setTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public String getTeacherIcon() {
        return teacherIcon;
    }

    public EduPlan setTeacherIcon(String teacherIcon) {
        this.teacherIcon = teacherIcon;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public EduPlan setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public EduPlan setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public EduPlan() {
    }

    public String getPlanName() {
        return planName;
    }

    public EduPlan setPlanName(String planName) {
        this.planName = planName;
        return this;
    }

    public String getPeriod() {
        return period;
    }

    public EduPlan setPeriod(String period) {
        this.period = period;
        return this;
    }

    public String getSemester() {
        return semester;
    }

    public EduPlan setSemester(String semester) {
        this.semester = semester;
        return this;
    }

    public String getYear() {
        return year;
    }

    public EduPlan setYear(String year) {
        this.year = year;
        return this;
    }

    public String getHoliday() {
        return holiday;
    }

    public EduPlan setHoliday(String holiday) {
        this.holiday = holiday;
        return this;
    }
}
