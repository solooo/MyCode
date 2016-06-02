package solooo.mycode.resttemplate;

/**
 * @Description 订阅消息对象
 * @Copyright Copyright 2016 HtDataCloud
 * @Author 赵伟(zhaow@htdatacloud.com)
 * @Date 2016年5月31日
 */
public class TopicMessageObject {
	private String command;
	private String content;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
