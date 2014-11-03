package com.easytrack.component.system;

import java.io.Serializable;

import javax.crypto.SecretKey;

public class License implements Serializable {
	private static final long serialVersionUID = -66479260863201194L;
	private SecretKey deskey = null;
	private String license = null;
	private String adminEmail = null;
	private String companyName = null;
	private String language = null;
	private String expiredDate = null;
	private String macAddress = null;
	private int licenseCount = 0;

	public SecretKey getDeskey() {
		return this.deskey;
	}

	public void setDeskey(SecretKey deskey) {
		this.deskey = deskey;
	}

	public String getLicense() {
		return this.license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getAdminEmail() {
		return this.adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getExpiredDate() {
		return this.expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public int getLicenseCount() {
		return this.licenseCount;
	}

	public void setLicenseCount(int licenseCount) {
		this.licenseCount = licenseCount;
	}

	@Override
	public String toString() {
		return "License [deskey=" + deskey.getAlgorithm() + ",Format "
				+ deskey.getFormat() + ", license=" + license + ", adminEmail="
				+ adminEmail + ", companyName=" + companyName + ", language="
				+ language + ", expiredDate=" + expiredDate + ", macAddress="
				+ macAddress + ", licenseCount=" + licenseCount + "]";
	}

}
