package com.crossarx.rest.api.hiv.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Module {

	private Integer Id;
	private String Name;
	private String Type;
	private Double Order;
	private Integer Year;
	private Boolean Extra;
	private String Pretest_picture;
	private String Posttest_picture;
	private Boolean Active;
	private Boolean Allowed_for_guest;
	private String Voice_over;
	private Boolean Show_chapter_titles_in_menu;
	private List<String> Available_languages;

	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@JsonProperty("Name")
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@JsonProperty("Type")
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@JsonProperty("Order")
	public Double getOrder() {
		return Order;
	}

	public void setOrder(Double order) {
		Order = order;
	}

	@JsonProperty("Year")
	public Integer getYear() {
		return Year;
	}

	public void setYear(Integer year) {
		Year = year;
	}

	@JsonProperty("Extra")
	public Boolean getExtra() {
		return Extra;
	}

	public void setExtra(Boolean extra) {
		Extra = extra;
	}

	@JsonProperty("Pretest_picture")
	public String getPretest_picture() {
		return Pretest_picture;
	}

	public void setPretest_picture(String pretest_picture) {
		Pretest_picture = pretest_picture;
	}

	@JsonProperty("Posttest_picture")
	public String getPosttest_picture() {
		return Posttest_picture;
	}

	public void setPosttest_picture(String posttest_picture) {
		Posttest_picture = posttest_picture;
	}

	@JsonProperty("Active")
	public Boolean getActive() {
		return Active;
	}

	public void setActive(Boolean active) {
		Active = active;
	}

	@JsonProperty("Allowed_for_guest")
	public Boolean getAllowed_for_guest() {
		return Allowed_for_guest;
	}

	public void setAllowed_for_guest(Boolean allowed_for_guest) {
		Allowed_for_guest = allowed_for_guest;
	}

	@JsonProperty("Voice_over")
	public String getVoice_over() {
		return Voice_over;
	}

	public void setVoice_over(String voice_over) {
		Voice_over = voice_over;
	}

	@JsonProperty("Show_chapter_titles_in_menu")
	public Boolean getShow_chapter_titles_in_menu() {
		return Show_chapter_titles_in_menu;
	}

	public void setShow_chapter_titles_in_menu(Boolean show_chapter_titles_in_menu) {
		Show_chapter_titles_in_menu = show_chapter_titles_in_menu;
	}

	@JsonProperty("Available_languages")
	public List<String> getAvailable_languages() {
		return Available_languages;
	}

	public void setAvailable_languages(List<String> available_languages) {
		Available_languages = available_languages;
	}
	
	@Override
	public String toString(){
		return "Id: " + this.Id + "\n"
				+ "Name: " + this.Name + "\n"
				+ "Type: " + this.Type + "\n"
				+ "Order: " + this.Order + "\n"
				+ "Year: " + this.Year + "\n"
				+ "Extra: " + this.Extra + "\n"
				+ "Pretest_picture: " + this.Pretest_picture + "\n"
				+ "Posttest_picture: " + this.Posttest_picture + "\n"
				+ "Active: " + this.Active + "\n"
				+ "Allowed_for_guest: " + this.Allowed_for_guest + "\n"
				+ "Voice_over: " + this.Voice_over + "\n"
				+ "Show_chapter_titles_in_menu: " + this.Show_chapter_titles_in_menu + "\n"
				+ "Available_languages: " + getAvailableLanguagesFormated() + "\n";
	}

	private String getAvailableLanguagesFormated() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Available_languages.size() - 1; i++) {
			sb.append(Available_languages.get(i));
			sb.append(", ");
		}
		sb.append(Available_languages.get(Available_languages.size() - 1));
		return sb.toString();
	}

}
