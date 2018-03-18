package com.helon.mailconsumer;

import com.github.pagehelper.PageHelper;
import com.helon.mail.entity.MailSend;
import com.helon.mail.entity.MstDict;
import com.helon.mail.mapper.MstDictMapper;
import com.helon.mail.service.MailSendService;
import com.helon.mail.service.MstDictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailConsumerApplication.class)
public class MailConsumerApplicationTests {
	@Resource(name="masterDataSource")
	private DataSource masterDataSource;
	@Resource(name="slaveDataSource")
	private DataSource slaveDataSource;
	@Resource
	private MstDictMapper mstDictMapper;
	@Autowired
	private MstDictService mstDictService;
	@Autowired
	private MailSendService mailSendService;

	@Test
	public void contextLoads() throws Exception{
		Connection c1 = masterDataSource.getConnection("root", "root");
		System.out.println(c1.getMetaData().getURL());
		Connection c2 = slaveDataSource.getConnection("root", "root");
		System.out.println(c2.getMetaData().getURL());
	}

	@Test
	public void test1() throws Exception {
		PageHelper.startPage(1, 2, false);
		List<MstDict> list = mstDictMapper.selectAll();
		for (MstDict md : list) {
			System.out.println(md.getName());
		}
	}


	@Test
	public void test2() throws Exception {
		List<MstDict> list = mstDictService.findByStatus(1);
		for (MstDict md : list) {
			System.out.println(md.getName());
		}
	}

	@Test
	public void testMail() throws Exception{
		MailSend mailSend = new MailSend();
		mailSend.setSendContent("Hello!!!");
		mailSend.setSendTo("381614569@qq.com");
		mailSend.setSendUserId("zhangsan");
        mailSend.setSendId("fc3dae8c-29ff-11e8-9d70-60eb69557725");
        mailSend.setSendCount(0L);
        mailSend.setVersion(0L);
        mailSend.setSendPriority(2L);
        mailSend.setUpdateTime(new Date());
		mailSendService.sendMessageOrder(mailSend);
	}

}
