package utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Parse {
	/**
	 * Extract the data in the file path in parameter to a String with this data
	 * @param pathFile
	 * @return String with this data contend in the file target by pathFile
	 * @throws IOException
	 */
	public static List<String> parseFileToString(Path pathFile) throws IOException{
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<>();
		try(Stream<String> lines = Files.lines(pathFile, Charset.forName("ISO-8859-15"))){
			lines.forEach(list::add);
		}
		return list;
	}

	/**
	 * Parse all products data From one String with CSV Format contained this data
	 * @param data
	 * @return HashMap<firstname, isMale>
	 * @throws IOException
	 */
	public static HashMap<String, Boolean> parseFirstname(List<String> data) throws IOException{
		boolean isFirst = true;
		HashMap<String, Boolean> map = new HashMap<>();
		List languages = new ArrayList();
		languages.add("french");
		languages.add("english");
		languages.add("spain");
		languages.add("finnish");
		for(String str : data) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			String[] values = str.split(";",3);
			String language = values[2].split(",")[0];

			if (languages.contains(language))
				map.putIfAbsent(values[0].split(" ")[0], values[1].toLowerCase().split(" ")[0].equals("m"));
		}
		return map;
	}
	/**
	 * Parse all products data From one String with CSV Format contained this data
	 * @param data
	 * @return HashMap<Address>
	 * @throws IOException
	 */
	public static List<Address> parseSampleAddress(List<String> data, List<InseeRef> inseeRefs) throws IOException{

		boolean isFirst = true;

		List<Address> list = new ArrayList<>();

		for(String str : data) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			String[] values = str.split(";",16);
			String addressString = values[2].toLowerCase();
			Integer postCode = Integer.parseInt(values[5]);
			String country = values[7].toLowerCase();
			String city = values[6].toLowerCase();
			InseeRef inseeRef = inseeRefs.stream().filter( p -> p.getPostCode().equals(postCode)).findFirst().orElse(null);
			String insee = "";
			if(inseeRef != null)
				insee = inseeRef.getInsee();
			Address address = new Address(addressString, city, postCode, country, insee);
			list.add(address);
		}
		return list;
	}
	/**
	 * Parse all products data From one String with CSV Format contained this data
	 * @param data
	 * @return HashMap<InseeRef>
	 * @throws IOException
	 */
	public static List<InseeRef> parseInseeRef(List<String> data) throws IOException{

		boolean isFirst = true;
		List<InseeRef> list = new ArrayList<>();
		for(String str : data) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			String[] values = str.split(";",5);
			Integer postCode = Integer.parseInt(values[2]);
			String city = values[1].toLowerCase();
			String insee = values[0];
			list.add(new InseeRef(city, postCode, insee));
		}
		return list;
	}
	/**
	 * Parse all products data From one String with CSV Format contained this data
	 * @param data
	 * @return List<MedicalSpeciality>
	 * @throws IOException
	 */
	public static List<String> parseSampleMedicalSpeciality(List<String> data) throws IOException{

		boolean isFirst = true;
		List<String> list = new ArrayList<>();
		for(String str : data) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			String[] values = str.split(";",5);
			list.add(values[0]);
		}
		return list;
	}
	/**
	 * Parse all products data From one String with CSV Format contained this data
	 * @param data
	 * @return HashMap<Name, Address>
	 * @throws IOException
	 */
	public static HashMap<String, Address> parseHospitalApHp(List<String> data) throws IOException{

		boolean isFirst = true;
		HashMap<String, Address> map = new HashMap<>();
		for(String str : data) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			String[] values = str.split(";",5);
			String name= values[3];
			if(name.toLowerCase().contains("ap-hp")){
				String addressString = values[6].toLowerCase()+" "+values[8]+" "+ values[9];
				Integer postCode = Integer.parseInt(values[14].split(" ")[0]);
				String country = "France";
				String city = values[14].split(" ")[1].toLowerCase();
				Address address = new Address(addressString, city, postCode, country, "");
				map.putIfAbsent(name, address);
			}
		}
		return map;
	}



}

