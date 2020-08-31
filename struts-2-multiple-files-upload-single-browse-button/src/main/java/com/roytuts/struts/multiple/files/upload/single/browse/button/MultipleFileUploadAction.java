package com.roytuts.struts.multiple.files.upload.single.browse.button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class MultipleFileUploadAction extends ActionSupport implements ServletContextAware {

	private static final long serialVersionUID = 5109187855480607759L;
	// input type file name - files
	private File[] files;
	// all uploaded file names
	private String[] filesFileName;
	// all uploaded files content type
	private String[] filesContentType;
	// servletcontext for getting the context
	private ServletContext servletContext;

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void validate() {
		// check whether at least one file was selected for upload
		if (null == files) {
			addActionMessage("You must select at least one file!");
		}
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
		try {
			for (int i = 0; i < files.length; i++) {
				String targetPath = dir.getPath() + File.separator + filesFileName[i];
				System.out.println("targetPath: " + targetPath);
				File f = new File(targetPath);
				fileInputStream = new FileInputStream(files[i]);
				fileOutputStream = new FileOutputStream(f);
				int c;
				while ((c = fileInputStream.read()) != -1) {
					fileOutputStream.write(c);
				}
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

		addActionMessage("File(s) successfully uploaded!");

		return SUCCESS;
	}

}
