package com.icloud.paper;

import com.icloud.paper.bean.PaperImporter;

public class App {

	public void addPaper() {
		PaperImporter importer = new PaperImporter();
		importer.importPaper();
	}

	public static void main(String[] args) {
		App app = new App();
		app.addPaper();
	}
}
