package utils;
import java.io.IOException;
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
	public static String parseFileToString(Path pathFile) throws IOException{
		StringBuilder sb = new StringBuilder();
		try(Stream<String> lines = Files.lines(pathFile)){
			lines.forEach(x -> sb.append(x+"\n"));
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	/**
	 * Parse all products data From one String with CSV Format contained this data
	 * @param data
	 * @return HashMap<firstname, isMale>
	 * @throws IOException
	 */
	public static HashMap<String, Boolean> parseFirstname(String data) throws IOException{

		boolean isFirst = true;
		data = data.replaceAll("\"", "");
		HashMap<String, Boolean> map = new HashMap<>();
		String[] tokens = data.split("\n");
		for(String str : tokens) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			String[] values = str.split(",",3);
			map.putIfAbsent(values[0], values[1].toLowerCase().equals("m"));
		}
		return map;
	}
	/**
	 * Parse all products data From one String with CSV Format contained this data
	 * @param data
	 * @return HashMap<Address>
	 * @throws IOException
	 */
	public static List<Address> parseSampleAddress(String data, List<InseeRef> inseeRefs) throws IOException{

		boolean isFirst = true;
		data = data.replaceAll("\"", "");
		List<Address> list = new ArrayList<>();
		String[] tokens = data.split("\n");
		for(String str : tokens) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			String[] values = str.split(",",16);
			String addressString = values[3].toLowerCase();
			Integer postCode = Integer.parseInt(values[6]);
			String country = values[8].toLowerCase();
			String city = values[7].toLowerCase();
			InseeRef inseeRef = inseeRefs.stream().filter( p -> p.getPostCode().equals(postCode)).findFirst().orElse(null);
			Integer insee = 99999;
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
	public static List<InseeRef> parseInseeRef(String data) throws IOException{

		boolean isFirst = true;
		data = data.replaceAll("\"", "");
		List<InseeRef> list = new ArrayList<>();
		String[] tokens = data.split("\n");
		for(String str : tokens) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			String[] values = str.split(",",5);
			Integer postCode = Integer.parseInt(values[2]);
			String city = values[1].toLowerCase();
			Integer insee = Integer.parseInt(values[0]);
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
	public static List<String> parseSampleMedicalSpeciality(String data) throws IOException{

		boolean isFirst = true;
		data = data.replaceAll("\"", "");
		List<String> list = new ArrayList<>();
		String[] tokens = data.split("\n");
		for(String str : tokens) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			String[] values = str.split(",",5);
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
	public static HashMap<String, Address> parseHospitalApHp(String data, List<InseeRef> inseeRefs) throws IOException{

		boolean isFirst = true;
		data = data.replaceAll("\"", "");
		HashMap<String, Address> map = new HashMap<>();
		String[] tokens = data.split("\n");
		for(String str : tokens) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			String[] values = str.split(",",5);
			String name= values[3];
			if(name.toLowerCase().contains("ap-hp")){
				String addressString = values[6].toLowerCase()+" "+values[8]+" "+ values[9];
				Integer postCode = Integer.parseInt(values[14].split(" ")[0]);
				String country = "France";
				String city = values[14].split(" ")[1].toLowerCase();
				InseeRef inseeRef = inseeRefs.stream().filter( p -> p.getPostCode().equals(postCode)).findFirst().orElse(null);
				Integer insee = 99999;
				if(inseeRef != null)
					insee = inseeRef.getInsee();
				Address address = new Address(addressString, city, postCode, country, insee);
				map.putIfAbsent(name, address);
			}
		}
		return map;
	}



}

