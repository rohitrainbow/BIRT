package com.dxc.reports.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Component;

@Component
public class FileCopier {

	public void copyFiles(File source, File dest) throws IOException {
	    Files.copy(source.toPath(), dest.toPath());
	}
}
