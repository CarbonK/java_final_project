package torrent;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;

import com.turn.ttorrent.client.*;
import com.turn.ttorrent.client.peer.SharingPeer;

public class BitTorrent {
	
	public BitTorrent() {
		sharing = false;
		tmr = new Timer();
		dlbyte = new long[10];
		ulbyte = new long[10];
		for(int i = 0;i < 10;i++) {
			dlbyte[i] = 0;
			ulbyte[i] = 0;
		}
	}
	
	/**
	 * 
	 * @param path torrent file path
	 */
	public void setTorrent(String path) {
		torrent = path;
	}
	
	/**
	 * 
	 * @param path destination folder path
	 */
	public void setDestination(String path) {
		dest = path;
	}
	
	/**
	 * 
	 * @param share quit after download finished when false, 
	 * continue sharing when true.
	 */
	public void setSharing(boolean share) {
		sharing = share;
	}
	
	/**
	 * 
	 * @return true when OK, false when torrent or directory not exist.
	 */
	public boolean start() {
		File tor = new File(torrent);
		File dst = new File(dest);
		
		if(!tor.exists()) return false;
		if(!dst.exists()) dst.mkdirs();
		
		try {
			client = new Client(
				InetAddress.getLocalHost(),
				SharedTorrent.fromFile(tor, dst)
			);
			if(sharing) client.share();
			else client.download();
			tmr.schedule(new TimerTask() {
				@Override
				public void run() {
					for(int i = 0;i < 9;i++) {
						dlbyte[i] = dlbyte[i + 1];
						ulbyte[i] = ulbyte[i + 1];
					}
					dlbyte[9] = client.getTorrent().getDownloaded();
					dlrate = (dlbyte[9] - dlbyte[0]) / 10;
					
					ulbyte[9] = client.getTorrent().getUploaded();
					ulrate = (ulbyte[9] - ulbyte[0]) / 10;
				}
			}, 1000, 1000);
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * 
	 * @brief wait for torrent complete (blocking)
	 */
	public void waitForCompletion() {
		client.waitForCompletion();
	}
	
	public void pause() {
		client.stop();
	}
	
	public void resume() {
		if(sharing) client.share();
		else client.download();
	}
	
	public float getProgress() {
		return client.getTorrent().getCompletion();
	}
	
	public String getStatus() {
		return client.getState().toString();
	}
	
	public Set<SharingPeer> getPeers() {
		return client.getPeers();
	}
	
	public String getFileName() {
		return client.getTorrent().getName();
	}
	
	public long getFileSize() {
		return client.getTorrent().getSize();
	}
	
	public long getDownloadedSize() {
		return (long)(getFileSize() * (getProgress() / 100));
	}
	
	public long getDownloadRate() {
		return dlrate;
	}
	
	public long getUploadRate() {
		return ulrate;
	}
	
	public int getPeerNumber() {
		return client.getPeers().size();
	}
	
	public int getActivePeerNumber() {
		Set<SharingPeer> sp = client.getPeers();
		int active = 0;
		for(SharingPeer p : sp) {
			if(p.isDownloading()) active++;
		}
		
		return active;
	}
	
	public static String byteConvert(long byte_size) {
		String res = "B";
		if(byte_size == 0) {
			return "0.0 B";
		}
		float num = byte_size;
		if(num > 1024) {
			num /= 1024;
			res = "KB";
		}
		if(num > 1024) {
			num /= 1024;
			res = "MB";
		}
		if(num > 1024) {
			num /= 1024;
			res = "GB";
		}
		
		DecimalFormat fmt = new DecimalFormat(".#");
		
		return fmt.format(num) + " " + res;
	}
	
	public void delete(boolean deleteTorrent) {
		client.stop();
		
		File tor = new File(torrent);
		File dst = new File(dest);
		
		File down = new File(dst.getAbsolutePath() + "/" + client.getTorrent().getName());
		File temp = new File(dst.getAbsolutePath() + "/" + client.getTorrent().getName() + ".part");
		
		System.out.println(down.delete());
		System.out.println(temp.delete());
		
		if(deleteTorrent) {
			System.out.println(tor.delete());
		}
	}
	
	private Client client;
	
	private String torrent;
	
	private String dest;
	
	private Timer tmr;
	
	private long[] dlbyte;
	
	private long dlrate;
	
	private long[] ulbyte;
	
	private long ulrate;
	
	private boolean sharing;
}
