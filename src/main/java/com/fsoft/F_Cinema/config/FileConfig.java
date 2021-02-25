package com.fsoft.F_Cinema.config;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class FileConfig {

	public File getFolderUpload() {
		File folderUpload = new File(System.getProperty("user.dir") + "/src/main/resources/static/uploads");
		if (!folderUpload.exists())
			folderUpload.mkdirs();

		return folderUpload;
	}
}
