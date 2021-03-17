package com.fsoft.F_Cinema.utils;

import org.springframework.stereotype.Component;

@Component
public class FileUtils {

	public String renameFile(String fileName) {
		String[] arrImg = fileName.split("\\.");
		String extension = arrImg[arrImg.length - 1];
		String name = "";
		for (int i = 0; i < (arrImg.length - 1); i++) {
			if (i == 0) {
				name = arrImg[i];
			} else {
				name += "-" + arrImg[i];
			}
		}
		name = name + "-" + System.nanoTime() + "." + extension;
		return name;
	}

}
