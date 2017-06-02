package com.icloud.model.user;

/**
 * 用户信息扩展,认证后的
 * @author leiyi
 *
 */
public class UserExt {
	
	private String id;
	private String photo;//头像
	private String realName;
	private String wordNo;
	private String departments;//部门 院校
	private String nativePlace;//籍贯
	private String gender;//性别
	private String professional;//专业，职称
	private String grade;//学历，职称
	private String phone;
	private String email;
	private String roleId;
	private String userId;
	private String isCertification;
	private String hobby;//爱好
	private String declaration ;//宣言
	private UserRole userRole;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getWordNo() {
		return wordNo;
	}
	public void setWordNo(String wordNo) {
		this.wordNo = wordNo;
	}
	public String getDepartments() {
		return departments;
	}
	public void setDepartments(String departments) {
		this.departments = departments;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getIsCertification() {
		return isCertification;
	}
	public void setIsCertification(String isCertification) {
		this.isCertification = isCertification;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	

}
