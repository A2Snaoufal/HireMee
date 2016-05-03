package taxonomy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Taxonomy")
public class Taxonomy {

	private static final String FILE_NAME = "taxonomy.xml";

	List<Family> Family = new ArrayList<Family>();

	public Taxonomy() {
		super();
		
	}

	public List<Family> getFamily() {
		return Family;
	}

	public void setFamily(List<Family> Family) {
		this.Family = Family;
	}

	public void add(Family family) {
		Family.add(family);
	}
	
	public static Taxonomy XMLToObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(Taxonomy.class);
            Unmarshaller un = context.createUnmarshaller();
            Taxonomy taxonomy = (Taxonomy) un.unmarshal(new File(FILE_NAME));
            return taxonomy;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
 

	public static void ObjectToXML(Taxonomy taxonomy) {

		try {
			JAXBContext context = JAXBContext.newInstance(Taxonomy.class);
			Marshaller m = context.createMarshaller();
			// for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out for debugging
		    m.marshal(taxonomy, System.out);

			// Write to File
			m.marshal(taxonomy, new File(FILE_NAME));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		List<Family> skills = new ArrayList<Family>();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		String name = null;
		String cmd = null;
		Family Family = null;
		Taxonomy taxonomy = XMLToObject();
		int id = 0;
		int acc = 0;
		try {
			System.out.print("--- New Family : --- \n ");
			System.out.print("Please enter a skill family id : ");
			id = Integer.parseInt(bReader.readLine());
			System.out.print("Please enter a skill family : ");
			name = bReader.readLine();
			Family = new Family(name, id);

			while (true) {

				System.out
						.println("-- Commands: q (generate & quit) n (new family) a (add skill to current family) s (save current family): ");
				cmd = bReader.readLine();

				if (cmd.compareTo("n") == 0) {
					System.out.print("Please enter a skill family id : ");
					id = Integer.parseInt(bReader.readLine());
					System.out.print("Please enter a skill family : ");
					name = bReader.readLine();
					Family = new Family(name, id);

				}
				if (cmd.compareTo("a") == 0) {

					System.out.print("Please enter a skill name : ");
					name = bReader.readLine();
					acc++;
					Family.addSkill(new Skill(name, acc));
				}
				if (cmd.compareTo("s") == 0) {
					taxonomy.add(Family);
				}
				if (cmd.compareTo("q") == 0) {
					ObjectToXML(taxonomy);
					break;
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
