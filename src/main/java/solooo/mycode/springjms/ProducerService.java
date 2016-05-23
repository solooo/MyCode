package solooo.mycode.springjms;

import javax.jms.Destination;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/18
 * History:
 * his1:
 */
public interface ProducerService {
    public void sendMessage(Destination destination, final String message);

}
