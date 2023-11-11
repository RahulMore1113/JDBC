package com.rahul.servicefactory;

import com.rahul.service.IStudentService;
import com.rahul.service.StudentServiceImpl;

public class StudentServiceFactory {

	private StudentServiceFactory() {

	}

	private static IStudentService service = null;

	public static IStudentService getStudentService() {

		if (service == null)
			service = new StudentServiceImpl();

		return service;

	}

}
