package cn.com.icloud.poi.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class PoiWordReader {
	public void readerWord() throws IOException, GeneralSecurityException {
		// InputStream is = new
		// FileInputStream("D:\\cjnpafa\\tongbupan\\pingan\\work\\保险会议\\资料\\分布式事务和编码.doc");
		// InputStream is = new FileInputStream();
		String file = "D:\\cjnpafa\\tongbupan\\private\\LP\\世家会艺术品投资公司经营计划书-JKN - 副本.doc";
		InputStream fis = new FileInputStream(file);

		// POIFSFileSystem pfs = new POIFSFileSystem(fis);
		// EncryptionInfo encInfo = new EncryptionInfo(pfs);
		// Decryptor decryptor = Decryptor.getInstance(encInfo);
		// decryptor.verifyPassword("Excel文件密碼");

		// WordExtractor extractor = new WordExtractor(
		// decryptor.getDataStream(pfs));
		WordExtractor extractor = new WordExtractor(fis);
		// 输出word文档所有的文本
		System.out.println(extractor.getText());
		System.out.println(extractor.getTextFromPieces());
		// 输出页眉的内容
		System.out.println("页眉：" + extractor.getHeaderText());
		// 输出页脚的内容
		System.out.println("页脚：" + extractor.getFooterText());
		// 输出当前word文档的元数据信息，包括作者、文档的修改时间等。
		System.out.println(extractor.getMetadataTextExtractor().getText());
		// 获取各个段落的文本
		String paraTexts[] = extractor.getParagraphText();
		for (int i = 0; i < paraTexts.length; i++) {
			System.out.println("Paragraph " + (i + 1) + " : " + paraTexts[i]);
		}
		// 输出当前word的一些信息
		printInfo(extractor.getSummaryInformation());
		// 输出当前word的一些信息
		this.printInfo(extractor.getDocSummaryInformation());
		this.closeStream(fis);
	}

	/**
	 * 输出SummaryInfomation
	 * 
	 * @param info
	 */
	private void printInfo(SummaryInformation info) {
		// 作者
		System.out.println(info.getAuthor());
		// 字符统计
		System.out.println(info.getCharCount());
		// 页数
		System.out.println(info.getPageCount());
		// 标题
		System.out.println(info.getTitle());
		// 主题
		System.out.println(info.getSubject());
	}

	/**
	 * 输出DocumentSummaryInfomation
	 * 
	 * @param info
	 */
	private void printInfo(DocumentSummaryInformation info) {
		// 分类
		System.out.println(info.getCategory());
		// 公司
		System.out.println(info.getCompany());
	}

	/**
	 * 关闭输入流
	 * 
	 * @param is
	 */
	private void closeStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException,
			GeneralSecurityException {
		PoiWordReader reader = new PoiWordReader();
		reader.readerWord();
	}
}
