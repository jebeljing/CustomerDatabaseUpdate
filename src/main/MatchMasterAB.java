package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import model.MarshIndexEntry;
import model.MasterABEntry;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.TxtFileReader;

public class MatchMasterAB {

//	private static final String masterAB = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Master AB.xlsx";
	private static final String masterABCSV = "C:\\Users\\lalala123\\Desktop\\Work\\CustomerDBUpdate\\Master AB updated\\01242016\\Master AB update 9.11.csv";
	private static final String masterABMatchDB = "C:\\Users\\lalala123\\Desktop\\Work\\CustomerDBUpdate\\Master AB updated\\01242016\\master database file 012016.csv";
	
	private static final String resultMasterAB = "C:\\Users\\lalala123\\Desktop\\Work\\CustomerDBUpdate\\Master AB updated\\01242016\\result.csv";
//	private static final String resultMasterAB2 = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\masterABOriginal.csv";
//	private static final String resultMasterAB2 = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\result2222.txt";
	
	public static void writeToCSV (Map<String, MasterABEntry> updatingMap) throws Exception {
		Map<String, MasterABEntry> originalCustomerDB = readOriginalCustomerDB();
		System.out.println("hello:  " + originalCustomerDB.get("40003382997"));
		System.out.println(updatingMap.keySet().size());
		System.out.println(originalCustomerDB.keySet().size());
		
		Map<String, MarshIndexEntry> readHouseholdCustomerMatchDB = readHouseholdCustomerMatchDB();
		System.out.println(readHouseholdCustomerMatchDB.keySet().size());
		Map<String, MasterABEntry> updatingMapNew = new HashMap<String, MasterABEntry>();
		
		for(String card1 : updatingMap.keySet()) {
			if (readHouseholdCustomerMatchDB.keySet().contains(card1)) {
				MarshIndexEntry marshIndexEntry = readHouseholdCustomerMatchDB.get(card1);
				if (card1.equals(marshIndexEntry.getCard1())) {
					MasterABEntry masterABEntry = updatingMap.get(card1);
					masterABEntry.setHouseHold(marshIndexEntry.getHousehold());
					masterABEntry.setCard2(marshIndexEntry.getCard2());
					masterABEntry.setCard3(marshIndexEntry.getCard3());
					updatingMapNew.put(card1, masterABEntry);
				} else if (card1.equals(marshIndexEntry.getCard2())) {
					MasterABEntry masterABEntryB = updatingMap.get(card1);
					masterABEntryB = null;
				} else if (card1.equals(marshIndexEntry.getCard3())) {
					MasterABEntry masterABEntryC = updatingMap.get(card1);
					masterABEntryC = null;
				}
			} else {
				MasterABEntry masterABEntry = updatingMap.get(card1);
				updatingMapNew.put(card1, masterABEntry);
			}
		}
		System.out.println("After match with Marsh: " + updatingMapNew.keySet().size());
		System.out.println(updatingMapNew.get("40002361124"));
		writeFile(updatingMapNew, originalCustomerDB);
//		writeFile(updatingMap, null);
	}
	
	public static void writeFileFirstLine(FileWriter fw) throws Exception{
		fw.write("Household"); fw.write(",");
		fw.write("Card1"); fw.write(",");
		fw.write("Card2"); fw.write(",");
		fw.write("Card3"); fw.write(",");
		fw.write("Essence"); fw.write(",");
		fw.write("Affluence"); fw.write(",");
		fw.write("Gender & Age"); fw.write(",");
		fw.write("Ethnicity"); fw.write(",");
		fw.write("Cooking Style"); fw.write(",");
		fw.write("Health & Wellness"); fw.write(",");
		fw.write("Diet Style"); fw.write(",");
		fw.write("Special Dietary Needs"); fw.write(",");
		fw.write("Marsh Segment"); fw.write(",");
		fw.write("Distance_Marsh"); fw.write(",");
		fw.write("Distance_Kroger"); fw.write(",");
		fw.write("Distance_Meijer"); fw.write(",");
		fw.write("Distance_WalMart"); fw.write(",");
		fw.write("Distance_Target"); fw.write(",");
		fw.write("Distance_Walgreen"); fw.write(",");
		fw.write("Distance_CVS"); fw.write(",");
		fw.write("Distance_WholeFoods"); fw.write(",");
		fw.write("Price Sensitivity"); fw.write(",");
		fw.write("EBT"); fw.write(",");
		fw.write("Primary Store #"); fw.write(",");
		fw.write("Fuel Signup"); fw.write(",");
		fw.write("RX Customer"); fw.write(",");
		fw.write("Digital Coupon User"); fw.write(",");
		fw.write("Registered Shopper"); fw.write(",");
		fw.write("FOODIES"); fw.write(",");
		fw.write("HEALTH_AND_FIT"); fw.write(",");
		fw.write("NEW_PARENTS"); fw.write(",");
		fw.write("TRENDY_HOMEMAKERS"); fw.write(",");
		fw.write("HEALTH_AND_WELLNESS_BUYERS"); fw.write(",");
		fw.write("NATURAL_WELLNESS"); fw.write(",");
		fw.write("WEIGHT_LOSS_AND_SUPPLEMENTS"); fw.write(",");
		fw.write("CAT_PRODUCT_BUYERS"); fw.write(",");
		fw.write("DOG_PRODUCT_BUYERS"); fw.write(",");
		fw.write("k_age_range"); fw.write(",");
		fw.write("NUMBER OF ADULTS"); fw.write(",");
		fw.write("NUMBER OF CHILDREN"); fw.write(",");
		fw.write("K_C_PEOPLE_IN_HH"); fw.write(",");
		fw.write("K_CHILDREN_0_2"); fw.write(",");
		fw.write("K_DIET_CONCERNS"); fw.write(",");
		fw.write("K_DIET_CONCERNS_NAT_ORGANIC"); fw.write(",");
		fw.write("K_DIET_CONCERNS_WEIGHT"); fw.write(",");
		fw.write("K_DIET_LOW_FAT_HEALTHY"); fw.write(",");
		fw.write("K_ETHNIC_CODE"); fw.write(",");
		fw.write("K_GENERATION"); fw.write(",");
		fw.write("K_HOBBY_COOKING"); fw.write(",");
		fw.write("K_HOBBY_GOURMET"); fw.write(",");
		fw.write("K_INCOME"); fw.write(",");
		fw.write("K_HOBBY_COOKING_LOW_FAT"); fw.write(",");
		fw.write("K_HOBBY_WINE"); fw.write("\n");
		
	}
	
	public static void writeFile(Map<String, MasterABEntry> updatingMap, Map<String, MasterABEntry> originalCustomerDB) throws Exception {
		File file = new File(resultMasterAB);
//		File file2 = new File(resultMasterAB2);
//		BufferedWriter bw = null;
		System.out.println("hello:  " + updatingMap.get("40003382997"));
		try {
			if (!file.exists())
					file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
//			FileWriter fw2 = new FileWriter(file2.getAbsoluteFile());
//			BufferedWriter bw = new BufferedWriter(fw, 20000);
			String empty = " ";
			
			writeFileFirstLine(fw);
			System.out.println(updatingMap.keySet().size());
			for (String card1 : updatingMap.keySet()) {
				MasterABEntry tempEntry = null;
				MasterABEntry entry = updatingMap.get(card1);
//				System.out.println(entry);
				if(entry == null || entry.getHouseHold() == null || entry.getHouseHold().trim().isEmpty()) continue;
				if (originalCustomerDB != null && originalCustomerDB.containsKey(card1)) {
					tempEntry = originalCustomerDB.get(card1);
					if ((entry.getMarshSegment() == null || entry.getMarshSegment().trim().isEmpty()) && (tempEntry.getMarshSegment() == null || tempEntry.getMarshSegment().trim().isEmpty())) {
						continue;
					}
					fw.write(tempEntry.getHouseHold() != null && !tempEntry.getHouseHold().isEmpty() ? tempEntry.getHouseHold() : empty); fw.write(",");
				} else {
					if (entry.getMarshSegment() == null || entry.getMarshSegment().trim().isEmpty()) continue;
					fw.write(entry.getHouseHold() != null ? entry.getHouseHold() : empty); fw.write(",");
				}
				fw.write(entry.getCard1() != null ? entry.getCard1() : empty); fw.write(",");
//				fw2.write("card_num: " + entry.getCard1() + "\n");
				if (tempEntry != null) {
					fw.write(tempEntry.getCard2() != null ? tempEntry.getCard2() : empty); fw.write(",");
					fw.write(tempEntry.getCard3() != null ? tempEntry.getCard3() : empty); fw.write(",");
				} else {
					fw.write(entry.getCard2() != null ? entry.getCard2() : empty); fw.write(",");
					fw.write(entry.getCard3() != null ? entry.getCard3() : empty); fw.write(",");
				}
				fw.write(entry.getEssence() != null ? entry.getEssence() : empty); fw.write(",");
				fw.write(entry.getAffluence() != null ? entry.getAffluence() : empty); fw.write(",");
				fw.write(entry.getGenderAge() != null ? entry.getGenderAge() : empty); fw.write(",");
				fw.write(entry.getEthnicity() != null ? entry.getEthnicity() : empty); fw.write(",");
				fw.write(entry.getCookingStyle() != null ? entry.getCookingStyle() : empty); fw.write(",");
				fw.write(entry.getHealthWellness() != null ? entry.getHealthWellness() : empty); fw.write(",");
				fw.write(entry.getDietStyle() != null ? entry.getDietStyle() : empty); fw.write(",");
				fw.write(entry.getSpecialDietaryNeeds() != null ? entry.getSpecialDietaryNeeds() : empty); fw.write(",");
				if (tempEntry != null && (entry.getMarshSegment() == null ||entry.getMarshSegment().trim().isEmpty())) {
					if (entry.getCard1().equals("40003382997")) {
						System.out.println("Entry 1:" + entry);
						System.out.println("TempEntry 1:" + tempEntry);
					}
					fw.write(tempEntry.getMarshSegment() != null ? tempEntry.getMarshSegment() : empty); fw.write(",");
				} else {
					if (entry.getCard1().equals("40003382997")) {
						System.out.println("Entry 2:" + entry);
					}
					fw.write(entry.getMarshSegment() != null ? entry.getMarshSegment() : empty); fw.write(",");
				}
				if (tempEntry != null) {
					fw.write(tempEntry.getDistanceMarsh() != null ? tempEntry.getDistanceMarsh() : empty); fw.write(",");
					fw.write(tempEntry.getDistanceKroger() != null ? tempEntry.getDistanceKroger() : empty); fw.write(",");
					fw.write(tempEntry.getDistanceMerijer() != null ? tempEntry.getDistanceMerijer() : empty); fw.write(",");
					fw.write(tempEntry.getDistanceWalmart() != null ? tempEntry.getDistanceWalmart() : empty); fw.write(",");
					fw.write(tempEntry.getDistanceTarget() != null ? tempEntry.getDistanceTarget() : empty); fw.write(",");
					fw.write(tempEntry.getDistanceWalgreens() != null ? tempEntry.getDistanceWalgreens() : empty); fw.write(",");
					fw.write(tempEntry.getDistanceCVS() != null ? tempEntry.getDistanceCVS() : empty); fw.write(",");
					fw.write(tempEntry.getDistanceWholeFoods() != null ? tempEntry.getDistanceWholeFoods() : empty); fw.write(",");
				} else {
					fw.write(entry.getDistanceMarsh() != null ? entry.getDistanceMarsh() : empty); fw.write(",");
					fw.write(entry.getDistanceKroger() != null ? entry.getDistanceKroger() : empty); fw.write(",");
					fw.write(entry.getDistanceMerijer() != null ? entry.getDistanceMerijer() : empty); fw.write(",");
					fw.write(entry.getDistanceWalmart() != null ? entry.getDistanceWalmart() : empty); fw.write(",");
					fw.write(entry.getDistanceTarget() != null ? entry.getDistanceTarget() : empty); fw.write(",");
					fw.write(entry.getDistanceWalgreens() != null ? entry.getDistanceWalgreens() : empty); fw.write(",");
					fw.write(entry.getDistanceCVS() != null ? entry.getDistanceCVS() : empty); fw.write(",");
					fw.write(entry.getDistanceWholeFoods() != null ? entry.getDistanceWholeFoods() : empty); fw.write(",");
				}
				fw.write(entry.getPriceSensitivity() != null ? entry.getPriceSensitivity() : empty); fw.write(",");
				if (tempEntry != null) {
					fw.write(tempEntry.geteBT() != null ? tempEntry.geteBT() : empty); fw.write(",");
				} else {
					fw.write(entry.geteBT() != null ? entry.geteBT() : empty); fw.write(",");
				}
				fw.write(entry.getPrimaryStoreNum() != null ? entry.getPrimaryStoreNum() : empty); fw.write(",");
				if (tempEntry != null) {
					fw.write(tempEntry.getFuelSignup() != null ? tempEntry.getFuelSignup() : empty); fw.write(",");
					fw.write(tempEntry.getRxCustomer() != null ? tempEntry.getRxCustomer() : empty); fw.write(",");
					fw.write(tempEntry.getDigitalCouponUser() != null ? tempEntry.getDigitalCouponUser() : empty); fw.write(",");
					fw.write(tempEntry.getRegisteredShopper() != null ? tempEntry.getRegisteredShopper() : empty); fw.write(",");
				} else {
					fw.write(entry.getFuelSignup() != null ? entry.getFuelSignup() : empty); fw.write(",");
					fw.write(entry.getRxCustomer() != null ? entry.getRxCustomer() : empty); fw.write(",");
					fw.write(entry.getDigitalCouponUser() != null ? entry.getDigitalCouponUser() : empty); fw.write(",");
					fw.write(entry.getRegisteredShopper() != null ? entry.getRegisteredShopper() : empty); fw.write(",");
				}
				fw.write(entry.getFoodies() != null ? entry.getFoodies() : empty); fw.write(",");
				fw.write(entry.getHealthAndFit() != null ? entry.getHealthAndFit() : empty); fw.write(",");
				fw.write(entry.getNewParents() != null ? entry.getNewParents() : empty); fw.write(",");
				fw.write(entry.getTrendyHomemakers() != null ? entry.getTrendyHomemakers() : empty); fw.write(",");
				fw.write(entry.getHealthAndWellnessBuyers() != null ? entry.getHealthAndWellnessBuyers() : empty); fw.write(",");
				fw.write(entry.getNaturalWellness() != null ? entry.getNaturalWellness() : empty); fw.write(",");
				fw.write(entry.getWeightLossAndSupplements() != null ? entry.getWeightLossAndSupplements() : empty); fw.write(",");
				fw.write(entry.getCatProductBuyers() != null ? entry.getCatProductBuyers() : empty); fw.write(",");
				fw.write(entry.getDogProductBuyers() != null ? entry.getDogProductBuyers() : empty); fw.write(",");
				fw.write(entry.getkAgeRange() != null ? entry.getkAgeRange() : empty); fw.write(",");
				fw.write(entry.getNumberOfAdults() != null ? entry.getNumberOfAdults() : empty); fw.write(",");
				fw.write(entry.getNumberOfChildren() != null ? entry.getNumberOfChildren() : empty); fw.write(",");
				fw.write(entry.getkCPeopleInHH() != null ? entry.getkCPeopleInHH() : empty); fw.write(",");
				fw.write(entry.getkChildren02() != null ? entry.getkChildren02() : empty); fw.write(",");
				fw.write(entry.getkDietConcerns() != null ? entry.getkDietConcerns() : empty); fw.write(",");
				fw.write(entry.getkDietConcernsNatOrganic() != null ? entry.getkDietConcernsNatOrganic() : empty); fw.write(",");
				fw.write(entry.getkDietConcernsWeight() != null ? entry.getkDietConcernsWeight() : empty); fw.write(",");
				fw.write(entry.getkDietLowFatHealthy() != null ? entry.getkDietLowFatHealthy() : empty); fw.write(",");
				fw.write(entry.getkEthnicCode() != null ? entry.getkEthnicCode() : empty); fw.write(",");
				fw.write(entry.getkGeneration() != null ? entry.getkGeneration() : empty); fw.write(",");
				fw.write(entry.getkHobbyCooking() != null ? entry.getkHobbyCooking() : empty); fw.write(",");
				fw.write(entry.getkHobbyGourmet() != null ? entry.getkHobbyGourmet() : empty); fw.write(",");
				fw.write(entry.getkIncome() != null ? entry.getkIncome() : empty); fw.write(",");
				fw.write(entry.getkHobbyCookingLowFat() != null ? entry.getkHobbyCookingLowFat() : empty); fw.write(",");
				fw.write(entry.getkHobbyWine() != null ? entry.getkHobbyWine() : empty);
				fw.write("\n");
			}
//			System.out.println(originalCustomerDB.keySet().size());
//			writeFileFirstLine(fw2);
//			int i = 0;
//			for (String card1 : originalCustomerDB.keySet()) {
//				
//				MasterABEntry entry = originalCustomerDB.get(card1);
//				if (entry.getCard1().equals("40004799377")) {
//					System.out.println(entry);
//				}
//				
//				if (updatingMap.containsKey(card1)) {
//					i++;
//				}
//				fw2.write(entry.getHouseHold() != null && !entry.getHouseHold().isEmpty() ? entry.getHouseHold() : empty); fw2.write(",");
//				fw2.write(entry.getCard1() != null && !entry.getCard1().isEmpty()  ? entry.getCard1() : empty); fw2.write(",");
////				fw2.write("card_num: " + entry.getCard1() + "\n");
//				fw2.write(entry.getCard2() != null && !entry.getCard2().isEmpty()  ? entry.getCard2() : empty); fw2.write(",");
//				fw2.write(entry.getCard3() != null && !entry.getCard3().isEmpty()  ? entry.getCard3() : empty); fw2.write(",");
//				fw2.write(entry.getEssence() != null && !entry.getEssence().isEmpty()  ? entry.getEssence() : empty); fw2.write(",");
//				fw2.write(entry.getAffluence() != null && !entry.getAffluence().isEmpty()  ? entry.getAffluence() : empty); fw2.write(",");
//				fw2.write(entry.getGenderAge() != null && !entry.getGenderAge().isEmpty()  ? entry.getGenderAge() : empty); fw2.write(",");
//				fw2.write(entry.getEthnicity() != null && !entry.getEthnicity().isEmpty()  ? entry.getEthnicity() : empty); fw2.write(",");
//				fw2.write(entry.getCookingStyle() != null && !entry.getCookingStyle().isEmpty()  ? entry.getCookingStyle() : empty); fw2.write(",");
//				fw2.write(entry.getHealthWellness() != null && !entry.getHealthWellness().isEmpty()  ? entry.getHealthWellness() : empty); fw2.write(",");
//				fw2.write(entry.getDietStyle() != null && !entry.getDietStyle().isEmpty()  ? entry.getDietStyle() : empty); fw2.write(",");
//				fw2.write(entry.getSpecialDietaryNeeds() != null && !entry.getSpecialDietaryNeeds().isEmpty()  ? entry.getSpecialDietaryNeeds() : empty); fw2.write(",");
//				fw2.write(entry.getMarshSegment() != null && !entry.getMarshSegment().isEmpty()  ? entry.getMarshSegment() : empty); fw2.write(",");
//				fw2.write(entry.getDistanceMarsh() != null && !entry.getDistanceMarsh().isEmpty()  ? entry.getDistanceMarsh() : empty); fw2.write(",");
//				fw2.write(entry.getDistanceKroger() != null && !entry.getDistanceKroger().isEmpty()  ? entry.getDistanceKroger() : empty); fw2.write(",");
//				fw2.write(entry.getDistanceMerijer() != null && !entry.getDistanceMerijer().isEmpty()  ? entry.getDistanceMerijer() : empty); fw2.write(",");
//				fw2.write(entry.getDistanceWalmart() != null && !entry.getDistanceWalmart().isEmpty()  ? entry.getDistanceWalmart() : empty); fw2.write(",");
//				fw2.write(entry.getDistanceTarget() != null && !entry.getDistanceTarget().isEmpty()  ? entry.getDistanceTarget() : empty); fw2.write(",");
//				fw2.write(entry.getDistanceWalgreens() != null && !entry.getDistanceWalgreens().isEmpty()  ? entry.getDistanceWalgreens() : empty); fw2.write(",");
//				fw2.write(entry.getDistanceCVS() != null && !entry.getDistanceCVS().isEmpty()  ? entry.getDistanceCVS() : empty); fw2.write(",");
//				fw2.write(entry.getDistanceWholeFoods() != null && !entry.getDistanceWholeFoods().isEmpty()  ? entry.getDistanceWholeFoods() : empty); fw2.write(",");
//				fw2.write(entry.getPriceSensitivity() != null && !entry.getPriceSensitivity().isEmpty()  ? entry.getPriceSensitivity() : empty); fw2.write(",");
//				fw2.write(entry.geteBT() != null && !entry.geteBT().isEmpty()  ? entry.geteBT() : empty); fw2.write(",");
//				fw2.write(entry.getPrimaryStoreNum() != null && !entry.getPrimaryStoreNum().isEmpty()  ? entry.getPrimaryStoreNum() : empty); fw2.write(",");
//				fw2.write(entry.getFuelSignup() != null && !entry.getFuelSignup().isEmpty()  ? entry.getFuelSignup() : empty); fw2.write(",");
//				fw2.write(entry.getRxCustomer() != null && !entry.getRxCustomer().isEmpty()  ? entry.getRxCustomer() : empty); fw2.write(",");
//				fw2.write(entry.getDigitalCouponUser() != null && !entry.getDigitalCouponUser().isEmpty()  ? entry.getDigitalCouponUser() : empty); fw2.write(",");
//				fw2.write(entry.getRegisteredShopper() != null && !entry.getRegisteredShopper().isEmpty()  ? entry.getRegisteredShopper() : empty); fw2.write(",");
//				fw2.write(entry.getFoodies() != null && !entry.getFoodies().isEmpty()  ? entry.getFoodies() : empty); fw2.write(",");
//				fw2.write(entry.getHealthAndFit() != null && !entry.getHealthAndFit().isEmpty()  ? entry.getHealthAndFit() : empty); fw2.write(",");
//				fw2.write(entry.getNewParents() != null && !entry.getNewParents().isEmpty()  ? entry.getNewParents() : empty); fw2.write(",");
//				fw2.write(entry.getTrendyHomemakers() != null && !entry.getTrendyHomemakers().isEmpty()  ? entry.getTrendyHomemakers() : empty); fw2.write(",");
//				fw2.write(entry.getHealthAndWellnessBuyers() != null && !entry.getHealthAndWellnessBuyers().isEmpty()  ? entry.getHealthAndWellnessBuyers() : empty); fw2.write(",");
//				fw2.write(entry.getNaturalWellness() != null && !entry.getNaturalWellness().isEmpty()  ? entry.getNaturalWellness() : empty); fw2.write(",");
//				fw2.write(entry.getWeightLossAndSupplements() != null && !entry.getWeightLossAndSupplements().isEmpty()  ? entry.getWeightLossAndSupplements() : empty); fw2.write(",");
//				fw2.write(entry.getCatProductBuyers() != null && !entry.getCatProductBuyers().isEmpty()  ? entry.getCatProductBuyers() : empty); fw2.write(",");
//				fw2.write(entry.getDogProductBuyers() != null && !entry.getDogProductBuyers().isEmpty()  ? entry.getDogProductBuyers() : empty); fw2.write(",");
//				fw2.write(entry.getkAgeRange() != null && !entry.getkAgeRange().isEmpty()  ? entry.getkAgeRange() : empty); fw2.write(",");
//				fw2.write(entry.getNumberOfAdults() != null && !entry.getNumberOfAdults().isEmpty()  ? entry.getNumberOfAdults() : empty); fw2.write(",");
//				fw2.write(entry.getNumberOfChildren() != null && !entry.getNumberOfChildren().isEmpty()  ? entry.getNumberOfChildren() : empty); fw2.write(",");
//				fw2.write(entry.getkCPeopleInHH() != null && !entry.getkCPeopleInHH().isEmpty()  ? entry.getkCPeopleInHH() : empty); fw2.write(",");
//				fw2.write(entry.getkChildren02() != null && !entry.getkChildren02().isEmpty()  ? entry.getkChildren02() : empty); fw2.write(",");
//				fw2.write(entry.getkDietConcerns() != null && !entry.getkDietConcerns().isEmpty()  ? entry.getkDietConcerns() : empty); fw2.write(",");
//				fw2.write(entry.getkDietConcernsNatOrganic() != null && !entry.getkDietConcernsNatOrganic().isEmpty()  ? entry.getkDietConcernsNatOrganic() : empty); fw2.write(",");
//				fw2.write(entry.getkDietConcernsWeight() != null && !entry.getkDietConcernsWeight().isEmpty()  ? entry.getkDietConcernsWeight() : empty); fw2.write(",");
//				fw2.write(entry.getkDietLowFatHealthy() != null && !entry.getkDietLowFatHealthy().isEmpty()  ? entry.getkDietLowFatHealthy() : empty); fw2.write(",");
//				fw2.write(entry.getkEthnicCode() != null && !entry.getkEthnicCode().isEmpty()  ? entry.getkEthnicCode() : empty); fw2.write(",");
//				fw2.write(entry.getkGeneration() != null && !entry.getkGeneration().isEmpty()  ? entry.getkGeneration() : empty); fw2.write(",");
//				fw2.write(entry.getkHobbyCooking() != null && !entry.getkHobbyCooking().isEmpty()  ? entry.getkHobbyCooking() : empty); fw2.write(",");
//				fw2.write(entry.getkHobbyGourmet() != null && !entry.getkHobbyGourmet().isEmpty()  ? entry.getkHobbyGourmet() : empty); fw2.write(",");
//				fw2.write(entry.getkIncome() != null && !entry.getkIncome().isEmpty()  ? entry.getkIncome() : empty); fw2.write(",");
//				fw2.write(entry.getkHobbyCookingLowFat() != null && !entry.getkHobbyCookingLowFat().isEmpty()  ? entry.getkHobbyCookingLowFat() : empty); fw2.write(",");
//				fw2.write(entry.getkHobbyWine() != null && !entry.getkHobbyWine().isEmpty()  ? entry.getkHobbyWine() : empty);
//				fw2.write("\n");
//			}
//			System.out.println("Existing customer in updating map from original: " + i);
//			fw2.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, MasterABEntry> readOriginalCustomerDB() throws Exception {
		TxtFileReader txtFileReader = new TxtFileReader(masterABCSV);
		Map<String, MasterABEntry> originalCustomerDB = new HashMap<String, MasterABEntry>();
		txtFileReader.readOriginalCustomerDB(originalCustomerDB);
		return originalCustomerDB;
	}
	
	public static Map<String, MarshIndexEntry> readHouseholdCustomerMatchDB() throws Exception {
		TxtFileReader txtFileReader = new TxtFileReader(masterABMatchDB);
		Map<String, MarshIndexEntry> householdCustomerOri = new HashMap<String, MarshIndexEntry>();
		txtFileReader.readOriginalHouseholdCustomerDB(householdCustomerOri);
		System.out.println("Ori size:" + householdCustomerOri.keySet().size());
		return householdCustomerOri;
	}
	
//	public static void writeXLSXFile(Map<String, MasterABEntry> updatingMap) throws IOException {
//		InputStream ExcelFileToRead = new FileInputStream(masterAB);
//		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
//		XSSFSheet sheet = wb.getSheetAt(0);
//		System.out.println("LastRowNum = " + sheet.getLastRowNum());
//		System.out.println("PhysicalNumber = " + sheet.getPhysicalNumberOfRows());
//		int lastRowNum = sheet.getLastRowNum();
//		for (int i = 0; i < lastRowNum; i++) {
//			XSSFRow row = sheet.getRow(i);
//			System.out.println(row.getRowNum());
//			if (updatingMap.get(row.getCell(1).getRawValue()) != null) {
//				MasterABEntry entry = updatingMap.get(row.getCell(1).getRawValue());
//				System.out.println(entry.getCard1());
//				if (!row.getCell(4).getRawValue().equals(entry.getEssence())) {
//					row.getCell(4).setCellValue(entry.getEssence());
//				}
//				if (!row.getCell(5).getRawValue().equals(entry.getAffluence())) {
//					row.getCell(5).setCellValue(entry.getAffluence());
//				}
//				if (!row.getCell(6).getRawValue().equals(entry.getGenderAge())) {
//					row.getCell(6).setCellValue(entry.getGenderAge());
//				}
//				if (!row.getCell(7).getRawValue().equals(entry.getEthnicity())) {
//					row.getCell(7).setCellValue(entry.getEthnicity());
//				}
//				if (!row.getCell(8).getRawValue().equals(entry.getCookingStyle())) {
//					row.getCell(8).setCellValue(entry.getCookingStyle());
//				}
//				if (!row.getCell(9).getRawValue().equals(entry.getHealthWellness())) {
//					row.getCell(9).setCellValue(entry.getHealthWellness());
//				}
//				if (!row.getCell(10).getRawValue().equals(entry.getDietStyle())) {
//					row.getCell(10).setCellValue(entry.getDietStyle());
//				}
//				if (!row.getCell(11).getRawValue().equals(entry.getSpecialDietaryNeeds())) {
//					row.getCell(11).setCellValue(entry.getSpecialDietaryNeeds());
//				}
//				if (!row.getCell(12).getRawValue().equals(entry.getMarshSegment())) {
//					row.getCell(12).setCellValue(entry.getMarshSegment());
//				}
//				if (!row.getCell(13).getRawValue().equals(entry.getDistanceMarsh())) {
//					row.getCell(13).setCellValue(entry.getDistanceMarsh());
//				}
//				if (!row.getCell(14).getRawValue().equals(entry.getDistanceKroger())) {
//					row.getCell(14).setCellValue(entry.getDistanceKroger());
//				}
//				if (!row.getCell(15).getRawValue().equals(entry.getDistanceMerijer())) {
//					row.getCell(15).setCellValue(entry.getDistanceMerijer());
//				}
//				if (!row.getCell(16).getRawValue().equals(entry.getDistanceWalmart())) {
//					row.getCell(16).setCellValue(entry.getDistanceWalmart());
//				}
//				if (!row.getCell(17).getRawValue().equals(entry.getDistanceTarget())) {
//					row.getCell(17).setCellValue(entry.getDistanceTarget());
//				}
//				if (!row.getCell(18).getRawValue().equals(entry.getDistanceWalgreens())) {
//					row.getCell(18).setCellValue(entry.getDistanceWalgreens());
//				}
//				if (!row.getCell(19).getRawValue().equals(entry.getDistanceCVS())) {
//					row.getCell(19).setCellValue(entry.getDistanceCVS());
//				}
//				if (!row.getCell(20).getRawValue().equals(entry.getDistanceWholeFoods())) {
//					row.getCell(20).setCellValue(entry.getDistanceWholeFoods());
//				}
//				if (!row.getCell(21).getRawValue().equals(entry.getPriceSensitivity())) {
//					row.getCell(21).setCellValue(entry.getPriceSensitivity());
//				}
//				if (!row.getCell(22).getRawValue().equals(entry.geteBT())) {
//					row.getCell(22).setCellValue(entry.geteBT());
//				}
//				if (!row.getCell(23).getRawValue().equals(entry.getPrimaryStoreNum())) {
//					row.getCell(23).setCellValue(entry.getPrimaryStoreNum());
//				}
//				if (!row.getCell(24).getRawValue().equals(entry.getFuelSignup())) {
//					row.getCell(24).setCellValue(entry.getFuelSignup());
//				}
//				if (!row.getCell(25).getRawValue().equals(entry.getRxCustomer())) {
//					row.getCell(25).setCellValue(entry.getRxCustomer());
//				}
//				if (!row.getCell(26).getRawValue().equals(entry.getDigitalCouponUser())) {
//					row.getCell(26).setCellValue(entry.getDigitalCouponUser());
//				}
//				if (!row.getCell(27).getRawValue().equals(entry.getRegisteredShopper())) {
//					row.getCell(27).setCellValue(entry.getRegisteredShopper());
//				}
//				if (!row.getCell(28).getRawValue().equals(entry.getFoodies())) {
//					row.getCell(28).setCellValue(entry.getFoodies());
//				}
//				if (!row.getCell(29).getRawValue().equals(entry.getHealthAndFit())) {
//					row.getCell(29).setCellValue(entry.getHealthAndFit());
//				}
//				if (!row.getCell(30).getRawValue().equals(entry.getNewParents())) {
//					row.getCell(30).setCellValue(entry.getNewParents());
//				}
//				if (!row.getCell(31).getRawValue().equals(entry.getTrendyHomemakers())) {
//					row.getCell(31).setCellValue(entry.getTrendyHomemakers());
//				}
//				if (!row.getCell(32).getRawValue().equals(entry.getHealthAndWellnessBuyers())) {
//					row.getCell(32).setCellValue(entry.getHealthAndWellnessBuyers());
//				}
//				if (!row.getCell(33).getRawValue().equals(entry.getNaturalWellness())) {
//					row.getCell(33).setCellValue(entry.getNaturalWellness());
//				}
//				if (!row.getCell(34).getRawValue().equals(entry.getWeightLossAndSupplements())) {
//					row.getCell(34).setCellValue(entry.getWeightLossAndSupplements());
//				}
//				if (!row.getCell(35).getRawValue().equals(entry.getCatProductBuyers())) {
//					row.getCell(35).setCellValue(entry.getCatProductBuyers());
//				}
//				if (!row.getCell(36).getRawValue().equals(entry.getDogProductBuyers())) {
//					row.getCell(36).setCellValue(entry.getDogProductBuyers());
//				}
//				if (!row.getCell(37).getRawValue().equals(entry.getkAgeRange())) {
//					row.getCell(37).setCellValue(entry.getkAgeRange());
//				}
//				if (!row.getCell(38).getRawValue().equals(entry.getNumberOfAdults())) {
//					row.getCell(38).setCellValue(entry.getNumberOfAdults());
//				}
//				if (!row.getCell(39).getRawValue().equals(entry.getNumberOfChildren())) {
//					row.getCell(39).setCellValue(entry.getNumberOfChildren());
//				}
//				if (!row.getCell(40).getRawValue().equals(entry.getkCPeopleInHH())) {
//					row.getCell(40).setCellValue(entry.getkCPeopleInHH());
//				}
//				if (!row.getCell(41).getRawValue().equals(entry.getkChildren02())) {
//					row.getCell(41).setCellValue(entry.getkChildren02());
//				}
//				if (!row.getCell(42).getRawValue().equals(entry.getkDietConcerns())) {
//					row.getCell(42).setCellValue(entry.getkDietConcerns());
//				}
//				if (!row.getCell(43).getRawValue().equals(entry.getkDietConcernsNatOrganic())) {
//					row.getCell(43).setCellValue(entry.getkDietConcernsNatOrganic());
//				}
//				if (!row.getCell(44).getRawValue().equals(entry.getkDietConcernsWeight())) {
//					row.getCell(44).setCellValue(entry.getkDietConcernsWeight());
//				}
//				if (!row.getCell(45).getRawValue().equals(entry.getkDietLowFatHealthy())) {
//					row.getCell(45).setCellValue(entry.getkDietLowFatHealthy());
//				}
//				if (!row.getCell(46).getRawValue().equals(entry.getkEthnicCode())) {
//					row.getCell(46).setCellValue(entry.getkEthnicCode());
//				}
//				if (!row.getCell(47).getRawValue().equals(entry.getkGeneration())) {
//					row.getCell(47).setCellValue(entry.getkGeneration());
//				}
//				if (!row.getCell(48).getRawValue().equals(entry.getkHobbyCooking())) {
//					row.getCell(48).setCellValue(entry.getkHobbyCooking());
//				}
//				if (!row.getCell(49).getRawValue().equals(entry.getkHobbyGourmet())) {
//					row.getCell(49).setCellValue(entry.getkHobbyGourmet());
//				}
//				if (!row.getCell(50).getRawValue().equals(entry.getkIncome())) {
//					row.getCell(50).setCellValue(entry.getkIncome());
//				}
//				if (!row.getCell(51).getRawValue().equals(entry.getkHobbyCookingLowFat())) {
//					row.getCell(51).setCellValue(entry.getkHobbyCookingLowFat());
//				}
//				if (!row.getCell(52).getRawValue().equals(entry.getkHobbyWine())) {
//					row.getCell(52).setCellValue(entry.getkHobbyWine());
//				}
//				updatingMap.remove(entry.getCard1());
//			}
//			row = null;
//		}
//		System.out.println("After updating the existing customers. " + updatingMap.keySet().size() + " left to be added.");
//		
//		FileOutputStream fileOut = new FileOutputStream(masterAB);
//
//		//write this workbook to an Outputstream.
//		wb.write(fileOut);
//		fileOut.flush();
//		fileOut.close();
//	}
}
