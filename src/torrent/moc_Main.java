package torrent;

import java.util.Scanner;

public class moc_Main {

	public static void main(String[] args) {
		BitTorrent BT = new BitTorrent();

		BT.setDestination("/home/hmkrl/ubuntu_img");
		BT.setTorrent("/home/hmkrl/ubuntu_img/ubuntu-17.04-desktop-amd64.iso.torrent");
		
		BT.start();
		
		Scanner stdin = new Scanner(System.in);

		while(true) {
			String in = stdin.nextLine();
//			if(in.equals("p")) System.out.println(BT.getProgress());
//			if(in.equals("s")) System.out.println(BT.getStatus());
//			if(in.equals("n")) System.out.println(BT.getFileName());			
//			if(in.equals("size")) System.out.println(BT.getFileSize());
//			if(in.equals("d")) System.out.println(BT.getDownloadedSize());
//			if(in.equals("r")) System.out.println(BT.getPeerNumber());
//			if(in.equals("a")) System.out.println(BT.getActivePeerNumber());
			BT.delete(true);
		}
	}

}
