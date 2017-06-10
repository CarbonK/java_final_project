
import java.security.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.JFileChooser; 
import javax.swing.JLabel; 
import javax.swing.JPanel;
public class Encrypto extends JFrame implements WindowStateListener, ActionListener{
	JLabel encryPathShow=new JLabel("path");
	JLabel userinfo2=new JLabel("Decrypto");
	JLabel userinfo1=new JLabel("Encrypto");
	JTextField encryPasswordInput=new JTextField();
	JTextField decryPasswordInput=new JTextField();
	JLabel Inputpassword1=new JLabel();
	JLabel Inputpassword2=new JLabel();
	JLabel decryPathShow=new JLabel("path");
	JButton encryOk=new JButton();
	JButton decryOk=new JButton();
	JButton chooseEncryFile=new JButton();
	JButton chooseDecryFile=new JButton();
	GridBagLayout gbl=new GridBagLayout ();
	GridBagConstraints gbcs=new GridBagConstraints();
	JPanel p = new JPanel(new GridBagLayout());
	JPanel p2 = new JPanel(new GridBagLayout());
	String ciphermode="AES/CBC/PKCS5Padding";
	IvParameterSpec iv=new IvParameterSpec("abcdefghijklmnop".getBytes());
	InputStream FIN;
	File selectedFile;
	File selectedFile1;
	private Dimension d;
	private SecretKeySpec skey;
	public Encrypto()
	{
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width/3-(this.getSize().width)), (screenSize.height/4-(this.getHeight())));
		p2.setBackground(Color.orange);
		p.setBackground(Color.lightGray);
		this.setSize(600,500);
		this.setTitle("File");
		this.addWindowStateListener(this);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setGridBagConstraints(0,0,1,1,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST);
		Inputpassword1.setText("Input the key：");
		Inputpassword2.setText("Input the Key：");
		gbcs.insets=new Insets(20,10,10,10);
		p.add(Inputpassword1,gbcs);
		setGridBagConstraints(1,0,5,1,1,0,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST);
		p.add(encryPasswordInput,gbcs);
		setGridBagConstraints(0,1,1,1,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST);
		chooseEncryFile.setText("choose encryptofile");
		p.add(chooseEncryFile,gbcs);
		setGridBagConstraints(1,1,4,1,0,0,GridBagConstraints.NONE,GridBagConstraints.WEST);
		p.add(encryPathShow,gbcs);
		encryOk.setText("Encrypto");
		setGridBagConstraints(0,2,1,1,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST);
		p.add(encryOk,gbcs);
		setGridBagConstraints(1,2,4,1,0,0,GridBagConstraints.NONE,GridBagConstraints.WEST);
		p.add(userinfo1, gbcs);
		setGridBagConstraints(0,0,1,1,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST);
		p2.add(Inputpassword2,gbcs);
		setGridBagConstraints(1,0,5,1,1,0,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST);
		p2.add(decryPasswordInput,gbcs);
		setGridBagConstraints(0,1,1,1,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST);
		chooseDecryFile.setText("choose decryptofile");
		p2.add(chooseDecryFile,gbcs); 
		setGridBagConstraints(1,1,4,1,0,0,GridBagConstraints.NONE,GridBagConstraints.WEST);
		p2.add(decryPathShow,gbcs);
		decryOk.setText("Decrypto");
		setGridBagConstraints(0,2,1,1,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST);
		p2.add(decryOk,gbcs);
		setGridBagConstraints(1,2,4,1,0,0,GridBagConstraints.NONE,GridBagConstraints.WEST);
		p2.add(userinfo2, gbcs);
		this.getContentPane().setLayout(new GridLayout(0,1));
		add(p);
		add(p2);
		encryOk.addActionListener(this);
		decryOk.addActionListener(this);
		chooseDecryFile.addActionListener(this);
		chooseEncryFile.addActionListener(this);
		userinfo1.setForeground(Color.RED);
		userinfo2.setForeground(Color.RED);
	}
	public void setWindowSize(Dimension size)
	{
		d=size;
	}
	@Override
	public void windowStateChanged(WindowEvent e) 
	{
		if(e.getNewState()==Frame.NORMAL)
		{
			setWindowSize(this.getSize());
			System.out.println(d);
		}
		if(e.getNewState()==Frame.MAXIMIZED_BOTH)
		{
			setWindowSize(this.getSize());
			System.out.println(d);
		}
		
	}
	public void setGridBagConstraints (int gridx,int gridy,int gridwidth,int gridheigh,double weightx,double weighty,int fill,int anchor)
	{
		 gbcs.gridx=gridx;
		 gbcs.gridy=gridy;
		 gbcs.gridheight=gridheigh;
		 gbcs.gridwidth=gridwidth;
		 gbcs.fill=fill;
		 gbcs.weightx=weightx;
		 gbcs.weighty=weighty;
		 gbcs.anchor=anchor;
		//gridx, gridy 這個屬性是用來描述組件在佈局時應處於那個網格位置,即描述網格開始的位置
		//gridwidth,gridheigh這兩個屬性用來描述組件在佈局中的所佔的網格的個數
	}

	public void encryptofile(String key,File path) 
	{
		MessageDigest md=null;	
		try
		{
			FIN=new FileInputStream(path);//讀檔
			byte[] byteofkey=key.getBytes("UTF-8");
			md=MessageDigest.getInstance("MD5");
			byte[] realkey=md.digest(byteofkey);
			skey=new SecretKeySpec(realkey,"AES");
			byte [] ciphertext=new byte[1024];
			Cipher cipher=Cipher.getInstance(ciphermode);
			cipher.init(Cipher.ENCRYPT_MODE, skey,iv);
			FileOutputStream fos=new FileOutputStream(path.getAbsolutePath()+".encrypt");
			System.out.println(cipher.getAlgorithm());
			CipherInputStream cipherInputStream = new CipherInputStream(FIN, cipher);
			int read=0;
			while((read=cipherInputStream.read(ciphertext))>= 0)
			{
				fos.write(ciphertext,0,read);
				fos.flush();
			}
			
			cipherInputStream.close();
			fos.close();
			FIN.close();
			path.delete();
			
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(UnsupportedEncodingException e)
    	{
   		 System.out.println("\nError"+e.getMessage());
    	}
		catch(NoSuchAlgorithmException a)
		{
			System.out.println("\nError"+a.getMessage());
   	  	}
        catch(NoSuchPaddingException f)
   	    {
    	   System.out.println("\nError"+f.getMessage());
   	    }
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (InvalidKeyException e) 
		{
			e.printStackTrace();
		} 
		catch (InvalidAlgorithmParameterException e)
		{
			e.printStackTrace();
		}
	}
		
	
	public void decryptofile(String key,File path)
	{
		 	MessageDigest md=null;	
			try
			{
				System.out.println(path.getAbsolutePath());
				FIN=new FileInputStream(path.getAbsolutePath()+".encrypt");
				byte[] byteofkey=key.getBytes("UTF-8");
				md=MessageDigest.getInstance("MD5");
				byte[] realkey=md.digest(byteofkey);
				skey=new SecretKeySpec(realkey,"AES");
				byte [] ciphertext=new byte[1024];
				Cipher cipher=Cipher.getInstance(ciphermode);
				cipher.init(Cipher.DECRYPT_MODE, skey,iv);
				FileOutputStream fos=new FileOutputStream(path.getAbsolutePath());
				CipherOutputStream cipherOutputStream = new CipherOutputStream(fos, cipher);
				System.out.println(cipher.getAlgorithm());
				int read=0;
				while((read = FIN.read(ciphertext)) >= 0)
				{
					cipherOutputStream.write(ciphertext,0,read);
					fos.flush();
				}
				cipherOutputStream.close();
				fos.close();
				FIN.close();
				File f=new File(path.getAbsolutePath()+".encrypt");
				f.delete();
				
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch(UnsupportedEncodingException e)
	    	{
	   		 System.out.println("\nError"+e.getMessage());
	    	}
			catch(NoSuchAlgorithmException a)
			{
				System.out.println("\nError"+a.getMessage());
	   	  	}
	        catch(NoSuchPaddingException f)
	   	    {
	    	   System.out.println("\nError"+f.getMessage());
	   	    } 
			catch (IOException e)
			{
				e.printStackTrace();
			} 
			catch (InvalidKeyException e)
			{
				e.printStackTrace();
			} 
			catch (InvalidAlgorithmParameterException e)
			{
				e.printStackTrace();
			}
	}
	public void actionPerformed(ActionEvent e)
	{
			System.out.println(e.getActionCommand());
			if(e.getActionCommand().equals("choose encryptofile"))
			{
				JFileChooser fileChooser = new JFileChooser();
				int returnvalue=fileChooser.showOpenDialog(this);
				if(returnvalue==JFileChooser.APPROVE_OPTION)
				{
					selectedFile=fileChooser.getSelectedFile();
					encryPathShow.setText(selectedFile.getAbsolutePath());
				}
			}
			if(e.getActionCommand().equals("choose decryptofile"))
			{
				JFileChooser fileChooser = new JFileChooser();
				int returnvalue=fileChooser.showOpenDialog(this);
				if(returnvalue==JFileChooser.APPROVE_OPTION)
				{
					selectedFile1=fileChooser.getSelectedFile();
					decryPathShow.setText(selectedFile1.getAbsolutePath());
				}
			}
			if(e.getActionCommand().equals("Encrypto"))
			{
				System.out.println(encryPasswordInput.getText());
				if(encryPasswordInput.getText().equals(""))
				{
					userinfo1.setText("Key can't be empty");
				}
				else
				{
					if(!encryPathShow.getText().equals("path"))
					{
						userinfo1.setText("Encrypto now");
						encryptofile(encryPasswordInput.getText(),selectedFile) ;
						userinfo1.setText("Encrypto complete");
						encryPasswordInput.setText("");
					}
					else
					{
						userinfo1.setText("Please Choose File");
					}
				}
			}
			if(e.getActionCommand().equals("Decrypto"))
			{

				if(decryPasswordInput.getText().equals(""))
				{
					userinfo2.setText("Key can't be empty");
				}
				else
				{
					if(!decryPathShow.getText().equals("path"))
					{
						String temp=selectedFile1.getAbsolutePath();
						System.out.println(temp=temp.substring(0, temp.indexOf(".encrypt")));
						File f=new File(temp);
						userinfo2.setText("Decrypto now");
						decryptofile(decryPasswordInput.getText(),f);
						userinfo2.setText("Decrypto complete");
						decryPasswordInput.setText("");
					}
					else
					{
						userinfo2.setText("Please Choose File");
					}
				}
			}
			
	}

}
