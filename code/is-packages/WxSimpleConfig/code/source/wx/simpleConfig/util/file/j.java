package wx.simpleConfig.util.file;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.PackageManager;
import com.wm.app.b2b.server.cluster.CMException;
import com.wm.app.b2b.server.cluster.ClusterManager;
import com.wm.util.EncUtil;
import com.wm.app.log.impl.sc.LevelTranslator;
import com.wm.lang.flow.IDataWmPathProcessor;
import com.wm.util.JournalLogger;
import com.entrust.toolkit.util.ByteArray;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.lang.reflect.*;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.*;
// --- <<IS-END-IMPORTS>> ---

public final class j

{
	// ---( internal utility methods )---

	final static j _instance = new j();

	static j _newInstance() { return new j(); }

	static j _cast(Object o) { return (j)o; }

	// ---( server methods )---




	public static final void fileExists (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(fileExists)>> ---
		// @sigtype java 3.5
		// [i] field:0:required fullPathFileName
		// [o] field:0:required bExists
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	fullPathFileName = IDataUtil.getString( pipelineCursor, "fullPathFileName" );
		pipelineCursor.destroy();
		
		boolean bExists = false;
		
		if(null != fullPathFileName && fullPathFileName.length()>0){
		    File f = new File(fullPathFileName); 
		    bExists = f.exists();
		}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "bExists", ""+bExists );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void readFileAsBytes (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(readFileAsBytes)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [o] object:0:required fileBytes
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	filename = IDataUtil.getString( pipelineCursor, "filename" );
		pipelineCursor.destroy();
		
		byte[] array = null;
		try {
			array = Files.readAllBytes(Paths.get(filename));
		} catch (IOException e) {
			throw new ServiceException(e);
		}			
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "fileBytes", array );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	public static final String DATE_FULL_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS zzz";
	
	private static boolean matchAny(String value, String[] values, String operator, String columnType, String pattern) throws ParseException
	{
	  if (value == null || values == null)
	  {
	    return false;
	  }
	  String dataType = columnType;
	  if (columnType == null)
	  {
	    dataType = "String";
	  }
	  for (int i = 0; i < values.length; i++)
	  {
	    if (operator.equals("equals"))
	    {
	      if (value.equals(values[i]))
	      {
	        return true;
	      }
	    }
	    else if (operator.equals("equalsIgnoreCase"))
	    {
	      if (value.equalsIgnoreCase(values[i]))
	      {
	        return true;
	      }
	    }
	    else
	    {
	      if (dataType.equalsIgnoreCase("Numeric"))
	      {
	        return compareNumerics(value, values[i], operator);
	      }
	      else if (dataType.equalsIgnoreCase("Date"))
	      {
	        return compareDates(value, values[i], operator, pattern);
	      }
	      else
	      {
	        return compareStrings(value, values[i], operator);
	      }
	    }
	  }
	  return false;
	}
		
	private static boolean compareNumerics(String value1, String value2, String operator)
	{
	  if (value1 == null || value2 == null)
	  {
	    return false;
	  }
	  double doubleValue1;
	  double doubleValue2;
	  try
	  {
	    doubleValue1 = Double.parseDouble(value1);
	    doubleValue2 = Double.parseDouble(value2);
	  }
	  catch (NumberFormatException nfe)
	  {
	    return false;
	  }
	  if ("=".equals(operator))
	  {
	    return doubleValue1 == doubleValue2;
	  }
	  else if (">".equals(operator))
	  {
	    return doubleValue1 > doubleValue2;
	  }
	  else if ("<".equals(operator))
	  {
	    return doubleValue1 < doubleValue2;
	  }
	  if (">=".equals(operator))
	  {
	    return doubleValue1 >= doubleValue2;
	  }
	  if ("<=".equals(operator))
	  {
	    return doubleValue1 <= doubleValue2;
	  }
	  return false;
	}
	
	private static boolean compareStrings(String value1, String value2, String operator)
	{
	  if (value1 == null || value2 == null)
	  {
	    return false;
	  }
	  if ("=".equals(operator) || "equals".equals(operator))
	  {
	    return value1.equals(value2);
	  }
	  else if ("equalsIgnoreCase".equals(operator))
	  {
	    return value1.equalsIgnoreCase(value2);
	  }
	  else if (">".equals(operator))
	  {
	    return value1.compareTo(value2) > 0;
	  }
	  else if ("<".equals(operator))
	  {
	    return value1.compareTo(value2) < 0;
	  }
	  else if (">=".equals(operator))
	  {
	    return value1.compareTo(value2) >= 0;
	  }
	  if ("<=".equals(operator))
	  {
	    return value1.compareTo(value2) <= 0;
	  }
	  return false;
	}
	
	private static boolean compareDates(String value1, String value2, String operator, String pattern) throws ParseException
	{
	  if (value1 == null || value2 == null)
	  {
	    return false;
	  }
	  String datePattern = pattern;
	  if (pattern == null)
	  {
	    datePattern = "MM/dd/yyyy hh:mm:ss a";
	  }
	  long time1;
	  long time2;
	  DateFormat dateFormat = new SimpleDateFormat(datePattern);
	  Date date1 = dateFormat.parse(value1);
	  Date date2 = dateFormat.parse(value2);
	  time1 = date1.getTime();
	  time2 = date2.getTime();
	
	  if ("=".equals(operator))
	  {
	    return time1 == time2;
	  }
	  else if (">".equals(operator))
	  {
	    return time1 > time2;
	  }
	  else if ("<".equals(operator))
	  {
	    return time1 < time2;
	  }
	  else if (">=".equals(operator))
	  {
	    return time1 >= time2;
	  }
	  else if ("<=".equals(operator))
	  {
	    return time1 <= time2;
	  }
	  return false;
	}
	
	private static int[] getMaxMinValuesIndices(IData[] data, String columnName, String columnType, String pattern, String comparisonOperator) throws ParseException
	{
	  String selectedValue = null;
	  String operator = ">";
	  ArrayList selectedIndicesList = new ArrayList();
	  if ("min".equalsIgnoreCase(comparisonOperator))
	  {
	    operator = "<";
	  }
	  for (int i = 0; i < data.length; i++)
	  {
	    IDataCursor cursor = data[i].getCursor();
	    String value = IDataUtil.getString(cursor, columnName);
	    if (selectedValue == null)
	    {
	      selectedValue = value;
	      selectedIndicesList.add(new Integer(i));
	      continue;
	    }
	
	    if ("Numeric".equalsIgnoreCase(columnType))
	    {
	      if (compareNumerics(value, selectedValue, operator))
	      {
	        selectedIndicesList.clear();
	        selectedIndicesList.add(new Integer(i));
	        selectedValue = value;
	      }
	      else if (compareNumerics(value, selectedValue, "="))
	      {
	        selectedIndicesList.add(new Integer(i));
	        selectedValue = value;
	      }
	    }
	    else if ("Date".equalsIgnoreCase(columnType))
	    {
	      if (compareDates(value, selectedValue, operator, pattern))
	      {
	        selectedIndicesList.clear();
	        selectedIndicesList.add(new Integer(i));
	        selectedValue = value;
	      }
	      else if (compareDates(value, selectedValue, "=", pattern))
	      {
	        selectedIndicesList.add(new Integer(i));
	        selectedValue = value;
	      }
	    }
	    else
	    {
	      if (compareStrings(value, selectedValue, operator))
	      {
	        selectedIndicesList.clear();
	        selectedIndicesList.add(new Integer(i));
	        selectedValue = value;
	      }
	      else if (compareStrings(value, selectedValue, "="))
	      {
	        selectedIndicesList.add(new Integer(i));
	        selectedValue = value;
	      }
	    }
	    cursor.destroy();
	  }
	
	  int[] selectedIndices = new int[selectedIndicesList.size()];
	  for (int i = 0; i < selectedIndices.length; i++)
	  {
	    selectedIndices[i] = ((Integer) selectedIndicesList.get(i)).intValue();
	  }
	  return selectedIndices;
	}
	
	private static String[] getColumnInfo(IData[] columnTypes, String columnName)
	{
	  for (int i = 0; columnTypes != null && i < columnTypes.length; i++)
	  {
	    IDataCursor cursor = columnTypes[i].getCursor();
	    String name = IDataUtil.getString(cursor, "name");
	    if (name.equals(columnName))
	    {
	      String columnType = IDataUtil.getString(cursor, "type");
	      String pattern = IDataUtil.getString(cursor, "pattern");
	      cursor.destroy();
	      return new String[] { columnType, pattern };
	    }
	    cursor.destroy();
	  }
	  return new String[] { null, null };
	}
	// --- <<IS-END-SHARED>> ---
}

