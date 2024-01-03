package jdbc_2.eeit179.garylee.lab.lab09.product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jdbc_2.eeit179.garylee.lab.lab09.product.dao.EtfDao;
import jdbc_2.eeit179.garylee.lab.util.GetDataUtil;

public class EtfDataAction {

	public static void main(String[] args) {
		GetDataUtil getDataUtil = new GetDataUtil();
		String url="https://opendata.tdcc.com.tw/getOD.ashx?id=2-41";
		EtfDao dao=null;
		try {
			List<String> dataList = getDataUtil.getURLContent(url);
//			觀察第一筆資料
//			String[] tokens = dataList.get(0).split(",");
//			System.out.println(tokens[0]);				
//			System.out.println(tokens[1]);				
//			System.out.println(tokens[2]);				
//			觀察全部資料的第一筆
			dao = new EtfDao();
			dao.createConnection();
			for(int i=0;i<dataList.size();i++) {
				String[] tokens = dataList.get(i).split(",");
				System.out.println("正要輸入:"+tokens[1]+","+tokens[2]+","+tokens[3]);
				dao.insertEtfData(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dao.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
