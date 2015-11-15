package com.hjh.she.model.oa;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hjh.she.model.base.AuditEntityBean;

/**
 * 用户
 * 
 * @author haojiahong
 * 
 * @createtime：2015-11-5 上午9:54:56
 * 
 * 
 */
@Entity
@Table(name = "oa_user")
public class User extends AuditEntityBean {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "ORGANIZE_ID")
	private String organizeId;

	@Column(name = "DUTY_ID")
	private String dutyId;

	@Column(name = "TITLE_ID")
	private String titleId;

	@Column(name = "MYID")
	private String myid;

	@Column(name = "ACCOUNT")
	private String account;

	@Column(name = "LOGIN_COUNT")
	private Long loginCount;

	@Column(name = "NAME")
	private String name;

	@Column(name = "TEL")
	private String tel;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "LANG")
	private String lang;

	@Column(name = "THEME")
	private String theme;

	@Column(name = "IS_EMPLOYEE")
	private String isEmployee;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "IP")
	private String ip;

	@Column(name = "QUESTION_ID")
	private String questionId;

	@Column(name = "ANSWER")
	private String answer;

	@Column(name = "IS_ONLINE")
	private Long isOnline;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "FIRST_VISIT")
	private Timestamp firstVisit;

	@Column(name = "PREVIOUS_VISIT")
	private Timestamp previousVisit;

	@Column(name = "LAST_VISIT")
	private Timestamp lastVisit;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrganizeId() {
		return organizeId;
	}

	public void setOrganizeId(String organizeId) {
		this.organizeId = organizeId;
	}

	public String getDutyId() {
		return dutyId;
	}

	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getMyid() {
		return myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getIsEmployee() {
		return isEmployee;
	}

	public void setIsEmployee(String isEmployee) {
		this.isEmployee = isEmployee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getFirstVisit() {
		return firstVisit;
	}

	public void setFirstVisit(Timestamp firstVisit) {
		this.firstVisit = firstVisit;
	}

	public Timestamp getPreviousVisit() {
		return previousVisit;
	}

	public void setPreviousVisit(Timestamp previousVisit) {
		this.previousVisit = previousVisit;
	}

	public Timestamp getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Timestamp lastVisit) {
		this.lastVisit = lastVisit;
	}

	public Long getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}

	public Long getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Long isOnline) {
		this.isOnline = isOnline;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
