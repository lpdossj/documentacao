package br.com.estudo.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ManipulacaoJson {

	static String categories_base =  "[{\"id\":\"1\",\"name\":\"Moradia\",\"description\":\"Pagamento de contas da casa\"},"
			+ "{\"id\":\"2\",\"name\":\"Saúde\",  \"description\":\"Plano de saúde e remédios\"},"
			+ "{\"id\":\"3\",\"name\":\"Lazer\",  \"description\":\"Cinema, parques, praias, etc\"},"
			+ "{\"id\":\"4\",\"name\":\"Salário\",\"description\":\"Recebimento de salário\"},"
			+ "{\"id\":\"5\",\"name\":\"Freels\", \"description\":\"Trabalhos como Freelancer\"}]";
	
	static String sql_categories = "INSERT INTO bdfinancas.category(name, description)"
			+ " VALUES ('@v1', '@v2');";
	
	public static void main(String[] args) throws ParseException {
		JSONArray jSONArray;
		//Cria o parse de tratamento
		JSONParser parser = new JSONParser();
		
		jSONArray = (JSONArray) parser.parse(categories_base);

		System.out.println(jSONArray.toString());
		
		for (int i = 0; i < jSONArray.size(); i++) {
			JSONObject obj = (JSONObject) jSONArray.get(i);
			
			System.out.println(insertSqlCategory(obj));
			//obj.forEach((k,v) -> System.out.println(k+": "+v));
		}
	}
	
	private static String insertSqlCategory(JSONObject obj) {
		String name = (String) obj.get("name");
		String description = (String) obj.get("description");
		
		
		return sql_categories.replace("@v1", name).replace("@v2", description);
	}

}
