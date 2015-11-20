package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import model.MasterABEntry;

public class TxtFileReader {
	
	private String fileName;
	private BufferedReader br = null;
	
	public TxtFileReader(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void readSpireHHEssenceScores(Map<String, MasterABEntry> updateMaps) throws IOException {
		readFile();
		String line = null;
		while((line = br.readLine()) != null) {
			String[] split = line.split("\t");
			MasterABEntry entry = new MasterABEntry();
			entry.setCard1(split[0]);
			entry.setEssence(split[1]);
			entry.setAffluence(split[2]);
			entry.setGenderAge(split[4]);
			entry.setEthnicity(split[5]);
			entry.setCookingStyle(split[6]);
			entry.setHealthWellness(split[7]);
			entry.setDietStyle(split[8]);
			entry.setSpecialDietaryNeeds(split[9]);
			
			//TODO need to double check duplicate
			if (updateMaps.get(entry.getCard1()) == null) {
				updateMaps.put(entry.getCard1(), entry);
			} else {
				System.out.println("Duplicate card1 number in SpireHHEssenceScores text file: " + entry.getCard1());
				updateMaps.put(entry.getCard1(), entry);
			}
		}
		br.close();
	}
	
	public void readMarshSyndicatedSegment(Map<String, MasterABEntry> updateMaps) throws IOException {
		readFile();
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.startsWith("card")) {
				String[] split = line.split("\\|");
//				System.out.println(split.length);
				continue;
			}
			String[] split = line.split("\\|");
			if (updateMaps.get(split[0]) == null) {
				MasterABEntry entry = new MasterABEntry();
				entry.setCard1(split[0]);
				entry.setFoodies(split[2]);
				entry.setHealthAndFit(split[3]);
				entry.setNewParents(split[4]);
				entry.setTrendyHomemakers(split[5]);
				entry.setHealthAndWellnessBuyers(split[7]);
				entry.setNaturalWellness(split[8]);
				entry.setWeightLossAndSupplements(split[9]);
				entry.setCatProductBuyers(split[11]);
				entry.setDogProductBuyers(split[12]);
				updateMaps.put(entry.getCard1(), entry);
			} else {
//				System.out.println("Existing: " + split[0]);
				MasterABEntry existEntry = updateMaps.get(split[0]);
				existEntry.setFoodies(split[2]);
				existEntry.setHealthAndFit(split[3]);
				existEntry.setNewParents(split[4]);
				existEntry.setTrendyHomemakers(split[5]);
				existEntry.setHealthAndWellnessBuyers(split[7]);
				existEntry.setNaturalWellness(split[8]);
				existEntry.setWeightLossAndSupplements(split[9]);
				existEntry.setCatProductBuyers(split[11]);
				existEntry.setDogProductBuyers(split[12]);
				updateMaps.put(split[0], existEntry);
			}
		}
		br.close();
	}
	
	public void readMarshDemo(Map<String, MasterABEntry> updateMaps) throws IOException {
		readFile();
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.startsWith("card")) {
				String[] split = line.split("\\|");
//				System.out.println(split.length);
				continue;
			}
			String[] split = line.split("\\|");
//			System.out.println(split.length);
			if (split.length < 1) continue;
			if (updateMaps.get(split[0]) == null) {
				MasterABEntry entry = new MasterABEntry();
				entry.setCard1(split[0]);
				entry.setkAgeRange(split.length > 1 && !split[1].isEmpty() ? split[1] : null);
				entry.setNumberOfAdults(split.length > 2 && !split[2].isEmpty() ? split[2] : null);
				entry.setNumberOfChildren(split.length > 3 && !split[3].isEmpty()  ? split[3] : null);
				entry.setkCPeopleInHH(split.length > 4 && !split[4].isEmpty()  ? split[4] : null);
				entry.setkChildren02(split.length > 6 && !split[6].isEmpty()  ? split[6] : null);
				entry.setkDietConcerns(split.length > 12 && !split[12].isEmpty()  ? split[12] : null);
				entry.setkDietConcernsNatOrganic(split.length > 13 && !split[13].isEmpty()  ? split[13] : null);
				entry.setkDietConcernsWeight(split.length > 15 && !split[15].isEmpty()  ? split[15] : null);
				entry.setkDietLowFatHealthy(split.length > 16 && !split[16].isEmpty()  ? split[16] : null);
				entry.setkEthnicCode(split.length > 18 && !split[18].isEmpty()  ? split[18] : null);
				entry.setkGeneration(split.length > 19 && !split[19].isEmpty()  ? split[19] : null);
				entry.setkHobbyCooking(split.length > 21 && !split[21].isEmpty()  ? split[21] : null);
				entry.setkHobbyGourmet(split.length > 22 && !split[22].isEmpty()  ? split[22] : null);
				entry.setkIncome(split.length > 24 && !split[24].isEmpty()  ? split[24] : null);
				entry.setkHobbyCookingLowFat(split.length > 25 && !split[25].isEmpty()  ? split[25] : null);
				entry.setkHobbyWine(split.length > 26 && !split[26].isEmpty()  ? split[26] : null);
				
				updateMaps.put(entry.getCard1(), entry);
			} else {
				MasterABEntry existEntry = updateMaps.get(split[0]);
				existEntry.setkAgeRange(split.length > 1 && !split[1].isEmpty() ? split[1] : null);
				existEntry.setNumberOfAdults(split.length > 2 && !split[2].isEmpty() ? split[2] : null);
				existEntry.setNumberOfChildren(split.length > 3 && !split[3].isEmpty() ? split[3] : null);
				existEntry.setkCPeopleInHH(split.length > 4 && !split[4].isEmpty() ? split[4] : null);
				existEntry.setkChildren02(split.length > 6 && !split[6].isEmpty() ? split[6] : null);
				existEntry.setkDietConcerns(split.length > 12 && !split[12].isEmpty() ? split[12] : null);
				existEntry.setkDietConcernsNatOrganic(split.length > 13 && !split[13].isEmpty() ? split[13] : null);
				existEntry.setkDietConcernsWeight(split.length > 15 && !split[15].isEmpty() ? split[15] : null);
				existEntry.setkDietLowFatHealthy(split.length > 16 && !split[16].isEmpty() ? split[16] : null);
				existEntry.setkEthnicCode(split.length > 18 && !split[18].isEmpty() ? split[18] : null);
				existEntry.setkGeneration(split.length > 19 && !split[19].isEmpty() ? split[19] : null);
				existEntry.setkHobbyCooking(split.length > 21 && !split[21].isEmpty() ? split[21] : null);
				existEntry.setkHobbyGourmet(split.length > 22 && !split[22].isEmpty() ? split[22] : null);
				existEntry.setkIncome(split.length > 24 && !split[24].isEmpty() ? split[24] : null);
				existEntry.setkHobbyCookingLowFat(split.length > 25 && !split[25].isEmpty() ? split[25] : null);
				existEntry.setkHobbyWine(split.length > 26 && !split[26].isEmpty() ? split[26] : null);
				
				updateMaps.put(split[0], existEntry);
			}
		}
		br.close();
	}
	
	public void readMarshHHsPS(Map<String, MasterABEntry> updateMaps) throws IOException {
		readFile();
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.startsWith("Household")) {
				String[] split = line.split("\\|");
//				System.out.println(split.length);
				continue;
			}
			String tempLine = null;
			String[] split = null;
			if (line.startsWith("-")) {
				tempLine = line.substring(1);
				split = tempLine.split("\\|");
			} else {
				split = line.split("\\|");
			}
//			System.out.println(split.length);
			if (split.length < 1) continue;
			if (updateMaps.get(split[0]) == null) {
				MasterABEntry entry = new MasterABEntry();
				entry.setCard1(split[0]);
				entry.setPriceSensitivity(split[1]);
				
				updateMaps.put(entry.getCard1(), entry);
			} else {
				MasterABEntry existEntry = updateMaps.get(split[0]);
				existEntry.setPriceSensitivity(split[1]);
				
				updateMaps.put(split[0], existEntry);
			}
		}
		br.close();
	}
	
	public void readSpireEngagementSegment(Map<String, MasterABEntry> updateMaps) throws IOException {
		readFile();
		String line = null;
		while((line = br.readLine()) != null) {
			String tempLine = null;
			String[] split = null;
			if (line.startsWith("-")) {
				tempLine = line.substring(1);
				split = tempLine.split("\\|");
			} else {
				split = line.split("\\|");
			}
//			System.out.println(split.length);
			if (split.length < 1) continue;
			if (updateMaps.get(split[0]) == null) {
				MasterABEntry entry = new MasterABEntry();
				entry.setCard1(split[0]);
				entry.setPrimaryStoreNum(split[2]);
				entry.setMarshSegment(split[5]);
				
				updateMaps.put(entry.getCard1(), entry);
			} else {
				MasterABEntry existEntry = updateMaps.get(split[0]);
				existEntry.setPrimaryStoreNum(split[2]);
				existEntry.setMarshSegment(split[5]);
				
				updateMaps.put(split[0], existEntry);
			}
		}
		br.close();
	}
	
	private void readFile() {
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
