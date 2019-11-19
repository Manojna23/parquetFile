package com.parquet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import com.parquet.dao.Team;

/**
 * Hello world!
 *
 */
public class ParquetApp 
{
    public static void main( String[] args )
    {
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
        
        System.out.println("teamListSize:"+teamList.size()+ " initialSize:"+initialSize+" finalSize:"+finalSize);
        ExecutorService service = Executors.newFixedThreadPool(10);
       
        for(int i = 0; i<10; i++) {
        	String file = "D:/parquet/parquet."+(i+1);
        	Path path = new Path(file);
        	try(ParquetWriter<Team> writer = AvroParquetWriter.<Team>builder(path)
            		.withConf(new Configuration()).withSchema(ReflectData.AllowNull.get().getSchema(Team.class))
            		.withCompressionCodec(CompressionCodecName.SNAPPY).withDataModel(ReflectData.get()).build()) {
        		Runnable runnable = new ParquetThreadWriter(teamList.get(i), writer);
        		service.execute(runnable);
            } catch (IllegalArgumentException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
    }
}
