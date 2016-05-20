package com.crossarx.rest.api.hiv.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {
	
	private Integer id;
	private String name;
	private String objective;
	private String status;
	private List<String> available_languages;
	private Integer bezocht;
	private String css_class;
	private String description;
	private String introduction;
	private Boolean download_completed;
	private Boolean webdisplay;
	private Boolean free;
	
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("objective")
	public String getObjective() {
		return objective;
	}
	
	public void setObjective(String objective) {
		this.objective = objective;
	}
	
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonProperty("available_languages")
	public List<String> getAvailable_languages() {
		return available_languages;
	}
	
	public void setAvailable_languages(List<String> available_languages) {
		this.available_languages = available_languages;
	}
	
	@JsonProperty("bezocht")
	public Integer getBezocht() {
		return bezocht;
	}
	
	public void setBezocht(Integer bezocht) {
		this.bezocht = bezocht;
	}
	
	@JsonProperty("css_class")
	public String getCss_class() {
		return css_class;
	}
	
	public void setCss_class(String css_class) {
		this.css_class = css_class;
	}
	
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty("introduction")
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@JsonProperty("download_completed")
	public Boolean getDownload_completed() {
		return download_completed;
	}
	
	public void setDownload_completed(Boolean download_completed) {
		this.download_completed = download_completed;
	}
	
	@JsonProperty("webdisplay")
	public Boolean getWebdisplay() {
		return webdisplay;
	}
	
	public void setWebdisplay(Boolean webdisplay) {
		this.webdisplay = webdisplay;
	}
	
	@JsonProperty("free")
	public Boolean getFree() {
		return free;
	}
	
	public void setFree(Boolean free) {
		this.free = free;
	}
	
	@Override
	public String toString(){
		return "id: " + this.id + "\n"
				+ "name: " + this.name + "\n"
				+ "objective: " + this.objective + "\n"
				+ "status: " + this.status + "\n"
				+ "available_languages: " + getAvailableLanguagesFormated() + "\n"
				+ "bezocht: " + this.bezocht + "\n"
				+ "css_class: " + this.css_class + "\n"
				+ "description: " + this.description + "\n"
				+ "introduction: " + this.introduction + "\n"
				+ "download_completed: " + this.download_completed + "\n"
				+ "webdisplay: " + this.webdisplay + "\n"
				+ "free: " + this.free + "\n";
	}
	
	private String getAvailableLanguagesFormated(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i< available_languages.size()-1;i++) {
			sb.append(available_languages.get(i));
			sb.append(", ");
		}
		sb.append(available_languages.get(available_languages.size()-1));
		return sb.toString();
	}


}
