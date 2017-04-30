// Burak Demirci
// 141044091

import java.lang.*;
import java.util.*;
import java.io.*;

public class BigramDyn<T> implements Bigram<T>
{ 
	private int dataType ; 
	private int size=0;
	private Object[] data;//Bilgilerin tutulduğu yapı
	public BigramDyn (int dataType1)
	{
		//Data type atamasi yapildi
		dataType = dataType1;
	}
	public int GetDataType()
	{
		//DataType donduren modul
		return dataType;
	}
	public void setSize(int siz)
	{
		//Array size'ını atama
		size=siz;
	}
	public int size()
	{
		//Array size'ını dondurur
		return size;
	}
	public void readFile(String fileName) throws Exception 
	{
		
		File input = new File(fileName);
		Scanner inpFile = new Scanner (input);
		int siz=0;
		while (inpFile.hasNext()) 
		{
			String temp =inpFile.next();
			siz++;
		}
		//Array size belirlendi 
		setSize(siz);
		//Dosya tekrardan açıldı 
		inpFile.close();
		inpFile = new Scanner (input);
		// DataType integer ise integer okuması

		if(GetDataType()==1)
		{
			int i=0;
			data = new Object[size()];
			
			while (inpFile.hasNextInt()) 
			{
				data[i]= inpFile.nextInt();
				i++;
			}
			if(i!=size())
			{
				throw new Exception(" Wrong Type !");	
			}
		}
		// DataType String ise String okuması	
		if(GetDataType()==2)
		{
			int i=0;
			data = new Object[size()];
			while (inpFile.hasNext()) 
			{
				data[i]= inpFile.next();
				i++;
			}
		}
		// DataType Double ise Double okuması
		if(GetDataType()==3)
		{
			int i=0;
			data = new Object[size()];
			while (inpFile.hasNextDouble()) 
			{
				data[i]= inpFile.nextDouble();
				i++;
			}
			if(i!=size())
			{
				throw new Exception(" Wrong Type !");	
			}	
		}
	}

	public int numGrams()
	{
		return size()-1;
	}

	public int numOfGrams(T first, T second)
	{
		int count =0;
		int i=0;
		while(i < size()-1)
		{
			//Objects te bulunan equals fonksiyonu kullanılarak eşitliklere bakıldı
			if(Objects.equals(data[i],first) && Objects.equals(data[i+1],second))
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
		int []flag= new int[size()-1];
		Objects swap1,swap2;
		for (int t=0; t<size()-1;t++)
		{
			flag[t]=1;	
		}
		//Tekrar eden pairleri bulup tekrar sayısına göre falag atama işlemi 
		while(i<size()-1) 
		{
			for (int j=0; j<size()-1; j++)
			{
				//Objects te bulunan equals fonksiyonu kullanılarak eşitliklere bakıldı
				if(Objects.equals(data[i],data[j]) && Objects.equals(data[i+1],data[j+1]) && i>j)
				{
					flag[i]+=1;
					flag[j]=-1;
				} 		
			}	
			i++;
		}
		//Stringe bilgileri atma
		String temp=null;
		//Maksimum tekrar sırasına gore strınge atama işlemi
		int max;
		i=0;
		while(i<size())
		{
			max=-1;
			for (int j=0; j<size()-1 ;j++)
			{
				if(flag[j]!=-1)
				{
					for (int t=0; t<size()-1; t++)
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
					temp=Objects.toString(data[max]);
					str=str.concat(temp);
					str=str.concat(" , ");
					temp=Objects.toString(data[max+1]);
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