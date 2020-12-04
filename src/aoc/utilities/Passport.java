package aoc.utilities;

public class Passport {
	private int birthYear = 0;
	private int issueYear = 0;
	private int expYear = 0;
	private String height = "";
	private String hairColor = "";
	private String eyeColor = "";
	private String passportId = "";
	private String countryId = "";
	
	private static final int MIN_HEIGHT_CM = 150;
	private static final int MAX_HEIGHT_CM = 193;
	private static final int MIN_HEIGHT_IN = 59;
	private static final int MAX_HEIGHT_IN = 76;
	private static final String EYE_COLORS = "amb blu brn gry grn hzl oth";
	
	public Passport() {
		
	}
	
	public int getBirthYear() {
		return birthYear;
	}
	
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	/**
	 * Check if birth year is valid.
	 * @param minYear set to 0 if only checking for existence of field
	 * @param maxYear set to 0 if only checking for existence of field
	 * @return true if birth year is valid
	 */
	public boolean isBirthYearValid(int minYear, int maxYear) {
		if(minYear > 0 && maxYear > 0) {
			if(birthYear >= minYear &&
			   birthYear <= maxYear) {
				return true;
			}
		}
		else if(birthYear > 0) {
			return true;
		}
		
		return false;
	}
	
	public int getIssueYear() {
		return issueYear;
	}
	
	public void setIssueYear(int issueYear) {
		this.issueYear = issueYear;
	}
	
	/**
	 * Check if issue year is valid.
	 * @param minYear set to 0 if only checking for existence of field
	 * @param maxYear set to 0 if only checking for existence of field
	 * @return true if issue year is valid
	 */
	public boolean isIssueYearValid(int minYear, int maxYear) {
		if(minYear > 0 && maxYear > 0) {
			if(issueYear >= minYear &&
			   issueYear <= maxYear) {
				return true;
			}
		}
		else if(issueYear > 0) {
			return true;
		}
		
		return false;
	}
	
	public int getExpYear() {
		return expYear;
	}
	
	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	
	/**
	 * Check if exp year is valid.
	 * @param minYear set to 0 if only checking for existence of field
	 * @param maxYear set to 0 if only checking for existence of field
	 * @return true if exp year is valid
	 */
	public boolean isExpYearValid(int minYear, int maxYear) {
		if(minYear > 0 && maxYear > 0) {
			if(expYear >= minYear &&
			   expYear <= maxYear) {
				return true;
			}
		}
		else if(expYear > 0) {
			return true;
		}
		
		return false;
	}
	
	public String getHeight() {
		return height;
	}
	
	public void setHeight(String height) {
		this.height = height;
	}
	
	/**
	 * Check if height is valid.
	 * @param useLimits set to false if only checking for existence of field
	 * @return true if height is valid
	 */
	public boolean isHeightValid(boolean useLimits) {
		if(!height.isEmpty()) {
			if(useLimits) {
				String type = height.substring(height.length()-2);
				
				String heightNum = height.substring(0,height.length()-2);
				
				if(!heightNum.equals("")) {
					Integer num = Integer.parseInt(height.substring(0,height.length()-2));
					
					switch(type) {
						case "cm":
							if(num >= MIN_HEIGHT_CM &&
							   num <= MAX_HEIGHT_CM) {
								return true;
							}
							break;
						case "in":
							if(num >= MIN_HEIGHT_IN &&
							   num <= MAX_HEIGHT_IN) {
								return true;
							}
							break;
					}
				}
			}
			else {
				return true;
			}
		}
		
		return false;
	}
	
	public String getHairColor() {
		return hairColor;
	}
	
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	
	/**
	 * Check if hair color is valid.
	 * @param useValidation set to false if only checking for existence of field
	 * @return true if hair color is valid
	 */
	public boolean isHairColorValid(boolean useValidation) {
		if(!hairColor.isEmpty()) {
			if(useValidation) {
				if(hairColor.substring(0, 1).equals("#")) {
					if(hairColor.length() == 7) {
						String color = hairColor.substring(1);
						
						if(color.matches("^[a-z0-9]+$")) {
							return true;
						}
					}
				}
			}
			else {
				return true;
			}
		}
		
		return false;
	}
	
	public String getEyeColor() {
		return eyeColor;
	}
	
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	
	/**
	 * Check if eye color is valid.
	 * @param useValidation set to false if only checking for existence of field
	 * @return true if eye color is valid
	 */
	public boolean isEyeColorValid(boolean useValidation) {
		if(!eyeColor.isEmpty()) {
			if(useValidation) {
				if(EYE_COLORS.indexOf(eyeColor) >= 0) {
					return true;
				}
			}
			else {
				return true;
			}
		}
		
		return false;
	}
	
	public String getPassportId() {
		return passportId;
	}
	
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	
	/**
	 * Check if passport id is valid.
	 * @param useValidation set to false if only checking for existence of field
	 * @return true if passport id is valid
	 */
	public boolean isPassportIdValid(boolean useValidation) {
		if(!passportId.isEmpty()) {
			if(useValidation) {
				if(passportId.length() == 9) {
					if(passportId.matches("^[0-9]+$")) {
						return true;
					}
				}
			}
			else {
				return true;
			}
		}
		
		return false;
	}
	
	public String getCountryId() {
		return countryId;
	}
	
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	
	public boolean isValid(int birthYearMin, int birthYearMax, int issueYearMin, int issueYearMax,
			int expYearMin, int expYearMax, boolean useHeightLimits, boolean validateHairColor,
			boolean validateEyeColor, boolean validatePassportId) {
		if(isBirthYearValid(birthYearMin, birthYearMax) &&
		   isIssueYearValid(issueYearMin, issueYearMax) &&
		   isExpYearValid(expYearMin, expYearMax) &&
		   isHeightValid(useHeightLimits) &&
		   isHairColorValid(validateHairColor) &&
		   isEyeColorValid(validateEyeColor) &&
		   isPassportIdValid(validatePassportId)) {
			return true;
		}
		
		return false;
	}
}
