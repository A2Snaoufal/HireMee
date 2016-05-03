package taxonomy;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Skill")
@XmlType(propOrder = {"name", "value"})
public class Skill {

	String name;
	int value;
	
	
	public Skill(String name,int value) {
		
		this.name=name;
		this.value=value;
	}
	
    public Skill() {
		
	
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}
	


	
	@Override
	public String toString() {
		return "Skill [name=" + name + ", value=" + value + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
