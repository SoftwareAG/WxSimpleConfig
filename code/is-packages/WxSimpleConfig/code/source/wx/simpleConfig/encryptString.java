package wx.simpleConfig;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.is.dynamicvariables.DynamicVariablesEncryptor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
// --- <<IS-END-IMPORTS>> ---

public final class encryptString

{
	// ---( internal utility methods )---

	final static encryptString _instance = new encryptString();

	static encryptString _newInstance() { return new encryptString(); }

	static encryptString _cast(Object o) { return (encryptString)o; }

	// ---( server methods )---




	public static final void decrypter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decrypter)>> ---
		// @sigtype java 3.5
		// [i] field:0:required encryptedData
		// [o] field:0:required decryptedData
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	encryptedData = IDataUtil.getString( pipelineCursor, "encryptedData" );
		pipelineCursor.destroy();
		String decryptedData = null;
		  try {
		  decryptedData = DynamicVariablesEncryptor.instance().decryptData(encryptedData);
		  } catch (Exception e) {
		  throw new ServiceException(e);
		  }
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "decryptedData", decryptedData );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void encrypter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(encrypter)>> ---
		// @sigtype java 3.5
		// [i] field:0:required decryptedData
		// [o] field:0:required encryptedData
		String encryptedData = null;
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String decryptedData = IDataUtil.getString( pipelineCursor, "decryptedData" );
		pipelineCursor.destroy();
		
		try {
		      encryptedData = DynamicVariablesEncryptor.instance().encryptData(decryptedData);
		    } catch (Exception e) {
		      throw new ServiceException(e);}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "encryptedData", encryptedData );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}
}

