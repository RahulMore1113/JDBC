package com.rahul.main;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class TestApp 
{
	public static void main(String[] args) throws SQLException 
	{
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs = rsf.createCachedRowSet();
		FilteredRowSet frs = rsf.createFilteredRowSet();
		JdbcRowSet jrs = rsf.createJdbcRowSet();
		JoinRowSet jnrs = rsf.createJoinRowSet();
		WebRowSet wrs = rsf.createWebRowSet();
		
		System.out.println(crs.getClass().getName());
		System.out.println(frs.getClass().getName());
		System.out.println(jrs.getClass().getName());
		System.out.println(jnrs.getClass().getName());
		System.out.println(wrs.getClass().getName());
		
	}
}
