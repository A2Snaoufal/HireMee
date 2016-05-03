package taxonomy;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = {"id", "name", "skills"})
public class Family {
	
	int id;
	String name;
	List<Skill> skills;

	public Family(String name,int id) {
		this.name=name;
		this.id=id;
		this.skills=new ArrayList<Skill>();
	}
	public Family() {
		
	}
	
	@XmlAttribute
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	
	@XmlElement(name = "skill")
	public List<Skill> getSkills() {
		return skills;
	}



	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public void addSkill(Skill skill){
		this.skills.add(skill);
	}

	
	@Override
	public String toString() {
		return "SkillFamily [id=" + id + ", name=" + name + ", skills="
				+ skills + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
