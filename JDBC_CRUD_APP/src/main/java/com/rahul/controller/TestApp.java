package com.rahul.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.rahul.dto.Student;
import com.rahul.service.IStudentService;
import com.rahul.servicefactory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) throws IOException {

		while (true) {

			System.out.println("-----------------");
			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");
			System.out.println("-----------------");

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter you choice, press [1/2/3/4/5] :: ");
			int ch = sc.nextInt();

			switch (ch) {

			case 1 -> insertOperation();

			case 2 -> selectOperation();

			case 3 -> updateOperation();

			case 4 -> deleteOperation();

			case 5 -> exitOperation();

			default -> System.out.println("Enter correct choice");

			}

		}

	}

	private static void exitOperation() {

		System.out.println("Exiting from the CRUD App...");
		try {
			Thread.sleep(5000);
			System.out.println("Thanks for using the CRUP APPLICATION :)");
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void deleteOperation() {

		IStudentService service = StudentServiceFactory.getStudentService();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the id of student to delete the record :: ");
		int id = sc.nextInt();

		String msg = service.deleteStudent(id);
		if (msg.equalsIgnoreCase("success"))
			System.out.println("Record deleted successully for the id " + id);
		else
			System.out.println("Record not available for id :: " + id);

	}

	private static void updateOperation() throws IOException {

		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the student id to update the data :: ");
		String id = sc.readLine();

		IStudentService service = StudentServiceFactory.getStudentService();
		Student student = service.searchStudent(Integer.parseInt(id));

		if (student != null) {
			Student newStudent = new Student();

			System.out.println("Student ID is :: " + id);
			newStudent.setSid(student.getSid());

			System.out.println("Student old name is " + student.getSname() + " Enter new name :: ");
			String newName = sc.readLine();
			if (newName.equals("") || newName == "")
				newStudent.setSname(student.getSname());
			else
				newStudent.setSname(newName);

			System.out.println("Student old age is " + student.getSage() + " Enter new age :: ");
			String newAge = sc.readLine();
			if (newAge.equals("") || newAge == "")
				newStudent.setSage(student.getSage());
			else
				newStudent.setSage(Integer.parseInt(newAge));

			System.out.println("Student old address is " + student.getSaddress() + " Enter new address :: ");
			String newAddress = sc.readLine();
			if (newAddress.equals("") || newAddress == "")
				newStudent.setSaddress(student.getSaddress());
			else
				newStudent.setSaddress(newAddress);

			System.out.println("New Student data is :: " + newStudent);
			System.out.println();

			String msg = service.updateStudent(newStudent);
			if (msg.equalsIgnoreCase("success"))
				System.out.println("Record update succesfully...");
			else
				System.out.println("Record updation failed...");
		} else
			System.out.println("Student record not avaialable for the given id " + id + " for updation...");

	}

	private static void selectOperation() {
		IStudentService service = StudentServiceFactory.getStudentService();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the id of the student to be search :: ");
		int id = sc.nextInt();

		Student std = service.searchStudent(id);

		if (std != null) {
			System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
			System.out.println(std.getSid() + "\t" + std.getSname() + "\t" + std.getSage() + "\t" + std.getSaddress());
		} else
			System.out.println("Record not found for given id :: " + id);
	}

	private static void insertOperation() {
		IStudentService service = StudentServiceFactory.getStudentService();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the name of the student :: ");
		String sname = sc.next();

		System.out.println("Enter the age of the student :: ");
		int sage = sc.nextInt();

		System.out.println("Enter the address of the student :: ");
		String saddress = sc.next();

		String msg = service.addStudent(sname, sage, saddress);

		if (msg.equalsIgnoreCase("success"))
			System.out.println("Record inserted successfullu...");
		else
			System.out.println("Record insertion failed...");
	}

}
