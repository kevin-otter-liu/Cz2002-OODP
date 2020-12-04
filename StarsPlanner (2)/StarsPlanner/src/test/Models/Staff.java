package test.Models;

import test.Enums.Gender;
/**
 * Entity Class for Staff
 * @author kevin
 *
 */
public class Staff extends Account{
	Staff(Gender gender, String name, String id, String nationality){
				this.setGender(gender);
				this.setName(name);
				this.setId(id);
				this.setNationality(nationality);
			}
}
