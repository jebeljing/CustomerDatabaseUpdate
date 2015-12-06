package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.ss.formula.functions.Value;

import model.MarshIndexEntry;
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
			String[] split = line.split("\\|");
			MasterABEntry entry = new MasterABEntry();
			entry.setCard1(split[0]);
			entry.setEssence(ValueToCompre.transformEssence(split[1]));
			entry.setAffluence(ValueToCompre.transformAffluence(split[2]));
			entry.setGenderAge(ValueToCompre.transformGenAge(split[4]));
			entry.setEthnicity(ValueToCompre.transformEthnic(split[5]));
			entry.setCookingStyle(ValueToCompre.transformCook(split[6]));
			entry.setHealthWellness(ValueToCompre.transformHW(split[7]));
			entry.setDietStyle(ValueToCompre.transformDieter(split[8]));
			entry.setSpecialDietaryNeeds(ValueToCompre.transformSpNeeds(split[9]));
			split = null;
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
			split = null;
		}
		br.close();
	}
	
	public void readMarshDemo(Map<String, MasterABEntry> updateMaps) throws IOException {
		readFile();
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.startsWith("card")) {
				continue;
			}
			String[] split = line.split("\\|");
//			System.out.println(split.length);
			if (split.length < 1) continue;
			if (updateMaps.get(split[0]) == null) {
				MasterABEntry entry = new MasterABEntry();
				entry.setCard1(split[0]);
				entry.setkAgeRange(ValueToCompre.transformKAgeRange(split.length > 1 && !split[1].isEmpty() ? split[1] : "Unknown"));
				entry.setNumberOfAdults(split.length > 2 && !split[2].isEmpty() ? split[2] : null);
				entry.setNumberOfChildren(split.length > 3 && !split[3].isEmpty()  ? split[3] : null);
				entry.setkCPeopleInHH(split.length > 4 && !split[4].isEmpty()  ? split[4] : null);
				entry.setkChildren02(split.length > 6 && !split[6].isEmpty()  ? split[6] : null);
				entry.setkDietConcerns(split.length > 12 && !split[12].isEmpty()  ? split[12] : null);
				entry.setkDietConcernsNatOrganic(split.length > 13 && !split[13].isEmpty()  ? split[13] : null);
				entry.setkDietConcernsWeight(split.length > 15 && !split[15].isEmpty()  ? split[15] : null);
				entry.setkDietLowFatHealthy(split.length > 16 && !split[16].isEmpty()  ? split[16] : null);
				entry.setkEthnicCode(ValueToCompre.transformKEthnicCode(split.length > 18 && !split[18].isEmpty()  ? split[18] : "Unknown"));
				entry.setkGeneration(ValueToCompre.transformKGeneration(split.length > 19 && !split[19].isEmpty()  ? split[19] : "Unknown"));
				entry.setkHobbyCooking(split.length > 21 && !split[21].isEmpty()  ? split[21] : null);
				entry.setkHobbyGourmet(split.length > 22 && !split[22].isEmpty()  ? split[22] : null);
				entry.setkIncome(ValueToCompre.transformKIncome(split.length > 24 && !split[24].isEmpty()  ? split[24] : "Unknown"));
				entry.setkHobbyCookingLowFat(split.length > 25 && !split[25].isEmpty()  ? split[25] : null);
				entry.setkHobbyWine(split.length > 26 && !split[26].isEmpty()  ? split[26] : null);
				
				updateMaps.put(entry.getCard1(), entry);
			} else {
				MasterABEntry existEntry = updateMaps.get(split[0]);
				existEntry.setkAgeRange(ValueToCompre.transformKAgeRange(split.length > 1 && !split[1].isEmpty() ? split[1] : "Unknown"));
				existEntry.setNumberOfAdults(split.length > 2 && !split[2].isEmpty() ? split[2] : null);
				existEntry.setNumberOfChildren(split.length > 3 && !split[3].isEmpty() ? split[3] : null);
				existEntry.setkCPeopleInHH(split.length > 4 && !split[4].isEmpty() ? split[4] : null);
				existEntry.setkChildren02(split.length > 6 && !split[6].isEmpty() ? split[6] : null);
				existEntry.setkDietConcerns(split.length > 12 && !split[12].isEmpty() ? split[12] : null);
				existEntry.setkDietConcernsNatOrganic(split.length > 13 && !split[13].isEmpty() ? split[13] : null);
				existEntry.setkDietConcernsWeight(split.length > 15 && !split[15].isEmpty() ? split[15] : null);
				existEntry.setkDietLowFatHealthy(split.length > 16 && !split[16].isEmpty() ? split[16] : null);
				existEntry.setkEthnicCode(ValueToCompre.transformKEthnicCode(split.length > 18 && !split[18].isEmpty() ? split[18] : "Unknown"));
				existEntry.setkGeneration(ValueToCompre.transformKGeneration(split.length > 19 && !split[19].isEmpty() ? split[19] : "Unknown"));
				existEntry.setkHobbyCooking(split.length > 21 && !split[21].isEmpty() ? split[21] : null);
				existEntry.setkHobbyGourmet(split.length > 22 && !split[22].isEmpty() ? split[22] : null);
				existEntry.setkIncome(ValueToCompre.transformKIncome(split.length > 24 && !split[24].isEmpty() ? split[24] : "Unknown"));
				existEntry.setkHobbyCookingLowFat(split.length > 25 && !split[25].isEmpty() ? split[25] : null);
				existEntry.setkHobbyWine(split.length > 26 && !split[26].isEmpty() ? split[26] : null);
				
				updateMaps.put(split[0], existEntry);
			}
			split = null;
		}
		br.close();
	}
	
	public void readMarshHHsPS(Map<String, MasterABEntry> updateMaps) throws IOException {
		readFile();
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.startsWith("Household")) {
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
				entry.setkAgeRange(ValueToCompre.transformKAgeRange(split.length > 1 && !split[1].isEmpty() ? split[1] : "Unknown"));
				entry.setkEthnicCode(ValueToCompre.transformKEthnicCode(split.length > 18 && !split[18].isEmpty()  ? split[18] : "Unknown"));
				entry.setkGeneration(ValueToCompre.transformKGeneration(split.length > 19 && !split[19].isEmpty()  ? split[19] : "Unknown"));
				entry.setkIncome(ValueToCompre.transformKIncome(split.length > 24 && !split[24].isEmpty()  ? split[24] : "Unknown"));
				updateMaps.put(entry.getCard1(), entry);
			} else {
				MasterABEntry existEntry = updateMaps.get(split[0]);
				existEntry.setPriceSensitivity(split[1]);
				
				updateMaps.put(split[0], existEntry);
			}
			split = null;
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
				entry.setkAgeRange(ValueToCompre.transformKAgeRange(split.length > 1 && !split[1].isEmpty() ? split[1] : "Unknown"));
				entry.setkEthnicCode(ValueToCompre.transformKEthnicCode(split.length > 18 && !split[18].isEmpty()  ? split[18] : "Unknown"));
				entry.setkGeneration(ValueToCompre.transformKGeneration(split.length > 19 && !split[19].isEmpty()  ? split[19] : "Unknown"));
				entry.setkIncome(ValueToCompre.transformKIncome(split.length > 24 && !split[24].isEmpty()  ? split[24] : "Unknown"));
				updateMaps.put(entry.getCard1(), entry);
			} else {
				MasterABEntry existEntry = updateMaps.get(split[0]);
				existEntry.setPrimaryStoreNum(split[2]);
				existEntry.setMarshSegment(split[5]);
				
				updateMaps.put(split[0], existEntry);
			}
			split = null;
		}
		br.close();
	}
	
	public void readCustomerDBMatch(Map<String, MasterABEntry> originalCustomerDB) throws IOException {
		readFile();
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.startsWith("HOUSE")) continue;
			String[] split = null;
			split = line.split(",");
			if (split.length < 1) continue;
			
			MasterABEntry entry = new MasterABEntry();
			entry.setHouseHold(split[0]);
			entry.setCard1(split.length > 1 && !split[1].isEmpty() ? split[1] : null);
			entry.setCard2(split.length > 2 && !split[2].isEmpty() ? split[2] : null);
			entry.setCard3(split.length > 3 && !split[3].isEmpty() ? split[3] : null);
			originalCustomerDB.put(entry.getCard1(), entry);
			split = null;
		}
		br.close();
	}
	
	public void readOriginalHouseholdCustomerDB(Map<String, MarshIndexEntry> originalHouseholdCustomerDB) throws IOException {
		readFile();
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.startsWith("HOUSE")) continue;
			String[] split = null;
			split = line.split(",");
//			System.out.println(split.length);
			if (split.length < 1) continue;
			
			MarshIndexEntry entry = new MarshIndexEntry();
			entry.setHousehold(split[0]);
			entry.setCard1(split.length > 1 && !split[1].isEmpty() ? split[1] : null);
			entry.setCard2(split.length > 2 && !split[2].isEmpty() ? split[2] : null);
			entry.setCard3(split.length > 3 && !split[3].isEmpty() ? split[3] : null);
			originalHouseholdCustomerDB.put(entry.getCard1(), entry);
			if (entry.getCard2() != null) {
				originalHouseholdCustomerDB.put(entry.getCard2(), entry);
			}
			if (entry.getCard3() != null) {
				originalHouseholdCustomerDB.put(entry.getCard3(), entry);
			}
			split = null;
		}
		br.close();
	}
	
	public void readOriginalCustomerDB(Map<String, MasterABEntry> originalCustomerDB) throws IOException {
		readFile();
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.startsWith("Household")) continue;
			String[] split = null;
			split = line.split(",");
//			System.out.println(split.length);
			if (split.length < 1) continue;
			
			MasterABEntry entry = new MasterABEntry();
			entry.setHouseHold(split[0]);
			entry.setCard1(split.length > 1 && !split[1].isEmpty() ? split[1] : null);
			entry.setCard2(split.length > 2 && !split[2].isEmpty() ? split[2] : null);
			entry.setCard3(split.length > 3 && !split[3].isEmpty() ? split[3] : null);
			entry.setEssence(split.length > 4 && !split[4].isEmpty() ? split[4] : null);
			entry.setAffluence(split.length > 5 && !split[5].isEmpty() ? split[5] : null);
			entry.setGenderAge(split.length > 6 && !split[6].isEmpty() ? split[6] : null);
			entry.setEthnicity(split.length > 7 && !split[7].isEmpty() ? split[7] : null);
			entry.setCookingStyle(split.length > 8 && !split[8].isEmpty() ? split[8] : null);
			entry.setHealthWellness(split.length > 9 && !split[9].isEmpty() ? split[9] : null);
			entry.setDietStyle(split.length > 10 && !split[10].isEmpty() ? split[10] : null);
			entry.setSpecialDietaryNeeds(split.length > 11 && !split[11].isEmpty() ? split[11] : null);
			entry.setMarshSegment(split.length > 12 && !split[12].isEmpty() ? split[12] : null);
			entry.setDistanceMarsh(split.length > 13 && !split[13].isEmpty() ? split[13] : null);
			entry.setDistanceKroger(split.length > 14 && !split[14].isEmpty() ? split[14] : null);
			entry.setDistanceMerijer(split.length > 15 && !split[15].isEmpty() ? split[15] : null);
			entry.setDistanceWalmart(split.length > 16 && !split[16].isEmpty() ? split[16] : null);
			entry.setDistanceTarget(split.length > 17 && !split[17].isEmpty() ? split[17] : null);
			entry.setDistanceWalgreens(split.length > 18 && !split[18].isEmpty() ? split[18] : null);
			entry.setDistanceCVS(split.length > 19 && !split[19].isEmpty() ? split[19] : null);
			entry.setDistanceWholeFoods(split.length > 20 && !split[20].isEmpty() ? split[20] : null);
			entry.setPriceSensitivity(split.length > 21 && !split[21].isEmpty() ? split[21] : null);
			entry.seteBT(split.length > 22 && !split[22].isEmpty() ? split[22] : null);
			entry.setPrimaryStoreNum(split.length > 23 && !split[23].isEmpty() ? split[23] : null);
			entry.setFuelSignup(split.length > 24 && !split[24].isEmpty() ? split[24] : null);
			entry.setRxCustomer(split.length > 25 && !split[25].isEmpty() ? split[25] : null);
			entry.setDigitalCouponUser(split.length > 26 && !split[26].isEmpty() ? split[26] : null);
			entry.setRegisteredShopper(split.length > 27 && !split[27].isEmpty() ? split[27] : null);
			entry.setFoodies(split.length > 28 && !split[28].isEmpty() ? split[28] : null);
			entry.setHealthAndFit(split.length > 29 && !split[29].isEmpty() ? split[29] : null);
			entry.setNewParents(split.length > 30 && !split[30].isEmpty() ? split[30] : null);
			entry.setTrendyHomemakers(split.length > 31 && !split[31].isEmpty() ? split[31] : null);
			entry.setHealthAndWellnessBuyers(split.length > 32 && !split[32].isEmpty() ? split[32] : null);
			entry.setNaturalWellness(split.length > 33 && !split[33].isEmpty() ? split[33] : null);
			entry.setWeightLossAndSupplements(split.length > 34 && !split[34].isEmpty() ? split[34] : null);
			entry.setCatProductBuyers(split.length > 35 && !split[35].isEmpty() ? split[35] : null);
			entry.setDogProductBuyers(split.length > 36 && !split[36].isEmpty() ? split[36] : null);
			entry.setkAgeRange(split.length > 37 && !split[37].isEmpty() ? split[37] : null);
			entry.setNumberOfAdults(split.length > 38 && !split[38].isEmpty() ? split[38] : null);
			entry.setNumberOfChildren(split.length > 39 && !split[39].isEmpty() ? split[39] : null);
			entry.setkCPeopleInHH(split.length > 40 && !split[40].isEmpty() ? split[40] : null);
			entry.setkChildren02(split.length > 41 && !split[41].isEmpty() ? split[41] : null);
			entry.setkDietConcerns(split.length > 42 && !split[42].isEmpty() ? split[42] : null);
			entry.setkDietConcernsNatOrganic(split.length > 43 && !split[43].isEmpty() ? split[43] : null);
			entry.setkDietConcernsWeight(split.length > 44 && !split[44].isEmpty() ? split[44] : null);
			entry.setkDietLowFatHealthy(split.length > 45 && !split[45].isEmpty() ? split[45] : null);
			entry.setkEthnicCode(split.length > 46 && !split[46].isEmpty() ? split[46] : null);
			entry.setkGeneration(split.length > 47 && !split[47].isEmpty() ? split[47] : null);
			entry.setkHobbyCooking(split.length > 48 && !split[48].isEmpty() ? split[48] : null);
			entry.setkHobbyGourmet(split.length > 49 && !split[49].isEmpty() ? split[49] : null);
			entry.setkIncome(split.length > 52 && !split[50].isEmpty() ? (split[50] + split[51] + split[52]).replace("\"", "") : null);
			entry.setkHobbyCookingLowFat(split.length > 53 && !split[53].isEmpty() ? split[53] : null);
			entry.setkHobbyWine(split.length > 54 && !split[54].isEmpty() ? split[54] : null);
			originalCustomerDB.put(entry.getCard1(), entry);
			split = null;
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
