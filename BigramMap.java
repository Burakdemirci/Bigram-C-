// Burak Demirci
// 141044091

import java.lang.*;
import java.util.*;
import java.io.*;

public class BigramMap<T> implements Bigram<T>
{
	Map data = new HashMap();
	public int dataType;
	public BigramMap (int dataType1)
	{
		//Data type atamasi yapildi
		dataType = dataType1;
	}
	public int GetDataType()
	{
		//DataType donduren modul
		return dataType;
	}
	public void readFile(String fileName) throws Exception 
	{
		File input = new File(fileName);
		Scanner inpFile = new Scanner (input);
		int size=0;
		Object temp;
		while (inpFile.hasNext()) 
		{
			String temp1 =inpFile.next();
			size++;
		}
		//Dosya tekrardan açıldı 
		inpFile.close();
		inpFile = new Scanner (input);
		if(GetDataType()==1)
		{
			int i=0;			
			while (inpFile.hasNextInt()) 
			{
				temp=inpFile.nextInt();
				data.put(i,temp);
				i++;
			}
			if(i!=size)
			{
				throw new Exception(" Wrong Type !");
			}
		}
		// DataType String ise String okuması	
		if(GetDataType()==2)
		{
			int i=0;
			while (inpFile.hasNext()) 
			{
				temp=inpFile.next();
				data.put(i,temp);
				i++;
			}
		}
		// DataType Double ise Double okuması
		if(GetDataType()==3)
		{
			int i=0;
			while (inpFile.hasNextDouble()) 
			{
				temp= inpFile.nextDouble();
				data.put(i,temp);
				i++;
			}
			if(i!=size)
			{
				throw new Exception(" Wrong Type !"); 	
			}	
		}
	}
	public int numGrams()
	{
		return data.size()-1;
	}
	public int numOfGrams(T first, T second)
	{
		int count =0;
		int i=0;
		while(i < data.size()-1)
		{
			//Objects te bulunan equals fonksiyonu kullanılarak eşitliklere bakıldı
			if(Objects.equals(data.get(i),first) && Objects.equals(data.get(i+1),second))
			{
				count++;
			}
			i++;	
		}
		return count;
	}
	public String toString()
	{
		String str="";
		int i=0;
		int []flag= new int[data.size()-1];
		Objects swap1,swap2;
		for (int t=0; t<data.size()-1;t++)
		{
			flag[t]=1;	
		}
		//Tekrar eden pairleri bulup tekrar sayısına göre falag atama işlemi 
		while(i<data.size()-1) 
		{
			for (int j=0; j<data.size()-1; j++)
			{
				//Objects te bulunan equals fonksiyonu kullanılarak eşitliklere bakıldı
				if(Objects.equals(data.get(i),data.get(j)) && Objects.equals(data.get(i+1),data.get(j+1)) && i>j)
				{
					flag[i]+=1;
					flag[j]=-1;
				} 		
			}	
			i++;
		}
		i=0;
		int max;
		String temp=null;
		//Maksimum tekrar sırasına gore strınge atama işlemi
		while(i<data.size())
		{
			max=-1;
			for (int j=0; j<data.size()-1 ;j++)
			{
				if(flag[j]!=-1)
				{
					for (int t=0; t<data.size()-1; t++)
				 	{
						if(flag[j]<=flag[t] && flag[t]>=max && flag[t]!=-1)
						{
							max=t;
						}	
					}
				}	
			}
			if(max!=-1)
			{	
				if(flag[max]!=-1)
				{
					//Stringin içerisine atma işlemi 
					str=str.concat("[");
					temp=Objects.toString(data.get(max));
					str=str.concat(temp);
					str=str.concat(" , ");
					temp=Objects.toString(data.get(max+1));
					str=str.concat(temp);
					str=str.concat("] => ");
					temp=Integer.toString(flag[max]);
					str=str.concat(temp);
					str=str.concat("\n");
					flag[max]=-1;
				}
			}	
			i++;
		}	
		return str;
	} 
}