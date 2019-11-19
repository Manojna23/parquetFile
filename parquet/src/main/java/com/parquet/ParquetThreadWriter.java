package com.parquet;

import java.io.IOException;
import java.util.List;

import org.apache.parquet.hadoop.ParquetWriter;

import com.parquet.dao.Team;

public class ParquetThreadWriter implements Runnable{

	private List<Team> teamList;
	private ParquetWriter<Team> writer;
	
	public ParquetThreadWriter(List<Team> list, ParquetWriter<Team> writer) {
		super();
		this.teamList = list;
		this.writer = writer;
	}


	@Override
	public void run() {
		System.out.println("Here for thread:"+Thread.currentThread().getName());
		teamList.forEach(team -> {
			try {
				System.out.println("Writing to parquet file in thread:"+Thread.currentThread().getName());
				writer.write(team);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

}
