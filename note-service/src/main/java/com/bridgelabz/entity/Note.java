package com.bridgelabz.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="noteinfo")
@Component
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long noteid;

	private String title;

	private String description;

	@Column(name="isarchive",columnDefinition = "boolean default false", nullable=false)
	private boolean isArchieved;

	@Column(name="ispin",columnDefinition = "boolean default false", nullable=false)
	private boolean isPinned;

	@Column(name="istrash",columnDefinition = "boolean default false", nullable=false)
	private boolean isTrashed;

	@JsonIgnore
	@Column(name="created_at")
	private LocalDateTime createdAt;

	@JsonIgnore
	@Column(name="updated_at")
	private LocalDateTime updatedAt;

	@Column(name="color")
	private String color;

	@Column(name="reminder")
	private LocalDateTime reminder;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId")
	private User user;

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getNoteid() {
		return noteid;
	}

	public void setNoteid(long noteid) {
		this.noteid = noteid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isArchieved() {
		return isArchieved;
	}

	public void setArchieved(boolean isArchieved) {
		this.isArchieved = isArchieved;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public boolean isTrashed() {
		return isTrashed;
	}

	public void setTrashed(boolean isTrashed) {
		this.isTrashed = isTrashed;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public LocalDateTime getReminder() {
		return reminder;
	}

	public void setReminder(LocalDateTime reminder) {
		this.reminder = reminder;
	}
	
	
}
