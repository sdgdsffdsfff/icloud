package com.easytrack.component.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytrack.commons.config.Config;
import com.easytrack.commons.exception.CommonException;
import com.easytrack.commons.util.CipherUtils;

public class LicenseCheck {
	public static final String DELIMITER = ";";
	private static LicenseCheck lc = null;

	// private CacheManager usrCacheMgr = CacheManager.getCacheManager("User");

	private Log log = LogFactory.getLog(getClass().getName());

	private License license = new License();

	private double alertThreshold = 0.8D;

	private String preAlertMessage = "Your activated user counter has reached @AlertThreshold% of total user license number.";

	private String exceedMessage = "Maximum available user licenses have been reached, please contact CeruleanSoft to increase your user licenses or deactivate some active users.";

	private String adminEmail = "";

	private String VAR_ALERT_THRESHOLD = "@AlertThreshold";

	private LicenseCheck() throws Exception {
		try {
			try {
//				String file = Config.getHomeDir();
//				if (file == null) {
//					if ("Windows".startsWith(System.getProperty("os.name")))
//						file = "D:/EasyTrack";
//					else {
//						file = "/home/easytrack";
//					}
//				}
				String file = "D:/EasyTrack"; 
//				File f = new File(file + "/config/license.lic");
				File f = new File(file + "/config2/license.lic");
				if (f.exists()) {
					FileInputStream o = new FileInputStream(f);
					ObjectInputStream in = new ObjectInputStream(o);
					this.license = ((License) in.readObject());
					System.out.println(this.license.toString());
					System.out.println("-------------------");
					in.close();
					o.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.license = new License();
			}
			checkLicense(this.license);

			String s = Config.getConfig("LICENSE", "ALERT-THRESHOLD");
			this.alertThreshold = (Integer.parseInt(s) / 100.0D);
			if (this.alertThreshold > 1.0D) {
				this.alertThreshold = 1.0D;
			}
			this.adminEmail = Config.getConfig("LICENSE", "ADMIN-EMAIL");
		} catch (CommonException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("InvalidLicenseKey");
		}
	}

	public void checkLicense(License l) throws Exception {
		try {
			CipherUtils cu = CipherUtils.getInstance();
			if (l.getDeskey() != null)
				cu.setDeskey(l.getDeskey());
			else {
				l.setLicense(Config.getConfig("LICENSE", "KEY"));
			}

			String decryptKey = cu.decrypt(l.getLicense());
			System.out.println("----decryptKey = " + decryptKey);
			parseKey(l, decryptKey);
		} catch (CommonException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("InvalidLicenseKey");
		}
	}

	public static LicenseCheck getInstance() throws Exception {
		if (lc == null) {
			lc = new LicenseCheck();
		}
		return lc;
	}

	public String getExpiredDate() {
		return this.license.getExpiredDate();
	}

	public boolean check() {
		boolean ret = false;

		int usedLicenseCount = 0;
		usedLicenseCount = getActiveUserCount();

		if (this.log.isInfoEnabled()) {
			this.log.info("Assigned license count is:"
					+ this.license.getLicenseCount());
			this.log.info("Alert threshold is:" + this.alertThreshold);
			this.log.info("Currently used license count is:" + usedLicenseCount);
		}

		if (usedLicenseCount >= this.license.getLicenseCount()) {
			sendAlert(this.exceedMessage);
			ret = true;
		} else if (usedLicenseCount >= this.license.getLicenseCount()
				* this.alertThreshold) {
			String s = StringUtils.replace(this.preAlertMessage,
					this.VAR_ALERT_THRESHOLD,
					Config.getConfig("LICENSE", "ALERT-THRESHOLD"));
			sendAlert(s);
			ret = true;
		}

		return ret;
	}

	public boolean checkExceed() {
		boolean ret = false;

		int usedLicenseCount = 0;
		usedLicenseCount = getActiveUserCount();

		if (this.log.isInfoEnabled()) {
			this.log.info("Assigned license count is:"
					+ this.license.getLicenseCount());
			this.log.info("Alert threshold is:" + this.alertThreshold);
			this.log.info("Currently used license count is:" + usedLicenseCount);
		}

		if (usedLicenseCount >= this.license.getLicenseCount()) {
			sendAlert(this.exceedMessage);
			ret = true;
		}
		return ret;
	}

	public boolean applyForNewLicense(int applyLicense) {
		int usedLicenseCount = 0;
		usedLicenseCount = getActiveUserCount();

		if (this.log.isInfoEnabled()) {
			this.log.info("Assigned license count is:"
					+ this.license.getLicenseCount());
			this.log.info("Alert threshold is:" + this.alertThreshold);
			this.log.info("Currently used license count is:" + usedLicenseCount);
			this.log.info("Apply for " + applyLicense + " licenses.");
		}

		if (usedLicenseCount + applyLicense > this.license.getLicenseCount()) {
			return false;
		}
		return true;
	}

	public License getLicense() {
		return this.license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public boolean applyForNewLicense(int applyLicense, int companyID) {
		int usedLicenseCount = 0;
		usedLicenseCount = getActiveUserCount(companyID);

		if (this.log.isInfoEnabled()) {
			this.log.info("Assigned license count is:"
					+ this.license.getLicenseCount());
			this.log.info("Alert threshold is:" + this.alertThreshold);
			this.log.info("Currently used license count is:" + usedLicenseCount);
			this.log.info("Apply for " + applyLicense + " licenses.");
		}

		if (usedLicenseCount + applyLicense > this.license.getLicenseCount()) {
			return false;
		}
		return true;
	}

	private int getActiveUserCount() {
		int cnt = 0;
		// Object[] users = this.usrCacheMgr.getObjectArray();
		// for (int i = 0; i < users.length; i++) {
		// User user = (User) users[i];
		// if ((user.getStatus() == 1) && (user.getType() != 2)) {
		// cnt++;
		// }
		// }
		return cnt;
	}

	public int getActiveUserCount(int companyID) {
		return 0;
		// int cnt = 0;
		// Object[] users = this.usrCacheMgr.getObjectArray();
		// for (int i = 0; i < users.length; i++) {
		// User user = (User) users[i];
		// if ((user.getStatus() == 1) && (user.getType() != 2)
		// && (user.getCompanyID() == companyID)) {
		// cnt++;
		// }
		// }
		// return cnt;
	}

	private void sendAlert(String content) {
		try {
			if (this.log.isInfoEnabled()) {
				this.log.info("admin email is:" + this.adminEmail);
			}

		} catch (Exception e) {
			this.log.error("error occurs", e);
		}
	}

	private void parseKey(License l, String licenseKey) throws Exception {
		String digest = "";
		StringTokenizer st = new StringTokenizer(licenseKey, ";");
		int tokenCount = st.countTokens();
		if (tokenCount > 4) {
			int cnt = 0;
			while (st.hasMoreTokens()) {
				cnt++;
				switch (cnt) {
				case 1:
					digest = st.nextToken();
					break;
				case 2:
					l.setAdminEmail(st.nextToken());
					break;
				case 3:
					l.setLicenseCount(Integer.parseInt(st.nextToken()));
					break;
				case 4:
					String companyName = st.nextToken();
					if (l.getCompanyName() == null)
						l.setCompanyName(companyName);
					break;
				case 5:
					l.setLanguage(st.nextToken());
					break;
				case 6:
					l.setExpiredDate(st.nextToken());
				}

			}

			System.out.println(l.toString());
//			if ((l.getExpiredDate() == null) || ("".equals(l.getExpiredDate()))) {
////				List address = getPhysicalAddress();
//				boolean hasMAC = false;
//				for (int i = 0; i < address.size(); i++) {
//					String mac = (String) address.get(i);
//					System.out.println(mac);
//					String calculatedDigest = CipherUtils.getInstance().digest(
//							mac);
//					if (calculatedDigest.equals(digest)) {
//						hasMAC = true;
//						break;
//					}
//				}
//				if (!hasMAC) {
//					l.setLicenseCount(0);
//					throw new CommonException("InvalidLicenseKey");
//				}
//			}
		} else {
			throw new CommonException("InvalidLicenseKey");
		}
	}

	public List getPhysicalAddress() {
		String physicalAddress = "read MAC error!";
		ArrayList address = new ArrayList();
		String macStr = "Physical Address. . . . . . . . . :";
		String os = System.getProperty("os.name");
		System.out.println("OS Name=" + os);
		if (os.startsWith("Windows")) {
			if ("Windows Vista".equals(os)) {
				macStr = "物理地址. . . . . . . . . . . . . :";
			}
			try {
				Process process = Runtime.getRuntime().exec(
						"cmd /c ipconfig /all");
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(process.getInputStream()));
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					if ((line.indexOf(macStr) != -1)
							&& (line.indexOf(":") != -1)) {
						physicalAddress = line.substring(line.indexOf(":") + 2);
						address.add(physicalAddress);
					}
				}

				process.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return address;
		}
		return getLinuxMACAddress();
	}

	public List getLinuxMACAddress() {
		ArrayList address = new ArrayList();
		try {
			ProcessBuilder pb = new ProcessBuilder(new String[] {
					"/sbin/ifconfig", "-a" });
			Process p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				if (line.indexOf(" HWaddr") != -1) {
					int index = line.indexOf("HWaddr");
					String mac = line.substring(index + 7);
					address.add(mac);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}
}
