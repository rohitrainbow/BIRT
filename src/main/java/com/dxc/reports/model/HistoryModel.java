package com.dxc.reports.model;

public class HistoryModel {

	Integer number;
	String id;
	String status;
	String createdBy;
	String parameters;
	String endTime;
	String startTime;
	String title;
	String duration;
	String fileld;
	String serverUsed;
	String storedFileLocation;
	String externalDestination;
	String contentType;
	String fileName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getFileld() {
		return fileld;
	}

	public void setFileld(String fileld) {
		this.fileld = fileld;
	}

	public String getServerUsed() {
		return serverUsed;
	}

	public void setServerUsed(String serverUsed) {
		this.serverUsed = serverUsed;
	}

	public String getStoredFileLocation() {
		return storedFileLocation;
	}

	public void setStoredFileLocation(String storedFileLocation) {
		this.storedFileLocation = storedFileLocation;
	}

	public String getExternalDestination() {
		return externalDestination;
	}

	public void setExternalDestination(String externalDestination) {
		this.externalDestination = externalDestination;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", status=" + status + ", createdBy=" + createdBy + ", parameters=" + parameters
				+ ", endTime=" + endTime + ", startTime=" + startTime + ", title=" + title + ", duration=" + duration
				+ ", fileld=" + fileld + ", serverUsed=" + serverUsed + ", storedFileLocation=" + storedFileLocation
				+ ", externalDestination=" + externalDestination + ", contentType=" + contentType + ", fileName="
				+ fileName + "]";
	}

}
