package solooo.mycode.ssh;

import com.jcraft.jsch.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Vector;

public class JSSHUtil {	
	static Logger log4j = Logger.getLogger(JSSHUtil.class.getClass());
	
	/** 
	 * 检测连通性
	 */
	public static Session connect(String host, String user, String passwd) throws Exception {
		Session session = null;
		
		JSch jsch = new JSch();
		try {
			session = jsch.getSession(user, host, 22);
							
			session.setPassword(passwd);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");  //设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig(config);
			session.connect(30000);		// 登录超时时间			
			
		} catch (Exception e) {
			throw new Exception(" ssh 未能连通. " + host);
		}
		
		return session;
	}	

	
	/**
	 * 文件传输
	 */
	public static void sshSftp(Session session, String dst, String src) throws Exception{	  
	    Channel channel = null;
	         
	    try {
	        //创建sftp通信通道
	        channel = (Channel) session.openChannel("sftp");
	        channel.connect(1000);
	        ChannelSftp sftp = (ChannelSftp) channel;	         
	         
	        //进入服务器指定的文件夹
	        sftp.cd(dst);	         
	        	         
	        //以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
	        File srcFile = new File(src);
	        long size = srcFile.length();
	        OutputStream outstream = sftp.put(srcFile.getName(), new FileProgressMonitor(size), ChannelSftp.OVERWRITE);
	        InputStream instream = new FileInputStream(srcFile);
	        	        
	        byte b[] = new byte[1024];
	        int n;
	        while ((n = instream.read(b)) != -1) {
	            outstream.write(b, 0, n);
	        }
	        	      	         
	        outstream.flush();
	        outstream.close();
	        instream.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {	     
	        channel.disconnect();
	    }
	}
	public static void sshSftp(Session session, String dst, String srcName, InputStream srcIn) throws Exception{
		   
	    Channel channel = null;
	         
	    try {
	        //创建sftp通信通道
	        channel = (Channel) session.openChannel("sftp");
	        channel.connect(1000);
	        ChannelSftp sftp = (ChannelSftp) channel;	         
	         
	        //进入服务器指定的文件夹
	        sftp.cd(dst);
	         
	        //以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了	       
	        InputStream instream = srcIn;
	        long size = srcIn.available();
	        OutputStream outstream = sftp.put(srcName, new FileProgressMonitor(size), ChannelSftp.OVERWRITE);
	        
	        	        
	        byte b[] = new byte[1024];
	        int n;
	        while ((n = instream.read(b)) != -1) {
	            outstream.write(b, 0, n);
	        }
	        	      	         
	        outstream.flush();
	        outstream.close();
	        instream.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {	       
	        channel.disconnect();	       
	    }
	}
	
	/**
	 * 执行相关的命令
	 * 
	 * @throws JSchException
	 */
	public static String execCmd(Session session, String command) throws Exception {	
	    Channel channel = null;	 	
	    
	    BufferedReader reader = null;		

		try {
			if (command != null) {				
				
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(command);

				channel.setInputStream(null);
				((ChannelExec) channel).setErrStream(System.err);

				channel.connect();
				InputStream in = channel.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
				StringBuffer sb = new StringBuffer();
				String buf = null;
				while ((buf = reader.readLine()) != null) {
					// System.out.println(buf);
					sb.append(buf).append("\n");
				}
				return sb.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();			
		}
		
		return null;
	}	
	
	public static String execCmdExt(Session session, String command) throws Exception {	
	    Channel channel = null;
	 	
	    BufferedReader reader = null;		

		try {
			if (command != null) {				
				
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(command);

				channel.setInputStream(null);
				
				channel.connect();
				InputStream in = channel.getExtInputStream();
				reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
				StringBuffer sb = new StringBuffer();
				String buf = null;
				while ((buf = reader.readLine()) != null) {				
					sb.append(buf).append("\n");
				}
				return sb.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();
		}
		
		return null;
	}
	
	// 文件是否存在
	public static boolean sshDir(Session session, String dst){	   
	    Channel channel = null;
	    try {
	        //创建sftp通信通道
	        channel = (Channel) session.openChannel("sftp");
	        channel.connect(1000);
	        ChannelSftp sftp = (ChannelSftp) channel;	  
	        	        
			Vector content = sftp.ls(dst);
			if (content != null) {
				return true;
			}	        
	    } catch (Exception e) {
	        log4j.info(e.getMessage());
	    } finally {	       
	        channel.disconnect();
	    }
	    
	    return false;
	}
		

			
	
	
	/******************************************************************************************************************************/
	
	
	
	// 设置超时时间
	public static String execCmd(String command,  String user, String passwd, String host, int delay) throws Exception {
		Session session = null;
	    Channel channel = null;
	 	     
	    JSch jsch = new JSch();	  
	    
	    //连接服务器，采用默认端口
        session = jsch.getSession(user, host);	   
	 
	    //如果服务器连接不上，则抛出异常
	    if (session == null) {
	        throw new Exception("session is null");
	    }
	    
	    session.setPassword(passwd);//设置密码   
	    //设置第一次登陆的时候提示，可选值：(ask | yes | no)
	    session.setConfig("StrictHostKeyChecking", "no");
	    //设置登陆超时时间   
	    session.connect(delay);
	    
	    BufferedReader reader = null;		

		try {
			if (command != null) {				
				
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(command);

				channel.setInputStream(null);
				((ChannelExec) channel).setErrStream(System.err);

				channel.connect();
				InputStream in = channel.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
				StringBuffer sb = new StringBuffer();
				String buf = null;
				while ((buf = reader.readLine()) != null) {
					// System.out.println(buf);
					sb.append(buf).append("\n");
				}
				return sb.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();
			session.disconnect();
		}
		
		return null;
	}	
	
	
	
}
