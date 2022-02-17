package test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestURLConnection {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL("https://www.naver.com");
		
		URLConnection conn = url.openConnection();
		
		conn.connect();
		
		Map<String,List<String>> map = conn.getHeaderFields();
		Set<String> set = map.keySet();
		
		for(String key : set) {
			List<String> list = map.get(key);
			System.out.print(key + ":");
			for(String value : list) {
				System.out.println(value + " ");				
			}System.out.println();
		}
		
		/*
		 * InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"utf-8");
		 * 
		 * int count=0; while(true) { count++; int ch = isr.read(); if(ch==-1 ||
		 * count==100000)break; System.out.printf("%c",ch);
		 * 
		 * }
		 */
		
		}
}
