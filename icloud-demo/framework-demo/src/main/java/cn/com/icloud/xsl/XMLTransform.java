package cn.com.icloud.xsl;

import java.io.*;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;

import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.*;

public class XMLTransform {
	private Source style = null;
	// private StreamSource style=null;
	// private static Logger log = Logger.getLogger("REALTIME");
	private static final String ENCODING = "GBK";
	TransformerFactory transFactory = TransformerFactory.newInstance();
	Transformer trans = null;

	public XMLTransform() {
	}

	public XMLTransform(InputStream Stylesheet)
			throws TransformerConfigurationException {
		this.style = new SAXSource(new InputSource(Stylesheet));
		// this.style = new StreamSource(Stylesheet);
		if (this.style != null) {
			trans = transFactory.newTransformer(this.style);
		} else {
			trans = transFactory.newTransformer();
		}
		trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	}

	public void transform(InputStream xmlStream, OutputStream output)
			throws Exception {
		Source source = new SAXSource(new InputSource(xmlStream));
		Result result = new StreamResult(output);
		trans.transform(source, result);
	}

	public String transform(InputStream xmlStream) {
		String re = "";
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			transform(xmlStream, out);
			byte bcont[] = out.toByteArray();
			/* ��utf-8�ַ�ת��Ϊstring����,֧��GBK yuhs 20080917 */
			re = new String(bcont, "UTF-8");
			/* ��ת��ͷ���ΪGBK������XMLStrToMap���� yuhs 20080917 */
			re = re.replaceFirst("UTF-8", ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
			// log.debug(e);
		}

		return re;
		// return out.toString();
	}

	public String transform(String xmlString) {
		ByteArrayInputStream In = new ByteArrayInputStream(xmlString.getBytes());
		return transform(In);
	}

	public String transformUTF8(String xmlString) throws Exception {
		ByteArrayInputStream In = new ByteArrayInputStream(
				xmlString.getBytes("UTF-8"));
		return transform(In);
	}

	public static String performanceLast(int len)
			throws TransformerConfigurationException, FileNotFoundException {
		long start = System.currentTimeMillis();
		String StyleFileName = "D:/test/xttss_lfex_XT_000984_rsp.xsl";
		String XMLFileName = "D:/test/test.TXT";
		for (int i = 0; i < len; i++) {
			XMLTransform xmltrans = new XMLTransform(new FileInputStream(
					StyleFileName));
//			try {
//				String str = xmltrans
//						.transform(new FileInputStream(XMLFileName));
//				// System.out.println(str);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			if (i % 100 == 0) {
				System.out.println("performanceLast running, i=" + i
						+ ",count=" + len);
			}
		}
		long end = System.currentTimeMillis();
		String s = "performanceLast time=" + (end - start) / 1000.0f
				+ "s,count=" + len;
		System.out.println(s);
		return s;
	}

	public static String performanceNew(int len)
			throws TransformerConfigurationException, FileNotFoundException {
		long start = System.currentTimeMillis();
		String StyleFileName = "D:/test/xttss_lfex_XT_000984_rsp.xsl";
		String XMLFileName = "D:/test/test.TXT";
		XMLTransform xmltrans = new XMLTransform(new FileInputStream(
				StyleFileName));
		for (int i = 0; i < len; i++) {
			try {
				String str = xmltrans
						.transform(new FileInputStream(XMLFileName));
				// System.out.println(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i % 100 == 0) {
				System.out.println("performanceNew running, i=" + i + ",count="
						+ len);
			}
		}
		long end = System.currentTimeMillis();
		String s = "performanceNew time=" + (end - start) / 1000.0f
				+ "s,count=" + len;
		System.out.println(s);
		return s;
	}

	public static void main(String args[])
			throws TransformerConfigurationException, FileNotFoundException {
		int len = 2000;
		String lastString = performanceLast(len);
		String newString = performanceNew(len);
		System.out.println("----------");
		System.out.println("改进前:");
		System.out.println(lastString);
		System.out.println("改进后:");
		System.out.println(newString);
		System.out.println();
	}
}
