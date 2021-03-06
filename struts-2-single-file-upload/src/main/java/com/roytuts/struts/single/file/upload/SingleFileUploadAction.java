package com.roytuts.struts.single.file.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class SingleFileUploadAction extends ActionSupport implements ServletContextAware {

	private static final long serialVersionUID = 1L;

	private File file;
	private String filePath;
	private String fileFileName;
	private String fileContentType;
	private ServletContext servletContext;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public String execute() throws Exception {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		File dir = new File(servletContext.getRealPath(""));
		System.out.println("servletContext.getRealPath(\"\"): " + servletContext.getRealPath(""));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String targetPath = dir.getPath() + File.separator + fileFileName;
		System.out.println("targetPath: " + targetPath);
		File f = new File(targetPath);
		try {
			fileInputStream = new FileInputStream(file);
			fileOutputStream = new FileOutputStream(f);
			int c;
			while ((c = fileInputStream.read()) != -1) {
				fileOutputStream.write(c);
			}
		} catch (Exception e) {
			addActionError("Error occurred during uploading the file!");
			return INPUT;
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
		addActionMessage("File successfully uploaded!");
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (file == null) {
			addActionMessage("You must select a file!");
		}

	}

}
