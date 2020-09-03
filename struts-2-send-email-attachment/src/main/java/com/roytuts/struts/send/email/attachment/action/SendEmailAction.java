package com.roytuts.struts.send.email.attachment.action;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.roytuts.struts.send.email.attachment.EmailAttachmentSender;

public class SendEmailAction extends ActionSupport implements ServletContextAware {
	private static final long serialVersionUID = 1L;

	// email subject
	private String emailSubject;

	// email message
	private String emailBodyText;

	// email to address
	private String emailToAddress;

	// input file
	private File file;

	// input file name - if the input type name is 'file' then file name should be
	// 'fileFileName', if input type // name is 'f' then file name should be
	// 'fFileName'
	private String fileFileName;

	// servlet context
	private ServletContext servletContext;

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailBodyText() {
		return emailBodyText;
	}

	public void setEmailBodyText(String emailBodyText) {
		this.emailBodyText = emailBodyText;
	}

	public String getEmailToAddress() {
		return emailToAddress;
	}

	public void setEmailToAddress(String emailToAddress) {
		this.emailToAddress = emailToAddress;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	// overridden method when we implement ServletContextAware
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	// action method
	@Override
	public String execute() throws Exception {
		// send the email
		EmailAttachmentSender.sendEmail(emailToAddress, emailSubject, emailBodyText, file, fileFileName);
		addActionMessage("Email with attachment successfully sent!");

		// return to the success page which is the same page here
		return SUCCESS;
	}

	// validate method is called before the execute method to validate the input
	// fields
	@Override
	public void validate() {
		if (emailToAddress == null) {
			String errorMsg = "You must provide To Email Address!";
			addActionError(errorMsg);
		}
		if (emailSubject == null) {
			String errorMsg = "You must provide Email Subject!";
			addActionError(errorMsg);
		}
		if (emailBodyText == null) {
			String errorMsg = "You must provide Email Message!";
			addActionError(errorMsg);
		}
		if (file == null) {
			String errorMsg = "You must select a file!";
			addActionError(errorMsg);
		}
	}

}
