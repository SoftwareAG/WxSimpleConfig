package wx.simpleConfig.util;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;
import java.util.Map;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
// --- <<IS-END-IMPORTS>> ---

public final class j

{
	// ---( internal utility methods )---

	final static j _instance = new j();

	static j _newInstance() { return new j(); }

	static j _cast(Object o) { return (j)o; }

	// ---( server methods )---




	public static final void booleanToString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(booleanToString)>> ---
		// @sigtype java 3.5
		// [i] object:0:optional bJava
		// [o] field:0:optional bString
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		boolean bPresent = false;
		String bString = null;
		Object bJava = null;
		if (bPresent = pipelineCursor.first("bJava")) {
			bJava = IDataUtil.get(pipelineCursor, "bJava");
		}
		pipelineCursor.destroy();
		
		// pipeline
		if (bPresent) {
			if (null != bJava)
				if (bJava instanceof java.lang.Boolean)
					bString = bJava.toString();
				else
					throw new ServiceException("Received object is not a java.lang.Boolean!");
			IDataCursor pipelineCursor_1 = pipeline.getCursor();
			IDataUtil.put(pipelineCursor_1, "bString", bString);
			pipelineCursor_1.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void decryptPassword (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decryptPassword)>> ---
		// @sigtype java 3.5
		// [i] object:0:required saltBytes
		// [i] field:0:required secret
		// [i] field:0:required encryptedBase64Password
		// [o] field:0:required password
		// [o] field:0:required bPasswordDecrypted
		// [o] field:0:optional errorMessage
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			byte[]	saltBytes = (byte[]) IDataUtil.get( pipelineCursor, "saltBytes" );
			String	secret = IDataUtil.getString( pipelineCursor, "secret" );
			String	encryptedBase64Password = IDataUtil.getString( pipelineCursor, "encryptedBase64Password" );
		pipelineCursor.destroy();
		
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		
		String password = "NotDecoded!";
		boolean bPasswordDecrypted = false;
		String errorMessage = null;
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secret.toCharArray(), saltBytes, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			password = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedBase64Password)));
			bPasswordDecrypted = true;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			errorMessage = "Unexpected error caught while decrypting a password: (" + e.getClass().getCanonicalName() + " ): " + e.getMessage();
		}
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "password", password );
		IDataUtil.put( pipelineCursor_1, "bPasswordDecrypted", bPasswordDecrypted );
		if(!bPasswordDecrypted)
			IDataUtil.put( pipelineCursor_1, "errorMessage", errorMessage );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getCurrentHostName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getCurrentHostName)>> ---
		// @sigtype java 3.5
		// [o] field:0:required currentHostName
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "currentHostName", crtHostName );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getEncryptedPassword (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getEncryptedPassword)>> ---
		// @sigtype java 3.5
		// [i] object:0:required saltBytes
		// [i] field:0:required password
		// [i] field:0:required secret
		// [o] field:0:required encryptedPasswordBase64
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			byte[]	saltBytes = (byte[]) IDataUtil.get( pipelineCursor, "saltBytes" );
			String	password = IDataUtil.getString( pipelineCursor, "password" );
			String	secret = IDataUtil.getString( pipelineCursor, "secret" );
		pipelineCursor.destroy();
		// Assume validation enbaled -> already done
		
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		
		byte [] encodedBytes = null;
		 
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		    KeySpec spec = new PBEKeySpec(secret.toCharArray(), saltBytes, 65536, 256);
		    SecretKey tmp = factory.generateSecret(spec);
		    SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
		    encodedBytes = cipher.doFinal(password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			throw new ServiceException(e);
		}
		 
		// pipeline
		
		if (null == encodedBytes){
			throw new ServiceException("Unexpected situation: encodedBytes is null");
		} else {
			IDataCursor pipelineCursor_1 = pipeline.getCursor();
			IDataUtil.put( pipelineCursor_1, "encryptedPasswordBase64", Base64.getEncoder().encodeToString(encodedBytes) );
			pipelineCursor_1.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void getLocalSaltAsBytes (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLocalSaltAsBytes)>> ---
		// @sigtype java 3.5
		// [o] object:0:required bLocalSalt
		IDataCursor pipelineCursor = pipeline.getCursor();;
		IDataUtil.put( pipelineCursor, "bLocalSalt", bLocalSalt );
		pipelineCursor.destroy();			
		// --- <<IS-END>> ---

                
	}



	public static final void getLocalSaltAsLong (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLocalSaltAsLong)>> ---
		// @sigtype java 3.5
		// [o] field:0:required lLocalSalt
		// pipeline
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "lLocalSalt", "" + lLocalSalt );
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void getLocalSecret (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLocalSecret)>> ---
		// @sigtype java 3.5
		// [o] field:0:required secret
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "secret", localSecret );
		pipelineCursor.destroy();		
		// --- <<IS-END>> ---

                
	}



	public static final void getNextIdAsHexString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getNextIdAsHexString)>> ---
		// @sigtype java 3.5
		// [o] field:0:required sNextIdHex
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "sNextIdHex",  Long.toHexString(sf.nextId()) );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getNextIdAsLong (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getNextIdAsLong)>> ---
		// @sigtype java 3.5
		// [o] object:0:required lNextId
		// pipeline
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "lNextId", new Long(sf.nextId()) );
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void getNextIdAsShortString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getNextIdAsShortString)>> ---
		// @sigtype java 3.5
		// [o] field:0:required sNextIdShort
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "sNextIdShort", Base64.getEncoder().encodeToString(longToBytes(sf.nextId())) );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getNextIdAsStringConstrainedLong (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getNextIdAsStringConstrainedLong)>> ---
		// @sigtype java 3.5
		// [o] field:0:required sNextId
		// pipeline
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "sNextId", ""+sf.nextId() );
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void getNextIdMultipleForms (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getNextIdMultipleForms)>> ---
		// @sigtype java 3.5
		// [o] field:0:required sNextIdHex
		// [o] object:0:required lNextId
		// [o] field:0:required sNextId
		// [o] field:0:required sNextIdShort
		// pipeline
		
		long l=sf.nextId();
		Long lL = new Long(l);
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "sNextIdHex",  Long.toHexString(l) );
		IDataUtil.put( pipelineCursor, "lNextId", lL );
		IDataUtil.put( pipelineCursor, "sNextId", ""+l );
		IDataUtil.put( pipelineCursor, "sNextIdShort", Base64.getEncoder().encodeToString(longToBytes(l)) );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getOneWayHashedPasswordBytes (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getOneWayHashedPasswordBytes)>> ---
		// @sigtype java 3.5
		// [i] object:0:required saltBytes
		// [i] field:0:required password
		// [o] object:0:required encryptedPasswordBytes
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			byte[]	saltBytes = (byte[]) IDataUtil.get( pipelineCursor, "saltBytes" );
			String	password = IDataUtil.getString( pipelineCursor, "password" );
		pipelineCursor.destroy();
		// Assume validation enbaled -> already done
		
		KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, 2222, 160);
		byte[] encBytes = null;
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			encBytes = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException(e);
		}
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "encryptedPasswordBytes", encBytes );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void objectInspect (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(objectInspect)>> ---
		// @sigtype java 3.5
		// [i] object:0:required objectToInspect
		// [o] field:0:required present
		// [o] field:0:required class
		// [o] field:0:required toString
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		boolean bPresent = pipelineCursor.first("objectToInspect");
		Object	objectToInspect = null;
		if(bPresent) 
			objectToInspect = pipelineCursor.getValue();
		pipelineCursor.destroy();
		
		String className = null==objectToInspect?"null":objectToInspect.getClass().getCanonicalName();
		// pipeline
		pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "present", ""+bPresent );
		IDataUtil.put( pipelineCursor, "class", className );
		if(null != objectToInspect)
			IDataUtil.put( pipelineCursor, "toString", objectToInspect.toString() );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void saltBytesAndLongFromString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(saltBytesAndLongFromString)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional saltString
		// [o] object:0:required saltBytes
		// [o] object:0:required saltLong
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	saltString = IDataUtil.getString( pipelineCursor, "saltString" );
		pipelineCursor.destroy();
		
		long hash = getStringHash(enhanceSaltString(saltString));
		
		// pipeline
		pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "saltBytes", LongToBytes(hash) );
		IDataUtil.put( pipelineCursor, "saltLong", new Long(hash) );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void stringToInteger (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stringToInteger)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional integerString
		// [o] object:0:optional integerObject
		String	integerString = null;
		boolean present = false;
		Integer iObj = null;
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		present=pipelineCursor.first("integerString");
		if (present)
		{
			integerString = (String) pipelineCursor.getValue();
			if (null != integerString)
				iObj = Integer.parseInt(integerString);
		}
		pipelineCursor.destroy();
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "integerObject", iObj );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	// local secret management
	static final String localSecret = getLocalSecret();
	static final String getLocalSecret(){
		String sSecret = "manage";
		if(System.getenv().containsKey("WxSimpleConfigSecret") ){
			System.out.println("Found WxSimpleConfigSecret system property");
			sSecret = System.getenv("WxSimpleConfigSecret");
		}else{
			System.out.println("WxSimpleConfigSecret system property not present, using default!");
		}
		return sSecret;
	}
	
	// local salt management
	static final long lLocalSalt = getLLocalSalt();
	static final long getLLocalSalt(){
		String sSalt = null;
		if(System.getenv().containsKey("WxSimpleConfigSalt") ){
			System.out.println("Found WxSimpleConfigSalt system property");
			sSalt = System.getenv("WxSimpleConfigSalt");
		}else{
			System.out.println("WxSimpleConfigSalt system property not present, using default!");
		}
		return getStringHash(enhanceSaltString(sSalt));
	}
	static final byte[] bLocalSalt = getBLocalSalt();
	static final byte[] getBLocalSalt(){
		return LongToBytes(lLocalSalt);
	}
	static final String enhanceSaltString(String originalSaltString){
		String saltString = originalSaltString;
		if(null==saltString || 0==saltString.length())
			saltString = "HimalayaRose";
		if(saltString.length()<10)
			saltString += "HimalayaRose";
		return saltString;
	}
	// local utilities
	static final long getStringHash(String s){
		long hash = 873982308377L;
		for (int i = 0; i < s.length(); i++) {
		    hash = hash*31^i + s.charAt(i);
		}
		return hash;
	}
	static final byte [] LongToBytes (long l){
		java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(l);
		return buffer.array();
	}
	
	
	public static byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(x);
		return buffer.array();
	}
	
	private static final SagSnowFlake sf = new SagSnowFlake();
	
	/**
	 * @author Software AG
	 *
	 */
	public static class SagSnowFlake {
		private static final int TOTAL_BITS = 64;
		private static final int EPOCH_BITS = 42;
		private static final int NODE_ID_BITS = 10;
		private static final int SEQUENCE_BITS = 12;
		private static final String HOSTNAME = "HOSTNAME";
		private final int maxNodeId = (int) (Math.pow(2, NODE_ID_BITS) - 1);
		private final int maxSequence = (int) (Math.pow(2, SEQUENCE_BITS) - 1);
	
		// Custom Epoch (January 1, 2015 Midnight UTC = 2015-01-01T00:00:00Z)
		private static final long CUSTOM_EPOCH = 1420070400000L;
	
		private final int nodeId;
	
		private volatile long lastTimestamp = -1L;
		private volatile long sequence = 0L;
	
		// Let SequenceGenerator generate a nodeId
		public SagSnowFlake() {
			this.nodeId = createNodeId();
		}
	
		/**
		 * @return a long UUID based on the SnowFlake Alogorithm. The class uses the mac
		 *         address + the PID of the process. If the mac is not valid
		 *         (ff:ff:ff:ff:ff:ff) the hostname variable is used as fallback. This
		 *         variable in a docker environment represents the container Id. If
		 *         there is no valid hostname or PID, a random long is used as nodeId
		 */
		public synchronized long nextId() {
			long currentTimestamp = timestamp();
	
			if (currentTimestamp < lastTimestamp) {
				throw new IllegalStateException("Invalid System Clock!");
			}
	
			if (currentTimestamp == lastTimestamp) {
				sequence = (sequence + 1) & maxSequence;
				if (sequence == 0) {
					// Sequence Exhausted, wait till next millisecond.
					currentTimestamp = waitNextMillis(currentTimestamp);
				}
			} else {
				// reset sequence to start with zero for the next millisecond
				sequence = 0;
			}
	
			lastTimestamp = currentTimestamp;
	
			long id = currentTimestamp << (TOTAL_BITS - EPOCH_BITS);
			id |= (nodeId << (TOTAL_BITS - EPOCH_BITS - NODE_ID_BITS));
			id |= sequence;
			return id;
		}
	
		// Get current timestamp in milliseconds, adjust for the custom epoch.
		private long timestamp() {
			return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
		}
	
		// Block and wait till next millisecond
		private long waitNextMillis(long currentTimestamp) {
			while (currentTimestamp == lastTimestamp) {
				currentTimestamp = timestamp();
			}
			return currentTimestamp;
		}
	
		private int createNodeId() {
			int nodeId;
			try {
				StringBuilder sb = new StringBuilder();
				Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
				// The first network interface will be picked as nodeId
				NetworkInterface networkInterface = networkInterfaces.nextElement();
	
				byte[] mac = networkInterface.getHardwareAddress();
				if (mac != null) {
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X", mac[i]));
					}
				}
	
				String nodeIdStr = sb.toString();
				// if the process is running in a docker container then
				// there is the opportunity that the mac address will be ff:ff:ff:ff:ff:ff
				// we observed that the random containerId is passed to the container as
				// $HOSTNAME env variable, if also this variable is absent we finally rely on a
				// random long.
	
				if (nodeIdStr.equals("FFFFFFFFFFFF")) {
					Map<String, String> map = System.getenv();
					if (map.containsKey(HOSTNAME)) {
						nodeIdStr = map.get(HOSTNAME);
					} else {
						throw new Exception();
					}
	
				}
				// to distinguish between different processes running on the same machine
				// we append also the process Id to the mac/hostname string, considering that
				// before java 9 there is no unified method to get the pid in every platform
				// the getPid() may raise an exception, in that case a random number will be
				// used
				// as before.
	
				nodeIdStr = nodeIdStr + "-" + this.getPID();
				nodeId = nodeIdStr.hashCode();
	
			} catch (Exception ex) {
				nodeId = (new SecureRandom().nextInt());
			}
			nodeId = nodeId & maxNodeId;
			return nodeId;
		}
	
		protected long getPID() throws Exception {
			String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
			if (processName != null && processName.length() > 0) {
				return Long.parseLong(processName.split("@")[0]);
			} else {
				throw new Exception();
			}
		}
	}
	
	static private String getCrtHostName(){
		String s = null;
		try {
			s = java.net.InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	static final private String crtHostName=getCrtHostName();
	// --- <<IS-END-SHARED>> ---
}

