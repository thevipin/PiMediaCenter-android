package mediacenter.pi.vipin.pimediacenter.network.client;

import java.util.ArrayList;

public class DataCodex {
	ArrayList<String> dataKey=new ArrayList<String>();
	ArrayList<String> dataValue=new ArrayList<String>();
	String codex;
	String Identifier;
	public int Socketindex;
	public DataCodex(String identifier)
	{
		Identifier=identifier;
	}
	public DataCodex()
	{
		
	}
	public void setIdentifier(String identity)
    {
        Identifier = identity;
    }
	public void addKeys(String... param)
	{
		for(int i=0;i<param.length;i++)
		{
			dataKey.add(param[i]);
		}
	}
	public ArrayList<String> getKeys()
	{
		return dataKey;
	}
	public void addValues(String... param)
	{
		for(int i=0;i<param.length;i++)
		{
			dataValue.add(param[i]);
		}
	}
	public String getIdentifier()
	{
		return (Identifier);
	}
	public void put(String key,String value)
	{
		dataKey.add(key);
		dataValue.add(value);
	}
	public String get(String key)
	{
		return(dataValue.get(dataKey.indexOf(key)));				
	}
	public String getCodex()
	{
		Encode();
		return codex;
	}
	public void putCodex(String code)
	{
		codex=code;
		dataKey= new ArrayList<String>();
		dataValue=new ArrayList<String>();		
		Decode();
	}
	public ArrayList<String> Keys()
	{
		return dataKey;
	}
	private void Encode()
	{
		StringBuilder code= new StringBuilder("");
		code.append("vipin's codex#"+Identifier+":{");
		for(int i=0;i<dataKey.size();i++)
		{
			code.append("&"+dataKey.get(i)+"="+dataValue.get(i)+";");		
		}
		code.append("}");		
		//Can Write code for Encipher
		codex=code.toString();
	}
	private void Decode()
	{
		StringBuilder code= new StringBuilder(codex);
		int fromIndex=0;
		Identifier=code.substring(fromIndex=code.indexOf("#", fromIndex)+1,fromIndex=code.indexOf(":{", fromIndex));
		int lastIndex=code.lastIndexOf(";");
		while(fromIndex<lastIndex)
		{
			dataKey.add(code.substring(fromIndex=code.indexOf("&", fromIndex)+1, fromIndex=code.indexOf("=", fromIndex)));
			dataValue.add(code.substring(fromIndex+1, fromIndex=code.indexOf(";", fromIndex)));
		}
	}

}
