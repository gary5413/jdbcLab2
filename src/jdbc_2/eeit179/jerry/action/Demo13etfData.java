package jdbc_2.eeit179.jerry.action;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.util.List;

import jdbc_2.eeit179.jerry.model.EtfDao;
import jdbc_2.eeit179.jerry.util.GetDataUtil;

public class Demo13etfData {

	public static void main(String[] args) {
		GetDataUtil getDataUtil = new GetDataUtil();
		EtfDao dao=null;
		try {
			List<String> dataList = getDataUtil.getURLContent();
//			觀察第一筆資料
			String[] tokens = dataList.get(0).split(",");
//			System.out.println("============一筆資料============");
//			System.out.println("證券代號:"+tokens[1]);
//			System.out.println("證券名稱:"+tokens[2]);
//			System.out.println("集保量:"+tokens[3]);
//			System.out.println("===============================");
			
//			確認完資料 開始封裝並存入資料
			dao=new EtfDao();
			dao.createConnection();
			for(int i=0;i<dataList.size();i++) {
				String[] tokens1=dataList.get(i).split(",");
				System.out.println("正要輸入:"+tokens1[1]+","+tokens1[2]+","+tokens1[3]);
				dao.insertEtfData(tokens1[1], tokens1[2], Integer.parseInt(tokens1[3]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
