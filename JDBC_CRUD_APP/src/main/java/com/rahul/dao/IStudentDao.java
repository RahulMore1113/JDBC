package com.rahul.dao;

import com.rahul.dto.Student;

public interface IStudentDao {

	public String addStudent(String sname, Integer sage, String saddress);

	public Student searchStudent(Integer sid);

	public String updateStudent(Student student);

	public String deleteStudent(Integer sid);

}
