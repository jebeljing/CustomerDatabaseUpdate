package main;

import java.util.Map;

import model.MasterABEntry;

public class Main {

	public static void main(String[] args) throws Exception {

		Map<String, MasterABEntry> data = CombineAllTxt.combine();
		System.out.println(data.keySet().size());
		
		Map<String, MasterABEntry> finalValidData = CombineAllTxt.reduce(data);
		System.out.println(finalValidData.keySet().size());
		for (String entry : finalValidData.keySet()) {
			System.out.println(finalValidData.get(entry));
		}
	}

}
