package com.rahul.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rahul.dto.Student;
import com.rahul.util.JdbcUtil;

public class StudentDaoImple implements IStudentDao {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {

		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("insert into student (sname, sage, saddress) values (?,?,?)");

			if (pstmt != null) {
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddress);

				int affectedRow = pstmt.executeUpdate();

				if (affectedRow == 1)
					return "success";
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {

		Student std = null;

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("select sid, sname, sage, saddress from student where sid = ?");

			if (pstmt != null) {
				pstmt.setInt(1, sid);

				rs = pstmt.executeQuery();
			}

			if (rs != null) {
				if (rs.next()) {
					std = new Student();
					std.setSid(rs.getInt(1));
					std.setSname(rs.getString(2));
					std.setSage(rs.getInt(3));
					std.setSaddress(rs.getString(4));

					return std;
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return std;
	}

	@Override
	public String updateStudent(Student student) {

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("update student set sname = ?, sage = ?, saddress = ? where sid = ?");

			if (pstmt != null) {
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddress());
				pstmt.setInt(4, student.getSid());

				int affectedRow = pstmt.executeUpdate();
				if (affectedRow == 1)
					return "success";
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "failure";
	}

	@Override
	public String deleteStudent(Integer sid) {

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("delete from student where sid = ?");

			if (pstmt != null) {
				pstmt.setInt(1, sid);

				int affectedRow = pstmt.executeUpdate();
				if (affectedRow == 1)
					return "success";
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "failure";
	}

}
