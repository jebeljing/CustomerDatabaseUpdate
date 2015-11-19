package main;

import java.util.HashMap;
import java.util.Map;

import model.MasterABEntry;
import util.TxtFileReader;

public class Main {

	private static final String essenceFile = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Spire HH Essence Scores 20150911.TXT";
	private static final String syndicatedSegmentFile = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Marsh_SyndicatedSegment_Extract_20150828.txt";
	private static final String marshDemoFile = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Marsh_Demo_Extract_20150828.txt";
	private static final String marshHHsPSFile = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Marsh HHs PS Segmentation Assignments 20150724.txt";
	
	public static void main(String[] args) throws Exception {

		TxtFileReader txtFileReader = new TxtFileReader(essenceFile);
		Map<String, MasterABEntry> updateMaps = new HashMap<String, MasterABEntry>();
		
		txtFileReader.readSpireHHEssenceScores(updateMaps);
		System.out.println(updateMaps.keySet().size());
		
		txtFileReader.setFileName(syndicatedSegmentFile);
		txtFileReader.readMarshSyndicatedSegment(updateMaps);
		System.out.println(updateMaps.keySet().size());
		
		txtFileReader.setFileName(marshDemoFile);
		txtFileReader.readMarshDemo(updateMaps);
		System.out.println(updateMaps.keySet().size());
		System.out.println(updateMaps.get("40005175114"));
		
		txtFileReader.setFileName(marshHHsPSFile);
		txtFileReader.readMarshHHsPS(updateMaps);
		System.out.println(updateMaps.keySet().size());
		System.out.println(updateMaps.get("40005175114"));
	}

}
