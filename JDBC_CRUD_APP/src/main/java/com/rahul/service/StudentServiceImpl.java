package com.rahul.service;

import com.rahul.dao.IStudentDao;
import com.rahul.daofactory.StudentDaoFactory;
import com.rahul.dto.Student;

public class StudentServiceImpl implements IStudentService {

	private IStudentDao dao;

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		dao = StudentDaoFactory.getStudentDao();
		return dao.addStudent(sname, sage, saddress);
	}

	@Override
	public Student searchStudent(Integer sid) {
		dao = StudentDaoFactory.getStudentDao();
		return dao.searchStudent(sid);
	}

	@Override
	public String updateStudent(Student student) {
		dao = StudentDaoFactory.getStudentDao();
		return dao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer sid) {
		dao = StudentDaoFactory.getStudentDao();
		return dao.deleteStudent(sid);
	}

}
