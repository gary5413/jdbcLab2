package jdbc_2.eeit179.jerry.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetDataUtil {
	public List<String> getURLContent() throws IOException{
		String etf_url="https://opendata.tdcc.com.tw/getOD.ashx?id=2-41";
		ArrayList<String> list = new ArrayList<String>();
		URL url = new URL(etf_url);
		InputStream inputStream = url.openStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		try {
			String content="";
			while (bufferedReader.ready()) {
				content = bufferedReader.readLine();
//				System.out.println(content);
//				加到list裏面
				list.add(content);
			}
//			刪除第一筆
			list.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			bufferedReader.close();
			inputStreamReader.close();
		}
		return list;
	}
}
