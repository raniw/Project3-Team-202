/**
 * <p>
 * This class takes Strings of Roman Numerals and converts them to integers and
 * vice versa.
 * </p>
 * 
 * <p>
 * For simplicity's sake, the methods given only use the 'I' and 'V' numerals,
 * for it is highly unlikely more than 8 films are released with the same year
 * and title.
 * </p>
 * 
 * <p>
 * The methods are static for they do not utilize a constructor.
 * </p>
 * 
 * 
 * @version 1.0
 *
 */
public class RomanNumerals {

	public RomanNumerals() {
		// not utilized, but declared for consistency
	}

	/**
	 * This method returns an int value of a given String containing a roman
	 * numeral.
	 * 
	 * @param numeral
	 *            - a String of a specific Roman Numeral
	 * @return an int value of the given String Roman Numeral
	 */
	public static int romanNumToInt(String numeral) {

		int number = 0;
		char[] allNums = numeral.toCharArray();
		for (int i = 0; i < allNums.length; ++i) {
			if (allNums[i] == 'I') {
				++number;
			}
			if (allNums[i] == 'V') {
				number = 5;
			}
			if (numeral.equals("IV")) {
				number = 4;
			}

		}

		return number;

	}

	/**
	 * <p>
	 * This Method changes an int number into Roman numerals.
	 * </p>
	 * 
	 * <p>
	 * For simplicity's sake, the method only go up to 8 in the Roman Numerals.
	 * </p>
	 * 
	 * @param num
	 *            - a value to be converted to Roman Numerals.
	 * @return a String of Roman Numerals.
	 */
	public static String intToRomanNum(int num) {
		String romanNum = "";
		if (num <= 3) {
			for (int i = 0; i < num; ++i) {
				romanNum = romanNum.concat("I");
			}
			return romanNum;
		}
		if (num == 4) {
			romanNum = "IV";
			return romanNum;
		}
		if (num >= 5 && num < 9) {
			romanNum = "V";
			for (int i = 5; i < num; ++i) {
				romanNum = romanNum.concat("I");
			}
			return romanNum;
		}
		return romanNum;
	}
}