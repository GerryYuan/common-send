package link.gerry.common.send.initialization;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class InitializationSendProperties implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(InitializationSendProperties.class);

	private static Properties props = new Properties();;

	@Override
	public void afterPropertiesSet() throws Exception {
		InputStreamReader in = null;
		try {
			String path = getPath() + "/conf/smsTemplet.properties";
			logger.info("加载属性文件短信模板到内存中:" + path);
			in = new FileReader(new File(path));
			props.load(in);

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static String getValue(String key) {
		return props.getProperty(key);
	}

	private static String getPath() {
		return new File("").getAbsolutePath();
	}

}
