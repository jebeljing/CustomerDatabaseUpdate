package main;

import java.util.HashMap;
import java.util.Map;

import util.TxtFileReader;
import model.MasterABEntry;

public class CombineAllTxt {
	
	private static final String essenceFile = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Spire HH Essence Scores 20150911.TXT";
	private static final String syndicatedSegmentFile = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Marsh_SyndicatedSegment_Extract_20150828.txt";
	private static final String marshDemoFile = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Marsh_Demo_Extract_20150828.txt";
	private static final String marshHHsPSFile = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Marsh HHs PS Segmentation Assignments 20150911.txt";
	private static final String spireEngagementSegmentFile = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Spire Engagement Segmentation Households - 091115.txt";
	
	public static Map<String, MasterABEntry> combine() throws Exception {
		TxtFileReader txtFileReader = new TxtFileReader(essenceFile);
		Map<String, MasterABEntry> updateMaps = new HashMap<String, MasterABEntry>();
		
		txtFileReader.readSpireHHEssenceScores(updateMaps);
//		System.out.println(updateMaps.keySet().size());
		
		txtFileReader.setFileName(syndicatedSegmentFile);
		txtFileReader.readMarshSyndicatedSegment(updateMaps);
//		System.out.println(updateMaps.keySet().size());
		
		txtFileReader.setFileName(marshDemoFile);
		txtFileReader.readMarshDemo(updateMaps);
//		System.out.println(updateMaps.keySet().size());
//		System.out.println(updateMaps.get("40002049394"));
		
		txtFileReader.setFileName(marshHHsPSFile);
		txtFileReader.readMarshHHsPS(updateMaps);
//		System.out.println(updateMaps.keySet().size());
//		System.out.println(updateMaps.get("40002049394"));
		
		txtFileReader.setFileName(spireEngagementSegmentFile);
		txtFileReader.readSpireEngagementSegment(updateMaps);
//		System.out.println(updateMaps.keySet().size());
//		System.out.println(updateMaps.get("40002049394"));
		
		return updateMaps;
	}
	
	public static Map<String, MasterABEntry> reduce(Map<String, MasterABEntry> entries) {
		Map<String, MasterABEntry> result = new HashMap<String, MasterABEntry>();
		
		for (String cardId : entries.keySet()) {
			MasterABEntry entry = entries.get(cardId);
			if (entry.isValidEntry()) {
				result.put(entry.getCard1(), entry);
			}
		}
		return result;
	}
}
