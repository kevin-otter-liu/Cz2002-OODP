package test.Models;
//import test.Mode;
//import test.Gender;

import test.Enums.Gender;

/**
 * Account Abstract class that contains basic account attributes and methods
 * @author kevin
 *
 */
public abstract class Account {
	private Gender gender;
	private String name;
	private String id;
	private String nationality;
	
	
	/**
	 * get gender of Account
	 * @return
	 */
	public Gender getGender() {
		return gender;
	}
	/**
	 * get Name of Account
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * get Id of Account
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * get nationality of Account
	 * @return
	 */
	public String getNationality() {
		return nationality;
	}

	
	/**
	 * set gender of Account
	 * @param gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * set Name of Account
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * set id of Account
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * set nationality of Account
	 * @param nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	

}
