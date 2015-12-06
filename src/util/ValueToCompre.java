package util;

public class ValueToCompre {

	public static String transformKAgeRange(String input) {
		String result = "Unknown";
		switch (input) {
		case "A": result = "16-20 Years"; break;
		case "B": result = "21-25 Years"; break;
		case "C": result = "26-30 Years"; break;
		case "D": result = "31-35 Years"; break;
		case "E": result = "36-40 Years"; break;
		case "F": result = "41-45 Years"; break;
		case "G": result = "46-50 Years"; break;
		case "H": result = "51-55 Years"; break;
		case "I": result = "56-60 Years"; break;
		case "J": result = "61-65 Years"; break;
		case "K": result = "66-70 Years"; break;
		case "L": result = "71-75 Years"; break;
		case "M": result = "Over 75 Years"; break;
		default:
			result = "Unknown";
			break;
		}
		return result;
	}
	
	public static String transformKEthnicCode(String input) {
		String result = "Unknown";
		switch (input) {
		case "A": result = "African American"; break;
		case "B": result = "Hispanic"; break;
		case "C": result = "Asian"; break;
		case "D": result = "European"; break;
		case "E": result = "Native American"; break;
		case "F": result = "Middle Eastern"; break;
		case "G": result = "Other"; break;
		default:
			result = "Unknown";
			break;
		}
		return result;
	}
	
	public static String transformKGeneration(String input) {
		String result = "Unknown";
		switch (input) {
		case "A": result = "Seniors"; break;
		case "B": result = "Leading Boomers"; break;
		case "C": result = "Trailing Boomers"; break;
		case "D": result = "Generation Xers"; break;
		case "E": result = "Millenials"; break;
		default:
			result = "Unknown";
			break;
		}
		return result;
	}
	
	public static String transformKIncome(String input) {
		String result = "Unknown";
		switch (input) {
		case "A": result = "Less than $15000"; break;
		case "B": result = "$15000-$19999"; break;
		case "C": result = "$20000-$29999"; break;
		case "D": result = "$30000-$39999"; break;
		case "E": result = "$40000-$49999"; break;
		case "F": result = "$50000-$59999"; break;
		case "G": result = "$60000-$74999"; break;
		case "H": result = "$75000-$99999"; break;
		case "I": result = "$100000-$124999"; break;
		case "J": result = "$125000-$149999"; break;
		case "K": result = "$150000-$199999"; break;
		case "L": result = "$200000-$249999"; break;
		case "M": result = "$250000-$399999"; break;
		case "N": result = "$400000-$499999"; break;
		case "O": result = "More than $500000"; break;
		default:
			result = "Unknown";
			break;
		}
		return result;
	}
	
	public static String transformEssence(String input) {
		String result = null;
		switch (input) {
		case "1": result = "Finest Fresh Foodie Couples"; break;
		case "2": result = "Premium Healthy-Living Couples"; break;
		case "3": result = "Healty Living Couples"; break;
		case "4": result = "Eating Right Value Couples"; break;
		case "5": result = "Premium Healthy Living Families"; break;
		case "6": result = "Eating Right On-the-Go Families"; break;
		case "7": result = "Eating Right Value Families"; break;
		case "8": result = "Premium On-the_go Couples"; break;
		case "9": result = "Premium On-the-Go Families"; break;
		case "10": result = "Finest Fresh Watching the Waistline Singles/Couples"; break;
		case "11": result = "Premium Watching the Waistline Singles/Couples"; break;
		case "12": result = "On-the-Go Watching the Waistline Singles/Couples"; break;
		case "13": result = "Premium Watching the Waistline Families"; break;
		case "14": result = "On-the-Go Watching the Waistline Families"; break;
		case "15": result = "Finest-Focused Singles/Couples"; break;
		case "16": result = "Finest-Focused Families"; break;
		case "17": result = "Quick-Cooking Singles"; break;
		case "18": result = "Value Quick-Cooking Singles"; break;
		case "19": result = "On-the-Go Quick-Cooking Families"; break;
		case "20": result = "On-the-Go Value Quick-Cooking Families"; break;
		case "21": result = "Sunday Dinner Couples"; break;
		case "22": result = "Value Meat & Potatoes Couples"; break;
		case "23": result = "Value Meat & Potatoes Families"; break;
		case "24": result = "Meat & Potatoes Families"; break;
		case "25": result = "Premium Families with Babies"; break;
		case "26": result = "Young Families with Babies"; break;
		case "27": result = "Value Young Families with Babies"; break;
		case "28": result = "Premium Healthy Kid-Focused Families"; break;
		case "29": result = "Kid-Focused Families"; break;
		case "30": result = "Value Kid-Focused Families"; break;
		case "31": result = "Healthy Living Older Adults"; break;
		case "32": result = "Quick Convenience Older Adults"; break;
		case "33": result = "Value Older Adults"; break;
		case "34": result = "Natural & Organic Couples"; break;
		case "35": result = "Natural & Organic Families"; break;
		case "36": result = "Hispanic Couples"; break;
		case "37": result = "Hispanic Young Families"; break;
		case "38": result = "Hispanic Kid-Focused Families"; break;
		case "39": result = "Kosher Couples"; break;
		case "40": result = "Kosher Families"; break;
		default:
			System.out.println("Undefined essence number" + input);
			result = input;
			break;
		}
		return result;
	}
	
	public static String transformAffluence(String input) {
		String result = null;
		switch (input) {
		case "1": result = "Value"; break;
		case "2": result = "Premium"; break;
		case "3": result = "Mainstream"; break;
		case "4": result = "Super Premium"; break;
		default:
			System.out.println("Undefined Affluence number" + input);
			result = input;
			break;
		}
		return result;
	}
	
	public static String transformBabyKids(String input) {
		String result = null;
		switch (input) {
		case "1": result = "Kid-Focused Families"; break;
		case "2": result = "Small Families with Kids"; break;
		case "3": result = "Med. Families with Kids"; break;
		case "4": result = "No Kids"; break;
		case "5": result = "Families with Babies"; break;
		default:
			System.out.println("Undefined BabyKids number" + input);
			result = input;
			break;
		}
		return result;
	}
	
	public static String transformGenAge(String input) {
		String result = null;
		switch (input) {
		case "1": result = "Not Specified"; break;
		case "2": result = "Older Adults"; break;
		case "3": result = "Couples"; break;
		case "4": result = "F"; break;
		case "9": result = "M"; break;
		default:
			System.out.println("Undefined GenAge number" + input);
			result = input;
			break;
		}
		return result;
	}
	
	public static String transformEthnic(String input) {
		String result = null;
		switch (input) {
		case "1": result = "Hispanic"; break;
		case "2": result = "Asian/Indian"; break;
		case "3": result = "Mex/Tex-Mex"; break;
		case "4": result = "African American"; break;
		case "5": result = "Mex/Tex-Mex"; break;
		case "6": result = "Ethnic Explorers"; break;
		case "7": result = "Italian"; break;
		case "8": result = "African American"; break;
		case "9": result = "Low Ethnic"; break;
		case "10": result = "Kosher"; break;
		default:
			System.out.println("Undefined Ethnic number" + input);
			result = input;
			break;
		}
		return result;
	}
	
	public static String transformCook(String input) {
		String result = null;
		switch (input) {
		case "1": result = "Simple Prepared"; break;
		case "2": result = "Meat Seekers"; break;
		case "3": result = "Fresh Home Meals"; break;
		case "4": result = "Make It Quick"; break;
		case "5": result = "Meat & Potatoes"; break;
		default:
			System.out.println("Undefined Cook number" + input);
			result = input;
			break;
		}
		return result;
	}
	
	public static String transformHW(String input) {
		String result = null;
		switch (input) {
		case "1": result = "Fresh & Healthy"; break;
		case "2": result = "Healthy Families"; break;
		case "3": result = "Mainstream Health"; break;
		case "4": result = "Natural/Organic Whole Health"; break;
		case "5": result = "Dieters"; break;
		case "6": result = "Special Needs"; break;
		case "7": result = "Sweet Tooth"; break;
		case "8": result = "Low Health Concern"; break;
		default:
			System.out.println("Undefined HW number" + input);
			result = input;
			break;
		}
		return result;
	}
	
	public static String transformDieter(String input) {
		String result = null;
		switch (input) {
		case "0": result = "Not Dieter"; break;
		case "1": result = "Reduced Fat"; break;
		case "2": result = "Diet Aids & Supplements"; break;
		case "3": result = "Calorie Counters"; break;
		case "4": result = "Low Carb"; break;
		case "7": result = "Healthy Snackers"; break;
		default:
			System.out.println("Undefined Dieter number" + input);
			result = input;
			break;
		}
		return result;
	}
	
	public static String transformSpNeeds(String input) {
		String result = null;
		switch (input) {
		case "1": result = "Vegetarian"; break;
		case "2": result = "No Special Needs"; break;
		case "3": result = "Diabetic Sugar-Free"; break;
		case "4": result = "Lactose-Intolerant"; break;
		case "5": result = "Soy"; break;
		case "8": result = "Gluten-Free"; break;
		default:
			System.out.println("Undefined SpNeeds number" + input);
			result = input;
			break;
		}
		return result;
	}
	
	public static String transformPets(String input) {
		String result = null;
		switch (input) {
		case "0": result = "No Pets"; break;
		case "1": result = "Cat Only"; break;
		case "2": result = "Dog Only"; break;
		case "3": result = "Dog & Cat"; break;
		case "4": result = "Cat & Other"; break;
		case "5": result = "Dog & Other"; break;
		case "6": result = "Other Only"; break;
		case "7": result = "Dog & Cat & Other"; break;
		default:
			System.out.println("Undefined Pet number" + input);
			result = input;
			break;
		}
		return result;
	}
}
