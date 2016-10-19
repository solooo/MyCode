package solooo.mycode.ssh;

import com.jcraft.jsch.SftpProgressMonitor;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class FileProgressMonitor extends TimerTask implements SftpProgressMonitor {

	private long progressInterval = 5 * 1000; // 默认间隔时候为5秒
	private boolean isEnd = false; // 记录传输是否停止
	private long transfered; // 记录已传输的数据总大小
	private long fileSize; // 记录文件总大小
	private Timer timer; // 按时器对象
	private boolean isScheduled = false; // 记录是否已启动timer记时器

	public FileProgressMonitor(long fileSize) {
		this.fileSize = fileSize;
	}

	public void run() {
		if (!isEnd()) { // 断定传输是否已停止
			// System.out.println("Transfering is in progress.");

			long transfered = getTransfered();
			if (transfered != fileSize) { // 断定当前已传输数据大小是否便是文件总大小
				// System.out.println("Current transfered: " + transfered	+ " bytes");
				sendProgressMessage(transfered);
			} else {
				System.out.println("\tFile transfering is done.");
				setEnd(true); // 若是当前已传输数据大小便是文件总大小，申明已完成，设置end
			}

		} else {
			System.out.println("\tTransfering done. Cancel timer.");
			stop(); // 若是传输停止，停止timer记时器
			return;
		}
	}

	public void stop() {
		System.out.println("\tTry to stop progress monitor.");

		if (timer != null) {
			timer.cancel();
			timer.purge();
			timer = null;
			isScheduled = false;
		}

		System.out.println("\tProgress monitor stoped.");
	}

	public void start() {
		System.out.println("\tTry to start progress monitor.");
		if (timer == null) {
			timer = new Timer();
		}

		timer.schedule(this, 1000, progressInterval);
		isScheduled = true;
		System.out.println("\tProgress monitor started.");
	}

	private void sendProgressMessage(long transfered) {
		if (fileSize != 0) {
			double d = ((double) transfered * 100) / (double) fileSize;
			DecimalFormat df = new DecimalFormat("#.##");
			System.out.println("\tSending progress message: " + df.format(d) + "％" + "\t" + transfered + " byte");
		} else {
			System.out.println("\tSending progress message: " + transfered);
		}
	}

	public boolean count(long count) {
		if (isEnd())
			return false;
		if (!isScheduled) {
			start();
		}

		add(count);
		return true;
	}

	public void end() {
		setEnd(true);
		System.out.println("\ttransfering end.");
	}

	private synchronized void add(long count) {
		transfered = transfered + count;
	}

	private synchronized long getTransfered() {
		return transfered;
	}

	public synchronized void setTransfered(long transfered) {
		this.transfered = transfered;
	}

	private synchronized void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	private synchronized boolean isEnd() {
		return isEnd;
	}

	public void init(int op, String src, String dest, long max) {
		// Not used for putting InputStream
	}
}