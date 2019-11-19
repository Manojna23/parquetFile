package com.parquet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.parquet.dao.Team;

public class TrialApp {

	public static void main(String args[]) {
		Team team1 = new Team("team1",1);
        Team team2 = new Team("HIFI",2);
        List<Team> teams = new ArrayList<>();
        for(int i = 0; i<20; i++) {
        	teams.add(team1);
        }
        List<List<Team>> teamList= new ArrayList<>();
        int initialSize = teams.size();
        int finalSize = initialSize/10;
        List<Team> tempList = new ArrayList<>();
        for(int i = 0,j=1; i<initialSize; i++,j++) {
        	if(j==finalSize || j==initialSize-1) {
        		System.out.println("Adding records at j:"+j+" i:"+i);
        		teamList.add(tempList);
        		tempList = new ArrayList<>();
        		j=0;
        	}
        	tempList.add(teams.get(i));
        }
        
        List<String> stringList = new ArrayList<>(); 
        for(int i = 0; i<10; i++) {
        	stringList.add("Manojna");
        }
        System.out.println("intialSize:"+initialSize+" finalSize:"+finalSize+" stringListSize:"+stringList.size()
        		+" teamListSize:"+teamList.size());
        
        ExecutorService service = Executors.newFixedThreadPool(10);
        for(int i = 0; i<10; i++) {
        	Runnable runnable = new TrialRunnable(teamList.get(i),stringList.get(i));
        	service.submit(runnable);
        }
	}
}
