package cn.com.icloud.xsl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.transform.TransformerConfigurationException;

import com.icloud.framework.performance.runnable.TaskRunnable;
import com.icloud.framework.performance.runnable.ThreadStat;

public class XSLRunnable extends TaskRunnable {
	private XMLTransform xmltrans;
	String StyleFileName = "D:/test/xttss_lfex_XT_000984_rsp.xsl";
	String XMLFileName = "D:/test/test.TXT";

	public XSLRunnable(String threadName, ThreadStat threadStat) {
		super(threadName, threadStat);
		try {
			try {
				xmltrans = new XMLTransform(new FileInputStream(StyleFileName));
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String doRun() {
		try {
			return doTask();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ok";
	}

	public String doTask() throws FileNotFoundException {
		// XMLTransform

		String str = xmltrans.transform(new FileInputStream(XMLFileName));
		// System.out.println(str);
		return "为空";
	}
}
